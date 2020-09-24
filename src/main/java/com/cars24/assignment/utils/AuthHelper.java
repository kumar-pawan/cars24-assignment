package com.cars24.assignment.utils;

import com.cars24.assignment.exception.AuthenticationException;
import com.cars24.assignment.model.LoggedInUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
public class AuthHelper {

  private RedisTemplate<String, Object> redisTemplate;

  public AuthHelper(RedisTemplate<String, Object> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  /**
   * LoggedInUserDetails.
   *
   * @param accessToken {String}
   * @return LoggedInUserDetails {LoggedInUserDetails}
   */
  public LoggedInUserDetails validateUser(String accessToken) {

    try {
      LoggedInUserDetails userDetails = (LoggedInUserDetails) redisTemplate.opsForValue().get(accessToken);

      if (Objects.isNull(userDetails)) {
        throw new AuthenticationException("User not logged in");
      }
      return userDetails;
    }
    catch (Exception e) {
      log.error("Authentication failed {}", e);
      throw new AuthenticationException(e);
    }
  }
}
