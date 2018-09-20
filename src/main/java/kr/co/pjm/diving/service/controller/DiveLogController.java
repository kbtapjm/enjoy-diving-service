package kr.co.pjm.diving.service.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import kr.co.pjm.diving.common.domain.entity.DiveLog;
import kr.co.pjm.diving.common.domain.entity.User;
import kr.co.pjm.diving.service.common.domain.dto.PagingDto;
import kr.co.pjm.diving.service.common.domain.dto.SearchDto;
import kr.co.pjm.diving.service.domain.dto.DiveLogDto;
import kr.co.pjm.diving.service.service.DiveLogService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = DiveLogController.RESOURCE_PATH)
public class DiveLogController {

  static final String RESOURCE_PATH = "/{version}/divelogs";

  @Autowired
  private DiveLogService diveLogService;

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> create(@Valid @RequestBody DiveLogDto diveLogDto, UriComponentsBuilder b,
      HttpServletRequest request) throws Exception {
    
    DiveLog diveLog = diveLogService.set(diveLogDto);

    UriComponents uriComponents = b.path(request.getRequestURI().toString()).buildAndExpand(diveLog.getId());
    return ResponseEntity.created(uriComponents.toUri()).build();
  }
  
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> getDiveLogs(@ModelAttribute SearchDto searchDto, @ModelAttribute PagingDto pagingDto) {
    return ResponseEntity.ok(diveLogService.getDiveLogs(searchDto, pagingDto));
  }

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> getDiveLog(@PathVariable("id") Long id)
      throws Exception {
    if (log.isDebugEnabled()) {
      log.debug("id : {}", id);
    }

    return ResponseEntity.ok(diveLogService.getById(id));
  }
  
  @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> update(@PathVariable("id") Long id, @Valid @RequestBody DiveLogDto diveLogDto) throws Exception {
    
    diveLogService.update(id, diveLogDto);
    
    return ResponseEntity.ok(diveLogService.getById(id));
  }
  
  @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> deleteDiveLog(@PathVariable("id") Long id)
      throws Exception {
    
    diveLogService.delete(id);

    return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
  }

}
