import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Scanner;
import org.junit.jupiter.api.Test;

public class ExpenseTests {

  private static final String NAME = "Test1";
  private static final double AMOUNT = 100.0;
  private static final char SEP = ':';

  @Test
  public void testConstructor() {
    Expense expense = new Expense(NAME, AMOUNT);
    assertEquals(NAME, expense.getName());
    assertEquals(AMOUNT, expense.getAmount());

    String newName = "Test2";
    expense.setName(newName);
    assertEquals(newName, expense.getName());

    double newAmout = AMOUNT + 1.0;
    expense.setAmount(newAmout);
    assertEquals(newAmout, expense.getAmount());
  }

  @Test
  public void testToString() {
    Expense expense = new Expense(NAME, AMOUNT);

    String expected = NAME + " : " + AMOUNT;
    String actual = expense.toString();

    assertEquals(expected, actual);
  }

  @Test
  public void testToCsvString() {
    Expense expense = new Expense(NAME, AMOUNT);

    String expected = NAME + SEP + AMOUNT + '\n';
    String actual = expense.toCsvString(SEP);

    assertEquals(expected, actual);
  }

  @Test
  public void testParseFromCsv() {
    String source = NAME + SEP + AMOUNT;
    Expense actual = Expense.parseFromCsv(source, SEP);

    assertEquals(NAME, actual.getName());
    assertEquals(AMOUNT, actual.getAmount());
  }

  @Test
  public void testCsv() {
    Expense source = new Expense(NAME, AMOUNT);
    String csvLine = source.toCsvString(SEP);

    Scanner scanner = new Scanner(csvLine);
    String readLine = scanner.nextLine();
    Expense read = Expense.parseFromCsv(readLine, SEP);

    assertEquals(source.getName(), read.getName());
    assertEquals(source.getAmount(), read.getAmount());
  }
}
