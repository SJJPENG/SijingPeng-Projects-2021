package voyage.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import voyage.model.*;

public class NeighborhoodsDao {
	
	protected ConnectionManager connectionManager;
	private static NeighborhoodsDao instance = null;
	protected NeighborhoodsDao() {
		connectionManager = new ConnectionManager();
	}
	public static NeighborhoodsDao getInstance() {
		if(instance == null) {
			instance = new NeighborhoodsDao();
		}
		return instance;
	}
	
	public Neighborhoods create(Neighborhoods neighborhood) throws SQLException {
		String insertNeighborhood = "INSERT INTO Neighborhoods(neighborhoodId, name, neighborhoodGroupId) VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertNeighborhood);	
			insertStmt.setInt(1, neighborhood.getNeighborhoodId());
			insertStmt.setString(2, neighborhood.getName());
			insertStmt.setInt(3, neighborhood.getNeighborhoodGroupId());
			insertStmt.executeUpdate();
			return neighborhood;
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
	
	
	public Neighborhoods getNeighborhoodsByNeighborhoodId(int NeighborhoodId) throws SQLException {
		String selectNeighborhoods = "SELECT NeighborhoodId,Name,NeighborhoodGroupId FROM Neighborhoods WHERE NeighborhoodId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectNeighborhoods);
			selectStmt.setInt(1, NeighborhoodId);
			results = selectStmt.executeQuery();
			
			if(results.next()) {
				int resultNeighborhoodId = results.getInt("NeighborhoodId");
				String name = results.getString("Name");
				int neighborhoodGroupId = results.getInt("NeighborhoodGroupId");
				NeighborhoodGroupsDao neighborhoodGroupsDao = NeighborhoodGroupsDao.getInstance();
				NeighborhoodGroups neighborhoodGroup = neighborhoodGroupsDao.getNeighborhoodGroupsByNeighborhoodGroupsId(neighborhoodGroupId);
				Neighborhoods neighborhood = new Neighborhoods(resultNeighborhoodId,name,neighborhoodGroup);
				return neighborhood;
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
		return null;
	}

	
	public List<Neighborhoods> getNeighborhoodsByName(String Name) throws SQLException {
		List<Neighborhoods> neighborhoods = new ArrayList<Neighborhoods>();
		String selectNeighborhoods =
			"SELECT NeighborhoodId,Name,NeighborhoodGroupId FROM Neighborhoods WHERE NeighborhoodId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectNeighborhoods);
			selectStmt.setString(1, Name);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int neighborhoodId = results.getInt("NeighborhoodId");
				String resultName = results.getString("Name");
				int neighborhoodGroupId = results.getInt("NeighborhoodGroupId");
				NeighborhoodGroupsDao neighborhoodGroupsDao = NeighborhoodGroupsDao.getInstance();
				NeighborhoodGroups neighborhoodGroup = neighborhoodGroupsDao.getNeighborhoodGroupsByNeighborhoodGroupsId(neighborhoodGroupId);
				Neighborhoods neighborhood = new Neighborhoods(neighborhoodId,resultName,neighborhoodGroup);
				neighborhoods.add(neighborhood);
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
		return neighborhoods;
	}
	
	
	public Neighborhoods delete(Neighborhoods neighborhood) throws SQLException {
		String deleteNeighborhoodGroup = "DELETE FROM Neighborhoods WHERE NeighborhoodId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteNeighborhoodGroup);
			deleteStmt.setInt(1, neighborhood.getNeighborhoodId());
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
