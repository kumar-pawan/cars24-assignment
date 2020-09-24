package com.cars24.assignment.validation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.redis.core.ValueOperations;

import com.cars24.assignment.exception.AuthenticationException;
import com.cars24.assignment.model.LoggedInUserDetails;
import com.cars24.assignment.utils.AuthHelper;

public class ValidationsTests {

    @Mock
    private AuthHelper authHelper;

    @Mock
    private ValueOperations<String, Object> valueOperations;

    private Validations validations;

    private final String accessToken = "sdfasdfadsfsdafasdfasd";
    private final String email = "abc@cars24.com";

    @BeforeEach
    public void init() {
	MockitoAnnotations.initMocks(this);
	validations = new Validations(authHelper);
    }

    @Test
    public void checkAuthenticationEmptyToken() {

	Assertions.assertThrows(AuthenticationException.class, () -> validations.checkAuthentication(null));

    }

    @Test
    public void validationNoValuesInRedis() {
	Mockito.when(authHelper.validateUser(Mockito.anyString())).thenReturn(null);
	Assertions.assertThrows(AuthenticationException.class, () -> validations.checkAuthentication(accessToken));
    }

    @Test
    public void successTest() {
	Mockito.when(authHelper.validateUser(Mockito.anyString())).thenReturn(LoggedInUserDetails.builder().email(email).contact(1234567890).build());
	validations.checkAuthentication(accessToken);
    }
}
