package kr.co.pjm.diving.service.domain.dto;

import java.time.LocalDate;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import kr.co.pjm.diving.common.domain.entity.DiveLog;
import kr.co.pjm.diving.common.domain.enumeration.DiveCurrentEnum;
import kr.co.pjm.diving.common.domain.enumeration.DivePlanExrPtnEnum;
import kr.co.pjm.diving.common.domain.enumeration.DivePlanToolEnum;
import kr.co.pjm.diving.common.domain.enumeration.DiveTypeEnum;
import kr.co.pjm.diving.common.domain.enumeration.DiveWaterEnum;
import kr.co.pjm.diving.common.domain.enumeration.DiveWaveEnum;
import kr.co.pjm.diving.common.domain.enumeration.YnEnum;
import kr.co.pjm.diving.service.common.domain.dto.CommonDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@ApiModel(description = "다이브 로그 DTO")
public class DiveLogDto extends CommonDto {
  
  private Long id;
  
  @ApiModelProperty(notes = "다이브 로그 번호", example = "1", required = true, position = 0)
  @NotEmpty(message = "{message.diveLog.diveNo.notEmpty}")
  private String diveNo;
  
  @ApiModelProperty(notes = "다이브 날짜", example = "yyyy-MM-dd", required = true, position = 1)
  @NotNull(message = "{message.diveLog.diveDate.notEmpty}")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate diveDate;
  
  @ApiModelProperty(notes = "다이브 장소", example = "Raja Ampat", required = true, position = 2)
  @NotEmpty(message = "{message.diveLog.divePlace.notEmpty}")
  private String divePlace;
  
  @ApiModelProperty(notes = "다이브 포인트", example = "Melisa's Garden", required = true, position = 3)
  @NotEmpty(message = "{message.diveLog.divePoint.notEmpty}")
  private String divePoint;
  
  @ApiModelProperty(notes = "다이브 입수 시간(시)", example = "09", required = true, position = 4)
  @NotEmpty(message = "{message.diveLog.diveInHour.notEmpty}")
  private String diveInHour;
  
  @ApiModelProperty(notes = "다이브 입수 시간(분)", example = "10", required = true, position = 5)
  @NotEmpty(message = "{message.diveLog.diveInMinute.notEmpty}")
  private String diveInMinute;
  
  @ApiModelProperty(notes = "다이브 출수 시간(시)", example = "09", required = true, position = 6)
  @NotEmpty(message = "{message.diveLog.diveOutHour.notEmpty}")
  private String diveOutHour;
  
  @ApiModelProperty(notes = "다이브 출수 시간(분)", example = "55", required = true, position = 7)
  @NotEmpty(message = "{message.diveLog.diveOutMinute.notEmpty}")
  private String diveOutMinute;
  
  @ApiModelProperty(notes = "탱크 압력 시작(bar)", example = "200", required = true, position = 8)
  @NotEmpty(message = "{message.diveLog.diveTankStart.notEmpty}")
  private String diveTankStart;
  
  @ApiModelProperty(notes = "탱크 압력 종료(bar)", example = "40", required = true, position = 9)
  @NotEmpty(message = "{message.diveLog.diveTankEnd.notEmpty}")
  private String diveTankEnd;
  
  @ApiModelProperty(notes = "수면 휴식 시간(시)", example = "01", required = false, position = 10)
  private String groundRestHour;
  
  @ApiModelProperty(notes = "수면 휴식 시간(분)", example = "10", required = false, position = 11)
  private String groundRestMinute;
  
  @ApiModelProperty(notes = "최대 수심", example = "29.5", required = true, position = 12)
  @NotEmpty(message = "{message.diveLog.maxDepth.notEmpty}")
  private String maxDepth;
  
  @ApiModelProperty(notes = "평균 수심", example = "16.8", required = true, position = 13)
  @NotEmpty(message = "{message.diveLog.avgDepth.notEmpty}")
  private String avgDepth;
  
  @ApiModelProperty(notes = "다이브 시간(min)", example = "45", required = true, position = 14)
  @NotEmpty(message = "{message.diveLog.diveTime.notEmpty}")
  private String diveTime;
  
  @ApiModelProperty(notes = "안전정지 시간", example = "3", required = true, position = 15)
  @NotEmpty(message = "{message.diveLog.diveSafetyTime.notEmpty}")
  private String diveSafetyTime;
  
  @ApiModelProperty(notes = "다이빙 계획 도구", required = true, position = 16)
  @NotNull(message = "{message.diveLog.divePlanTool.notEmpty}")
  @Enumerated(EnumType.STRING)
  private DivePlanToolEnum divePlanTool;
  
  @ApiModelProperty(notes = "웨이트(Kg)", example = "4", required = false, position = 17)
  private String divePlanWeight;
  
