package System.Control;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class CommandLineParserTest {

  private static final String[] ARG1 = {"--csv-file", "todos.csv", "--add-todo", "--todo-text", "finish hw9"};
  private static final String[] ARG2 = {"--display"};
  private static final String[] ARG3 = {"--add-todo", "--csv-file", "todos.csv",
      "--todo-text", "finish hw9", "--complete", "--category", "homework","--complete-todo", "1", "--complete-todo", "2",
      "--display", "--show-incomplete"};
  private static final String[] ARG4 = {"--csv-file", "todos.csv", "--display", "--display"};
  private static final String[] ARG5 = {"--csv-file", "todos.csv", "--add-todo", "--todo-text"};
  private static final String[] ARG6 = {"--csv-file", "todos.csv", "--complete-todo"};
  private static final String[] ARG7 = {"--csv-file", "todos.csv", "--complete-todo", "3", "--unknown"};
  private static final String[] ARG8 = {"--add-todo", "--todo-text", "finish hw9","--category", "homework", "--complete",
      "--complete-todo", "1", "--complete-todo", "2", "--display", "--show-incomplete", "--show-category", "homework", "--sort-by-date"};

  private static final String[] ARG9 = {"--add-todo", "--csv-file", "todos.csv",
      "--todo-text", "finish hw9", "--due", "4/21/2021", "--priority", "1", "--complete", "--complete-todo", "1", "--complete-todo", "2",
      "--complete-todo", "3", "--display", "--show-incomplete", "--show-category", "homework", "--sort-by-date"};

  private static final String[] ARG10 = {"--add-todo", "--csv-file", "todos.csv",
      "--todo-text", "finish hw9", "--due", "04/21/2021","--priority", "1", "--complete", "--complete-todo", "1", "--complete-todo", "2",
      "--complete-todo", "3", "--display", "--show-incomplete", "--show-category", "homework", "--sort-by-date", "--sort-by-priority"};

  private static final String[] ARG11 = {"--add-todo", "--csv-file", "todos.csv", "--due", "04/21/2021", "--priority", "1", "--complete", "--complete-todo", "1", "--complete-todo", "2",
      "--complete-todo", "3", "--display", "--show-incomplete", "--show-category", "homework", "--sort-by-date"};


  private static final String[] ARG12 = {"--add-todo", "--csv-file", "todos.csv",
      "--todo-text", "finish hw9", "--due", "April 21 2021","--complete", "--complete-todo", "1", "--complete-todo", "2",
      "--display", "--show-incomplete"};

  private static final String[] ARG13 = {"--add-todo", "--csv-file", "todos.csv",
      "--todo-text", "finish hw9", "--due", "4/21/2021", "--priority", "5", "--complete", "--complete-todo", "1", "--complete-todo", "2",
      "--display", "--show-incomplete"};

  private static final String[] ARG14 = {"--add-todo", "--csv-file", "todos.csv",
      "--todo-text", "finish hw9", "--due", "4/21/2021", "--priority", "highest", "--complete", "--complete-todo", "1", "--complete-todo", "2",
      "--display", "--show-incomplete"};


  private static final String[] ARG15 = {"--add-todo", "--csv-file", "todos.csv",
      "--todo-text", "finish hw9", "--due", "4/21/2021", "--priority", "1", "--complete", "--complete-todo", "1", "--complete-todo", "2",
      "--complete-todo", "3", "--display", "--show-incomplete", "--show-category", "homework", "--sort-by-date", "--sort-by-priority"};


  private CommandLineParser parser1;
  private CommandLineParser parser2;
  private CommandLineParser parser3;

  @Before
  public void setUp() throws Exception {
    parser1 = new CommandLineParser();
    parser2 = new CommandLineParser();
    parser3 = new CommandLineParser();
  }

  @Test
  public void getPath() {
    assert parser1.getPath() == null;
  }

  @Test
  public void parse() {
    List<ICommand> res1 = parser1.parse(ARG1);
    assertEquals(1, res1.size());
    assertEquals(parser1.getPath(), "todos.csv");
    List<ICommand> res2 = parser2.parse(ARG3);
    assertEquals(3, res2.size());
    assertEquals(parser2.getPath(), "todos.csv");
    List<ICommand> res3 = parser3.parse(ARG9);
    assertEquals(3, res3.size());
  }


  @Test
  public void illegalCommandLineTest1() {
    parser1.parse(ARG4);
    assertFalse(parser1.noError());
  }

  @Test
  public void illegalCommandLineTest2() {
    List<ICommand> res1 = parser1.parse(ARG5);
    assertFalse(parser1.noError());
  }

  @Test
  public void illegalCommandLineTest3() {
    List<ICommand> res1 = parser1.parse(ARG6);
    assertFalse(parser1.noError());
  }

  @Test
  public void illegalCommandLineTest4() {
    List<ICommand> res1 = parser1.parse(ARG7);
    assertFalse(parser1.noError());
  }

  @Test
  public void illegalCommandLineTest5() {
    List<ICommand> res1 = parser1.parse(ARG8);
    assertFalse(parser1.noError());
  }

  @Test
  public void illegalCommandLineTest6() {
    List<ICommand> res1 = parser1.parse(ARG10);
    assertFalse(parser1.noError());
  }

  @Test
  public void illegalCommandLineTest7() {
    List<ICommand> res1 = parser1.parse(ARG11);
    assertFalse(parser1.noError());
  }

  @Test
  public void illegalCommandLineTest8() {
    List<ICommand> res1 = parser1.parse(ARG12);
    assertFalse(parser1.noError());
  }

  @Test
  public void illegalCommandLineTest9() {
    List<ICommand> res1 = parser1.parse(ARG13);
    assertFalse(parser1.noError());
  }

  @Test
  public void illegalCommandLineTest10() {
    List<ICommand> res1 = parser1.parse(ARG14);
    assertFalse(parser1.noError());
  }

  @Test
  public void illegalCommandLineTest11() {
    List<ICommand> res1 = parser1.parse(ARG15);
    assertFalse(parser1.noError());
  }


  @Test
  public void parseNoPath() {
    parser1.parse(ARG2);
    assertFalse(parser1.noError());
  }


  @Test
  public void noError() {
    assertTrue(parser1.noError());
  }

  @Test
  public void getErrors() {
    assertEquals(parser1.getErrors(), "Empty log");
  }
}