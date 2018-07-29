package com.ingenico.transfer.processor;

import java.math.BigDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import com.ingenico.transfer.exception.Message;
import com.ingenico.transfer.exception.TransferServiceException;
import com.ingenico.transfer.resource.Transfer;
import com.ingenico.transfer.resource.TransferStatus;

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
		if (accountProcessor.getBalance(transfer.getSourceAccountNumber()).compareTo(amountToBeTransfered) >= 0) {

			accountProcessor.updateBalance(transfer.getDestinationAccountNumber(),
					(accountProcessor.getBalance(transfer.getDestinationAccountNumber()).add(amountToBeTransfered)));
			accountProcessor.updateBalance(transfer.getSourceAccountNumber(),
					(accountProcessor.getBalance(transfer.getSourceAccountNumber()).subtract(amountToBeTransfered)));

		} else {
			logger.error("ERROR OCCURRED :InsufficientBalance");
			throw new TransferServiceException(new Message("INSUFFICIENT_BALANCE",
					"Transaction failed due to insufficient balance", HttpStatus.PAYMENT_REQUIRED));
		}

		transfer.setTransferStatus(TransferStatus.SUCCESS);

		return transfer;
	}
}