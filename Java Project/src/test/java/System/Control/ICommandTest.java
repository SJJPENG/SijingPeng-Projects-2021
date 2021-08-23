package System.Control;

import static org.junit.Assert.*;

import System.Model.DataBaseProcessor;
import System.Model.Todo;
import System.Exceptions.IllegalCommandLineException;
import System.Exceptions.IllegalExecuteException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class ICommandTest {
  private AddNewToDo addNewToDo;
  private CompleteTodo completeTodo;

  private DataBaseProcessor dataBaseProcessor;
  private LocalDate localDate;
  private Todo todo;

  @Before
  public void setUp() throws Exception {
    dataBaseProcessor = new DataBaseProcessor("todos.csv");
    localDate = LocalDate.of(2021, 3,21);


    todo = new Todo.TodoBuilder("Complete Testing").
        complete("False").setDueDate(localDate).setCategory("School").setPriority(1).build();

    addNewToDo = new AddNewToDo.AddNewToDoBuilder("Complete Testing").setCompleted("False").
        setDueDate("3/21/2021").setCategory("School").setPriority("1").build();

    List<String> input = Arrays.asList("1", "3", "5");
    completeTodo = new CompleteTodo(input);
  }

  @Test (expected = IllegalCommandLineException.class)
  public void invalidDate() throws IllegalCommandLineException {
    AddNewToDo addNewToDo2 = new AddNewToDo.AddNewToDoBuilder("Complete Testing").setCompleted("False").
        setDueDate("2021/3/21").setCategory("School").setPriority("1").build();
  }

  @Test (expected = IllegalCommandLineException.class)
  public void invalidPriority() throws IllegalCommandLineException {
    AddNewToDo addNewToDo2 = new AddNewToDo.AddNewToDoBuilder("Complete Testing").setCompleted("False").
        setDueDate("3/21/2021").setCategory("School").setPriority("4").build();
  }

  @Test (expected = IllegalCommandLineException.class)
  public void invalidPriority2() throws IllegalCommandLineException {
    AddNewToDo addNewToDo2 = new AddNewToDo.AddNewToDoBuilder("Complete Testing").setCompleted("False").
        setDueDate("3/21/2021").setCategory("School").setPriority("-1").build();
  }

  @Test
  public void executeAddNewToDo() throws IllegalExecuteException {
    dataBaseProcessor.processData();
    int numTodos = dataBaseProcessor.getSize();
    addNewToDo.execute(dataBaseProcessor);
    assertTrue(dataBaseProcessor.getSize().equals(numTodos + 1));
    assertTrue(dataBaseProcessor.getDatabase().get(String.valueOf(numTodos + 1)).equals(todo));
  }

  @Test
  public void executeAddNewToDoNullDate()
      throws IllegalExecuteException, IllegalCommandLineException {
    AddNewToDo addNewToDo2 = new AddNewToDo.AddNewToDoBuilder("Complete Testing").setCompleted("False").setCategory("School").setPriority("1").build();

    dataBaseProcessor.processData();
    int numTodos = dataBaseProcessor.getSize();
    addNewToDo.execute(dataBaseProcessor);
    assertTrue(dataBaseProcessor.getSize().equals(numTodos + 1));
  }

  @Test
  public void executeAddNewToDoNullPriority()
      throws IllegalExecuteException, IllegalCommandLineException {
    AddNewToDo addNewToDo2 = new AddNewToDo.AddNewToDoBuilder("Complete Testing").setCompleted("False").setCategory("School").build();

    dataBaseProcessor.processData();
    int numTodos = dataBaseProcessor.getSize();
    addNewToDo.execute(dataBaseProcessor);
    assertTrue(dataBaseProcessor.getSize().equals(numTodos + 1));
  }

  @Test
  public void executeCompleteTodo() throws IllegalExecuteException {
    dataBaseProcessor.processData();
    completeTodo.execute(dataBaseProcessor);
    assertTrue(dataBaseProcessor.getDatabase().get("1").isCompleted());
    assertTrue(dataBaseProcessor.getDatabase().get("3").isCompleted());
    assertTrue(dataBaseProcessor.getDatabase().get("5").isCompleted());
  }

  @Test
  public void executeDisplayCategory() throws IllegalExecuteException {
    dataBaseProcessor.processData();
    DisplayTodos displayTodos = new DisplayTodos.DisplayTodosBuilder().showCategory("school").build();
    displayTodos.execute(dataBaseProcessor);
  }

  @Test
  public void executeDisplayIncomplete() throws IllegalExecuteException {
    dataBaseProcessor.processData();
    DisplayTodos displayTodos = new DisplayTodos.DisplayTodosBuilder().showIncomplete(true).build();
    displayTodos.execute(dataBaseProcessor);
  }

  @Test
  public void executeDisplaySortByPriority() throws IllegalExecuteException, IllegalCommandLineException {
    dataBaseProcessor.processData();
    DisplayTodos displayTodos = new DisplayTodos.DisplayTodosBuilder().sortByPriority(true).build();
    displayTodos.execute(dataBaseProcessor);
  }

  @Test
  public void executeSortByDisplay() throws IllegalExecuteException, IllegalCommandLineException {
    dataBaseProcessor.processData();
    DisplayTodos displayTodos = new DisplayTodos.DisplayTodosBuilder().sortByDate(true).build();
    displayTodos.execute(dataBaseProcessor);
  }
}