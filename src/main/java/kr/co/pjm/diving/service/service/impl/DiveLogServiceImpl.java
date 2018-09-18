package kr.co.pjm.diving.service.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.pjm.diving.common.domain.dto.ResourcesDto;
import kr.co.pjm.diving.common.domain.entity.DiveLog;
import kr.co.pjm.diving.common.repository.DiveLogRepository;
import kr.co.pjm.diving.service.domain.dto.DiveLogDto;
import kr.co.pjm.diving.service.service.DiveLogService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DiveLogServiceImpl implements DiveLogService {
  
  @Autowired
  private DiveLogRepository diveLogRepository;

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
    
    return diveLogRepository.save(diveLog);
  }

  @Override
  public DiveLog getById(Long id) {
    if (log.isInfoEnabled()) {
      log.info("id : {}", id);
    }
    
    return diveLogRepository.findOne(id);
  }

  @Override
  public ResourcesDto getAll() {
    List<DiveLog> list = diveLogRepository.findAll();
    
    // TODO querDsl paging  
    
    return new ResourcesDto(list);
  }

  @Override
  public void update(Long id, DiveLogDto diveLogDto) {
    DiveLog diveLog = new DiveLog();
    
    diveLogRepository.saveAndFlush(diveLog);
  }

  @Override
  public void delete(Long id) {
    diveLogRepository.delete(id);
  }
  
  

}
