package voyage.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import voyage.model.Listings;
import voyage.model.Reviews;
import voyage.model.Users;

public class ReviewsDao {
	
	protected ConnectionManager connectionManager;
	private static ReviewsDao instance = null;
	protected ReviewsDao() {
		connectionManager = new ConnectionManager();
	}
	public static ReviewsDao getInstance() {
		if(instance == null) {
			instance = new ReviewsDao();
		}
		return instance;
	}

	public Reviews create(Reviews review) throws SQLException {
		String insertReview = "INSERT INTO Reviews(ListingId,UserName,Date,Comments,Rating) VALUES(?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertReview,
					Statement.RETURN_GENERATED_KEYS);
			
			insertStmt.setInt(1, review.getListing().getListingId());
			insertStmt.setString(2, review.getUser().getUserName());
			insertStmt.setTimestamp(2, new Timestamp(review.getDate().getTime()));
			insertStmt.setString(4, review.getComments());
			insertStmt.setDouble(5, review.getRating());
			
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int reviewId = -1;
			if(resultKey.next()) {
				reviewId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			review.setReviewId(reviewId);
			
			return review;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
		}
	}
	
	public List<Reviews> getReviewsByListing(Listings listing) throws SQLException {
		List<Reviews> reviews = new ArrayList<>();
		
		String selectReview = "SELECT ReviewId,ListingId,UserName,Date,Comments,Rating FROM Reviews WHERE ListingId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReview);
			selectStmt.setInt(1, listing.getListingId());
			results = selectStmt.executeQuery();
			
			UsersDao usersDao = UsersDao.getInstance();
			while(results.next()) {
				int reviewId = results.getInt("ReviewId");
				String username = results.getString("UserName");
				Users user = usersDao.getUsersByUserName(username);
				Date date = results.getDate("Date");
				String comments = results.getString("Comments");
				Double rating = results.getDouble("Rating");
				Reviews review = new Reviews(reviewId, listing, user, date, comments, rating);
				reviews.add(review);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return reviews;
	}
	
	public List<Reviews> getReviewsByUser(Users user) throws SQLException {
		List<Reviews> reviews = new ArrayList<>();
		
		String selectReview = "SELECT ReviewId,ListingId,UserName,Date,Comments,Rating FROM Reviews WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReview);
			selectStmt.setString(1, user.getUserName());
			results = selectStmt.executeQuery();
			
			ListingsDao listingsDao = ListingsDao.getInstance();
			while(results.next()) {
				int reviewId = results.getInt("ReviewId");
				int listingId = results.getInt("ListingId");
				Listings listing = listingsDao.getListingByListingId(listingId);
				Date date = results.getDate("Date");
				String comments = results.getString("Comments");
				Double rating = results.getDouble("Rating");
				Reviews review = new Reviews(reviewId, listing, user, date, comments, rating);
				reviews.add(review);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return reviews;
	}
	
	public Reviews delete(Reviews review) throws SQLException {
		String deleteReview = "DELETE FROM Reviews WHERE ReviewId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteReview);
			deleteStmt.setInt(1, review.getReviewId());
			deleteStmt.executeUpdate();

			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}
	
}
