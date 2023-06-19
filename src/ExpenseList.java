import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExpenseList {

  private final List<Expense> expenses;
  private final File file;
  private final char sep;

  public ExpenseList(String fileName, char sep) {
    file = new File(fileName);
    this.sep = sep;
    expenses = readFromCsv();
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
  }
  public void addExpense(Expense expense) {
    expenses.add(expense);
  }
  public double getTotalExpenses() {
    double total = 0;
    for (Expense expense : expenses) {
      total += expense.getAmount();
    }
    return total;
  }
}
