package voyage.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import voyage.model.AirbnbReviewers;

public class AirbnbReviewersDao {

  private static AirbnbReviewersDao instance = null;
  protected ConnectionManager connectionManager;

  protected AirbnbReviewersDao() {
    connectionManager = new ConnectionManager();
  }

  public static AirbnbReviewersDao getInstance() {
    if (instance == null) {
      instance = new AirbnbReviewersDao();
    }
    return instance;
  }

  public AirbnbReviewers create(AirbnbReviewers airbnbReviewers) throws SQLException {
    String insertAirbnbReviewers = "INSERT INTO AirbnbReviewers(ReviewerId, ReviewerName) VALUES (?,?)";
    Connection connection = null;
    PreparedStatement insertStatement = null;
    try {
      connection = connectionManager.getConnection();
      insertStatement = connection.prepareStatement(insertAirbnbReviewers);
      insertStatement.setLong(1, airbnbReviewers.getReviewerId());
      insertStatement.setString(2, airbnbReviewers.getReviewerName());
      insertStatement.executeUpdate();
      return airbnbReviewers;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) {
        connection.close();
      }
      if (insertStatement != null) {
        insertStatement.close();
      }
    }
  }

  public AirbnbReviewers getAirbnbReviewersById(long airbnbReviewerId) throws SQLException {
    String selectReviewers = "SELECT ReviewerId, ReviewerName FROM AirbnbReviewers WHERE ReviewerId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectReviewers);
      selectStmt.setLong(1, airbnbReviewerId);
      results = selectStmt.executeQuery();

      if (results.next()) {
        long id = results.getLong("ReviewerId");
        String name = results.getString("ReviewerName");
        return new AirbnbReviewers(id, name);
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) {
        connection.close();
      }
      if (selectStmt != null) {
        selectStmt.close();
      }
      if (results != null) {
        results.close();
      }
    }
    return null;
  }

  public List<AirbnbReviewers> getAirbnbReviewersName(String name) throws SQLException {
    List<AirbnbReviewers> reviewers = new ArrayList<AirbnbReviewers>();
    String selectReviewers = "SELECT ReviewerId, ReviewerName FROM AirbnbReviewers WHERE ReviewerName=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectReviewers);
      selectStmt.setString(1, name);
      results = selectStmt.executeQuery();

      if (results.next()) {
        long id = results.getLong("ReviewerId");
        AirbnbReviewers reviewer = new AirbnbReviewers(id, name);
        reviewers.add(reviewer);
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) {
        connection.close();
      }
      if (selectStmt != null) {
        selectStmt.close();
      }
      if (results != null) {
        results.close();
      }
    }
    return reviewers;
  }

  public AirbnbReviewers delete(AirbnbReviewers airbnbReviewers) throws SQLException {
    String deleteAirbnbReviewers = "DELETE FROM AirbnbReviewers WHERE reviewerId=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteAirbnbReviewers);
      deleteStmt.setLong(1, airbnbReviewers.getReviewerId());
      deleteStmt.executeUpdate();
      return null;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) {
        connection.close();
      }
      if (deleteStmt != null) {
        deleteStmt.close();
      }
    }
  }
}

