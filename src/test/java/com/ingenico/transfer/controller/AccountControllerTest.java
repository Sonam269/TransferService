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
import com.ingenico.transfer.processor.AccountProcessor;
import com.ingenico.transfer.resource.Account;

/**
 * @author Sonam Mittal ,Date : 29-July-2018, initial commit
 */
@RunWith(MockitoJUnitRunner.class)
public class AccountControllerTest {

	@Mock
	AccountProcessor accountProcessor;

	@InjectMocks
	AccountController accountController;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test_createAccount_success() {
		Account account = new Account();
		account.setAccountName("user1");
		account.setAccountNumber("6809");
		account.setBalance(new BigDecimal("900"));
		when(accountProcessor.createAccount(account)).thenReturn(account);
		assertEquals(account, accountController.createAccount(account).getBody());

	}

	@Test
	public void test_createAccount_accountNameNull() throws TransferServiceException {
		Account account = new Account();

		account.setAccountNumber("6809");
		account.setBalance(new BigDecimal("900"));
		assertEquals(HttpStatus.BAD_REQUEST, accountController.createAccount(account).getStatusCode());

	}

	public void test_createAccount_accountBalanceNull() throws TransferServiceException {
		Account account = new Account();
		account.setAccountName("user1");
		account.setAccountNumber("6809");
		assertEquals(HttpStatus.BAD_REQUEST, accountController.createAccount(account).getStatusCode());

	}

}
