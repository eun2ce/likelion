package org.lionbank;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;
import org.lionbank.core.Bank;
import org.lionbank.db.DatabaseManager;

public class Eun2ceLionBankApplication {

  // 명령상수
  private static final int CREATE_USER = 1;
  private static final int CREATE_ACCOUNT = 2;
  private static final int DISPLAY_ACCOUNTS = 3;
  private static final int DEPOSIT_AMOUNT = 4;
  private static final int WITHDRAW_AMOUNT = 5;
  private static final int DISPLAY_ACCOUNT_BALANCE = 6;
  private static final int EXIT = 7;

  public static void main(String[] args) {
    try {
      DatabaseManager dbManager = new DatabaseManager(
          "jdbc:mysql://localhost:3306/lionbank_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",
          "root", "root");
      Bank bank = new Bank(dbManager);

      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      while (true) {
        System.out.println("=== 라이온 은행 시스템 ===");
        System.out.println("1. 고객 등록");
        System.out.println("2. 계좌 생성");
        System.out.println("3. 계좌 조회");
        System.out.println("4. 입금");
        System.out.println("5. 출금");
        System.out.println("6. 잔액 조회");
        System.out.println("7. 종료");
        System.out.print("선택: ");

        int choice = Integer.parseInt(br.readLine());

        switch (choice) {
          case CREATE_USER:
            System.out.print("고객 ID: ");
            String customerId = br.readLine();
            System.out.print("고객 이름: ");
            String name = br.readLine();
            bank.registerCustomer(customerId, name);
            System.out.println("고객이 등록되었습니다.");
            break;
          case CREATE_ACCOUNT:
            System.out.print("고객 ID: ");
            customerId = br.readLine();
            System.out.print("계좌 번호: ");
            String accountId = br.readLine();
            bank.createAccount(customerId, accountId);
            System.out.println("계좌가 생성되었습니다.");
            break;
          case DISPLAY_ACCOUNTS:
            System.out.print("고객 ID: ");
            customerId = br.readLine();
            List<String> accounts = bank.getAccounts(customerId);
            System.out.println(accounts.toString());
            break;
          case DEPOSIT_AMOUNT:
            System.out.print("계좌 번호: ");
            accountId = br.readLine();
            System.out.print("입금 금액: ");
            double depositAmount = Double.parseDouble(br.readLine());
            bank.deposit(accountId, depositAmount);
            System.out.println("입금이 완료되었습니다.");
            break;
          case WITHDRAW_AMOUNT:
            System.out.print("계좌 번호: ");
            accountId = br.readLine();
            System.out.print("출금 금액: ");
            double withdrawAmount = Double.parseDouble(br.readLine());
            bank.withdraw(accountId, withdrawAmount);
            System.out.println("출금이 완료되었습니다.");
            break;
          case DISPLAY_ACCOUNT_BALANCE:
            System.out.print("계좌 번호: ");
            accountId = br.readLine();
            double balance = bank.checkBalance(accountId);
            System.out.printf("현재 잔액: %.2f\n", balance);
            break;
          case EXIT:
            System.out.println("프로그램을 종료합니다.");
            return;
          default:
            System.out.println("잘못된 입력입니다.");
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}