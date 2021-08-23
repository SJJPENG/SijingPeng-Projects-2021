package System.Model;

import static org.junit.Assert.*;

import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

public class TodoTest {
  private Todo justText;
  private Todo partialPara;
  private LocalDate localDate;

  @Before
  public void setUp() throws Exception {
    localDate = LocalDate.of(2021, 3,21);
    justText = new Todo.TodoBuilder("Complete HW8").build();
    partialPara = new Todo.TodoBuilder("Complete Testing").
        complete("True").setDueDate(localDate).setCategory("School").build();
  }

  @Test
  public void getText() {
    assertEquals("Complete HW8", justText.getText());
    assertEquals("Complete Testing", partialPara.getText());
  }

  @Test
  public void isCompleted() {
    assertFalse(justText.isCompleted());
    assertTrue(partialPara.isCompleted());
  }

  @Test
  public void getDueDate() {
    assertNull(justText.getDueDate());
    assertEquals(partialPara.getDueDate(), localDate);
  }

  @Test
  public void getPriority() {
    assertNull(justText.getPriority());

    Todo priority1 = new Todo.TodoBuilder("Complete Testing").complete("True").setCategory("School").setPriority(1).build();
    assertEquals(1, (int) priority1.getPriority());
    Todo priority2 = new Todo.TodoBuilder("Complete Testing").complete("True").setCategory("School").setPriority(2).build();
    assertEquals(2, (int) priority2.getPriority());
    Todo priority3 = new Todo.TodoBuilder("Complete Testing").complete("True").setCategory("School").setPriority(3).build();
    assertEquals(3, (int) priority3.getPriority());
  }

  @Test
  public void getCategory() {
    assertNull(justText.getCategory());
    assertEquals("School", partialPara.getCategory());
  }

  @Test
  public void setAsCompleted() {
    Todo justTextCompleted = justText.setAsCompleted();
    assertTrue(justTextCompleted.isCompleted());
  }

  @Test
  public void testEquals() {
    Todo dummy = new Todo.TodoBuilder("Complete HW8").build();
    assertEquals(dummy, justText);
    assertFalse(dummy.equals(localDate));
    Todo dummy1 = new Todo.TodoBuilder("Complete Testing").
        complete("True").setDueDate(localDate).setCategory("School").build();
    Todo dummy2 = new Todo.TodoBuilder("Complete Testing").
        complete("False").setDueDate(localDate).setCategory("School").build();
    assertEquals(dummy1, partialPara);
    assertFalse(dummy2.equals(partialPara));
  }

  @Test
  public void testHashCode() {
    Todo partialPara2 = new Todo.TodoBuilder("Complete Testing").
        complete("True").setDueDate(localDate).setCategory("School").build();
    assertEquals(partialPara.hashCode(), partialPara2.hashCode());
  }

  @Test
  public void testToString() {
    String string1 = "\"Complete HW8\",\"FALSE\",\"?\",\"?\",\"?\"";
    String string2 = "\"Complete Testing\",\"TRUE\",3/21/2021,\"?\",\"School\"";
    assertEquals(string1, justText.toString());
    assertEquals(string2, partialPara.toString());
  }
}