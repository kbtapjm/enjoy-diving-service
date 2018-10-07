package kr.co.pjm.diving.service.common.domain.dto;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

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
public class SearchDto {
  private String q;
  private List<SearchQ> qList = new ArrayList<SearchQ>();
  private String sorts;
  private List<OrderBySort> sortList = new ArrayList<OrderBySort>();
  
  private String sort;
  private String orderby;

  public SearchDto() {
    if (StringUtils.isEmpty(this.orderby)) {
      this.orderby = "reg_date";
    }
    if (StringUtils.isEmpty(this.sort)) {
      this.sort = "DESC";
    }
  }

  @Getter
  @Setter
  public static class SearchQ {
    private String searchColumn;
    private String searchText;
  }

  @Getter
  @Setter
  @Builder
  public static class OrderBySort {
    private String sortType;
    private String sortColumn;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
  }
}
