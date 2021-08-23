package System.Control;

import System.Model.DataBaseProcessor;
import System.Exceptions.IllegalExecuteException;

/**
 * The interface Command.
 */
public interface ICommand {

  /**
   * Execute.
   *
   * @param dataBaseProcessor the data base processor
   * @throws IllegalExecuteException the illegal execute exception
   */
  void execute(DataBaseProcessor dataBaseProcessor) throws IllegalExecuteException;

}
