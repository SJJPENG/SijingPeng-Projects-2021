package System.Model;

import System.Model.Todo.TodoBuilder;
import System.Exceptions.IllegalExecuteException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * The type Data base processor.
 */
public class DataBaseProcessor {

  private static final String HEADER = "\"id\",\"text\",\"completed\",\"due\",\"priority\",\"category\"";
  private static final int TEXT_INDEX = 1;
  private static final int COMPLETION_INDEX = 2;
  private static final int DATE_INDEX = 3;
  private static final int PRIORITY_INDEX = 4;
  private static final int CATEGORY_INDEX = 5;
  private final String path;
  private Map<String, Todo> database;
  private Integer size;


  /**
   * Instantiates a new Data base processor.
   *
   * @param path the path
   */
  public DataBaseProcessor(String path) {
    this.database = new HashMap<>();
    this.path = path;
    this.size = 0;
  }

  /**
   * Conversion to list list.
   *
   * @return the list
   */
  public List<Todo> conversionToList() {
    return this.database.values().stream().collect(Collectors.toList());
  }

  /**
   * Sort Todos by DueDate
   *
   * @param todos the todos
   * @return a sorted stream of Todos
   */
  public static List<Todo> sortByDate(List<Todo> todos) {
    Comparator<Todo> todoComparator = Comparator.comparing(Todo::getDueDate,
        Comparator.nullsLast(Comparator.naturalOrder())).thenComparing(Todo::isCompleted);
    return todos.stream().sorted(todoComparator).collect(Collectors.toList());
  }

  /**
   * Sort by Priority
   *
   * @param todos the todos
   * @return the list
   */
  public static List<Todo> sortByPriority(List<Todo> todos) {
    Comparator<Todo> todoComparator = Comparator.comparing(Todo::getPriority,
        Comparator.nullsLast(Comparator.naturalOrder())).thenComparing(Todo::isCompleted);
    return todos.stream().sorted(todoComparator).collect(Collectors.toList());
  }

  /**
   * Filter out incomplete Todos
   *
   * @param todos the todos
   * @return a filtered stream of Todos
   */
  public static List<Todo> filterByIncomplete(List<Todo> todos) {
    return todos.stream().filter(Objects::nonNull)
        .filter(Todo -> Objects.nonNull(Todo.isCompleted()))
        .filter(Todo -> !Todo.isCompleted()).collect(Collectors.toList());
  }

  /**
   * Filter by category list.
   *
   * @param category the category
   * @param todos    the todos
   * @return the list
   */
  public static List<Todo> filterByCategory(String category, List<Todo> todos) {
    return todos.stream().filter(Objects::nonNull)
        .filter(Todo -> Objects.nonNull(Todo.getCategory()))
        .filter(Todo -> Todo.getCategory().equals(category)).collect(Collectors.toList());
  }

  /**
   * Gets database.
   *
   * @return the database
   */
  public Map<String, Todo> getDatabase() {
    return this.database;
  }

  /**
   * Gets size.
   *
   * @return the size
   */
  public Integer getSize() {
    return this.size;
  }

  /**
   * Process data.
   *
   * @throws IllegalExecuteException the illegal execute exception
   */
  public void processData() throws IllegalExecuteException {
    ArrayList<String> rawData = this.readCsv();
    ArrayList<ArrayList<String>> cleanedData = this.organizeData(rawData);
    this.createMap(cleanedData);
    this.size = this.database.size();
  }

  /**
   * Create map
   * @param cleanedData cleaned data from the csv file
   */
  private void createMap(ArrayList<ArrayList<String>> cleanedData) {
    for (int i = 1; i < cleanedData.size(); i++) {
      this.constructTodo(i, cleanedData.get(i));
    }
  }

