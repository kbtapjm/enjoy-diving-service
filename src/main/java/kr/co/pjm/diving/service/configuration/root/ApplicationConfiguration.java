package kr.co.pjm.diving.service.configuration.root;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties("application")
@Getter
@Setter
public class ApplicationConfiguration {
  private String message;
}
