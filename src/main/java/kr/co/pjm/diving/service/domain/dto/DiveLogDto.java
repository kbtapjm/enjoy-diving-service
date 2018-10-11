package kr.co.pjm.diving.service.domain.dto;

import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.annotations.ApiModelProperty;
import kr.co.pjm.diving.common.domain.enumeration.DiveCurrentEnum;
import kr.co.pjm.diving.common.domain.enumeration.DivePlanExrPtnEnum;
import kr.co.pjm.diving.common.domain.enumeration.DivePlanToolEnum;
import kr.co.pjm.diving.common.domain.enumeration.DiveTypeEnum;
import kr.co.pjm.diving.common.domain.enumeration.DiveWaterEnum;
import kr.co.pjm.diving.common.domain.enumeration.DiveWaveEnum;
import kr.co.pjm.diving.service.common.domain.dto.CommonDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiveLogDto extends CommonDto {
  
  /* 번호 */
  @NotEmpty(message = "{message.diveLog.diveNo.notEmpty}")
  @ApiModelProperty(notes = "다이브 로그 번호", example = "1", required = true, position = 0)
  private String diveNo;
  
  /* 다이브 날짜 */
  @Past
  @DateTimeFormat(pattern="yyyy-MM-dd")
  private Date diveDate;
  
  /* 다이브 장소 */
  @NotEmpty(message = "{message.diveLog.divePlace.notEmpty}")
  private String divePlace;
  
  /* 다이브 포인트 */
  @NotEmpty(message = "{message.diveLog.divePoint.notEmpty}")
  private String divePoint;
  
  /* 다이브 입수 시간(시) */
  @NotEmpty(message = "{message.diveLog.diveInHour.notEmpty}")
  private String diveInHour;
  
  /* 다이브 입수 시간(분) */
  @NotEmpty(message = "{message.diveLog.diveInMinute.notEmpty}")
  private String diveInMinute;
  
  /* 다이브 출수 시간(시) */
  @NotEmpty(message = "{message.diveLog.diveOutHour.notEmpty}")
  private String diveOutHour;
  
  /* 다이브 출수 시간(분) */
  @NotEmpty(message = "{message.diveLog.diveOutMinute.notEmpty}")
  private String diveOutMinute;
  
  /* 탱크 압력 시작(bar) */
  @NotEmpty(message = "{message.diveLog.diveTankStart.notEmpty}")
  private String diveTankStart;
  
  /* 탱크 압력 종료(bar) */
  @NotEmpty(message = "{message.diveLog.diveTankEnd.notEmpty}")
  private String diveTankEnd;
  
  /* 수면 휴식 시간(시) */
  private String groundRestHour;
  
  /* 수면 휴식 시간(분) */
  private String groundRestMinute;
  
  /* 최대 수심 */
  @NotEmpty(message = "{message.diveLog.maxDepth.notEmpty}")
  private String maxDepth;
  
  /* 평균 수심 */
  @NotEmpty(message = "{message.diveLog.avgDepth.notEmpty}")
  private String avgDepth;
  
  /* 다이브 시간(min) */
  @NotEmpty(message = "{message.diveLog.diveTime.notEmpty}")
  private String diveTime;
  
  /* 안전정지 시간 */
  @NotEmpty(message = "{message.diveLog.diveSafetyTime.notEmpty}")
  private String diveSafetyTime;
  
  /* 다이빙 계획 도구 */
  @NotNull(message = "{message.diveLog.divePlanTool.notEmpty}")
  @Enumerated(EnumType.STRING)
  private DivePlanToolEnum divePlanTool;
  
  /* 웨이트(Kg) */
  private String divePlanWeight;
  
  /* Eanx(나이트록스) */
  private String divePlanEanx;
  
  /* 노출 보호 */
  @Enumerated(EnumType.STRING)
  @NotNull(message = "{message.diveLog.divePlanExrPtn.notEmpty}")
  private DivePlanExrPtnEnum divePlanExrPtn;
  
  /* 다이브 후드 여부 */
  private String divePlanHoodYn;
  
  /* 다이브 장갑 여부 */
  private String divePlanGlovesYn;
  
  /* 다이브 부츠 여부 */
  private String divePlanBootsYn;
  
  /* 다이브 라이트 여부 */
  private String divePlanLightYn;
  
  /* 다이브 SMB 여부 */
  private String divePlanSmbYn;
  
  /* 다이브 나이프 여부 */
  private String divePlanKnifeYn;
  
  /* 다이브 카메라 여부 */
  private String divePlanCameraYn;
  
  /* 시야 */
  @NotEmpty(message = "{message.diveLog.visibility.notEmpty}")
  private String visibility;
  
  /* 수온 */
  @NotEmpty(message = "{message.diveLog.temperature.notEmpty}")
  private String temperature;
  
  /* 다이브 유형 */
  @Enumerated(EnumType.STRING)
  private DiveTypeEnum diveType;
  
  /* 다이브 워터 */
  @Enumerated(EnumType.STRING)
  private DiveWaterEnum diveWater;
  
  /* 다이브 파도 */
  @Enumerated(EnumType.STRING)
  private DiveWaveEnum diveWave;
  
  /* 다이브 조류  */
  @Enumerated(EnumType.STRING)
  private DiveCurrentEnum diveCurrent;
  
  /* 다이브 활동 */
  private String diveActivity;
  
  /* 다이브 노트 */
  private String diveNote;

}
