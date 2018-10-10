package kr.co.pjm.diving.service.common.domain.dto;

import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
  private Set<SearchQ> qList = new HashSet<SearchQ>();
  private String sorts;
  private Set<OrderBySort> orderBySorts = new HashSet<OrderBySort>();
  
  @Builder
  public SearchDto(String q, String sorts) {
    this.q = q;
    this.sorts = sorts;
    
    if (!StringUtils.isEmpty(this.q)) {
      String[] qArr = this.q.split(",");
      
      for (String str : qArr) {
        StringTokenizer st = new StringTokenizer(str, "=");
        
        String searchColumn = StringUtils.EMPTY;
        String searchValue = StringUtils.EMPTY;
        while (st.hasMoreTokens()) {
          searchColumn = st.nextToken();
          searchValue = st.nextToken();
        }
        SearchQ searchQ = SearchQ.builder().searchColumn(searchColumn).searchValue(searchValue).build();
        
        qList.add(searchQ);
      }
    }
    
    if (!StringUtils.isEmpty(this.sorts)) {
      String[] sortsArr = this.sorts.split(",");
      
      String sort = StringUtils.EMPTY;
      Direction orderBy = null;
      for (String s : sortsArr) {
        if (StringUtils.isEmpty(s)) continue;
        
        sort = s.substring(1, s.length());
        orderBy = s.substring(0, 1).equals("+") ? Direction.ASC : Direction.DESC;
        
        OrderBySort orderBySort = OrderBySort.builder().orderBy(orderBy).sort(sort).build();
        
        this.orderBySorts.add(orderBySort);
      }
    }
  }
  
  @JsonIgnore
  public Sort getPageSort() {
    Sort sort = null;
    if (!this.getOrderBySorts().isEmpty()) {
      Order[] order = new Order[this.getOrderBySorts().size()];
      
      int idx = 0;
      for (OrderBySort orderBySort : this.getOrderBySorts()) {
        order[idx++] = new Order(orderBySort.getOrderBy(), orderBySort.getSort());
      }
      
      sort = new Sort(order);
    }
    
    return sort;
  }
  
  @Getter
  @Setter
  @Builder
  public static class SearchQ {
    private String searchColumn;
    private String searchValue;
  }

  @Getter
  @Setter
  @Builder
  public static class OrderBySort {
    private String sort;
    private Direction orderBy;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
  }
}
