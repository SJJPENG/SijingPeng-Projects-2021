package System.Control;

import System.Control.AddNewToDo.AddNewToDoBuilder;
import System.Control.DisplayTodos.DisplayTodosBuilder;
import System.Exceptions.IllegalCommandLineException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

/**
 * The type Command factory.
 */
public class CommandFactory {

  /**
   * Generate command command.
   *
   * @param inputsEntry the inputs entry
   * @return the command
   * @throws IllegalCommandLineException the command line exception
   */
  public ICommand generateCommand(Entry<String, Map<String, String>> inputsEntry)
      throws IllegalCommandLineException {
    switch (inputsEntry.getKey()) {
      case Constants.ADD:
        return this.generateAddCommand(inputsEntry.getValue());
      case Constants.COMPLETE_TODO:
        return this.generateCompleteCommand(inputsEntry.getValue());
      case Constants.DISPLAY:
        return this.generateDisplayCommand(inputsEntry.getValue());
      default:
        throw new IllegalCommandLineException("can't identify this command");
    }
  }

  /**
   * Generate add command add new to do.
   *
   * @param parameters the parameters
   * @return the add new to do
   * @throws IllegalCommandLineException the command line exception
   */
  private AddNewToDo generateAddCommand(Map<String, String> parameters)
      throws IllegalCommandLineException {
    String text = parameters.get(Constants.TEXT);
    String completed = String.valueOf(parameters.containsKey(Constants.COMPLETE));
    String dueDate = parameters.get(Constants.DUE);
    String category = parameters.get(Constants.CATEGORY);
    String priority = parameters.get(Constants.PRIORITY);
    if (text == null) {
      throw new IllegalCommandLineException(
          Constants.TEXT + "is required when you provided --add-todo");
    }
    AddNewToDoBuilder addNewToDoBuilder = new AddNewToDo.AddNewToDoBuilder(text)
        .setCompleted(completed);
    if (category != null) {
      addNewToDoBuilder.setCategory(category);
    }
    if (priority != null) {
      addNewToDoBuilder.setPriority(priority);
    }
    if (dueDate != null) {
      addNewToDoBuilder.setDueDate(dueDate);
    }
    return addNewToDoBuilder.build();
  }

  /**
   * Generate complete command complete todo.
   *
   * @param parameters the parameters
   * @return the complete todo
   */
  private CompleteTodo generateCompleteCommand(Map<String, String> parameters) {
    return new CompleteTodo(new ArrayList<>(parameters.values()));
  }

  /**
   * Generate display command display todos.
   *
   * @param parameters the parameters
   * @return the display todos
   * @throws IllegalCommandLineException the command line exception
   */
  private DisplayTodos generateDisplayCommand(Map<String, String> parameters)
      throws IllegalCommandLineException {
    String category = parameters.get(Constants.SHOW_CAT);
    Boolean isSortByDueDate = parameters.containsKey(Constants.SORT_DATE);
    Boolean isSortByPriority = parameters.containsKey(Constants.SORT_PRIORITY);
    Boolean isShowIncomplete = parameters.containsKey(Constants.SHOW_INC);

    DisplayTodosBuilder displayTodosBuilder = new DisplayTodosBuilder();
    if (category != null) {
      displayTodosBuilder.showCategory(category);
    }
    if (isSortByDueDate && isSortByPriority) {
      throw new IllegalCommandLineException("--sort-by-date can't combine with --sort-by-priority");
    }
    return displayTodosBuilder.showIncomplete(isShowIncomplete)
        .sortByDate(isSortByDueDate).sortByPriority(isSortByPriority).build();
  }

}
