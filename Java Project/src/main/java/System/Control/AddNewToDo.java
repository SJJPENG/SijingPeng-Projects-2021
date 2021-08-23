package System.Control;

import System.Model.DataBaseProcessor;
import System.Model.Todo;
import System.Model.Todo.TodoBuilder;
import System.Exceptions.IllegalCommandLineException;
import System.Exceptions.IllegalExecuteException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The type Add new to do.
 */
public class AddNewToDo implements ICommand {

  private final String text;
  private final boolean completed;
  private final LocalDate dueDate;
  private final Integer priority;
  private final String category;

  /**
   * Constructor for a new todo
   * @param builder new todo builder
   */
  private AddNewToDo(AddNewToDoBuilder builder) {
    this.text = builder.text;
    this.completed = builder.completed;
    this.category = builder.category;
    this.dueDate = builder.dueDate;
    this.priority = builder.priority;
  }


  @Override
  public void execute(DataBaseProcessor dataBaseProcessor) throws IllegalExecuteException {
    TodoBuilder toDoBuilder = new Todo.TodoBuilder(this.text)
        .complete(String.valueOf(this.completed));
    if (this.category != null) {
      toDoBuilder.setCategory(this.category);
    }
    if (this.dueDate != null) {
      toDoBuilder.setDueDate(this.dueDate);
    }
    if (this.priority != null) {
      toDoBuilder.setPriority(this.priority).build();
    }
    Todo toAdd = toDoBuilder.build();
    dataBaseProcessor.addNewTodo(toAdd);
  }

  /**
   * The type Add new to do builder.
   */
  public static class AddNewToDoBuilder {

    private final String text;
    private boolean completed;
    private LocalDate dueDate;
    private Integer priority;
    private String category;

    /**
     * Instantiates a new Add new to do builder.
     *
     * @param text the text
     */
    public AddNewToDoBuilder(String text){
        this.text = text;
    }

    /**
     * Sets completed.
     *
     * @param completed the completed
     * @return the completed
     */
    public AddNewToDoBuilder setCompleted(String completed) {
      this.completed = completed.equalsIgnoreCase("true");
      return this;
    }

    /**
     * Sets due date.
     *
     * @param dueDate the due date
     * @return the due date
     * @throws IllegalCommandLineException the invalid input exception
     */
    public AddNewToDoBuilder setDueDate(String dueDate) throws IllegalCommandLineException {
      try {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("M/dd/yyyy");
        this.dueDate = LocalDate.parse(dueDate, dateTimeFormatter);
        return this;
      } catch (DateTimeParseException exception) {
        throw new IllegalCommandLineException("due date should be formatted as M/dd/yyyy");
      }
    }

    /**
     * Sets priority.
     *
     * @param priority the priority
     * @return the priority
     * @throws IllegalCommandLineException the invalid input exception
     */
    public AddNewToDoBuilder setPriority(String priority)
        throws IllegalCommandLineException {
      try{
        Integer.parseInt(priority);
      }catch (NumberFormatException e){
        throw new IllegalCommandLineException("priority should be  1, 2, or 3");
      }
      if (Integer.parseInt(priority) >= 1 && Integer.parseInt(priority) <= 3) {
        this.priority = Integer.parseInt(priority);
        return this;
      } else {
        throw new IllegalCommandLineException("priority should be 1, 2, or 3");
      }

    }

    /**
     * Set category add new to do builder.
     *
     * @param category the category
     * @return the add new to do builder
     */
    public AddNewToDoBuilder setCategory(String category) {
      this.category = category;
      return this;
    }

    /**
     * Build add new to do.
     *
     * @return the add new to do
     */
    public AddNewToDo build() {
      return new AddNewToDo(this);
    }
  }


}
