package voyage.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import voyage.model.Dislikes;
import voyage.model.Listings;
import voyage.model.Users;

public class DislikesDao {

	protected ConnectionManager connectionManager;
	private static DislikesDao instance = null;
	protected DislikesDao() {
		connectionManager = new ConnectionManager();
	}
	public static DislikesDao getInstance() {
		if(instance == null) {
			instance = new DislikesDao();
		}
		return instance;
	}

	public Dislikes create(Dislikes dislike) throws SQLException {
		String insertDislike = "INSERT INTO Dislikes(UserName,ListingId) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertDislike, Statement.RETURN_GENERATED_KEYS);	
			insertStmt.setString(1, dislike.getUser().getUserName());
			insertStmt.setInt(2, dislike.getListing().getListingId());
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int dislikeId = -1;
			if(resultKey.next()) {
				dislikeId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			dislike.setDislikeId(dislikeId);
			
			return dislike;
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
	
	public List<Dislikes> getDislikesByUser(Users user) throws SQLException {
		List<Dislikes> dislikes = new ArrayList<>();
		
		String selectDislikes = "SELECT DislikeId,UserName,ListingId FROM Dislikes WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectDislikes);
			selectStmt.setString(1, user.getUserName());
			results = selectStmt.executeQuery();
			
			ListingsDao listingsDao = ListingsDao.getInstance();
			while(results.next()) {
				int dislikeId = results.getInt("DislikeId");
				int listingId = results.getInt("ListingId");
				Listings listing = listingsDao.getListingByListingId(listingId);
				Dislikes dislike = new Dislikes(dislikeId, user, listing);
				dislikes.add(dislike);
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
		return dislikes;
	}
	
	public List<Dislikes> getDislikesByListing(Listings listing) throws SQLException {
		List<Dislikes> dislikes = new ArrayList<>();
		
		String selectDislikes = "SELECT DislikeId,UserName,ListingId FROM Dislikes WHERE ListingId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectDislikes);
			selectStmt.setInt(1, listing.getListingId());
			results = selectStmt.executeQuery();
			
			UsersDao usersDao = UsersDao.getInstance();
			while(results.next()) {
				int dislikeId = results.getInt("DislikeId");
				String userName = results.getString("UserName");
				Users user = usersDao.getUsersByUserName(userName);
				Dislikes dislike = new Dislikes(dislikeId, user, listing);
				dislikes.add(dislike);
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
		return dislikes;
	}
	
	public Dislikes delete(Dislikes dislike) throws SQLException {
		String deleteLike = "DELETE FROM Dislikes WHERE DislikeId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteLike);
			deleteStmt.setInt(1, dislike.getDislikeId());
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
