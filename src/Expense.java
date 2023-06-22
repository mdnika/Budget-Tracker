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

  public void setName(String name) {
    this.name = name;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  @Override
  public String toString() {
    return name + " : " + amount;
  }
  /**
   * the method takes a sep character as a parameter and returns a formatted string representing
   * the object's data in CSV (Comma-Separated Values) format.
   * @param sep breaks the line of text
   * @return the resulting String
   */
  public String toCsvString(char sep) {
    return name + sep + amount + '\n';
  }
  /**
   * the method parses the given line and extract the necessary data to create an Expense object.
   * @param line represents a text in File CSV
   * @param sep separator charater
   * @return the Expsense object
   */
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
