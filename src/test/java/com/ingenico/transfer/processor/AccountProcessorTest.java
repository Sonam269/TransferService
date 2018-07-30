
package com.ingenico.transfer.processor;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import com.ingenico.transfer.exception.TransferServiceException;
import com.ingenico.transfer.interfaces.AccountRepository;
import com.ingenico.transfer.resource.Account;

/**
 * @author Sonam Mittal ,Date : 29-July-2018, initial commit
 */
@RunWith(MockitoJUnitRunner.class)
public class AccountProcessorTest {

	@Mock
	AccountRepository accountRepository;

	@InjectMocks
	AccountProcessor accountProcessor;

	Account account = new Account();

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		String accNo = "8975";
		account.setAccountName("User1");
		account.setAccountNumber(accNo);
		account.setBalance(new BigDecimal("89"));
	}

	@Test
	public void test_createAccount_success() {

		when(accountRepository.save(account)).thenReturn(null);
		assertEquals(account, accountProcessor.createAccount(account));
	}

	@Test
	public void test_getBalance() throws TransferServiceException {

		when(accountRepository.findByAccountNumber(account.getAccountNumber())).thenReturn(account);
		assertEquals(new BigDecimal(89), accountProcessor.getBalance(account.getAccountNumber()));
	}

	@Test(expected = TransferServiceException.class)
	public void test_getBalance_accountNotFound() throws TransferServiceException {

		assertEquals(new BigDecimal(89), accountProcessor.getBalance(account.getAccountNumber()));
	}

	@Test
	public void test_updateAccount() throws TransferServiceException {
		when(accountRepository.findByAccountNumber("689")).thenReturn(account);
		account.setBalance(new BigDecimal("80"));
		assertEquals(new BigDecimal(80), accountProcessor.getBalance("689"));

	}

	@Test
	public void test_getAccounts() throws TransferServiceException {
		List<Account> accountList = new ArrayList<>();
		accountList.add(account);
		when(accountRepository.findAll()).thenReturn(accountList);
		assertEquals(1, accountProcessor.getAccounts().size());

	}
	

}
