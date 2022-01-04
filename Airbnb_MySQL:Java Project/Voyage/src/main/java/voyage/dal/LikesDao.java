package voyage.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import voyage.model.Likes;
import voyage.model.Listings;
import voyage.model.Users;

public class LikesDao {
	
	protected ConnectionManager connectionManager;
	private static LikesDao instance = null;
	protected LikesDao() {
		connectionManager = new ConnectionManager();
	}
	public static LikesDao getInstance() {
		if(instance == null) {
			instance = new LikesDao();
		}
		return instance;
	}

	public Likes create(Likes like) throws SQLException {
		String insertLike = "INSERT INTO Likes(UserName,ListingId) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertLike, Statement.RETURN_GENERATED_KEYS);	
			insertStmt.setString(1, like.getUser().getUserName());
			insertStmt.setInt(2, like.getListing().getListingId());
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int likeId = -1;
			if(resultKey.next()) {
				likeId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			like.setLikeId(likeId);
			
			return like;
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
	
	public List<Likes> getLikesByUser(Users user) throws SQLException {
		List<Likes> likes = new ArrayList<>();
		
		String selectLikes = "SELECT LikeId,UserName,ListingId FROM Likes WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectLikes);
			selectStmt.setString(1, user.getUserName());
			results = selectStmt.executeQuery();
			
			ListingsDao listingsDao = ListingsDao.getInstance();
			while(results.next()) {
				int likeId = results.getInt("LikeId");
				int listingId = results.getInt("ListingId");
				Listings listing = listingsDao.getListingByListingId(listingId);
				Likes like = new Likes(likeId, user, listing);
				likes.add(like);
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
		return likes;
	}
	
	public List<Likes> getLikesByListing(Listings listing) throws SQLException {
		List<Likes> likes = new ArrayList<>();
		
		String selectLikes = "SELECT LikeId,UserName,ListingId FROM Likes WHERE ListingId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectLikes);
			selectStmt.setInt(1, listing.getListingId());
			results = selectStmt.executeQuery();
			
			UsersDao usersDao = UsersDao.getInstance();
			while(results.next()) {
				int likeId = results.getInt("LikeId");
				String userName = results.getString("UserName");
				Users user = usersDao.getUsersByUserName(userName);
				Likes like = new Likes(likeId, user, listing);
				likes.add(like);
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
		return likes;
	}
	
	public Likes delete(Likes like) throws SQLException {
		String deleteLike = "DELETE FROM Likes WHERE LikeId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteLike);
			deleteStmt.setInt(1, like.getLikeId());
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
