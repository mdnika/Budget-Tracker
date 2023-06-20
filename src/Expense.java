public class Expense {

  private String name;
  private double amount;

  public Expense(String name, double amount) {
    this.name = name;
    this.amount = amount;
  }

  public String getName() {
    return name;
  }

  public double getAmount() {
    return amount;
  }

  @Override
  public String toString() {
    return name + " : " + amount;
  }

  public String toCsvString(char sep) {
    return name + sep + amount + '\n';
  }

  public static Expense parseFromCsv(String line, char sep) {
    // line = "Groceries:100.0"
    int sepIndex = line.indexOf(sep);
    String name = line.substring(0, sepIndex); // name = "Groceries"
    // ':' пропускаем, поэтому начинаем с `sepIndex + 1`
    String amountStr = line.substring(sepIndex + 1); // amountStr = "100.0"
    double amount = Double.parseDouble(amountStr);
    return new Expense(name, amount);
  }
}
