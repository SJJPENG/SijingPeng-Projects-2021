package System.Control;

import static org.junit.Assert.*;

import System.Control.ErrorLogger;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

public class ErrorLoggerTest {
  private ErrorLogger errorLogger;

  @Before
  public void setUp() throws Exception {
    errorLogger = new ErrorLogger();
  }

  @Test
  public void getLog() {
    assertTrue(errorLogger.getLog().equals(new ArrayList<>()));
  }

  @Test
  public void isEmpty() {
    assertTrue(errorLogger.getLog().isEmpty());
  }

  @Test
  public void log() {
    assertTrue(errorLogger.getLog().isEmpty());
    errorLogger.log("Some error message");
    assertEquals(1, errorLogger.getLog().size());
  }


  @Test
  public void testEquals() {
    assertTrue(errorLogger.equals(errorLogger));

    ErrorLogger errorLogger2 = new ErrorLogger();
    errorLogger2.log("Some error message");
    assertFalse(errorLogger.equals(errorLogger2));
  }

  @Test
  public void testHashCode() {
    errorLogger.log("Some error message");
    ErrorLogger errorLogger2 = new ErrorLogger();
    errorLogger2.log("Some error message");

    assertEquals(errorLogger.hashCode(), errorLogger2.hashCode());
  }

  @Test
  public void testToString() {
    assertTrue(errorLogger.toString().equals("Empty log"));
    errorLogger.log("Some error message");

    StringBuilder out = new StringBuilder();
    out.append("Some error message").append(System.lineSeparator());
    assertTrue(errorLogger.toString().equals(out.toString()));
  }
}