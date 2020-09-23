package com.cars24.assignment.validation;

import com.cars24.assignment.exception.AuthenticationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@SuppressWarnings("rawtypes")
@Slf4j
public class Validations {

  private RedisTemplate redisTemplate;

  public Validations(RedisTemplate redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  /**
   * CheckAuthentication.
   *
   * @param accessToken {String}
   */
  public void checkAuthentication(String accessToken) {

    log.info("checking authentication of the user");

    if (StringUtils.isEmpty(accessToken) || Objects.isNull(redisTemplate.opsForValue().get(accessToken))) {

      throw new AuthenticationException("Invalid user access Token, Please login again and try");
    }

  }
}
