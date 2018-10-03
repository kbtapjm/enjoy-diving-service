package kr.co.pjm.diving.service.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.pjm.diving.common.domain.dto.ResourcesDto;
import kr.co.pjm.diving.common.domain.entity.DiveLog;
import kr.co.pjm.diving.common.exception.ResourceNotFoundException;
import kr.co.pjm.diving.common.repository.DiveLogRepository;
import kr.co.pjm.diving.service.common.domain.dto.PagingDto;
import kr.co.pjm.diving.service.common.domain.dto.SearchDto;
import kr.co.pjm.diving.service.domain.dto.DiveLogDto;
import kr.co.pjm.diving.service.service.DiveLogService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DiveLogServiceImpl implements DiveLogService {
  
  @Autowired
  private DiveLogRepository diveLogRepository;
  
  @Autowired 
  MessageSourceAccessor msa;

  @Transactional
  @Override
  public DiveLog set(DiveLogDto diveLogDto) {
    DiveLog diveLog = new DiveLog();
    diveLog.setDiveNo(Long.valueOf(diveLogDto.getDiveNo()));
    diveLog.setDiveDate(diveLogDto.getDiveDate());
    diveLog.setDivePlace(diveLogDto.getDivePlace());
    diveLog.setDivePoint(diveLogDto.getDivePoint());
    diveLog.setDiveInHour(diveLogDto.getDiveInHour());
    diveLog.setDiveInMinute(diveLogDto.getDiveInMinute());
    diveLog.setDiveOutHour(diveLogDto.getDiveOutHour());
    diveLog.setDiveOutMinute(diveLogDto.getDiveOutMinute());
    diveLog.setDiveTankStart(diveLogDto.getDiveTankEnd());
    diveLog.setDiveTankEnd(diveLogDto.getDiveTankEnd());
    diveLog.setGroundRestHour(diveLogDto.getGroundRestMinute());
    diveLog.setGroundRestMinute(diveLogDto.getGroundRestMinute());
    diveLog.setMaxDepth(diveLogDto.getMaxDepth());
    diveLog.setAvgDepth(diveLogDto.getAvgDepth());
    diveLog.setDiveTime(diveLogDto.getDiveTime());
    diveLog.setDiveSafetyTime(diveLogDto.getDiveSafetyTime());
    diveLog.setDivePlanTool(diveLogDto.getDivePlanTool());
    diveLog.setDivePlanWeight(diveLogDto.getDivePlanWeight());
    diveLog.setDivePlanEanx(diveLogDto.getDivePlanEanx());
    diveLog.setDivePlanExrPtn(diveLogDto.getDivePlanExrPtn());
    diveLog.setDivePlanHoodYn(diveLogDto.getDivePlanHoodYn());
    diveLog.setDivePlanGlovesYn(diveLogDto.getDivePlanGlovesYn());
    diveLog.setDivePlanBootsYn(diveLogDto.getDivePlanBootsYn());
    diveLog.setDivePlanLightYn(diveLogDto.getDivePlanLightYn());
    diveLog.setDivePlanSmbYn(diveLogDto.getDivePlanSmbYn());
    diveLog.setDivePlanKnifeYn(diveLogDto.getDivePlanKnifeYn());
    diveLog.setDivePlanCameraYn(diveLogDto.getDivePlanCameraYn());
    diveLog.setVisibility(diveLogDto.getVisibility());
    diveLog.setTemperature(diveLogDto.getTemperature());
    diveLog.setDiveType(diveLogDto.getDiveType());
    diveLog.setDiveWater(diveLogDto.getDiveWater());
    diveLog.setDiveWave(diveLogDto.getDiveWave());
    diveLog.setDiveCurrent(diveLogDto.getDiveCurrent());
    diveLog.setDiveActivity(diveLogDto.getDiveActivity());
    diveLog.setDiveNote(diveLogDto.getDiveNote());
    diveLog.setRegDate(new Date());
    diveLog.setRegId(diveLogDto.getRegId());
    
    return diveLogRepository.save(diveLog);
  }

  @Override
  public DiveLog getById(Long id) {
    if (log.isInfoEnabled()) {
      log.info("id : {}", id);
    }
    
    DiveLog diveLog = diveLogRepository.findOne(id);
    if (diveLog == null) {
      throw new ResourceNotFoundException(msa.getMessage("message.common.resource.not.found", new String[]{ String.valueOf(id) }));
    }
    
    return diveLogRepository.findOne(id);
  }

  @Override
  public ResourcesDto getDiveLogs(SearchDto searchDto, PagingDto pagingDto) {
    List<DiveLog> list = diveLogRepository.findAll();
    
    /* search */
    
    /* Sort */
    if (!StringUtils.isEmpty(searchDto.getSorts())) {
      String[] sortsArr = searchDto.getSorts().split(",");
      
      int idx = 0;
      Order[] order = new Order[sortsArr.length];
      for (String s : sortsArr) {
        if (s == null) continue;
        
        String sortType = s.substring(0, 1);
        String sortColumn = s.substring(1, s.length());
        
        order[idx] = new Order(sortType.equals("+") ? Direction.ASC : Direction.DESC, sortColumn);
        
        idx++;
      }
      
      Sort sort = new Sort(order);  
    }
    
    /* page */
    
    return new ResourcesDto(list);
  }

  @Transactional
  @Override
  public void update(Long id, DiveLogDto diveLogDto) {
    DiveLog diveLog = new DiveLog();
    diveLog.setId(id);
    diveLog.setDiveNo(Long.valueOf(diveLogDto.getDiveNo()));
    diveLog.setDiveDate(diveLogDto.getDiveDate());
    diveLog.setDivePlace(diveLogDto.getDivePlace());
    diveLog.setDivePoint(diveLogDto.getDivePoint());
    diveLog.setDiveInHour(diveLogDto.getDiveInHour());
    diveLog.setDiveInMinute(diveLogDto.getDiveInMinute());
    diveLog.setDiveOutHour(diveLogDto.getDiveOutHour());
    diveLog.setDiveOutMinute(diveLogDto.getDiveOutMinute());
    diveLog.setDiveTankStart(diveLogDto.getDiveTankEnd());
    diveLog.setDiveTankEnd(diveLogDto.getDiveTankEnd());
    diveLog.setGroundRestHour(diveLogDto.getGroundRestMinute());
    diveLog.setGroundRestMinute(diveLogDto.getGroundRestMinute());
    diveLog.setMaxDepth(diveLogDto.getMaxDepth());
    diveLog.setAvgDepth(diveLogDto.getAvgDepth());
    diveLog.setDiveTime(diveLogDto.getDiveTime());
    diveLog.setDiveSafetyTime(diveLogDto.getDiveSafetyTime());
    diveLog.setDivePlanTool(diveLogDto.getDivePlanTool());
    diveLog.setDivePlanWeight(diveLogDto.getDivePlanWeight());
    diveLog.setDivePlanEanx(diveLogDto.getDivePlanEanx());
    diveLog.setDivePlanExrPtn(diveLogDto.getDivePlanExrPtn());
    diveLog.setDivePlanHoodYn(diveLogDto.getDivePlanHoodYn());
    diveLog.setDivePlanGlovesYn(diveLogDto.getDivePlanGlovesYn());
    diveLog.setDivePlanBootsYn(diveLogDto.getDivePlanBootsYn());
    diveLog.setDivePlanLightYn(diveLogDto.getDivePlanLightYn());
    diveLog.setDivePlanSmbYn(diveLogDto.getDivePlanSmbYn());
    diveLog.setDivePlanKnifeYn(diveLogDto.getDivePlanKnifeYn());
    diveLog.setDivePlanCameraYn(diveLogDto.getDivePlanCameraYn());
    diveLog.setVisibility(diveLogDto.getVisibility());
    diveLog.setTemperature(diveLogDto.getTemperature());
    diveLog.setDiveType(diveLogDto.getDiveType());
    diveLog.setDiveWater(diveLogDto.getDiveWater());
    diveLog.setDiveWave(diveLogDto.getDiveWave());
    diveLog.setDiveCurrent(diveLogDto.getDiveCurrent());
    diveLog.setDiveActivity(diveLogDto.getDiveActivity());
    diveLog.setDiveNote(diveLogDto.getDiveNote());
    diveLog.setUpdateDate(new Date());
    diveLog.setUpdateId(diveLogDto.getUpdateId());
    
    diveLogRepository.save(diveLog);
  }

  @Transactional
  @Override
  public void delete(Long id) {
    diveLogRepository.delete(id);
  }

}
