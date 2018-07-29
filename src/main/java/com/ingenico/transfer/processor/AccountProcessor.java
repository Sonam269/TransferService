package com.ingenico.transfer.processor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import com.ingenico.transfer.exception.Message;
import com.ingenico.transfer.exception.TransferServiceException;
import com.ingenico.transfer.interfaces.AccountRepository;
import com.ingenico.transfer.resource.Account;

/**
 * This processor is used to process any actions on resource Account
 * 
 * @author Sonam Mittal,Date : 29-July-2018, initial commit
 */
@Component
public class AccountProcessor {

	@Autowired
	AccountRepository repository;

	/**
	 * 
	 * @param account
	 *            neccessary attributes to create an account
	 * @return Account successfully created account
	 */
	public Account createAccount(Account account) {
		repository.save(account);
		return account;

	}

	/**
	 * This method returns balance of an account
	 * 
	 * @param accountNumber
	 * @return BigDecimal balance related to account
	 * @throws TransferServiceException
	 */
	public BigDecimal getBalance(String accountNumber) throws TransferServiceException {

		Account acc = repository.findByAccountNumber(accountNumber);
		Optional.ofNullable(acc).orElseThrow(() -> new TransferServiceException(
				new Message("MESSAGE_1001", "User not Found", HttpStatus.NOT_FOUND)));
		return acc.getBalance();
	}

	/**
	 * This method updates balance after transfer
	 * 
	 * @param accountNumber
	 * @return BigDecimal balance related to account
	 * @throws TransferServiceException
	 */
	public void updateBalance(String accountNumber, BigDecimal balance) throws TransferServiceException {
		Account acc = repository.findByAccountNumber(accountNumber);
		Optional.ofNullable(acc).orElseThrow(() -> new TransferServiceException(
				new Message("MESSAGE_1002", "User not Found", HttpStatus.NOT_FOUND)));
		acc.setBalance(balance);
		repository.save(acc);
	}

	/**
	 * 
	 * @return List<Account> returns all the accounts in the db
	 */
	public List<Account> getAccounts() {
		return repository.findAll();

	}

}
