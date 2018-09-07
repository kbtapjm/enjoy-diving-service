package kr.co.pjm.diving.service.domain.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import kr.co.pjm.diving.common.domain.enumeration.GenderEnum;
import kr.co.pjm.diving.common.domain.enumeration.UserStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {

  @NotEmpty(message = "{message.user.email.notEmpty}")
  @Email(message = "{message.user.email.valid}")
  private String email;

  @NotEmpty(message = "{message.user.password.notEmpty}")
  @Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})", message = "{message.user.password.pattern}")
  @Size(min = 6, max = 20, message = "{message.user.password.size}")
  private String password;

  @NotEmpty(message = "{message.user.name.notEmpty}")
  @Size(min = 1, max = 100, message = "{message.user.name.size}")
  private String name;

  @NotEmpty(message = "{message.user.nickname.notEmpty}")
  private String nickname;

  //@NotEmpty(message = "{message.user.gender.notEmpty}")
  @Enumerated(EnumType.ORDINAL)
  private GenderEnum gender;

  @NotEmpty(message = "{message.user.country.notEmpty}")
  private String country;

  @Enumerated(EnumType.ORDINAL)
  private UserStatusEnum status;

  private String profile;

  private String introduce;

  private String diveGroup;

  private String diveLevel;

  private String team;

  private String signature;
  
//  @Phone
//  private String phone;

}
