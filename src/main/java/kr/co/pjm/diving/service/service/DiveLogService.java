package kr.co.pjm.diving.service.service;

import kr.co.pjm.diving.common.domain.dto.ResourcesDto;
import kr.co.pjm.diving.common.domain.entity.DiveLog;
import kr.co.pjm.diving.service.domain.dto.DiveLogDto;

public interface DiveLogService {

  DiveLog set(DiveLogDto diveLogDto);
  
  DiveLog getById(Long id);
  
  ResourcesDto getAll(); 
  
  void update(Long id, DiveLogDto diveLogDto);
  
  void delete(Long id);
}
