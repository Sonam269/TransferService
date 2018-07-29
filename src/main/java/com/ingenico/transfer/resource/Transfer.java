package com.ingenico.transfer.resource;

import java.math.BigDecimal;
import org.springframework.stereotype.Component;

/**
 * This resource holds minimal set of information for transfer entity
 * 
 * @author Sonam Mittal, Date : 29-July-2018, initial commit
 */
@Component
public class Transfer {

	private String sourceAccountNumber;
	private String destinationAccountNumber;
	private BigDecimal amount;
	private TransferStatus transferStatus;

	public String getSourceAccountNumber() {
		return sourceAccountNumber;
	}

	public void setSourceAccountNumber(String sourceAccountNumber) {
		this.sourceAccountNumber = sourceAccountNumber;
	}

	public String getDestinationAccountNumber() {
		return destinationAccountNumber;
	}

	public void setDestinationAccountNumber(String destinationAccountNumber) {
		this.destinationAccountNumber = destinationAccountNumber;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public TransferStatus getTransferStatus() {
		return transferStatus;
	}

	public void setTransferStatus(TransferStatus transferStatus) {
		this.transferStatus = transferStatus;
	}

}
