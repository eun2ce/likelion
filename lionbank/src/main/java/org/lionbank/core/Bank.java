package org.lionbank.core;

import com.lionbank.exception.BankOperationException;
import com.lionbank.exception.InvalidTransactionException;
import java.sql.SQLException;
import java.util.List;
import javax.security.auth.login.AccountNotFoundException;
import org.lionbank.db.DatabaseManager;
import org.lionbank.util.FileReaderTask;

public class Bank {

  private final DatabaseManager dbManager;

  public Bank(DatabaseManager dbManager) {
    this.dbManager = dbManager;
  }

  public void registerCustomer(String id, String name) throws BankOperationException, SQLException {
    if (dbManager.isCustomerExists(id)) {
      throw new BankOperationException("Customer ID already exists.");
    }
    dbManager.addCustomer(id, name);

    // 로그 기록
    FileReaderTask logTask = new FileReaderTask("고객 등록: ID = " + id + ", 이름 = " + name);
    logTask.start();
  }

  public void createAccount(String customerId, String accountId)
      throws BankOperationException, SQLException {
    if (!dbManager.isCustomerExists(customerId)) {
      throw new BankOperationException("Customer not found.");
    }
    if (dbManager.getAccountCountByCustomer(customerId) >= 5) {
      throw new BankOperationException("Maximum 5 accounts allowed per customer.");
    }
    dbManager.addAccount(customerId, accountId);

    // 로그 기록
    FileReaderTask logTask = new FileReaderTask("계좌 생성: 고객 ID = " + customerId + ", 계좌 번호 = " + accountId);
    logTask.start();
  }

  public void deposit(String accountId, double amount)
      throws InvalidTransactionException, SQLException, AccountNotFoundException {
    if (amount <= 0) {
      throw new InvalidTransactionException("Deposit amount must be greater than zero.");
    }
    dbManager.updateBalance(accountId, amount);

    // 로그 기록
    FileReaderTask logTask = new FileReaderTask("입금: 계좌 번호 = " + accountId + ", 금액 = " + amount);
    logTask.start();
  }

  public void withdraw(String accountId, double amount)
      throws InvalidTransactionException, AccountNotFoundException, SQLException {
    double currentBalance = dbManager.getBalance(accountId);
    if (amount <= 0) {
      throw new InvalidTransactionException("Withdrawal amount must be greater than zero.");
    }
    if (currentBalance < amount) {
      throw new InvalidTransactionException("Insufficient funds.");
    }
    dbManager.updateBalance(accountId, -amount);

    // 로그 기록
    FileReaderTask logTask = new FileReaderTask("출금: 계좌 번호 = " + accountId + ", 금액 = " + amount);
    logTask.start();
  }

  public double checkBalance(String accountId) throws AccountNotFoundException, SQLException {
    return dbManager.getBalance(accountId);
  }

  public List<String> getAccounts(String customerId)
      throws SQLException, AccountNotFoundException {
    return dbManager.getAccounts(customerId);
  }
}