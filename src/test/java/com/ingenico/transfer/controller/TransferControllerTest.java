package com.ingenico.transfer.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import com.ingenico.transfer.exception.TransferServiceException;
import com.ingenico.transfer.processor.TransferProcessor;
import com.ingenico.transfer.resource.Transfer;

@RunWith(MockitoJUnitRunner.class)
public class TransferControllerTest {

	@Mock
	TransferProcessor transferProcessor;

	@InjectMocks
	TransferController transferController;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() throws TransferServiceException {
		Transfer transfer = new Transfer();
		transfer.setAmount(new BigDecimal("700"));
		transfer.setSourceAccountNumber("NLANBAN67");
		transfer.setDestinationAccountNumber("NLRABO9087");
		when(transferProcessor.transferAmount(transfer)).thenReturn(transfer);
		assertEquals(transfer, transferController.transferAccount(transfer).getBody());
	}
   
	@Test
	public void test_sourceAccountNull() throws TransferServiceException {
		Transfer transfer = new Transfer();
		transfer.setAmount(new BigDecimal("700"));
		transfer.setDestinationAccountNumber("NLRABO9087");
		assertEquals(HttpStatus.BAD_REQUEST, transferController.transferAccount(transfer).getStatusCode());
	}

	@Test
	public void test_destinationAccountNull() throws TransferServiceException {
		Transfer transfer = new Transfer();
		transfer.setAmount(new BigDecimal("700"));
		transfer.setSourceAccountNumber("NLANBAN67");
		assertEquals(HttpStatus.BAD_REQUEST, transferController.transferAccount(transfer).getStatusCode());
	}

}
