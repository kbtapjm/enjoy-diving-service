package kr.co.pjm.diving.service.common.domain.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_EMPTY)
public class PagingDto {
  private int offset;
  private int limit;
  private String fields;
  private List<String> fieldList = new ArrayList<String>();
  
  public PagingDto() {
    if (this.getOffset() == 0) this.setOffset(0); 
    if (this.getLimit() == 0) this.setLimit(10);
  }
}
