package com.ingenico.transfer.exception;

import org.springframework.stereotype.Component;

/**
 * Custom exception for transfer service
 * 
 * @author Sonam Mittal, Date : 29-July-2018, initial commit
 *
 */
@Component
public class TransferServiceException extends Exception {

	private static final long serialVersionUID = 1L;

	private Message errorMessage;

	public TransferServiceException(String message) {
		super(message);
	}

	public TransferServiceException(Message message) {
		super(message.getMessageKey());
		this.errorMessage = message;
	}

	public TransferServiceException() {
		super();
	}

	public Message getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(Message errorMessage) {
		this.errorMessage = errorMessage;
	}

}
