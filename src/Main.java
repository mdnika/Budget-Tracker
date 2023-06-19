import java.io.IOException;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) throws IOException {

    System.out.print("Input your budget: ");
    Scanner scanner = new Scanner(System.in);
    double existBudget = scanner.nextDouble();
    scanner.nextLine();
    ExpenseList expenseLists = new ExpenseList("res/expenses.csv", ':');

    // вывод
    expenseLists.print();

    // запись в файл
    expenseLists.writeToCsv();
    double totalExpenses = expenseLists.getTotalExpenses();
    System.out.println("Total expenses: " + totalExpenses + " EUR");
    if (existBudget - totalExpenses < 0) {
      System.out.println(
          "NOTE: You are out of budget with: " + (existBudget - totalExpenses) + " EUR");
    } else {
      System.out.println("You have in budget " + (existBudget - totalExpenses) + " EUR");
    }
  }
}
