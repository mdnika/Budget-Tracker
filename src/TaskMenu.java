import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class TaskMenu {

  private static final String EXIT = "0";
  private static final String ADD = "1";
  private static final String PRINT = "2";
  private static final String SEARCH = "3";
  // private static final String WR

  private static final Map<String, String> descriptions = new LinkedHashMap<>();

  static {
    descriptions.put(ADD, "Add Expense");
    descriptions.put(PRINT, "Print ExpenseLists");
    descriptions.put(SEARCH, "Delete Expense by Name");
    descriptions.put(EXIT, "Exit");
  }

  // Функциональный интерфейс для методов, которые ничего не получают и ничего не возвращают:
  // `Runnable`
  private static final Map<String, Runnable> actions = new LinkedHashMap<>();

  static {
    Scanner scanner = new Scanner(System.in);
    actions.put(ADD, new Runnable() {
      @Override
      public void run() {
        try {
          expenseLists.addUserExpense(scanner);
        } catch (ParseException e) {
          throw new RuntimeException(e);
        }
      }
    });
    actions.put(PRINT, new Runnable() {
      @Override
      public void run() {
        System.out.println(expenseLists);
      }
    });
    actions.put(SEARCH, new Runnable() {
      @Override
      public void run() {

        expenseLists.deleteExpense(scanner);
      }
    });
    actions.put(EXIT, new Runnable() {
      @Override
      public void run() {
        System.exit(0); // выход из программы
      }
    });
  }

  public static ExpenseList expenseLists = new ExpenseList("res/expenses.csv", ':');

  private static void print() {
    for (Map.Entry<String, String> entry : descriptions.entrySet()) {
      System.out.println(entry.getKey() + ". " + entry.getValue());
    }
  }

  public static String read() {
    print();
    Scanner scanner = new Scanner(System.in);
    System.out.print("Input a command: ");
    String command = scanner.nextLine();
    while (!descriptions.containsKey(command)) {
      System.out.println("Wrong input: \"" + command + '"');
      System.out.print("Input a command: ");
      command = scanner.nextLine();
    }
    return command;
  }

  public static void apply(String command) {
    if (!actions.containsKey(command)) {
      throw new IllegalArgumentException("Wrong input, try again: " + command);
    }
    Runnable action = actions.get(command);
    action.run();
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    while (true) {
      apply(read());
    }
  }
}
