enum Day {
  MON(1), TUE(2), WED(3), THU(4), FRI(5), SAT(6), SUN(7);

  private final int value;

  private Day(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }
}

public class Main {

  public static void main(String[] args) {
    Day d = Day.MON;
    System.out.println(d.getValue());
  }
}