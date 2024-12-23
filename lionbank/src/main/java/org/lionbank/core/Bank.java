package org.lionbank.core;

import com.lionbank.exception.BankOperationException;
import com.lionbank.exception.InvalidTransactionException;
import java.sql.SQLException;
import java.util.List;
import javax.security.auth.login.AccountNotFoundException;
import org.lionbank.db.DatabaseManager;

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
  }

  public void deposit(String accountId, double amount)
      throws InvalidTransactionException, SQLException, AccountNotFoundException {
    if (amount <= 0) {
      throw new InvalidTransactionException("Deposit amount must be greater than zero.");
    }
    dbManager.updateBalance(accountId, amount);
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
  }

  public double checkBalance(String accountId) throws AccountNotFoundException, SQLException {
    return dbManager.getBalance(accountId);
  }

  public List<String> getAccounts(String customerId)
      throws SQLException, AccountNotFoundException {
    return dbManager.getAccounts(customerId);
  }
}