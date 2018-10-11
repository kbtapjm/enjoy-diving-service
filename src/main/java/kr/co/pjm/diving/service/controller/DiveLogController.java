package kr.co.pjm.diving.service.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import kr.co.pjm.diving.common.domain.entity.DiveLog;
import kr.co.pjm.diving.service.common.domain.dto.PagingDto;
import kr.co.pjm.diving.service.common.domain.dto.SearchDto;
import kr.co.pjm.diving.service.domain.dto.DiveLogDto;
import kr.co.pjm.diving.service.service.DiveLogService;
import lombok.extern.slf4j.Slf4j;

@Api(value = "DiveLogController")
@Slf4j
@RestController
@RequestMapping(value = DiveLogController.RESOURCE_PATH)
public class DiveLogController {

  static final String RESOURCE_PATH = "/{version}/divelogs";

  @Autowired
  private DiveLogService diveLogService;
  
  @ApiOperation(value = "다이브 로그 목록 조회 API")
  @ApiImplicitParams({
    @ApiImplicitParam(name = "sorts", value = "정렬 타입(-:내림차순, +:오름차순) + 정렬 컬럼 ex)-diveNo,-regDate", required = false, dataType = "string", paramType = "path"),
    @ApiImplicitParam(name = "q", value = "검색컬럼 + 검색 값 ex) diveDate=2018-08-26,diveType=BOAT,divePlace=Liloan ", required = false, dataType = "string", paramType = "query"),
    @ApiImplicitParam(name = "offset", value = "페이지 번호", required = false, dataType = "int", paramType = "query", defaultValue = "0"),
    @ApiImplicitParam(name = "limit", value = "페이지당 로우 카운트", required = false, dataType = "int", paramType = "query", defaultValue = "10")
  })
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> getDiveLogs(
      @RequestParam(value = "sorts", required = false, defaultValue = "") String sorts, 
      @RequestParam(value = "q", required = false, defaultValue = "") String q,
      @RequestParam(value = "offset", required = false, defaultValue = "0") int offset,
      @RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {
    
    SearchDto searchDto = SearchDto.builder().q(q).sorts(sorts).build();
    PagingDto pagingDto = PagingDto.builder().offset(offset).limit(limit).build();
    
    return ResponseEntity.ok(diveLogService.getDiveLogs(searchDto, pagingDto));
  }

  @ApiOperation(value = "다이브 로그 등록 API")
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> createDiveLog(@Valid @RequestBody DiveLogDto diveLogDto, UriComponentsBuilder b,
      HttpServletRequest request) throws Exception {
    
    DiveLog diveLog = diveLogService.set(diveLogDto);

    UriComponents uriComponents = b.path(request.getRequestURI().toString()).buildAndExpand(diveLog.getId());
    return ResponseEntity.created(uriComponents.toUri()).build();
  }
  
  @ApiOperation(value = "다이브 로그 조회 API")
  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> getDiveLog(@PathVariable("id") Long id)
      throws Exception {
    if (log.isDebugEnabled()) {
      log.debug("id : {}", id);
    }

    return ResponseEntity.ok(diveLogService.getById(id));
  }
  
  @ApiOperation(value = "다이브 로그 수정 API")
  @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> updateDiveLog(@PathVariable("id") Long id, @Valid @RequestBody DiveLogDto diveLogDto) throws Exception {
    
    diveLogService.update(id, diveLogDto);
    
    return ResponseEntity.ok(diveLogService.getById(id));
  }
  
  @ApiOperation(value = "다이브 로그 삭제 API")
  @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> deleteDiveLog(@PathVariable("id") Long id)
      throws Exception {
    
    diveLogService.delete(id);

    return new ResponseEntity<DiveLog>(HttpStatus.NO_CONTENT);
  }

}
