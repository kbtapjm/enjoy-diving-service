package kr.co.pjm.diving.service.service.impl;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

import kr.co.pjm.diving.common.domain.dto.PagingDto;
import kr.co.pjm.diving.common.domain.dto.ResourcesDto;
import kr.co.pjm.diving.common.domain.dto.SearchDto;
import kr.co.pjm.diving.common.domain.dto.SearchDto.SearchQ;
import kr.co.pjm.diving.common.domain.entity.DiveLog;
import kr.co.pjm.diving.common.domain.entity.QDiveLog;
import kr.co.pjm.diving.common.domain.enumeration.DiveTypeEnum;
import kr.co.pjm.diving.common.exception.ResourceNotFoundException;
import kr.co.pjm.diving.common.repository.DiveLogRepository;
import kr.co.pjm.diving.service.domain.dto.DiveLogDto;
import kr.co.pjm.diving.service.service.DiveLogService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service
public class DiveLogServiceImpl implements DiveLogService {
  
  private DiveLogRepository diveLogRepository;
  
  @Override
  public ResourcesDto getDiveLogs(SearchDto searchDto, PagingDto pagingDto) {
    /* search */
    Predicate predicate = this.getPredicate(searchDto);
       
    /* page */
    PageRequest pageRequest = new PageRequest(pagingDto.getOffset(), pagingDto.getLimit(), searchDto.getPageSort());
    
    Page<DiveLog> page = diveLogRepository.findAll(predicate, pageRequest);
    
    if (log.isInfoEnabled()) {
      log.info("getNumber : {}", page.getNumber());
      log.info("getNumberOfElements : {}", page.getNumberOfElements());
      log.info("getSize : {}", page.getSize());
      log.info("getSort : {}", page.getSort());
      log.info("getTotalElements : {}", page.getTotalElements());
      log.info("getTotalPages : {}", page.getTotalPages());  
    }
    
    ResourcesDto resourcesDto = new ResourcesDto(page.getContent(), searchDto, pagingDto);
    resourcesDto.putContent("total", diveLogRepository.count(predicate));
    
    return resourcesDto;
  }
  
  public Predicate getPredicate(SearchDto searchDto) {
    BooleanBuilder booleanBuilder = new BooleanBuilder();
    QDiveLog qDiveLog = QDiveLog.diveLog;
    for (SearchQ searchQ : searchDto.getQList()) {
      switch (searchQ.getSearchColumn()) {
      case "diveDate":
        //booleanBuilder.and(qDiveLog.diveDate.eq(DateUtil.getInstance().toDate(searchQ.getSearchValue(), DateUtil.FORMAT_YYYY_MM_DD)));
        break;
      case "divePlace":
        booleanBuilder.and(qDiveLog.divePlace.like("%".concat(searchQ.getSearchValue()).concat("%")));
        break;
      case "diveType":
        booleanBuilder.and(qDiveLog.diveType.eq(DiveTypeEnum.findByValue(searchQ.getSearchValue())));
        break;
      case "regId":
        booleanBuilder.and(qDiveLog.regId.eq(searchQ.getSearchValue()));
        break;
      }
    }
    
    return booleanBuilder;
  }

  @Transactional
  @Override
  public DiveLog set(DiveLogDto diveLogDto) {
    diveLogDto.setRegDate(LocalDateTime.now());
    
    return diveLogRepository.save(diveLogDto.toEntity());
  }

  @Override
  public DiveLog getById(Long id) {
    DiveLog diveLog = diveLogRepository.findOne(id);
    if (diveLog == null) {
      throw new ResourceNotFoundException(String.valueOf(id));
    }
    
    return diveLogRepository.findOne(id);
  }
  
  @Transactional
  @Override
  public void update(Long id, DiveLogDto diveLogDto) {
    diveLogDto.setId(id);
    diveLogDto.setUpdateDate(LocalDateTime.now());
    
    diveLogRepository.save(diveLogDto.toEntity());
  }

  @Transactional
  @Override
  public void delete(Long id) {
    diveLogRepository.delete(id);
  }

  @Transactional
  @Override
  public void deleteByUser(String userId) {
    diveLogRepository.deleteByUser(userId);
  }

}
