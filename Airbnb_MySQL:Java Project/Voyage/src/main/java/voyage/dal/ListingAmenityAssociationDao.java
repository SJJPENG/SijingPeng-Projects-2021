package voyage.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import voyage.model.Amenities;
import voyage.model.ListingAmenityAssociation;
import voyage.model.Listings;

public class ListingAmenityAssociationDao {
	
	protected ConnectionManager connectionManager;
	private static ListingAmenityAssociationDao instance = null;
	protected ListingAmenityAssociationDao() {
		connectionManager = new ConnectionManager();
	}
	public static ListingAmenityAssociationDao getInstance() {
		if(instance == null) {
			instance = new ListingAmenityAssociationDao();
		}
		return instance;
	}
	
	public ListingAmenityAssociation create(ListingAmenityAssociation amenityAsso) throws SQLException {
        String insertAmenityAsso =
                "INSERT INTO ListingAmenityAssociation(AmenityAssoId,ListingId,AmenityId) " +
                        "VALUES(?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertAmenityAsso);
            insertStmt.setInt(1, amenityAsso.getAmenityAssoId());
            insertStmt.setInt(2, amenityAsso.getListings().getListingId());
            insertStmt.setInt(3, amenityAsso.getAmenity().getAmenityId());
            
            
            insertStmt.executeUpdate();
            return amenityAsso;
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
    
    
    public ListingAmenityAssociation getAmenityAssoById(int amenityAssoId) throws SQLException {
        String selectAmenityAsso= "SELECT AmenityAssoId,ListingId,AmenityId FROM "
        		+ "ListingAmenityAssociation WHERE AmenityAssoId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectAmenityAsso);
            selectStmt.setInt(1, amenityAssoId);
            results = selectStmt.executeQuery();
 
            if(results.next()) {
            	int resultAmenityAssoId = results.getInt("AmenityAssoId");
                int resListingId = results.getInt("ListingId");
                int resAmenityId = results.getInt("AmenityId");
                
                ListingsDao listingsDao = ListingsDao.getInstance();
                Listings listing = listingsDao.getListingByListingId(resListingId);
                
                AmenitiesDao amenitiesDao = AmenitiesDao.getInstance();
                Amenities amenity = amenitiesDao.getAmenitiesById(resAmenityId);
                
                ListingAmenityAssociation asso = new ListingAmenityAssociation(resultAmenityAssoId,listing, amenity);
                
                return asso;
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

    public List<ListingAmenityAssociation> getAmenityAssoByListingId(int listingId) throws SQLException {
    	List<ListingAmenityAssociation> assoList = new ArrayList<ListingAmenityAssociation>();
		String selectAmenityAsso = "SELECT AmenityAssoId,ListingId,AmenityId FROM "
        		+ "ListingAmenityAssociation WHERE ListingId=?;";
		
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAmenityAsso);
			selectStmt.setInt(1, listingId);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int resultAmenityAssoId = results.getInt("AmenityAssoId");
                int resListingId = results.getInt("ListingId");
                int resAmenityId = results.getInt("AmenityId");
                
                ListingsDao listingsDao = ListingsDao.getInstance();
                Listings listing = listingsDao.getListingByListingId(resListingId);
                
                AmenitiesDao amenitiesDao = AmenitiesDao.getInstance();
                Amenities amenity = amenitiesDao.getAmenitiesById(resAmenityId);
                
                ListingAmenityAssociation asso = new ListingAmenityAssociation(resultAmenityAssoId,listing, amenity);
                
                assoList.add(asso);
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
		return assoList;
    }
    
    public List<ListingAmenityAssociation> getAmenityAssoByAmenityId(int AmenityId) throws SQLException {
    	List<ListingAmenityAssociation> assoList = new ArrayList<ListingAmenityAssociation>();
		String selectAmenityAsso = "SELECT AmenityAssoId,ListingId,AmenityId FROM "
        		+ "ListingAmenityAssociation WHERE AmenityId=?;";
		
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAmenityAsso);
			selectStmt.setInt(1, AmenityId);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int resultAmenityAssoId = results.getInt("AmenityAssoId");
                int resListingId = results.getInt("ListingId");
                int resAmenityId = results.getInt("AmenityId");
                
                ListingsDao listingsDao = ListingsDao.getInstance();
                Listings listing = listingsDao.getListingByListingId(resListingId);
                
                AmenitiesDao amenitiesDao = AmenitiesDao.getInstance();
                Amenities amenity = amenitiesDao.getAmenitiesById(resAmenityId);
                
                ListingAmenityAssociation asso = new ListingAmenityAssociation(resultAmenityAssoId,listing, amenity);
                
                assoList.add(asso);
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
		return assoList;
    }


    public ListingAmenityAssociation delete(ListingAmenityAssociation amenityAsso) throws SQLException {
        String deleteAsso = "DELETE FROM ListingAmenityAssociation WHERE AmenityAssoId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteAsso);
            deleteStmt.setInt(1, amenityAsso.getAmenityAssoId());
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
