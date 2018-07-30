/**
 * 
 */
package com.ingenico.transfer.processor;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import java.math.BigDecimal;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ingenico.transfer.exception.TransferServiceException;
import com.ingenico.transfer.resource.Transfer;
import com.ingenico.transfer.resource.TransferStatus;

/**
 * @author Sonam Mittal ,Date : 29-July-2018, initial commit
 */

@RunWith(MockitoJUnitRunner.class)
public class TransferProcessorTest {

	@Mock
	AccountProcessor accountProcessor;

	@InjectMocks
	TransferProcessor transferProcessor;

	Transfer transfer = new Transfer();

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {

		Logger root = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
		String fromAcc = "6789";
		String toAcc = "6478";
		transfer.setAmount(new BigDecimal("67"));
		transfer.setDestinationAccountNumber(fromAcc);
		transfer.setSourceAccountNumber(toAcc);
		transfer.setTransferStatus(TransferStatus.SUCCESS);

	}

	
	@Test(expected = TransferServiceException.class)
	public void test_transferAmountInsufficientBalance() throws TransferServiceException {
		when(accountProcessor.getBalance(transfer.getSourceAccountNumber())).thenReturn(new BigDecimal("7"));
		assertEquals(TransferStatus.SUCCESS, transferProcessor.transferAmount(transfer).getTransferStatus());

	}

}
