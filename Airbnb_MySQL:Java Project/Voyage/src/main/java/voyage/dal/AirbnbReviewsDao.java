package voyage.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import voyage.model.AirbnbReviews;

public class AirbnbReviewsDao {

  private static AirbnbReviewsDao instance = null;
  protected ConnectionManager connectionManager;

  protected AirbnbReviewsDao() {
    connectionManager = new ConnectionManager();
  }

  public static AirbnbReviewsDao getInstance() {
    if (instance == null) {
      instance = new AirbnbReviewsDao();
    }
    return instance;
  }

  public AirbnbReviews create(AirbnbReviews airbnbReviews) throws SQLException {
    String insertAirbnbReviews = "INSERT INTO AirbnbReviews(ListingId, ReviewId, Date, ReviewerId, Comments) VALUES (?,?,?,?,?)";
    Connection connection = null;
    PreparedStatement insertStatement = null;
    try {
      connection = connectionManager.getConnection();
      insertStatement = connection.prepareStatement(insertAirbnbReviews);
      insertStatement.setInt(1, airbnbReviews.getListingId());
      insertStatement.setLong(2, airbnbReviews.getReviewId());
      insertStatement.setDate(3, airbnbReviews.getDate());
      insertStatement.setLong(4, airbnbReviews.getReviewerId());
      insertStatement.setString(5, airbnbReviews.getComments());
      insertStatement.executeUpdate();
      return airbnbReviews;
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


  public List<AirbnbReviews> getAirbnbReviewsByListingId(int listingId) throws SQLException {
    List<AirbnbReviews> airbnbReviews = new ArrayList<AirbnbReviews>();
    String selectAirbnbReviews =
        "SELECT ListingId, ReviewId, Date, ReviewerId, Comments FROM AirbnbReviews WHERE ListingId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectAirbnbReviews);
      selectStmt.setInt(1, listingId);
      results = selectStmt.executeQuery();
      while (results.next()) {
        long reviewId = results.getLong("ReviewId");
        Date date = results.getDate("Date");
        long reviewerId = results.getLong("ReviewerId");
        String comments = results.getString("Comments");
        AirbnbReviews review = new AirbnbReviews(listingId, reviewId, date, reviewerId, comments);
        airbnbReviews.add(review);
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
    return airbnbReviews;
  }

  public List<AirbnbReviews> getAirbnbReviewsByAirbnbReviewerId(long reviewerId)
      throws SQLException {
    List<AirbnbReviews> airbnbReviews = new ArrayList<AirbnbReviews>();
    String selectAirbnbReviews =
        "SELECT ListingId, ReviewId, Date, ReviewerId, Comments FROM AirbnbReviews WHERE ReviewerId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectAirbnbReviews);
      selectStmt.setLong(1, reviewerId);
      results = selectStmt.executeQuery();
      while (results.next()) {
        int listingId = results.getInt("ListingId");
        long reviewId = results.getLong("ReviewId");
        Date date = results.getDate("Date");
        String comments = results.getString("Comments");
        AirbnbReviews review = new AirbnbReviews(listingId, reviewId, date, reviewerId, comments);
        airbnbReviews.add(review);
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
    return airbnbReviews;
  }


  public AirbnbReviews delete(AirbnbReviews airbnbReviews) throws SQLException {
    String deleteAirbnbReviews = "DELETE FROM AirbnbReviews WHERE reviewsId=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteAirbnbReviews);
      deleteStmt.setLong(1, airbnbReviews.getReviewId());
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
