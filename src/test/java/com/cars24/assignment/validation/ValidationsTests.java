package com.cars24.assignment.validation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import com.cars24.assignment.exception.AuthenticationException;

public class ValidationsTests {

    @Mock
    private RedisTemplate<String, Object> redisTemplate;

    @Mock
    private ValueOperations<String, Object> valueOperations;

    private Validations validations;

    private final String accessToken = "sdfasdfadsfsdafasdfasd";
    private final String email = "abc@cars24.com";

    @BeforeEach
    public void init() {
	MockitoAnnotations.initMocks(this);
	validations = new Validations(redisTemplate);
	Mockito.when(redisTemplate.opsForValue()).thenReturn(valueOperations);
    }

    @Test
    public void checkAuthenticationEmptyToken() {

	Assertions.assertThrows(AuthenticationException.class, () -> validations.checkAuthentication(null));

    }

    @Test
    public void validationNoValuesInRedis() {
	Mockito.when(valueOperations.get(Mockito.anyString())).thenReturn(null);
	Assertions.assertThrows(AuthenticationException.class, () -> validations.checkAuthentication(accessToken));
    }

    @Test
    public void successTest() {
	Mockito.when(valueOperations.get(Mockito.anyString())).thenReturn(email);
	validations.checkAuthentication(accessToken);
    }
}
