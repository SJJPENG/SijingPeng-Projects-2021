package System.Control;

import System.Model.DataBaseProcessor;
import java.util.List;

public class SystemMain {

  public static void main(String[] args) {
    CommandLineParser commandLineParser = new CommandLineParser();
    List<ICommand> commands = commandLineParser.parse(args);
    if (commandLineParser.noError()) {
      DataBaseProcessor processor = new DataBaseProcessor(commandLineParser.getPath());
      CommandExecutor commandExecutor = new CommandExecutor(processor);
      commandExecutor.execute(commands);
      if (!commandExecutor.noError()) {
        System.out.println(commandExecutor.getErrors());
        System.out.println(Constants.USAGE);
      }
    } else {
      System.out.println(commandLineParser.getErrors());
      System.out.println(Constants.USAGE);
    }
  }
}

