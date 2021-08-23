package System.Control;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * ErrorLogger class stores all possible errors in our automation system.
 */
public class ErrorLogger {

  private List<String> log;

  /**
   * Construct a new ErrorLogger (empty log);
   */
  public ErrorLogger() {
    this.log = new ArrayList<>();
  }

  /**
   * Returns the error log
   *
   * @return the error log
   */
  protected List<String> getLog() {
    return this.log;
  }

  /**
   * AddNewToDo error event to the error list.
   *
   * @param event error
   */
  public void log(String event) {
    this.log.add(event);
  }

  /**
   * Check if the error list is empty.
   *
   * @return true if empty, false otherwise.
   */
  public boolean isEmpty() {
    return this.log.size() == 0;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ErrorLogger that = (ErrorLogger) o;
    return Objects.equals(log, that.log);
  }

  @Override
  public int hashCode() {
    return Objects.hash(log);
  }

  @Override
  public String toString() {
    if (this.log.isEmpty()) {
      return "Empty log";
    }
    StringBuilder out = new StringBuilder();
    for (String entry : this.log) {
      out.append(entry).append(System.lineSeparator());
    }
    return out.toString();
  }

}

