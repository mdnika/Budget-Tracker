import java.io.IOException;
import java.text.ParseException;
import java.util.Locale;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) throws IOException, ParseException {

    System.out.print("Input your budget: ");
    Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
    double existBudget = scanner.nextDouble();
    scanner.nextLine();

    System.out.println("These are your fix expenses:");
    BudgetTracker budgetTracker = new BudgetTracker("res/expenses.csv", ':');
    // вывод
    budgetTracker.print();

 /*   // запись в файл
    budgetTracker.writeToCsv();

    budgetTracker.addUserExpense(scanner);
    double totalExpenses = budgetTracker.getTotalExpenses();
    System.out.println("Total expenses: " + totalExpenses + " EUR");
    if (existBudget - totalExpenses < 0) {
      System.out.println(
          "NOTE: You are out of budget with: " + (existBudget - totalExpenses) + " EUR");
    } else {
      System.out.println("You have in budget: " + (existBudget - totalExpenses) + " EUR");
    }*/
  }
}
