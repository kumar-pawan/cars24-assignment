package com.cars24.assignment.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cars24.assignment.model.BidRequest;
import com.cars24.assignment.model.BidResponse;
import com.cars24.assignment.service.BiddingService;
import com.cars24.assignment.validation.Validations;

public class BiddingControllerTests {

    @Mock
    private BiddingService biddingService;
    @Mock
    private Validations validations;
    private BiddingController controller;

    @BeforeEach
    public void init() {
	MockitoAnnotations.initMocks(this);
	controller = new BiddingController(biddingService, validations);
    }

    @Test
    public void test() {
	doNothing().when(validations).checkAuthentication(Mockito.anyString());
	Mockito.when(biddingService.raiseBid(Mockito.any(), Mockito.anyString()))
		.thenReturn(BidResponse.builder().bidStatus("Accepted").build());

	ResponseEntity<BidResponse> responseEntity = controller.raiseBid(new BidRequest(), 1, "sdfadsfasdfasdfasdfas");
	assertNotNull(responseEntity);
	assertNotNull(responseEntity.getBody());
	assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }
}
