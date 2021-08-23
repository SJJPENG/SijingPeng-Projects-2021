package System.Model;

import static org.junit.Assert.*;

import System.Exceptions.IllegalExecuteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class DataBaseProcessorTest {
  private DataBaseProcessor dataBaseProcessor;
  LocalDate localDate;
  Todo partialPara1;
  List<Todo> list;

  @Before
  public void setUp() throws Exception {
    dataBaseProcessor = new DataBaseProcessor("todos.csv");
    localDate = LocalDate.of(2021, 3,21);
    partialPara1 = new Todo.TodoBuilder("Complete Testing").
        complete("False").setDueDate(localDate).setCategory("School").build();
  }

  @Test
  public void sortByDate() throws IllegalExecuteException {
    dataBaseProcessor.processData();
    list = dataBaseProcessor.conversionToList();
    List<Todo> temp = DataBaseProcessor.sortByDate(list);
    assertTrue(temp.get(0).getDueDate().isBefore(temp.get(1).getDueDate()));
  }

  @Test
  public void sortByPriority() throws IllegalExecuteException {
    dataBaseProcessor.processData();
    list = dataBaseProcessor.conversionToList();
    List<Todo> temp = DataBaseProcessor.sortByPriority(list);
    assertTrue(temp.get(0).getPriority() < temp.get(1).getPriority());
  }

  @Test
  public void filterByIncomplete() throws IllegalExecuteException {
    dataBaseProcessor.processData();
    list = dataBaseProcessor.conversionToList();
    List<Todo> temp = DataBaseProcessor.filterByIncomplete(list);
    assertFalse(temp.get(0).isCompleted());
    assertFalse(temp.get(1).isCompleted());
    assertFalse(temp.get(2).isCompleted());
  }

  @Test
  public void filterByCategory() throws IllegalExecuteException {
    dataBaseProcessor.processData();
    list = dataBaseProcessor.conversionToList();
    List<Todo> temp = DataBaseProcessor.filterByCategory("school", list);
    List<Todo> temp1 = DataBaseProcessor.filterByCategory("work", list);
    List<Todo> temp2 = DataBaseProcessor.filterByCategory("home", list);
    assertEquals("school", temp.get(0).getCategory());
    assertSame(0, temp1.size());
    assertEquals("home", temp2.get(0).getCategory());
  }

  @Test
  public void getDatabase() throws IllegalExecuteException {
    dataBaseProcessor.processData();
    assertEquals("Finish HW9", dataBaseProcessor.getDatabase().get("1").getText());
  }

  @Test
  public void getSize() throws IllegalExecuteException {
    dataBaseProcessor.processData();
    assertEquals(5, (int) dataBaseProcessor.getSize());
  }

  @Test (expected = IllegalExecuteException.class)
  public void invalidCSVPath() throws IllegalExecuteException {
    DataBaseProcessor dataBaseProcessor1 = new DataBaseProcessor("some_file.csv");
    dataBaseProcessor1.processData();
  }

  @Test
  public void constructTodo() {
    ArrayList<String> todoData = new ArrayList<>(Arrays.asList("6", "Complete Testing", "False", "3/21/2021", "?", "School"));
    dataBaseProcessor.constructTodo(6, todoData);

    assertEquals(dataBaseProcessor.getDatabase().get("6"), partialPara1);
  }


  @Test
  public void formatOutput() throws IllegalExecuteException {
    dataBaseProcessor.processData();
    List<String> csvContent =  dataBaseProcessor.formatOutput();
    assertEquals(6, csvContent.size());
  }


  @Test
  public void addNewTodo() throws IllegalExecuteException {
    dataBaseProcessor.addNewTodo(partialPara1);
    assertEquals(1, (int) dataBaseProcessor.getSize());
    assertEquals(dataBaseProcessor.getDatabase().get("1"), partialPara1);
  }

  @Test
  public void completeTodo() throws IllegalExecuteException {
    dataBaseProcessor.addNewTodo(partialPara1);
    assertFalse(dataBaseProcessor.getDatabase().get("1").isCompleted());
    dataBaseProcessor.completeTodo("1");
    assertTrue(dataBaseProcessor.getDatabase().get("1").isCompleted());

    Todo alreadyCompleted = new Todo.TodoBuilder("Complete Testing").
        complete("true").setDueDate(localDate).setCategory("School").build();
    dataBaseProcessor.addNewTodo(alreadyCompleted);
    assertTrue(dataBaseProcessor.getDatabase().get("2").isCompleted());
  }

  @Test (expected = IllegalExecuteException.class)
  public void completeTodoInvalidIndex() throws IllegalExecuteException {
    dataBaseProcessor.addNewTodo(partialPara1);
    dataBaseProcessor.completeTodo("2");
  }

  @Test
  public void conversionToList() {
  }
}