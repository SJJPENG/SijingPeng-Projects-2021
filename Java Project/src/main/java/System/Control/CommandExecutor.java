package System.Control;

import System.Model.DataBaseProcessor;
import System.Exceptions.IllegalExecuteException;
import java.util.List;

/**
 * Command Executor
 */
public class CommandExecutor {
  private DataBaseProcessor dataBaseProcessor;
  private ErrorLogger errorLogger;

  /**
   * Constructor for command executor
   * @param dataBaseProcessor database processor object
   */
  public CommandExecutor(DataBaseProcessor dataBaseProcessor){
    this.dataBaseProcessor = dataBaseProcessor;
    this.errorLogger = new ErrorLogger();
  }

  /**
   * Execute commands
   * @param commands list of commands
   */
  public void execute(List<ICommand> commands){
    try {
      this.dataBaseProcessor.processData();
    } catch (IllegalExecuteException illegalExecuteException) {
      this.errorLogger.log(illegalExecuteException.getMessage());
    }
    commands.forEach(iCommand -> {
      try {
        iCommand.execute(this.dataBaseProcessor);
      } catch (IllegalExecuteException illegalExecuteException) {
        errorLogger.log(illegalExecuteException.getMessage());
      }
    });
  }

  /**
   * Return true if no error in error log, false otherwise
   * @return true if no error in error log, false otherwise
   */
  public boolean noError() {
    return this.errorLogger.isEmpty();
  }

  /**
   * Returns error logger in string form
   * @return error logger in string form
   */
  public String getErrors() {
    return this.errorLogger.toString();
  }

}
