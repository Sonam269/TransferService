package com.ingenico.transfer.processor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ingenico.transfer.exception.Message;
import com.ingenico.transfer.exception.TransferServiceConstants;
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
		isAccountValid(acc);
		return acc.getBalance();
	}

	/**
	 * This method updates accounts after transfer
	 * 
	 * @param accountNumber
	 * @return BigDecimal balance related to account
	 * @throws TransferServiceException
	 */
	
	
	public void updateAccounts(String sourceAccountNumber, BigDecimal transferedAmount, String destinationAccount ) throws TransferServiceException {
		
		List<Account> accountList = new ArrayList<>();
		
		accountList.add(debitAmount(sourceAccountNumber,transferedAmount));
		accountList.add(creditAmount(destinationAccount, transferedAmount));
		repository.saveAll(accountList);
	
	}
	
	/**
	 * This method debit the amount to be transfered from source account
	 * @param accountNumber
	 * @param amt
	 * @return
	 * @throws TransferServiceException
	 */
	private Account debitAmount(String accountNumber, BigDecimal amt) throws TransferServiceException
	{
		Account account = repository.findByAccountNumber(accountNumber);
		isAccountValid(account);
		account.setBalance(getBalance(accountNumber).subtract(amt));
		return account;
	}
	
	/**
	 * This method credit the amount  transfered from destination account
	 * @param accountNumber
	 * @param amt
	 * @return
	 * @throws TransferServiceException
	 */
	private Account creditAmount(String accountNumber, BigDecimal amt) throws TransferServiceException
	{
		Account account = repository.findByAccountNumber(accountNumber);
		isAccountValid(account);
		account.setBalance(getBalance(accountNumber).add(amt));
		return account;
	}

	/**
	 * 
	 * @return List<Account> returns all the accounts in the db
	 */
	public List<Account> getAccounts() {
		return repository.findAll();

	}

	/**
	 * @param acc
	 * @throws TransferServiceException
	 */
	private void isAccountValid(Account acc) throws TransferServiceException {
		Optional.ofNullable(acc).orElseThrow(
				() -> new TransferServiceException(new Message(TransferServiceConstants.ERROR_USER_NOT_FOUND,
						TransferServiceConstants.MSG_USER_NOT_FOUND, HttpStatus.NOT_FOUND)));
	}

}
