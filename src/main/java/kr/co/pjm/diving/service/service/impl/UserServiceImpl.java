package kr.co.pjm.diving.service.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.pjm.diving.common.domain.dto.UserBasicDto;
import kr.co.pjm.diving.common.domain.dto.UserDiveDto;
import kr.co.pjm.diving.common.domain.entity.Role;
import kr.co.pjm.diving.common.domain.entity.User;
import kr.co.pjm.diving.common.domain.entity.UserBasic;
import kr.co.pjm.diving.common.domain.entity.UserDive;
import kr.co.pjm.diving.common.domain.entity.UserRole;
import kr.co.pjm.diving.common.domain.enumeration.RoleTypeEnum;
import kr.co.pjm.diving.common.domain.enumeration.UserStatusEnum;
import kr.co.pjm.diving.common.exception.ResourceNotFoundException;
import kr.co.pjm.diving.common.repository.RoleRepository;
import kr.co.pjm.diving.common.repository.UserBasicRepository;
import kr.co.pjm.diving.common.repository.UserConnectionRepasitory;
import kr.co.pjm.diving.common.repository.UserDiveRepository;
import kr.co.pjm.diving.common.repository.UserRepository;
import kr.co.pjm.diving.service.domain.dto.UserDto;
import kr.co.pjm.diving.service.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
  
  @Autowired
  private UserRepository userRepository;
  
  @Autowired
  private UserBasicRepository userBasicRepository;
  
  @Autowired
  private UserDiveRepository userDiveRepository;
  
  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  private UserConnectionRepasitory userConnectionRepasitory;
  
  @Autowired
  private PasswordEncoder passwordEncoder;
  
  @Autowired 
  MessageSourceAccessor msa;

  @Override
  @Transactional
  public User set(UserDto.Create create) {
    User user = new User();
    user.setEmail(create.getEmail());
    user.setPassword(passwordEncoder.encode(create.getPassword()));
    
    /* user basic create */
    UserBasic userBasic = new UserBasic();
    userBasic.setName(create.getName());
    userBasic.setNickname(create.getNickname());
    userBasic.setGender(create.getGender());
    userBasic.setCountry(create.getCountry());
    userBasic.setStatus(UserStatusEnum.NORMAL);
    
    userBasicRepository.save(userBasic);
    
    user.setUserBasic(userBasic);
    
    /* user dive create */
    UserDive userDive = new UserDive();
    userDive.setDiveLevel(StringUtils.EMPTY);
    userDive.setDiveGroup(StringUtils.EMPTY);
    userDive.setTeam(StringUtils.EMPTY);
    userDive.setSignature(StringUtils.EMPTY);
    
    userDiveRepository.save(userDive);
    
    user.setUserDive(userDive);
    
    /* get role */
    Role role = roleRepository.findByRole(RoleTypeEnum.USER);
    
    UserRole userRole = new UserRole();
    userRole.setUser(user);
    userRole.setRole(role);
    
    user.getUserRoles().add(userRole);
    
    /* user create */
    User retUser = userRepository.save(user);
    
    return retUser;
  }

  @Override
  public User getById(Long id) {
    User user = userRepository.findOne(id);
    if (user == null) {
      throw new ResourceNotFoundException(msa.getMessage("message.common.resource.not.found", new String[]{ String.valueOf(id) }));
    }
    
    return userRepository.findOne(id);
  }

  @Override
  public User getByEmail(String email) {
    User user = userRepository.findByEmail(email); 
    log.debug("user : {}", user.getEmail());
    
    return user;
  }

  @Override
  @Transactional
  public void update(Long id, UserDto.Update update) {
    /* user basic update */
    UserBasicDto userBasicDto = new UserBasicDto();
    userBasicDto.setId(id);
    userBasicDto.setName(update.getName());
    userBasicDto.setNickname(update.getNickname());
    userBasicDto.setCountry(update.getCountry());
    userBasicDto.setGender(update.getGender());
    userBasicDto.setIntroduce(update.getIntroduce());
    
    Long updateUserBasic = userBasicRepository.updateUserBasic(userBasicDto);
    log.debug("===> update updateUserBasic : {}", updateUserBasic);
    
    /* user dive update */
    UserDiveDto userDiveDto = new UserDiveDto();
    userDiveDto.setId(id);
    userDiveDto.setDiveGroup( update.getDiveGroup());
    userDiveDto.setDiveLevel( update.getDiveLevel());
    userDiveDto.setTeam( update.getTeam());
    userDiveDto.setSignature( update.getSignature());
    
    Long updateUserDive = userDiveRepository.updateUserDive(userDiveDto);
    log.debug("===> update updateUserDive : {}", updateUserDive);
  }

  @Override
  @Transactional
  public void delete(Long id) {
    User user = userRepository.findOne(id);
    
    userBasicRepository.delete(id);
    userDiveRepository.delete(id);
    userRepository.delete(id);
    userConnectionRepasitory.deleteUserConnection(user.getEmail());
  }

}
