package System.Control;

import System.Model.DataBaseProcessor;
import System.Exceptions.IllegalExecuteException;
import java.util.List;

/**
 * The type Complete todo.
 */
public class CompleteTodo implements ICommand {

  private final List<String> ids;

  /**
   * Instantiates a new Complete todo.
   *
   * @param ids the ids
   */
  public CompleteTodo(List<String> ids) {
    this.ids = ids;
  }

  @Override
  public void execute(DataBaseProcessor database) throws IllegalExecuteException {
    for (String id : ids) {
      database.completeTodo(id);
    }
  }
}
