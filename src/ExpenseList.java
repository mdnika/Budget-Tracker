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
  public void inputUserBudget(Scanner scanner) throws ParseException {
    System.out.print("Input your budget: ");
    existBudget = scanner.nextDouble();
    scanner.nextLine();
  }
  public void writeToCsv() throws IOException {
    FileWriter fileWriter = new FileWriter(file);
    for (Expense expense : expenses) {
      fileWriter.write(expense.toCsvString(sep));
    }
    fileWriter.close();
  }

  private List<Expense> readFromCsv() {
    List<Expense> result = new ArrayList<>();
    if (!file.exists() || !file.canRead()) {
      return result; // если файла нет или его нельзя читать
    }
    try { // try-catch с ignored, потому что мы заранее всё проверили
      Scanner scanner = new Scanner(file); // сканер для чтения из файла
      while (scanner.hasNext()) {
        String line = scanner.nextLine(); // читаем строку из файла
        try {
          Expense expense = Expense.parseFromCsv(line, sep); // получаем expense из строки
          result.add(expense); // добавляем expense в список
        } catch (IndexOutOfBoundsException | NumberFormatException ignored) {
        } // не получилось прочитать строку
      }
      scanner.close();
    } catch (FileNotFoundException ignored) {
    }
    return result;
  }

  public void print() {
    for (Expense expense : expenses) {
      System.out.println(expense);
    }
    System.out.println("Total budget: " + existBudget);
  }

  public void addUserExpense(Scanner scanner) throws ParseException {
    System.out.print("Input the name of your extra expense: ");
    String name = scanner.nextLine();
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
  public double getTotalExpenses() {
    double total = 0;
    for (Expense expense : expenses) {
      total += expense.getAmount();
    }
    return total;
  }
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


  public void addExpense(Expense expense) {
    expenses.add(expense);
  }
}
