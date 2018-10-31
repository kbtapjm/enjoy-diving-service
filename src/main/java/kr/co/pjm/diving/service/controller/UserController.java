package kr.co.pjm.diving.service.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
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

import kr.co.pjm.diving.common.domain.dto.PagingDto;
import kr.co.pjm.diving.common.domain.dto.SearchDto;
import kr.co.pjm.diving.common.domain.entity.User;
import kr.co.pjm.diving.service.domain.dto.UserDto;
import kr.co.pjm.diving.service.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = UserController.RESOURCE_PATH)
public class UserController {

  static final String RESOURCE_PATH = "/{version}/users";
  final String API_VERSION = "v1";

  @Autowired
  private UserService userService;
  
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Resources<Resource<User>> getUsers(@PathVariable("version") String version,
      @RequestParam(value = "sorts", required = false, defaultValue = "") String sorts, 
      @RequestParam(value = "q", required = false, defaultValue = "") String q,
      @RequestParam(value = "offset", required = false, defaultValue = "0") int offset,
      @RequestParam(value = "limit", required = false, defaultValue = "10") int limit) throws Exception {
    
    List<Resource<User>> resources = new ArrayList<Resource<User>>();
    
    SearchDto searchDto = SearchDto.builder().q(q).sorts(sorts).build();
    PagingDto pagingDto = PagingDto.builder().offset(offset).limit(limit).build();
    
    List<User> users = userService.getUsers(searchDto, pagingDto);
    for (User u : users) {
      Resource<User> resource = new Resource<User>(userService.getById(u.getId()));
      resource.add(linkTo(methodOn(UserController.class).getUser(API_VERSION, u.getId())).withSelfRel());
      
      resources.add(resource);
    }
    
    Link selfRel = linkTo(methodOn(UserController.class).getUsers(version, sorts, q, offset, limit)).withSelfRel();
    
    return new Resources<>(resources, selfRel);
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> createUser(@RequestBody UserDto.Create userCreate, UriComponentsBuilder b, HttpServletRequest request)
      throws Exception {
    User user = userService.set(userCreate);

    UriComponents uriComponents = b.path(request.getRequestURI().toString()).buildAndExpand(user.getId());
    return ResponseEntity.created(uriComponents.toUri()).build();
  }

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Resource<User> getUser(@PathVariable("version") String version, @PathVariable("id") Long id)
      throws Exception {
    if (log.isDebugEnabled()) {
      log.debug("api version : {}", version);
    }
    
    Resource<User> resource = new Resource<User>(userService.getById(id));
    resource.add(linkTo(methodOn(UserController.class).getUser(API_VERSION, id)).withSelfRel());

    return resource;
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
  
  @PutMapping(value = "/{id}/loginDate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> updateUserLoginDate(@PathVariable("id") Long id)
      throws Exception {
    
    userService.updateLoginDate(id);

    return ResponseEntity.ok(userService.getById(id));
  }

}
