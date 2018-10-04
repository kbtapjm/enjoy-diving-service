package kr.co.pjm.diving.service.common.domain.dto;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_EMPTY)
public class ResourcesDto {
  private HashMap<String, Object> content = null;
  private Object search = null;
  private Object page = null;

  @JsonIgnore
  private final static String RESOURCES_KEY = "resources";
  @JsonIgnore
  private final static String RESOURCE_KEY = "resource";

  public ResourcesDto() {}

  public ResourcesDto(Object resources) {
    if (null == this.content) {
      this.content = new HashMap<>();
    }

    this.content.put(getResourceKey(resources), resources);
  }

  public ResourcesDto(Object resources, Object search) {
    if (null == this.content) {
      this.content = new HashMap<>();
    }

    this.content.put(getResourceKey(resources), resources);
    this.search = search;
  }

  public ResourcesDto(Object resources, Object search, PagingDto pagingDto) {
    if (null == this.content) {
      this.content = new HashMap<>();
    }

    this.content.put(getResourceKey(resources), resources);
    this.search = search;
    this.page = pagingDto;
  }


  public void putContent(String key, Object value) {
    if (null == this.content) {
      this.content = new HashMap<>();
    }

    this.content.put(key, value);
  }

  @JsonIgnore
  public HashMap<String, Object> getMap() {
    HashMap<String, Object> map = new HashMap<>();
    if (null != this.content) {
      map.put("content", this.content);
    }

    if (null != this.search) {
      map.put("search", this.search);
    }

    if (null != this.page) {
      map.put("page", this.page);
    }

    return map;
  }

  private String getResourceKey(Object resources) {
    if (resources instanceof Collection<?>) {
      return RESOURCES_KEY;
    }

    if (resources instanceof Map<?, ?>) {
      return RESOURCES_KEY;
    }

    return RESOURCE_KEY;
  }
}
