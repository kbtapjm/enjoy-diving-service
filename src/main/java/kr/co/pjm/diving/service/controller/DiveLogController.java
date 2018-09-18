package kr.co.pjm.diving.service.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import kr.co.pjm.diving.common.domain.entity.DiveLog;
import kr.co.pjm.diving.service.domain.dto.DiveLogDto;
import kr.co.pjm.diving.service.service.DiveLogService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = DiveLogController.RESOURCE_PATH)
public class DiveLogController {

  static final String RESOURCE_PATH = "/{version}/{userId}/divelogs";

  @Autowired
  private DiveLogService diveLogService;

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> create(@PathVariable("userId") Long userId,
      @Valid @RequestBody DiveLogDto diveLogDto, UriComponentsBuilder b, HttpServletRequest request)
      throws Exception {
    if (log.isDebugEnabled()) log.debug("userId : {}", userId); 
    
    DiveLog diveLog = diveLogService.set(diveLogDto);

    UriComponents uriComponents = b.path(request.getRequestURI().toString()).buildAndExpand(diveLog.getId());
    return ResponseEntity.created(uriComponents.toUri()).build();
  }

}
