import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BudgetTracker {

  private ExpenseList expenseList;
  private double existBudget;

  public BudgetTracker(String fileName, char sep) {
    expenseList = new ExpenseList(fileName, sep);

  }

  public ExpenseList getExpenseList() {
    return expenseList;
  }

  public void setExpenseList(ExpenseList expenseList) {
    this.expenseList = expenseList;
  }

  public double getExistBudget() {
    return existBudget;
  }

  public void setExistBudget(double existBudget) {
    this.existBudget = existBudget;
  }

  public void inputUserBudget(Scanner scanner) throws ParseException {
    System.out.print("Input your budget: ");
    existBudget = scanner.nextDouble();
    scanner.nextLine();
  }

  public void print() {
    expenseList.print();
    System.out.println("Total budget: " + existBudget);
  }

  public void differenceBudgetExpenses() {
    double totalExpenses = expenseList.getTotalExpenses();
    System.out.println("Total expenses: " + totalExpenses + " EUR");
    if (existBudget < totalExpenses) {
      System.out.println(
          "NOTE: You are out of budget with: " + (existBudget - totalExpenses) + " EUR");
    } else {
      System.out.println("You have in budget: " + (existBudget - totalExpenses) + " EUR");
    }
  }
}
