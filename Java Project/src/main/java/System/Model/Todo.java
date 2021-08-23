package System.Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * The type Todo.
 */
public class Todo {

  private final String text;
  private final boolean completed;
  private final LocalDate dueDate;
  private final Integer priority;
  private final String category;

  /**
   * Constructor for Todo
   *
   * @param builder todo builder
   */
  private Todo(TodoBuilder builder) {
    this.text = builder.text;
    this.completed = builder.completed;
    this.category = builder.category;
    this.dueDate = builder.dueDate;
    this.priority = builder.priority;
  }

  /**
   * Gets text. Returns: the text
   *
   * @return the text
   */
  public String getText() {
    return this.text;
  }

  /**
   * Is completed boolean.
   *
   * @return the boolean
   */
  public boolean isCompleted() {
    return this.completed;
  }

  /**
   * Gets due date
   *
   * @return the due date
   */
  public LocalDate getDueDate() {
    return this.dueDate;
  }

  /**
   * Gets priority.
   *
   * @return the priority
   */
  public Integer getPriority() {
    return this.priority;
  }

  /**
   * Gets category.
   *
   * @return the category
   */
  public String getCategory() {
    return this.category;
  }

  /**
   * Sets the task as complete
   *
   * @return new task that has been completed
   */
  public Todo setAsCompleted() {
    return new TodoBuilder(this.getText()).complete("true").
        setDueDate(this.getDueDate()).setPriority(this.getPriority())
        .setCategory(this.getCategory()).build();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Todo)) {
      return false;
    }
    Todo todo = (Todo) o;
    return completed == todo.completed && Objects.equals(text, todo.text)
        && Objects.equals(dueDate, todo.dueDate) && Objects
        .equals(priority, todo.priority) && Objects.equals(category, todo.category);
  }

  @Override
  public int hashCode() {
    return Objects.hash(text, completed, dueDate, priority, category);
  }

  /**
   * Format string string.
   *
   * @param string the string
   * @return the string
   */
  protected String formatString(String string) {
    return string == null || string.equals("null") ? "\"" + "?" + "\"," : "\"" + string + "\",";
  }

  /**
   * Format string end cell string.
   *
   * @param string the string
   * @return the string
   */
  protected String formatStringEndCell(String string) {
    return string == null || string.equals("null") ? "\"" + "?" + "\"" : "\"" + string + "\"";
  }

  /**
   * Format date string.
   *
   * @param localDate the local date
   * @return the string
   */
  protected String formatDate(LocalDate localDate) {
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("M/dd/yyyy");
    return localDate == null ? "\"" + "?" + "\"," : localDate.format(dateTimeFormatter) + ",";
  }


  @Override
  public String toString() {
    return formatString(this.text) + formatString(String.valueOf(this.completed)
        .toUpperCase()) + formatDate(this.dueDate) + formatString(String.valueOf(this.priority))
        + formatStringEndCell(this.category);
  }

  /**
   * The type Todo builder.
   */
  public static class TodoBuilder {

    private final String text;
    private boolean completed;
    private LocalDate dueDate;
    private Integer priority;
    private String category;

    /**
     * Instantiates a new Todo builder.
     *
     * @param text the text
     */
    public TodoBuilder(String text) {
      this.text = text;
      this.completed = false;
    }

    /**
     * Complete todo builder.
     *
     * @param flag the flag
     * @return the todo builder
     */
    public TodoBuilder complete(String flag) {
      this.completed = flag.equalsIgnoreCase("TRUE");
      return this;
    }


    /**
     * Sets due date.
     *
     * @param dueDate the due date
     * @return the due date
     */
    public  TodoBuilder setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        return this;
    }


    /**
     * Sets priority.
     *
     * @param priority the priority
     * @return the priority
     */
    public  TodoBuilder setPriority(Integer priority) {
      // in case when creating new object for setter and priority is null
      if (priority == null) {
        return this;
      }
      this.priority = priority;
      return this;
    }


    /**
     * Sets category.
     *
     * @param category the category
     * @return the category
     */
    public TodoBuilder setCategory(String category) {
      this.category = category;
      return this;
    }

    /**
     * Build todo.
     *
     * @return the todo
     */
    public Todo build() {
      return new Todo(this);
    }
  }
}
