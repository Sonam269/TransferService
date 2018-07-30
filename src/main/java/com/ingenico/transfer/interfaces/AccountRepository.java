package com.ingenico.transfer.interfaces;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ingenico.transfer.resource.Account;

/**
 * Mongo DB is used as a database
 * 
 * @author Sonam Mittal, Date : 29-July-2018, initial commit
 */
@Component
@Transactional
public interface AccountRepository extends MongoRepository<Account, String> {

	public Account findByAccountNumber(String acountNumber);

	

}
