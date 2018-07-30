package com.ingenico.transfer.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.ingenico.transfer.exception.Message;
import com.ingenico.transfer.exception.TransferServiceConstants;
import com.ingenico.transfer.exception.TransferServiceException;
import com.ingenico.transfer.processor.AccountProcessor;
import com.ingenico.transfer.resource.Account;

/**
 * This controller is responsible for performing actions of Account resource
 * 
 * @author Sonam Mittal, Date : 29-July-2018, initial commit
 */

@RestController
public class AccountController {

	@Autowired
	private AccountProcessor accountProcessor;

	/**
	 * This method calls the processor to create account 
	 * @param account: resource holding information for account creation
	 * @return ResponseEntity<?> output containing response body
	 */
	@RequestMapping(method = RequestMethod.POST, path = "/accounts")
	public ResponseEntity<?> createAccount(@RequestBody Account account) {
		Account accountOutput;
		try {
			Optional.ofNullable(account).orElseThrow(() -> new TransferServiceException(
					new Message(TransferServiceConstants.ERROR_NO_INFORMATION_PRESENT,TransferServiceConstants.MSG_NO_INFORMATION_PRESENT, HttpStatus.BAD_REQUEST)));
			Optional.ofNullable(account.getAccountName())
					.orElseThrow(() -> new TransferServiceException(new Message(TransferServiceConstants.ERROR_ACCOUNT_NAME_IS_REQUIRE,TransferServiceConstants.MSG_ACCOUNT_NAME_IS_REQUIRE, HttpStatus.BAD_REQUEST)));
			Optional.ofNullable(account.getBalance()).orElseThrow(() -> new TransferServiceException(
					new Message(TransferServiceConstants.ERROR_BALANCE_IS_REQUIRED, TransferServiceConstants.MSG_BALANCE_IS_REQUIRED,HttpStatus.BAD_REQUEST)));
			accountOutput = accountProcessor.createAccount(account);
		} catch (TransferServiceException exception) {
			return new ResponseEntity<>(exception.getErrorMessage(), exception.getErrorMessage().getCode());
		}

		return new ResponseEntity<>(accountOutput, HttpStatus.CREATED);
	}

	/**
	 * This method returns all the accounts present in the database
	 * 
	 * @return List<Account> list of accounts
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/accounts")
	public List<Account> getAccounts() {
		return accountProcessor.getAccounts();
	}

}
