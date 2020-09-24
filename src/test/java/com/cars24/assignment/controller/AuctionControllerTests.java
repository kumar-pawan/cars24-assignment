package com.cars24.assignment.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cars24.assignment.model.AuctionResponse;
import com.cars24.assignment.service.AuctionService;
import com.cars24.assignment.validation.Validations;

public class AuctionControllerTests {

    @Mock
    private AuctionService auctionService;
    @Mock
    private Validations validations;

    private AuctionController controller;

    @BeforeEach
    public void init() {
	MockitoAnnotations.initMocks(this);
	controller = new AuctionController(auctionService, validations);
	doNothing().when(validations).checkAuthentication(Mockito.anyString());
	when(auctionService.getAuctionByStatus(Mockito.anyString())).thenReturn(new ArrayList<AuctionResponse>());
    }

    @Test
    public void getAuctions() {
	ResponseEntity<List<AuctionResponse>> responseEntity = controller.getAuctions("Running",
		"sdfsafdasfasdfasfrasdfafdsf");

	assertNotNull(responseEntity);
	assertNotNull(responseEntity.getBody());
	assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }
}
