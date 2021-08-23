package System.View;

import System.Model.Todo;
import java.util.List;

/**
 * The type Viewer.
 */
public class Viewer {


  /**
   * Print to screen.
   *
   * @param todos the todos
   */
  public static void printToScreen(List<Todo> todos) {

    todos.forEach(System.out::println);
  }

}
