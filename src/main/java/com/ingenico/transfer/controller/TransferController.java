package com.ingenico.transfer.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.ingenico.transfer.exception.Message;
import com.ingenico.transfer.exception.TransferServiceException;
import com.ingenico.transfer.processor.TransferProcessor;
import com.ingenico.transfer.resource.Transfer;

/**
 * This controller is responsible for performing actions on transfer resource
 * 
 * @author Sonam Mittal, Date : 29-July-2018, initial commit
 */
@RestController
public class TransferController {

	@Autowired
	TransferProcessor transferProcessor;

	/**
	 * This method calls the processor to transfer amount from one account to another
	 * @param transfer resource holding information for transferring amount
	 * @return ResponseEntity<?> output containing response body
	 */
	@RequestMapping(method = RequestMethod.POST, path = "/transfer")
	public ResponseEntity<?> transferAccount(@RequestBody Transfer transfer) {
		Transfer transferOutput;
		try {
			Optional.ofNullable(transfer).orElseThrow(() -> new TransferServiceException(
					new Message("NO_INFORMATION_PRESENT", "No information present", HttpStatus.BAD_REQUEST)));
			Optional.ofNullable(transfer.getDestinationAccountNumber())
					.orElseThrow(() -> new TransferServiceException(new Message("DESTINATION_ACCOUNT_REQUIRED",
							"Destination account must not be null", HttpStatus.BAD_REQUEST)));
			Optional.ofNullable(transfer.getSourceAccountNumber()).orElseThrow(() -> new TransferServiceException(
					new Message("SOURCE_ACCOUNT_REQUIRED", "Source account must not be null", HttpStatus.BAD_REQUEST)));
			transferOutput = transferProcessor.transferAmount(transfer);
		} catch (TransferServiceException transferServiceException) {
			return new ResponseEntity<>(transferServiceException.getErrorMessage(),
					transferServiceException.getErrorMessage().getCode());
		}

		return new ResponseEntity<>(transferOutput, HttpStatus.OK);
	}

}
