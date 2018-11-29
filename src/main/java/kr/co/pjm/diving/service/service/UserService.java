package kr.co.pjm.diving.service.service;

import java.util.List;

import kr.co.pjm.diving.common.domain.dto.PagingDto;
import kr.co.pjm.diving.common.domain.dto.SearchDto;
import kr.co.pjm.diving.common.domain.entity.User;
import kr.co.pjm.diving.service.domain.dto.UserDto;

public interface UserService {
  
  List<User> getUsers(SearchDto searchDto, PagingDto pagingDto);
  
  User set(UserDto.Create dto);
  
  User getById(Long id);
  
  User getByEmail(String email);
  
  void update(Long id, UserDto.Update dto);
  
  void delete(Long id);
  
  void updateLoginDate(Long id);
  
  void updatePassword(Long id, UserDto.Password dto);

}
