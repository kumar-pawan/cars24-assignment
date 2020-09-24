package com.cars24.assignment.validation;

import com.cars24.assignment.exception.AuthenticationException;
import com.cars24.assignment.utils.AuthHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Slf4j
public class Validations {

  private AuthHelper authHelper;

  public Validations(AuthHelper authHelper) {
    this.authHelper = authHelper;
  }

  /**
   * CheckAuthentication.
   *
   * @param accessToken {String}
   */
  public void checkAuthentication(String accessToken) {

    log.info("checking authentication of the user");

    if (StringUtils.isEmpty(accessToken) || Objects.isNull(authHelper.validateUser(accessToken))) {

      throw new AuthenticationException("Invalid user access Token, Please login again and try");
    }

  }
}
