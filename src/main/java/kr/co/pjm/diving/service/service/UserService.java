package kr.co.pjm.diving.service.service;

import kr.co.pjm.diving.common.domain.entity.User;
import kr.co.pjm.diving.service.common.domain.dto.PagingDto;
import kr.co.pjm.diving.service.common.domain.dto.ResourcesDto;
import kr.co.pjm.diving.service.common.domain.dto.SearchDto;
import kr.co.pjm.diving.service.domain.dto.UserDto;

public interface UserService {
  
  ResourcesDto getUsers(SearchDto searchDto, PagingDto pagingDto);
  
  User set(UserDto.Create userCreate);
  
  User getById(Long id);
  
  User getByEmail(String email);
  
  void update(Long id, UserDto.Update update);
  
  void delete(Long id);

}
