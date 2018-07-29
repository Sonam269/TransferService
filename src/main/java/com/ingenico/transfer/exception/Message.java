package com.ingenico.transfer.exception;

import org.springframework.http.HttpStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Thos class encapsulates details while an exception is thrown
 * 
 * @author Sonam Mittal, Date : 29-July-2018, initial commit
 *
 */
@JsonIgnoreProperties(value = { "code" })
public class Message {

	private String messageKey;
	private String messageText;
	private HttpStatus code;

	/**
	 * default constructor
	 */
	public Message() {

	}

	/**
	 * 
	 * @param messageKey
	 * @param messageText
	 * @param code
	 */
	public Message(String messageKey, String messageText, HttpStatus code) {
		this.messageKey = messageKey;
		this.messageText = messageText;
		this.code = code;
	}

	/**
	 * 
	 * @param messageKey
	 *            message key
	 */
	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}

	/**
	 * 
	 * @return String message information
	 */
	public String getMessageText() {
		return messageText;
	}

	/**
	 * 
	 * @param messageText
	 */
	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	/**
	 * 
	 * @return
	 */
	public String getMessageKey() {
		return messageKey;
	}

	/**
	 * 
	 * @return HttpStatus http status code
	 */
	public HttpStatus getCode() {
		return code;
	}

	/**
	 * 
	 * @param code
	 *            http status code
	 */
	public void setCode(HttpStatus code) {
		this.code = code;
	}

}
