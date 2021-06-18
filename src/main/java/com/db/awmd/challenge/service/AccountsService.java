package com.db.awmd.challenge.service;

import com.db.awmd.challenge.domain.Account;
import com.db.awmd.challenge.domain.Transaction;
import com.db.awmd.challenge.domain.TransferBalanceRequest;
import com.db.awmd.challenge.repository.AccountsRepository;
import com.db.awmd.challenge.repository.TransactionRepository;
import lombok.Getter;
import java.math.BigDecimal;
import java.sql.Timestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountsService {

  @Getter
  private final AccountsRepository accountsRepository;
  
	/*
	 * @Getter private final TransactionRepository transactionRepository;
	 */

  @Autowired
  public AccountsService(AccountsRepository accountsRepository) {
    this.accountsRepository = accountsRepository;
	//this.transactionRepository = transactionRepository;
  }

  public void createAccount(Account account) {
    this.accountsRepository.createAccount(account);
  }

  public Account getAccount(String accountId) {
    return this.accountsRepository.getAccount(accountId);
  }

  
  public Account save(Account account){
	  accountsRepository.save(account);
      return accountsRepository.findByAccountNumberEquals(account.getAccountId());
  }
  

  
  public Account findByAccountNumber(String accountNumber){
      Account account = accountsRepository.findByAccountNumberEquals(accountNumber);
      return account;
  }
  
 
  public BigDecimal transferAccount(TransferBalanceRequest request) {
		
  System.out.println("in service");
  String fromAccountNumber = request.getFromAccountNumber();
  String toAccountNumber = request.getToAccountNumber();
  BigDecimal amount = request.getAmount();
  Account fromAccount = accountsRepository.findByAccountNumberEquals(
          fromAccountNumber
  );
  Account toAccount = accountsRepository.findByAccountNumberEquals(toAccountNumber);
 
	  if(fromAccount.getBalance().compareTo(request.getAmount()) > 0 ) 
	  { 
		  BigDecimal fromupdateBalance, toUpdateBalance; 
		  fromupdateBalance = fromAccount.getBalance().subtract(request.getAmount());
		  System.out.println("account service"); toUpdateBalance=
				  toAccount.getBalance().add(request.getAmount());
		  fromAccount.setBalance(fromupdateBalance);
		  toAccount.setBalance(toUpdateBalance);
	  }
	 
else {
	System.out.println("Transferring account balance is insufficient to transfer funds.");
	  }
	return amount;
	  }

	
}

