import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExpenseList {

  private final List<Expense> expenses;
  private final File file;
  private final char sep;
  private double existBudget;

  public ExpenseList(String fileName, char sep) {
    file = new File(fileName);
    this.sep = sep;
    expenses = readFromCsv();
  }
  /**
   * user input his/her budget
   * @param scanner to read user input
   * @throws ParseException in case of unparseable input-data
   */
  public void inputUserBudget(Scanner scanner) throws ParseException {
    System.out.print("Input your budget: ");
    existBudget = scanner.nextDouble();
    scanner.nextLine();
  }
  /**
   * the writeToCsv() method writes the data of each Expense object in the expenses collection to the specified CSV file
   * @throws IOException in case there is an error during the file writing process
   */
  public void writeToCsv() throws IOException {
    FileWriter fileWriter = new FileWriter(file);
    for (Expense expense : expenses) {
      fileWriter.write(expense.toCsvString(sep));
    }
    fileWriter.close();
  }
  /**
   * the readFromCsv() method reads data from a CSV file, parses each line into an Expense object,
   * and returns a list of these objects. Also it handles various exceptions and ignores lines that cannot be parsed correctly
   * @return the result list, which contains the parsed Expense objects from the CSV file
   */
  private List<Expense> readFromCsv() {
    List<Expense> result = new ArrayList<>();
    if (!file.exists() || !file.canRead()) {
      return result; // если файла нет или его нельзя читать
    }
    try { // try-catch с ignored, потому что мы заранее всё проверили
      Scanner scanner = new Scanner(file);
      while (scanner.hasNext()) {
        String line = scanner.nextLine();
        try {
          Expense expense = Expense.parseFromCsv(line, sep);
          result.add(expense);
        } catch (IndexOutOfBoundsException | NumberFormatException ignored) {
        }
      }
      scanner.close();
    } catch (FileNotFoundException ignored) {
    }
    return result;
  }
  /**
   * the print() method prints the details of each expense in the expenses collection, followed by the total budget.
   */
  public void print() {
    for (Expense expense : expenses) {
      System.out.println(expense);
    }
    System.out.println("Total budget: " + existBudget);
  }
  /**
   * the given method allows the user to input a new expense and adds it to the expenses list
   * @param scanner allows the method to read user input or data from keyboard
   * @throws ParseException throws the exceptions that may occur during user input, for example,
   * parsing a value, such as when converting a string to a numerical value
   */
  public void addUserExpense(Scanner scanner) throws ParseException {
    System.out.print("Input the name of your extra expense: ");
    String name = scanner.next();
    while (name.isEmpty()) {
      System.out.println("The name can't be empty. Please, try again!");
      System.out.print("Input name: ");
      name = scanner.nextLine();
    }
    System.out.print("Input your extra expense amount: ");
    double amount = scanner.nextDouble();
    scanner.nextLine();
    expenses.add(new Expense(name, amount));
  }
  /**
   * the getTotalExpenses() method accumulates the total amount in the total variable, after iterating through all the expenses
   * @return the final value of the total variable, which represents the total amount of expenses
   */
  public double getTotalExpenses() {
    double total = 0;
    for (Expense expense : expenses) {
      total += expense.getAmount();
    }
    return total;
  }
  /**
   * the differenceBudgetExpenses() method calculates the difference between the existing budget
   * and the total expenses, and then prints the result to the console
   */
  public void differenceBudgetExpenses() {
    double totalExpenses = getTotalExpenses();
    System.out.println("Total expenses: " + totalExpenses + " EUR");
    if (existBudget < totalExpenses) {
      System.out.println(
          "NOTE: You are out of budget with: " + (existBudget - totalExpenses) + " EUR");
    } else {
      System.out.println("You have in budget: " + (existBudget - totalExpenses) + " EUR");
    }
  }
  /**
   * the given method takes a Scanner object as a parameter and returns a string indicating the result of
   * the deletion operation
   * @param scanner read user input or data from an input source (keyboard)
   * @return the result of deleting
   */
  public String deleteExpense(Scanner scanner) {
    System.out.print("Input an expense to delete: ");
    String name = scanner.next();
    for (int i = 0; i < expenses.size(); i++) {
      if (name.equals(expenses.get(i).getName())) {
        // System.out.println("To delete: " + expenses.get(i).getName());
        return expenses.remove(i).getName();
      }
    }
    return "Expense not found.";
  }
  /**
   * the addExpense method is responsible for adding an Expense object to the expenses list.
   * @param expense refers to the Expense object
   */
  public void addExpense(Expense expense) {
    expenses.add(expense);
  }
}
