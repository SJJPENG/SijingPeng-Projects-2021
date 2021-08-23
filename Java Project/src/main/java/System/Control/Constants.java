package System.Control;

/**
 * Constants used in the control branch
 */
public class Constants {

  public static final String CSV = "--csv-file";
  public static final String ADD = "--add-todo";
  public static final String TEXT = "--todo-text";
  public static final String DUE = "--due";
  public static final String PRIORITY = "--priority";
  public static final String CATEGORY = "--category";
  public static final String COMPLETE = "--complete";
  public static final String COMPLETE_TODO = "--complete-todo";
  public static final String DISPLAY = "--display";
  public static final String SHOW_INC = "--show-incomplete";
  public static final String SHOW_CAT = "--show-category";
  public static final String SORT_DATE = "--sort-by-date";
  public static final String SORT_PRIORITY = "--sort-by-priority";

  public static final String[] NO_ARGUMENT = {ADD, COMPLETE, DISPLAY, SHOW_INC, SORT_PRIORITY, SORT_DATE};

  public static final String[] NEED_ARGUMENT = {CSV, TEXT, DUE, PRIORITY, CATEGORY, SHOW_CAT};

  public static final String[] ACCEPT_DUPLICATES = {COMPLETE_TODO};

  public static final String USAGE = "Usage guidance for the command line : "
      + "--csv-file <path/to/file> "
      + "--add-todo"  + "  Add a new todo. If this option is provided, then --todo-text must also be provided."+ System.lineSeparator()
      + "--todo-text <description of todo> " + "The CSV file containing the todos. This option is required."+  System.lineSeparator()
      + "--completed" +  "  (Optional) Sets the completed status of a new todo to true." +System.lineSeparator()
      + "--due <due date>" + "  (Optional) Sets the due date of a new todo. You may choose how the date should be formatted." + System.lineSeparator()
      + "--priority <1, 2, or 3>"+ "  (Optional) Sets the priority of a new todo. The value can be 1, 2, or 3." + System.lineSeparator()
      + "--category <a category name>" + "  (Optional) Sets the category of a new todo. The value can be any String. Categories do not need to be pre-defined."+ System.lineSeparator()
      + "--complete-todo <id> " + " Mark the Todo with the provided ID as complete."+ System.lineSeparator()
      + "--display" + " Display todos. If none of the following optional arguments are provided, displays all todos." + System.lineSeparator()
      + "--show-category <category>" + "  (Optional) If --display is provided, only todos with the given category should be displayed." + System.lineSeparator()
      + "--show-incomplete"+ "  (Optional) If --display is provided, only incomplete todos should be displayed." + System.lineSeparator()
      + "--sort-by-date" + "  (Optional) If --display is provided, sort the list of by date order (ascending). Can't be combined with --sort-by-priority." + System.lineSeparator()
      + "--sort-by-priority" + "  (Optional) If --display is provided, sort the list of by priority (ascending). Cannot be combined with --sort-by-date." + System.lineSeparator();
}
