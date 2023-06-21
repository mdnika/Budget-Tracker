import java.io.IOException;
import java.text.ParseException;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class BudgetTracker {

  public static void main(String[] args) throws IOException {
    System.out.println("~~~ Budget Tracker ~~~");

    Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
    int userChoise = 0;
    ExpenseList current = new ExpenseList("res/expenses.csv", ':');

    do {
      printMenu();
      try {
        userChoise = scanner.nextInt();

        switch (userChoise) {
          case 1: {
            // add Budget amount
            current.inputUserBudget(scanner);
            break;
          }
          case 2: {
            // show (print fix expenses from file csv)
            System.out.println("These are your fix expenses:");
            current.print();
            break;
          }
          case 3: {
            // add users extra expense
            current.addUserExpense(scanner);
            break;
          }
          case 4: {
            // show the difference between Budget and Expenses amount
            current.differenceBudgetExpenses();
            break;
          }

          case 5: {
            // delete expense
            String name = current.deleteExpense(scanner);
            break;
          }
          case 6: {
            // write expense in file csv
            current.writeToCsv();
            System.out.println("Check the file: res/expenses.csv");
            break;
          }
          case 0: {
            // Exit
            return;
          }
          default: {
            System.out.println("Wrong input, try again.");
          }
        }
      } catch (InputMismatchException | ParseException e) {
        System.out.println("Wrong input, try again.");
        scanner.next();
      }

    } while (true); // end of loop while

  }

  // print Menu
  public static void printMenu() {
    System.out.println("~~~ Task Menu ~~~");
    System.out.println("1. Input your budget.");
    System.out.println("2. Show your fix expences.");
    System.out.println("3. Input your extra expence.");
    System.out.println("4. Show the difference between Budget amount and Expences amount.");
    System.out.println("5. Delete expence by name.");
    System.out.println("6. Save the final expences list in File - 'res/expenses.csv'.");
    System.out.println("0. Exit.");
    System.out.println();
    System.out.print("Please input your choice: ");
  }
}
