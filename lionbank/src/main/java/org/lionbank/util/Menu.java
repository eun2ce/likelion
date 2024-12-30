package org.lionbank.util;

import java.util.Arrays;

public enum Menu {
  CREATE_USER(1),
  CREATE_ACCOUNT(2),
  DISPLAY_ACCOUNTS(3),
  DEPOSIT_AMOUNT(4),
  WITHDRAW_AMOUNT(5),
  DISPLAY_ACCOUNT_BALANCE(6),
  EXIT(7);

  private final int value;

  Menu(int value) {
    this.value = value;
  }

  public static Menu findNameByValue(int value) {
    return Arrays.stream(values()).filter(menu -> menu.value == value).findFirst()
        .orElseThrow(IllegalAccessError::new);
  }
}
