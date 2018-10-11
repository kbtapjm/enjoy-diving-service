package kr.co.pjm.diving.service.common.domain.dto;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class CommonDto {
  @JsonIgnore
  private String regId;
  @JsonIgnore
  private Date regDate;
  @JsonIgnore
  private String updateId;
  @JsonIgnore
  private Date updateDate;
  
  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
  }

}
