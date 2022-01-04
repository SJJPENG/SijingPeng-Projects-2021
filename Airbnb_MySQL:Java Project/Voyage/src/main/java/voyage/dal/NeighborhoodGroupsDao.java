package voyage.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import voyage.model.*;


public class NeighborhoodGroupsDao {
	
	protected ConnectionManager connectionManager;
	private static NeighborhoodGroupsDao instance = null;
	protected NeighborhoodGroupsDao() {
		connectionManager = new ConnectionManager();
	}
	public static NeighborhoodGroupsDao getInstance() {
		if(instance == null) {
			instance = new NeighborhoodGroupsDao();
		}
		return instance;
	}

	public NeighborhoodGroups create(NeighborhoodGroups neighborhoodGroups) throws SQLException {
		String insertNeighborhoodGroups = "INSERT INTO NeighborhoodGroups(NeighborhoodGroupId,Name,CityId) VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertNeighborhoodGroups);	
			insertStmt.setInt(1, neighborhoodGroups.getNeighborhoodGroupId());
			insertStmt.setString(2, neighborhoodGroups.getName());
			insertStmt.setInt(3, neighborhoodGroups.getCity().getCityId());
			insertStmt.executeUpdate();
			return neighborhoodGroups;
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
	
	
	public NeighborhoodGroups getNeighborhoodGroupsByNeighborhoodGroupsId(int NeighborhoodGroupId) throws SQLException {
		String selectNeighborhoodGroups = "SELECT NeighborhoodGroupId,Name,CityId FROM NeighborhoodGroups WHERE NeighborhoodGroupId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectNeighborhoodGroups);
			selectStmt.setInt(1, NeighborhoodGroupId);
			results = selectStmt.executeQuery();
			
			if(results.next()) {
				int resultNeighborhoodGroupId = results.getInt("NeighborhoodGroupId");
				String name = results.getString("Name");
				int cityId = results.getInt("CityId");
				CitiesDao citiesDao = CitiesDao.getInstance();
				Cities cities = citiesDao.getCitiesByCityId(cityId);
				NeighborhoodGroups neighborhoodGroup = new NeighborhoodGroups(resultNeighborhoodGroupId,name,cities);
				return neighborhoodGroup;
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

	
	public List<NeighborhoodGroups> getNeighborhoodGroupsByName(String Name) throws SQLException {
		List<NeighborhoodGroups> neighborhoodGroups = new ArrayList<NeighborhoodGroups>();
		String selectNeighborhoodGroups =
			"SELECT NeighborhoodGroupId,Name,CityId FROM NeighborhoodGroups WHERE NeighborhoodGroupId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectNeighborhoodGroups);
			selectStmt.setString(1, Name);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int neighborhoodGroupId = results.getInt("NeighborhoodGroupId");
				String resultName = results.getString("Name");
				int cityId = results.getInt("CityId");
				CitiesDao citiesDao = CitiesDao.getInstance();
				Cities cities = citiesDao.getCitiesByCityId(cityId);
				NeighborhoodGroups neighborhoodGroup = new NeighborhoodGroups(neighborhoodGroupId,resultName,cities);
				neighborhoodGroups.add(neighborhoodGroup);
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
		return neighborhoodGroups;
	}
	
	
	public NeighborhoodGroups delete(NeighborhoodGroups neighborhoodGroup) throws SQLException {
		String deleteNeighborhoodGroup = "DELETE FROM NeighborhoodGroups WHERE NeighborhoodGroupId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteNeighborhoodGroup);
			deleteStmt.setInt(1, neighborhoodGroup.getNeighborhoodGroupId());
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
