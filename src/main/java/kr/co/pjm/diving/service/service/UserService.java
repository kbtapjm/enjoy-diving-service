package kr.co.pjm.diving.service.service;

import kr.co.pjm.diving.common.domain.dto.UserDto;
import kr.co.pjm.diving.common.domain.entity.User;
import kr.co.pjm.diving.service.domain.dto.UserRequestDto;

public interface UserService {
  
  User set(UserRequestDto userRequestDto);
  
  User getById(Long id);
  
  User getByEmail(String email);
  
  void update(UserDto userDto);
  
  void delete(Long id);

}
