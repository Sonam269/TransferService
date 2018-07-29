package com.ingenico.transfer.resource;

/**
 * This resource holds status for payment transfer, mainly for future use to
 * track progress of transfer
 * 
 * @author Sonam Mittal,Date : 29-July-2018, initial commit
 */
public enum TransferStatus {

	SUCCESS("SUCCESS"), REJECTED("REJECTED");

	TransferStatus(String status) {

	}

}