  /**
   * Construct todo.
   *
   * @param id       the id
   * @param todoData the todo data
   */
  protected void constructTodo(Integer id, ArrayList<String> todoData) {
    TodoBuilder todoBuilder = new TodoBuilder(todoData.get(TEXT_INDEX));
    todoBuilder.complete(todoData.get(COMPLETION_INDEX));
      if (!todoData.get(DATE_INDEX).equals("?")) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/dd/yyyy");
        todoBuilder.setDueDate(LocalDate.parse(todoData.get(DATE_INDEX), formatter));
      }
      if (!todoData.get(PRIORITY_INDEX).equals("?")) {
        todoBuilder.setPriority(Integer.valueOf(todoData.get(PRIORITY_INDEX)));
      }
      if (!todoData.get(CATEGORY_INDEX).equals("?")) {
        todoBuilder.setCategory(todoData.get(CATEGORY_INDEX));
      }
      this.database.put(id.toString(), todoBuilder.build());
  }


  /**
   * Read the csv file line by line and construct a Todo for each Todo
   *
   * @return the array list
   * @throws IllegalExecuteException the illegal execute exception
   */
  protected ArrayList<String> readCsv() throws IllegalExecuteException {
    try {
      return new BufferedReader(new FileReader(this.path))
          .lines().collect(Collectors.toCollection(ArrayList::new));
    } catch (FileNotFoundException e) {
      throw new IllegalExecuteException("*** OOPS! the csv file was not found");
    }
  }

  /**
   * Helper method to organize csvContent.
   *
   * @param csvContent ArrayList of strings represents data in csv
   */
  private ArrayList<ArrayList<String>> organizeData(ArrayList<String> csvContent) {
    ArrayList<ArrayList<String>> res = new ArrayList<>();
    for (String s : csvContent) {
      res.add(this.cleanLine(s));
    }
    return res;
  }

  /**
   * Helper method to clean a line , get rid of any unwanted double quotes and comma
   *
   * @param line a string
   * @return a clean string without any unwanted double quotes and comma
   */
  private ArrayList<String> cleanLine(String line) {
    String[] split = line.split(",\"");
    return Arrays.stream(split).map(value -> value.replace("\"", ""))
        .collect(Collectors.toCollection(ArrayList::new));
  }

  /**
   * Format output list.
   *
   * @return the list
   */
  protected List<String> formatOutput() {
    List<String> csvContent = new ArrayList<>();
    csvContent.add(HEADER);
    for (int i = 1; i < this.size + 1; i++) {
      String toDoToString = "\"" + i + "\"," + this.database.get(String.valueOf(i)).toString();
      csvContent.add(toDoToString);
    }
    return csvContent;
  }

  /**
   * Update the CSV file
   *
   * @throws IllegalExecuteException the illegal execute exception
   */
  protected void updateCsv() throws IllegalExecuteException {
    this.formatOutput();
    List<String> csvContent = this.formatOutput();
    // chose to export to new csv to maintain the integrity of the original file used for testing
    try (BufferedWriter outputFile = new BufferedWriter(new FileWriter("todos_updated.csv"))) {
      for (String line : csvContent) {
        outputFile.write(line + System.lineSeparator());
      }
    } catch (IOException ioe) {
      throw new IllegalExecuteException("Something went wrong when update csv file");
    }
  }


  /**
   * Add a new Todo
   *
   * @param newTodo the new Todo to add
   * @throws IllegalExecuteException the illegal execute exception
   */
  public void addNewTodo(Todo newTodo) throws IllegalExecuteException {
    this.size += 1;
    this.database.put(this.size.toString(), newTodo);
    this.updateCsv();
  }

  /**
   * Complete todo.
   *
   * @param id the id
   * @throws IllegalExecuteException the illegal execute exception
   */
  public void completeTodo(String id) throws IllegalExecuteException{
      if(this.database.get(id) == null){
        throw new IllegalExecuteException("id doesn't exist");
      }

      if (!this.database.get(id).isCompleted()) {
        Todo updatedAsComplete = this.database.get(id).setAsCompleted();
        this.database.replace(id, updatedAsComplete);
        this.updateCsv();
      }
  }

}
