package voyage.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import voyage.model.*;


public class CitiesDao {
	
	protected ConnectionManager connectionManager;
	private static CitiesDao instance = null;
	protected CitiesDao() {
		connectionManager = new ConnectionManager();
	}
	public static CitiesDao getInstance() {
		if(instance == null) {
			instance = new CitiesDao();
		}
		return instance;
	}

	public Cities create(Cities city) throws SQLException {
		String insertCity = "INSERT INTO Cities(CityId,Name,State,Country) VALUES(?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertCity);	
			insertStmt.setInt(1, city.getCityId());
			insertStmt.setString(2, city.getName());
			insertStmt.setString(3, city.getState());
			insertStmt.setString(4, city.getCountry());
			insertStmt.executeUpdate();
			return city;
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
	
	
	public Cities getCitiesByCityId(int cityId) throws SQLException {
		String selectCity = "SELECT CityId,Name,State,Country FROM Cities WHERE CityId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCity);
			selectStmt.setInt(1, cityId);
			results = selectStmt.executeQuery();
			
			if(results.next()) {
				int resultCityId = results.getInt("CityId");
				String name = results.getString("Name");
				String state = results.getString("State");
				String country = results.getString("Country");
				Cities city = new Cities(resultCityId, name, state,country);
				return city;
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

	
	public List<Cities> getCitiesByName(String Name) throws SQLException {
		List<Cities> cities = new ArrayList<Cities>();
		String selectCities =
			"SELECT CityId,Name,State,Country FROM Cities WHERE Name=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCities);
			selectStmt.setString(1, Name);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int cityId = results.getInt("CityId");
				String resultName = results.getString("Name");
				String state = results.getString("State");
				String country = results.getString("Country");
				Cities city = new Cities(cityId, resultName, state,country);
				cities.add(city);
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
		return cities;
	}
	
	
	public Cities delete(Cities city) throws SQLException {
		String deleteCity = "DELETE FROM Cities WHERE CityId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteCity);
			deleteStmt.setInt(1, city.getCityId());
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
