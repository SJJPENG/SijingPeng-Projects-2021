package System.Control;

import static org.junit.Assert.*;

import System.Control.AddNewToDo.AddNewToDoBuilder;
import System.Model.DataBaseProcessor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class CommandExecutorTest {
  private DataBaseProcessor processor1;
  private DataBaseProcessor processor2;
  private List<ICommand> commands1 = new ArrayList<>();
  private List<ICommand> commands2 = new ArrayList<>();
  private CommandExecutor executor1;
  private CommandExecutor executor2;

  @Before
  public void setUp() throws Exception {
    commands1.add(new AddNewToDoBuilder("finish test").build());
    commands2.add(new CompleteTodo(Arrays.asList(new String[]{"10000"}.clone())));
    processor1 = new DataBaseProcessor("todos.csv");
    processor2 = new DataBaseProcessor("not_exist.csv");
    executor1 = new CommandExecutor(processor1);
    executor2 = new CommandExecutor(processor2);
  }

  @Test
  public void noError() {
    assertTrue(executor1.noError());
  }

  @Test
  public void execute() {
    executor1.execute(commands1);
    assertTrue(executor1.noError());
  }

  @Test
  public void executeError1() {
    executor2.execute(commands1);
    assertFalse(executor2.noError());
  }

  @Test
  public void executeError2() {
    executor1.execute(commands2);
    assertFalse(executor1.noError());
  }

  @Test
  public void getErrors() {assertEquals(executor1.getErrors(), "Empty log"); }
}