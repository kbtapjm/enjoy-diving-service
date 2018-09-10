package kr.co.pjm.diving.service.service;

import kr.co.pjm.diving.common.domain.entity.User;
import kr.co.pjm.diving.service.domain.dto.UserDto;

public interface UserService {
  
  User set(UserDto.Create userCreate);
  
  User getById(Long id);
  
  User getByEmail(String email);
  
  void update(Long id, UserDto.Update update);
  
  void delete(Long id);

}
