package com.ingenico.transfer.processor;

import java.math.BigDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import com.ingenico.transfer.exception.Message;
import com.ingenico.transfer.exception.TransferServiceConstants;
import com.ingenico.transfer.exception.TransferServiceException;
import com.ingenico.transfer.resource.Transfer;
import com.ingenico.transfer.resource.TransferStatus;
import com.mongodb.Mongo;

/**
 * This processor is used to process any actions on resource Transfer
 * 
 * @author Sonam Mittal, Date : 29-July-2018, initial commit
 */

@Component
public class TransferProcessor {

	Logger logger = LoggerFactory.getLogger(TransferProcessor.class);

	@Autowired
	AccountProcessor accountProcessor;

	/**
	 * This method transfer money from one account to another
	 * 
	 * @param transfer
	 * @return Transfer with status
	 * @throws TransferServiceException
	 */
	public Transfer transferAmount(Transfer transfer) throws TransferServiceException {
		logger.debug("Start of transfer");

		BigDecimal amountToBeTransfered = transfer.getAmount();
		String sourceAccount = transfer.getSourceAccountNumber();
		if (accountProcessor.getBalance(sourceAccount).compareTo(amountToBeTransfered) >= 0) {

			accountProcessor.updateAccounts(sourceAccount, amountToBeTransfered,
					transfer.getDestinationAccountNumber());
			transfer.setTransferStatus(TransferStatus.SUCCESS);

		} else {
			logger.error(TransferServiceConstants.LOG_INSUFFICIENT_BALANCE);
			throw new TransferServiceException(new Message(TransferServiceConstants.ERROR_INSUFFICIENT_BALANCE,
					TransferServiceConstants.MSG_INSUFFICIENT_BALANCE, HttpStatus.PAYMENT_REQUIRED));
		}

		return transfer;
	}
}