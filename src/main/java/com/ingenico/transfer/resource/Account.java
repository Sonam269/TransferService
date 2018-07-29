package com.ingenico.transfer.resource;

import java.math.BigDecimal;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

/**
 * This resource holds minimal set of information for Account entity
 * 
 * @author Sonam Mittal, Date : 29-July-2018, initial commit
 */

@Component

public class Account {

	@Id
	String accountNumber;

	String accountName;

	BigDecimal balance;

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

}
