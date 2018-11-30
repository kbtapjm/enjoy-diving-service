package kr.co.pjm.diving.service.service;

import kr.co.pjm.diving.common.domain.dto.PagingDto;
import kr.co.pjm.diving.common.domain.dto.ResourcesDto;
import kr.co.pjm.diving.common.domain.dto.SearchDto;
import kr.co.pjm.diving.common.domain.entity.DiveLog;
import kr.co.pjm.diving.service.domain.dto.DiveLogDto;

public interface DiveLogService {
  
  ResourcesDto getDiveLogs(SearchDto searchDto, PagingDto pagingDto);

  DiveLog set(DiveLogDto diveLogDto);
  
  DiveLog getById(Long id);
  
  void update(Long id, DiveLogDto diveLogDto);
  
  void delete(Long id);
  
  void deleteByUser(String userId);
}
