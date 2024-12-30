import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

enum Menu {
  ONE(1), TWO(2), THREE(3);

  private final int value;

  Menu(int value) {
    this.value = value;
  }

  public static Menu findByValue(int value) {
    return Arrays.stream(values()).filter(menu -> (menu.value == value)).findFirst()
        .orElseThrow(IllegalAccessError::new);
  }
}

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    Menu menu = Menu.findByValue(Integer.parseInt(br.readLine()));

    switch (menu) {
      case ONE:
        System.out.println("one");
        break;
      case TWO:
        System.out.println("two");
        break;
      case THREE:
        System.out.println("three");
        break;
    }
  }
}