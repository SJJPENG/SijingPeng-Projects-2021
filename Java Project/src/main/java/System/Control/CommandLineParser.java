package System.Control;

import static java.util.Arrays.asList;

import System.Exceptions.IllegalCommandLineException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * The type Command line parser.
 */
public class CommandLineParser {


  private ErrorLogger errorLogger;
  private String path;

  /**
   * Instantiates a new Command line parser.
   */
  public CommandLineParser() {
    this.errorLogger = new ErrorLogger();
    this.path = null;
  }


  /**
   * Parse list.
   *
   * @param userInput the user input
   * @return the list
   */
  public List<ICommand> parse(String[] userInput) {
    try {
      return this.generateCommands(this.getInputsMap(userInput));
    } catch (IllegalCommandLineException exception){
      this.errorLogger.log(exception.getMessage());
    }
    return null;
  }

  /**
   * Get path string.
   *
   * @return the string
   */
  public String getPath(){
    return this.path;
  }

  /**
   * Helper method : Generate commands list.
   *
   * @param organizedInputsMap the organized inputs map
   * @return the list
   * @throws IllegalCommandLineException the illegal command line exception
   */
  private List<ICommand> generateCommands(Map<String, Map<String, String>> organizedInputsMap)
      throws IllegalCommandLineException {
    List<ICommand> commands = new ArrayList<>();
    CommandFactory factory = new CommandFactory();
    for (Entry<String, Map<String, String>> stringMapEntry : organizedInputsMap.entrySet()) {
      commands.add(factory.generateCommand(stringMapEntry));
    }
    return commands;
  }


  /**
   * Helper method : check if a string Is identified in a group boolean.
   *
   * @param target the target
   * @param group  the group
   * @return the boolean
   */
  private boolean isIdentifiedInAGroup(String target, String[] group) {
    return asList(group).contains(target);
  }


  /**
   * Helper method : Gets inputs map.
   *
   * @param inputs the inputs
   * @return the inputs map
   * @throws IllegalCommandLineException the illegal command line exception
   */
  private Map<String, Map<String, String>> getInputsMap(String[] inputs)
      throws IllegalCommandLineException {
    Map<String, Map<String, String>> organizedInputsMap = new HashMap<>();
    Map<String, String> noDuplicateInfoMap = new HashMap<>();
    Map<String, String> duplicateInfoMap = new HashMap<>();
    int i = 0;
    int j = 1;
    if (inputs.length < 2) {
      throw new IllegalCommandLineException("at least two inputs are required");
    }
    while (i < inputs.length) {
      if (noDuplicateInfoMap.containsKey(inputs[i])) {
        throw new IllegalCommandLineException("Contains invalid duplicate inputs");
      }
      if (isIdentifiedInAGroup(inputs[i], Constants.NO_ARGUMENT)) {
        noDuplicateInfoMap.put(inputs[i], null);
        i++;
        j = i + 1;
      } else if (isIdentifiedInAGroup(inputs[i], Constants.NEED_ARGUMENT)) {
        if (j < inputs.length && !isIdentifiedInAGroup(inputs[j], Constants.NO_ARGUMENT) && !isIdentifiedInAGroup(
            inputs[j], Constants.NEED_ARGUMENT)) {
          noDuplicateInfoMap.put(inputs[i], inputs[j]);
          i += 2;
          j = i + 1;
        } else {
          throw new IllegalCommandLineException(
              inputs[i] + "must be followed by an argument, please check your input");
        }
      } else if (isIdentifiedInAGroup(inputs[i], Constants.ACCEPT_DUPLICATES)){
        if (j < inputs.length && !isIdentifiedInAGroup(inputs[j], Constants.NO_ARGUMENT) && !isIdentifiedInAGroup(
            inputs[j], Constants.NEED_ARGUMENT)) {
          duplicateInfoMap.put(inputs[i] + i, inputs[j]);
          i += 2;
          j = i + 1;
        } else {
          throw new IllegalCommandLineException(
              "--complete-todo must be followed by an id, please check your input");
        }
      } else {
        throw new IllegalCommandLineException("Can't identify your input, please check your input");
      }
    }

    if (!noDuplicateInfoMap.containsKey(Constants.CSV)) {
      throw new IllegalCommandLineException(
          "--csv-file <csv file path> is required, please check your input");
    } else {
      this.path = noDuplicateInfoMap.get(Constants.CSV);
    }

    if (noDuplicateInfoMap.containsKey(Constants.ADD)) {
      organizedInputsMap.put(Constants.ADD, noDuplicateInfoMap);
    }

    if (noDuplicateInfoMap.containsKey(Constants.DISPLAY)) {
      organizedInputsMap.put(Constants.DISPLAY, noDuplicateInfoMap);
    }

    if (!duplicateInfoMap.isEmpty()) {
      organizedInputsMap.put(Constants.COMPLETE_TODO, duplicateInfoMap);
    }

    return organizedInputsMap;
  }


  /**
   * No error boolean.
   *
   * @return the boolean
   */
  public boolean noError() {
    return this.errorLogger.isEmpty();
  }

  /**
   * Gets errors.
   *
   * @return the errors
   */
  public String getErrors() {
    return this.errorLogger.toString();
  }

}
