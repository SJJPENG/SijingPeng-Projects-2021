package voyage.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import voyage.model.Listings;
import voyage.model.PriceCalendar;

public class PriceCalendarDao {

	
	protected ConnectionManager connectionManager;
	private static PriceCalendarDao instance = null;
	protected PriceCalendarDao() {
		connectionManager = new ConnectionManager();
	}
	public static PriceCalendarDao getInstance() {
		if(instance == null) {
			instance = new PriceCalendarDao();
		}
		return instance;
	}
	
	public PriceCalendar create(PriceCalendar priceCalendar) throws SQLException {
        String insertPC =
                "INSERT INTO PriceCalendar(PriceCalendarId,ListingId,Date,Price) " +
                        "VALUES(?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertPC);
            insertStmt.setInt(1, priceCalendar.getPriceCalendarId());
            insertStmt.setInt(2, priceCalendar.getListings().getListingId());
            insertStmt.setDate(3, priceCalendar.getDate());
            insertStmt.setDouble(4, priceCalendar.getPrice());
            
            insertStmt.executeUpdate();
            return priceCalendar;
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
    
    
    public PriceCalendar getPCById(int pcId) throws SQLException {
        String selectPC= "SELECT PriceCalendarId,ListingId,Date,Price FROM PriceCalendar WHERE PriceCalendarId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectPC);
            selectStmt.setInt(1, pcId);
        
            results = selectStmt.executeQuery();
 
            if(results.next()) {
                int resultPCId = results.getInt("PriceCalendarId");
                int listingID = results.getInt("ListingId");
                Date date = results.getDate("Date");
                double price = results.getDouble("Price");
                
                ListingsDao listingDao = ListingsDao.getInstance();
                Listings listing = listingDao.getListingByListingId(listingID);
                
                PriceCalendar pc = new PriceCalendar(resultPCId,listing,date,price);
                
                return pc;
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

    public List<PriceCalendar> getPCByListingId(int listingID) throws SQLException {
    	List<PriceCalendar> pcList = new ArrayList<PriceCalendar>();
    	String selectPC= "SELECT PriceCalendarId,ListingId,Date,Price FROM PriceCalendar WHERE ListingId=?;";
		
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPC);
			selectStmt.setInt(1, listingID);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int resultPCId = results.getInt("PriceCalendarId");
                int resultlistingID = results.getInt("ListingId");
                Date date = results.getDate("Date");
                double price = results.getDouble("Price");
                
                ListingsDao listingDao = ListingsDao.getInstance();
                Listings listing = listingDao.getListingByListingId(resultlistingID);
                
                PriceCalendar pc = new PriceCalendar(resultPCId,listing,date,price);
                
                pcList.add(pc);
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
		return pcList;
    }


    public PriceCalendar delete(PriceCalendar pc) throws SQLException {
        String deletePC = "DELETE FROM PriceCalendar WHERE PriceCalendarId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deletePC);
            deleteStmt.setInt(1, pc.getPriceCalendarId());
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
    
    public double getMinPCByListingId(int listingID) throws SQLException {
    	double minPrice = -1;
    	String selectMinPC= "SELECT MIN(Price) AS MinPrice FROM PriceCalendar WHERE ListingId=?;";
		
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectMinPC);
			selectStmt.setInt(1, listingID);
			results = selectStmt.executeQuery();
			if(results.next()) {
				minPrice = results.getDouble("MinPrice");
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
		return minPrice;
    }
}
