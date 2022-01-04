package voyage.dal;

import voyage.dal.AmenitiesDao;
import voyage.model.Amenities;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AmenitiesDao {
	protected ConnectionManager connectionManager;
	private static AmenitiesDao instance = null;
	protected AmenitiesDao() {
		connectionManager = new ConnectionManager();
	}
	public static AmenitiesDao getInstance() {
		if(instance == null) {
			instance = new AmenitiesDao();
		}
		return instance;
	}
	
	
    public Amenities create(Amenities amenities) throws SQLException {
        String insertAmenities =
                "INSERT INTO Amenities(AmenityId,Type) " +
                        "VALUES(?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertAmenities);
            insertStmt.setInt(1, amenities.getAmenityId());
            insertStmt.setString(2, amenities.getType());
            
            insertStmt.executeUpdate();
            return amenities;
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
    
    
    public Amenities getAmenitiesById(int amenityId) throws SQLException {
        String selectAmenities= "SELECT AmenityId,Type FROM Amenities WHERE AmenityId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectAmenities);
            selectStmt.setInt(1, amenityId);
        
            results = selectStmt.executeQuery();
 
            if(results.next()) {
                int AmenitiesamenityId = results.getInt("AmenityId");
                String type = results.getString("Type");
                Amenities amenities = new Amenities(AmenitiesamenityId,type);
                
                return amenities;
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

    public List<Amenities> getAmenitiesByType(String type) throws SQLException {
    	List<Amenities> amenitiesList = new ArrayList<Amenities>();
		String selectAmenities = "SELECT AmenityId,Type FROM Amenities WHERE Type=?;";
		
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAmenities);
			selectStmt.setString(1, type);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int AmenitiesamenityId = results.getInt("AmenityId");
                String Resulttype = results.getString("Type");
                
                Amenities amenities = new Amenities(AmenitiesamenityId,Resulttype);
                
                amenitiesList.add(amenities);
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
		return amenitiesList;
    }


    public Amenities delete(Amenities amenities) throws SQLException {
        String deleteAmenities = "DELETE FROM Amenities WHERE AmenityId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteAmenities);
            deleteStmt.setInt(1, amenities.getAmenityId());
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
