package System.Control;

import System.Model.DataBaseProcessor;
import System.Model.Todo;
import System.View.Viewer;
import System.Exceptions.IllegalCommandLineException;
import java.util.List;

/**
 * The type Display todos.
 */
public class DisplayTodos implements ICommand {

  private final boolean isShowIncomplete;
  private final String category;
  private final boolean isSortByDueDate;
  private final boolean isSortByPriority;


  /**
   * Constructor for DisplayTodos
   * @param builder display todos builder
   */
  private DisplayTodos(DisplayTodosBuilder builder) {
    this.isShowIncomplete = builder.isShowIncomplete;
    this.category = builder.category;
    this.isSortByDueDate = builder.isSortByDueDate;
    this.isSortByPriority = builder.isSortByPriority;
  }

  @Override
  public void execute(DataBaseProcessor dataBaseProcessor) {
    List<Todo> res = dataBaseProcessor.conversionToList();
    if (category != null) {
      res = DataBaseProcessor.filterByCategory(category, res);
    }
    if (isShowIncomplete) {
      res = DataBaseProcessor.filterByIncomplete(res);
    }
    if (isSortByPriority) {
      res = DataBaseProcessor.sortByPriority(res);
    }
    if (isSortByDueDate) {
      res = DataBaseProcessor.sortByDate(res);
    }
    Viewer.printToScreen(res);
  }


  /**
   * The type Display todos builder.
   */
  public static class DisplayTodosBuilder {

    private boolean isShowIncomplete;
    private String category;
    private boolean isSortByDueDate;
    private boolean isSortByPriority;

    /**
     * Instantiates a new Display todos builder.
     */
    public DisplayTodosBuilder() {
      this.category = null;
      this.isShowIncomplete = false;
      this.isSortByDueDate = false;
      this.isSortByPriority = false;
    }

    /**
     * Show incomplete display todos builder.
     *
     * @param flag the flag
     * @return the display todos builder
     */
    public DisplayTodosBuilder showIncomplete(Boolean flag) {
      this.isShowIncomplete = flag;
      return this;
    }

    /**
     * Show category display todos builder.
     *
     * @param category the category
     * @return the display todos builder
     */
    public DisplayTodosBuilder showCategory(String category) {
      this.category = category;
      return this;
    }


    /**
     * Sort by date display todos builder.
     *
     * @param flag the flag
     * @return the display todos builder
     * @throws IllegalCommandLineException the command line exception
     */
    public DisplayTodosBuilder sortByDate(Boolean flag) throws IllegalCommandLineException {
      this.isSortByDueDate = flag;
      return this;
    }


    /**
     * Sort by priority display todos builder.
     *
     * @param flag the flag
     * @return the display todos builder
     * @throws IllegalCommandLineException the command line exception
     */
    public DisplayTodosBuilder sortByPriority(Boolean flag) throws IllegalCommandLineException {
      this.isSortByPriority = flag;
      return this;
    }

    /**
     * Build display todos.
     *
     * @return the display todos
     */
    public DisplayTodos build() {
      return new DisplayTodos(this);
    }
  }
}
