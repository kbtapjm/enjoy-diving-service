package kr.co.pjm.diving.service.common.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_EMPTY)
public class PagingDto {
  private int offset;
  private int limit;

  @Builder
  public PagingDto(int offset, int limit) {
    this.offset = offset;
    this.limit = limit;
  }
}
