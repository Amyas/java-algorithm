public class Main {
  /** 斐波那契数列O(2n) */
  public static int fib1(int n) {
    if (n <= 1) return n;
    int n1 = fib1(n - 1);
    int n2 = fib1(n - 2);
    return n1 + n2;
  }

  /** 斐波那契数列for O(n) */
  public static int fib2(int n) {
    if (n <= 1) return n;
    int first = 0;
    int second = 1;
    for (int i = 0; i < n - 1; i++) {
      int sum = first + second;
      first = second;
      second = sum;
    }
    return second;
  }

  /** 斐波那契数列while O(n) */
  public static int fib3(int n) {
    if (n <= 1) return n;
    int first = 0;
    int second = 1;
    while (n-- > 1) {
      second += first;
      first = second - first;
    }
    return second;
  }

  public static void main(String[] args) {
    // System.out.println(fib1(64));
    // System.out.println(fib2(64));
    TimeTool.check(
        "fib1",
        new TimeTool.Task() {
          public void execute() {
            System.out.println(fib1(45));
          }
        });
    TimeTool.check(
        "fib2",
        new TimeTool.Task() {
          public void execute() {
            System.out.println(fib2(45));
          }
        });
    TimeTool.check(
        "fib3",
        new TimeTool.Task() {
          public void execute() {
            System.out.println(fib3(45));
          }
        });
  }
}
