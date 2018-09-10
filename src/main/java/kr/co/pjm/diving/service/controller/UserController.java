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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import kr.co.pjm.diving.common.domain.entity.User;
import kr.co.pjm.diving.service.domain.dto.UserDto;
import kr.co.pjm.diving.service.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = UserController.RESOURCE_PATH)
public class UserController {

  static final String RESOURCE_PATH = "/{version}/users";

  @Autowired
  private UserService userService;

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> create(@Valid @RequestBody UserDto.Create UserCreate, UriComponentsBuilder b, HttpServletRequest request)
      throws Exception {
    User user = userService.set(UserCreate);

    UriComponents uriComponents = b.path(request.getRequestURI().toString()).buildAndExpand(user.getId());
    return ResponseEntity.created(uriComponents.toUri()).build();
  }

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> getUser(@PathVariable("version") String version, @PathVariable("id") Long id)
      throws Exception {
    if (log.isDebugEnabled()) {
      log.debug("api version : {}", version);
    }

    return ResponseEntity.ok(userService.getById(id));
  }
  
  @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @Valid @RequestBody UserDto.Update userUpdate)
      throws Exception {
    
    userService.update(id, userUpdate);

    return ResponseEntity.ok(userService.getById(id));
  }
  
  @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> deleteUser(@PathVariable("id") Long id)
      throws Exception {
    
    userService.delete(id);

    return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
  }

}
