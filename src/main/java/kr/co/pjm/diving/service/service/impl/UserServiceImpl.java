package kr.co.pjm.diving.service.service.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

import kr.co.pjm.diving.common.domain.dto.PagingDto;
import kr.co.pjm.diving.common.domain.dto.ResourcesDto;
import kr.co.pjm.diving.common.domain.dto.SearchDto;
import kr.co.pjm.diving.common.domain.dto.SearchDto.SearchQ;
import kr.co.pjm.diving.common.domain.entity.QUser;
import kr.co.pjm.diving.common.domain.entity.QUserBasic;
import kr.co.pjm.diving.common.domain.entity.Role;
import kr.co.pjm.diving.common.domain.entity.User;
import kr.co.pjm.diving.common.domain.entity.UserBasic;
import kr.co.pjm.diving.common.domain.entity.UserDive;
import kr.co.pjm.diving.common.domain.entity.UserLoginLog;
import kr.co.pjm.diving.common.domain.entity.UserRole;
import kr.co.pjm.diving.common.domain.enumeration.GenderEnum;
import kr.co.pjm.diving.common.domain.enumeration.RoleTypeEnum;
import kr.co.pjm.diving.common.domain.enumeration.UserStatusEnum;
import kr.co.pjm.diving.common.exception.InvalidRequestException;
import kr.co.pjm.diving.common.exception.ResourceNotFoundException;
import kr.co.pjm.diving.common.repository.RoleRepository;
import kr.co.pjm.diving.common.repository.UserBasicRepository;
import kr.co.pjm.diving.common.repository.UserConnectionRepasitory;
import kr.co.pjm.diving.common.repository.UserDiveRepository;
import kr.co.pjm.diving.common.repository.UserLoginLogRepasitory;
import kr.co.pjm.diving.common.repository.UserRepository;
import kr.co.pjm.diving.service.domain.dto.UserDto;
import kr.co.pjm.diving.service.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
  
  private UserRepository userRepository;
  
  private UserBasicRepository userBasicRepository;
  
  private UserDiveRepository userDiveRepository;
  
  private RoleRepository roleRepository;

  private UserConnectionRepasitory userConnectionRepasitory;
  
  private UserLoginLogRepasitory userLoginLogRepasitory;
  
  private PasswordEncoder passwordEncoder;
  
  private MessageSourceAccessor messageSourceAccessor;
  
  @Override
  public List<User> getUsers(SearchDto searchDto, PagingDto pagingDto) {
    /* search */
    Predicate predicate = this.getPredicate(searchDto);
       
    /* page */
    PageRequest pageRequest = new PageRequest(pagingDto.getOffset(), pagingDto.getLimit(), searchDto.getPageSort());
    
    Page<User> page = userRepository.findAll(predicate, pageRequest);
    
    ResourcesDto resourcesDto = new ResourcesDto(page.getContent(), searchDto, pagingDto);
    resourcesDto.putContent("total", userRepository.count(predicate));
    
    return page.getContent();
  }
  
  public Predicate getPredicate(SearchDto searchDto) {
    BooleanBuilder booleanBuilder = new BooleanBuilder();
    QUserBasic qUserBasic = QUserBasic.userBasic;
    QUser qUser = QUser.user;
    
    for (SearchQ searchQ : searchDto.getQList()) {
      switch (searchQ.getSearchColumn()) {
      case "email":
        booleanBuilder.and(qUser.email.eq(searchQ.getSearchValue()));
        break;
      case "name":
        booleanBuilder.and(qUserBasic.name.like("%".concat(searchQ.getSearchValue()).concat("%")));
        break;
      case "nickname":
        booleanBuilder.and(qUserBasic.name.like("%".concat(searchQ.getSearchValue()).concat("%")));
        break;
      case "gender":
        booleanBuilder.and(qUserBasic.gender.eq(GenderEnum.findByValue(Integer.parseInt(searchQ.getSearchValue()))));
        break;
      case "country":
        booleanBuilder.and(qUserBasic.name.eq(searchQ.getSearchValue()));
        break;
      }
    }
    
    return booleanBuilder;
  }

  @Override
  @Transactional
  public User set(UserDto.Create dto) {
    /* user basic create */
    UserBasic userBasic = UserBasic.builder()
        .name(dto.getName())
        .nickname(dto.getNickname())
        .gender(dto.getGender())
        .country(dto.getCountry())
        .status(UserStatusEnum.NORMAL)
        .build();
    
    userBasicRepository.save(userBasic);
    
    /* user dive create */
    UserDive userDive = UserDive.builder()
        .diveLevel(StringUtils.EMPTY)
        .diveGroup(StringUtils.EMPTY)
        .team(StringUtils.EMPTY)
        .signature(StringUtils.EMPTY)
        .build();
    
    userDiveRepository.save(userDive);
        
    /* get role */
    Role role = roleRepository.findByRole(RoleTypeEnum.USER);
    
    User user = User.builder()
        .email(dto.getEmail())
        .password(passwordEncoder.encode(dto.getPassword()))
        .userBasic(userBasic)
        .userDive(userDive)
        .provider(dto.getProvider())
        .build();
    
    UserRole userRole = UserRole.builder()
        .role(role)
        .user(user)
        .build();
    
    /* user_role, user create */
    user.setUserRoles(new HashSet<UserRole>(Arrays.asList(userRole)));
    
    User retUser = userRepository.save(user);
    
    return retUser;
  }

  @Override
  public User getById(Long id) {
    User user = userRepository.findOne(id);
    if (user == null) {
      throw new ResourceNotFoundException(String.valueOf(id));
    }
    
    return user;
  }

  @Override
  public User getByEmail(String email) {
    User user = userRepository.findByEmail(email); 
    if (user == null) {
      throw new ResourceNotFoundException(email);
    }
    
    return user;
  }

  @Override
  @Transactional
  public void update(Long id, UserDto.Update dto) {
    /* user basic update */
    UserBasic userBasic = UserBasic.builder()
        .id(id)
        .name(dto.getName())
        .nickname(dto.getNickname())
        .country(dto.getCountry())
        .gender(dto.getGender())
        .introduce(dto.getIntroduce())
        .build();
    
    Long updateUserBasic = userBasicRepository.updateUserBasic(userBasic);
    log.debug("===> update updateUserBasic : {}", updateUserBasic);
    
    /* user dive update */
    UserDive userDive = UserDive.builder()
        .id(id)
        .diveGroup(dto.getDiveGroup())
        .diveLevel(dto.getDiveLevel())
        .team(dto.getTeam())
        .signature(dto.getSignature())
        .build();
    
    Long updateUserDive = userDiveRepository.updateUserDive(userDive);
    log.debug("===> update updateUserDive : {}", updateUserDive);
  }

  @Override
  @Transactional
  public void delete(Long id) {
    User user = userRepository.findOne(id);
    if (user == null) {
      throw new ResourceNotFoundException(String.valueOf(id));
    }
    
    userBasicRepository.delete(user.getUserBasic());
    userDiveRepository.delete(user.getUserDive());
    userRepository.delete(id);
    userConnectionRepasitory.deleteUserConnection(user.getEmail());
  }

  @Override
  @Transactional
  public void updateLoginDate(Long id) {
    UserBasic userBasic = UserBasic.builder().id(id).build();
    
    userBasicRepository.updateLoginDate(userBasic);
  }

  @Override
  @Transactional
  public void updatePassword(Long id, UserDto.Password dto) {
    User chkUser = userRepository.findOne(id);
    
    if (!passwordEncoder.matches(dto.getOldPassword(), chkUser.getPassword())) {
      throw new InvalidRequestException(messageSourceAccessor.getMessage("message.user.password.notMatch"));
    }
    
    User user = User.builder()
        .id(id)
        .password(passwordEncoder.encode(dto.getNewPassword()))
        .build();
    
    userRepository.updatePassword(user);
  }

  @Override
  @Transactional
  public void updateStatus(Long id, UserDto.Status dto) {
    UserBasic userBasic = UserBasic.builder()
        .id(id)
        .status(dto.getStatus())
        .build();
    
    userBasicRepository.updateUserBasic(userBasic);
  }

  @Override
  @Transactional
  public UserLoginLog setLoginLog(Long id) {
    User user = userRepository.findOne(id);
    
    UserLoginLog userLoginLog = UserLoginLog.builder()
        .email(user.getEmail())
        .user(user)
        .build();
  
    UserLoginLog result = userLoginLogRepasitory.save(userLoginLog);
    
    return result;
  }
  
}
