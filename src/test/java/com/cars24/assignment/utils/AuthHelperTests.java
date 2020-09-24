package com.cars24.assignment.utils;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import com.cars24.assignment.exception.AuthenticationException;
import com.cars24.assignment.model.LoggedInUserDetails;

public class AuthHelperTests {

    @Mock
    private RedisTemplate<String, Object> redisTemplate;

    @Mock
    private ValueOperations<String, Object> valueOperations;

    private AuthHelper authHelper;
    private final String accessToken = "sdfasdfq34wfsgafsdgfdsgsad";

    @BeforeEach
    public void init() {
	MockitoAnnotations.initMocks(this);
	authHelper = new AuthHelper(redisTemplate);
	Mockito.when(redisTemplate.opsForValue()).thenReturn(valueOperations);
    }

    @Test
    public void successResponse() {
	when(valueOperations.get(Mockito.anyString()))
		.thenReturn(LoggedInUserDetails.builder().email("abc@cars24.com").build());
	LoggedInUserDetails userDetails = authHelper.validateUser(accessToken);
	assertNotNull(userDetails);
    }

    @Test
    public void testexception() {
	when(valueOperations.get(Mockito.anyString())).thenThrow(new RuntimeException());
	Assertions.assertThrows(AuthenticationException.class, () -> authHelper.validateUser(null));
    }

}