  @ApiModelProperty(notes = "Eanx(나이트록스)", example = "21", required = false, position = 18)
  private String divePlanEanx;
  
  @ApiModelProperty(notes = "노출 보호", required = true, position = 19)
  @Enumerated(EnumType.STRING)
  @NotNull(message = "{message.diveLog.divePlanExrPtn.notEmpty}")
  private DivePlanExrPtnEnum divePlanExrPtn;
  
  @ApiModelProperty(notes = "다이브 후드 여부", required = false, position = 20)
  @Enumerated(EnumType.STRING)
  private YnEnum divePlanHoodYn;
  
  @ApiModelProperty(notes = "다이브 장갑 여부", required = false, position = 21)
  @Enumerated(EnumType.STRING)
  private YnEnum divePlanGlovesYn;
  
  @ApiModelProperty(notes = "다이브 부츠 여부", required = false, position = 22)
  @Enumerated(EnumType.STRING)
  private YnEnum divePlanBootsYn;
  
  @ApiModelProperty(notes = "다이브 라이트 여부", required = false, position = 23)
  @Enumerated(EnumType.STRING)
  private YnEnum divePlanLightYn;
  
  @ApiModelProperty(notes = "다이브 SMB 여부", required = false, position = 24)
  @Enumerated(EnumType.STRING)
  private YnEnum divePlanSmbYn;
  
  @ApiModelProperty(notes = "다이브 나이프 여부", required = false, position = 25)
  @Enumerated(EnumType.STRING)
  private YnEnum divePlanKnifeYn;
  
  @ApiModelProperty(notes = "다이브 카메라 여부", required = false, position = 26)
  @Enumerated(EnumType.STRING)
  private YnEnum divePlanCameraYn;
  
  @ApiModelProperty(notes = "시야", example = "17.5", required = true, position = 27)
  @NotEmpty(message = "{message.diveLog.visibility.notEmpty}")
  private String visibility;
  
  @ApiModelProperty(notes = "수온", example = "29.5", required = true, position = 28)
  @NotEmpty(message = "{message.diveLog.temperature.notEmpty}")
  private String temperature;
  
  @ApiModelProperty(notes = "다이브 유형", required = false, position = 29)
  @Enumerated(EnumType.STRING)
  private DiveTypeEnum diveType;
  
  @ApiModelProperty(notes = "다이브 워터", required = false, position = 30)
  @Enumerated(EnumType.STRING)
  private DiveWaterEnum diveWater;
  
  @ApiModelProperty(notes = "다이브 파도", required = false, position = 31)
  @Enumerated(EnumType.STRING)
  private DiveWaveEnum diveWave;
  
  @ApiModelProperty(notes = "다이브 조류", required = false, position = 32)
  @Enumerated(EnumType.STRING)
  private DiveCurrentEnum diveCurrent;
  
  @ApiModelProperty(notes = "다이브 활동", example = "펀다이빙", required = false, position = 33)
  private String diveActivity;
  
  @ApiModelProperty(notes = "다이브 노트", example = "라자암팟 최고의 포인트", required = false, position = 34)
  private String diveNote;
  
  public DiveLog toEntity() {
    return DiveLog.builder()
        .id(id)
        .diveNo(Long.valueOf(diveNo))
        .diveDate(diveDate)
        .divePlace(divePlace)
        .divePoint(divePoint)
        .diveInHour(diveInHour)
        .diveInMinute(diveInMinute)
        .diveOutHour(diveOutHour)
        .diveOutMinute(diveOutMinute)
        .diveTankStart(diveTankStart)
        .diveTankEnd(diveTankEnd)
        .groundRestHour(groundRestHour)
        .groundRestMinute(groundRestMinute)
        .maxDepth(maxDepth)
        .avgDepth(avgDepth)
        .diveTime(diveTime)
        .diveSafetyTime(diveSafetyTime)
        .divePlanTool(divePlanTool)
        .divePlanWeight(divePlanWeight)
        .divePlanEanx(divePlanEanx)
        .divePlanExrPtn(divePlanExrPtn)
        .divePlanHoodYn(divePlanHoodYn)
        .divePlanGlovesYn(divePlanGlovesYn)
        .divePlanBootsYn(divePlanBootsYn)
        .divePlanLightYn(divePlanLightYn)
        .divePlanSmbYn(divePlanSmbYn)
        .divePlanKnifeYn(divePlanKnifeYn)
        .divePlanCameraYn(divePlanCameraYn)
        .visibility(visibility)
        .temperature(temperature)
        .diveType(diveType)
        .diveWater(diveWater)
        .diveWave(diveWave)
        .diveCurrent(diveCurrent)
        .diveActivity(diveActivity)
        .diveNote(diveNote)
        .regId(this.getRegId())
        .updateId(this.getUpdateId())
        .regDate(this.getRegDate())
        .updateDate(this.getUpdateDate())
        .build();
  }

}
