package com.cars24.assignment.init;

import com.cars24.assignment.model.LoggedInUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Slf4j
@org.springframework.context.annotation.Configuration
public class Configuration {

  @Bean
  public JedisConnectionFactory getJedisConnectionFactory(@Value("${spring.redis.host}") String hostname,
      @Value("${spring.redis.port}") int port) {

    return new JedisConnectionFactory(new RedisStandaloneConfiguration(hostname, port));
  }

  /**
   * RedisTemplate.
   *
   * @param connectionFactory {JedisConnectionFactory}
   * @return RedisTemplate
   */
  @Bean
  public RedisTemplate<String, Object> getRedisTemplate(JedisConnectionFactory connectionFactory) {
    RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
    redisTemplate.setConnectionFactory(connectionFactory);
    return redisTemplate;
  }

  /**
   * CommandLineRunner.
   *
   * @param redisTemplate {RedisTemplate}
   * @return CommandLineRunner
   */
  @Bean
  public CommandLineRunner demo(RedisTemplate<String, Object> redisTemplate) {
    return (args) -> {
      log.info("Adding Dummy tokens in the redis for cache");
      // This is dummy access token for logged in users
      redisTemplate.opsForValue().set("sdlkfahqwo874y3874yf832ghf87234gf32",
          LoggedInUserDetails.builder().email("abc@cars24.com").contact(1234567890).build());
      redisTemplate.opsForValue().set("lksjdoighjrgownb3498h03gnrdsjknvf32",
          LoggedInUserDetails.builder().email("xyz@cars24.com").contact(1234567890).build());
      redisTemplate.opsForValue().set("kjwhef923y4rwehfe9238f2hfweofh23kbdw",
          LoggedInUserDetails.builder().email("pqr@cars24.com").contact(1234567890).build());

    };
  }

}
