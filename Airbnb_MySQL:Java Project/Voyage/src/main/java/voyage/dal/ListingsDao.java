package voyage.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import voyage.model.Cities;
import voyage.model.Hosts;
import voyage.model.Listings;
import voyage.model.Neighborhoods;
import voyage.model.PropertyTypes;
import voyage.model.Listings.RoomType;

public class ListingsDao {
	protected ConnectionManager connectionManager;

	private static ListingsDao instance = null;

	protected ListingsDao() {
		connectionManager = new ConnectionManager();
	}

	public static ListingsDao getInstance() {
		if (instance == null) {
			instance = new ListingsDao();
		}
		return instance;
	}

	public Listings create(Listings listings) throws SQLException {
		String insert = "INSERT INTO Listings(ListingId, Name, ListingURL, Description, NeighborhoodOverview, PictureURL, HostId, NeighborhoodId, Latitude, Longtitude, PropertyTypeId, RoomType, Acommodates, BathroomsText, Bedrooms,Beds, MinNights, MaxNights, ReviewScore, InstantBookable, CityName)"
				+ "VALUES(?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;

		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insert);
			insertStmt.setInt(1, listings.getListingId());
			insertStmt.setString(2, listings.getName());
			insertStmt.setString(3, listings.getListingURL());
			insertStmt.setString(4, listings.getDescription());
			insertStmt.setString(5, listings.getNeighborhoodOverview());
			insertStmt.setString(6, listings.getPictureURL());
			insertStmt.setLong(7, listings.getHosts().getHostId());
			insertStmt.setInt(8, listings.getNeighborhoods().getNeighborhoodId());
			insertStmt.setDouble(9, listings.getLatitude());
			insertStmt.setDouble(10, listings.getLongtitude());
			insertStmt.setInt(11, listings.getPropertyTypes().getPropertyId());
			insertStmt.setString(12, listings.getRoomType().toString());
			insertStmt.setInt(13, listings.getAcommodates());
			insertStmt.setString(14, listings.getBathroomsText());
			insertStmt.setInt(15, listings.getBedrooms());
			insertStmt.setInt(16, listings.getBeds());
			insertStmt.setInt(17, listings.getMinNights());
			insertStmt.setInt(18, listings.getMaxNights());
			insertStmt.setDouble(19, listings.getReviewScore());
			insertStmt.setBoolean(20, listings.getInstantBookable());
			insertStmt.setString(21, listings.getCities().getName());
			insertStmt.executeUpdate();
			return listings;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (insertStmt != null) {
				insertStmt.close();
			}
		}
	}

	public Listings delete(Listings listings) throws SQLException {
		String delete = "DELETE FROM Listings WHERE listingId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(delete);
			deleteStmt.setInt(1, listings.getListingId());
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

	public Listings getListingByListingId(int listingId) throws SQLException {
		String select = "SELECT * FROM Listings WHERE listingId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(select);
			selectStmt.setInt(1, listingId);
			results = selectStmt.executeQuery();

			if (results.next()) {
				String listingURL = results.getString("ListingURL");
				String name = results.getString("Name");
				String description = results.getString("Description");
				String neighborhoodOverview = results.getString("NeighborhoodOverview");
				String pictureURL = results.getString("PictureURL");
				int hostId = results.getInt("HostId");
				int neighborhoodId = results.getInt("NeighborhoodId");
				Double latitude = results.getDouble("Latitude");
				Double longtitude = results.getDouble("Longtitude");
				int propertyTypeId = results.getInt("PropertyTypeId");
				String roomType = results.getString("RoomType");
				int acommodates = results.getInt("Acommodates");
				String bathroomsText = results.getString("BathroomsText");
				int bedrooms = results.getInt("Bedrooms");
				int beds = results.getInt("Beds");
				int minNights = results.getInt("MinNights");
				int maxNights = results.getInt("MaxNights");
				Double reviewScore = results.getDouble("ReviewScore");
				Boolean instantBookable = results.getBoolean("InstantBookable");
				String cityName = results.getString("CityName");

				Hosts hosts = new HostsDao().getHostsByHostId(hostId);
				Neighborhoods neighborhoods = new NeighborhoodsDao().getNeighborhoodsByNeighborhoodId(neighborhoodId);
				PropertyTypes propertyTypes = new PropertyTypesDao().getPropertyTypesByPropertyTypeId(propertyTypeId);
				RoomType currentRomeType = RoomType.getRoomTypeByValue(roomType);
				Cities cities = new CitiesDao().getCitiesByName(cityName).get(0);

				Listings listings = new Listings(listingId, name, listingURL, description, neighborhoodOverview,
						pictureURL, hosts, neighborhoods, latitude, longtitude, propertyTypes, currentRomeType,
						acommodates, bathroomsText, bedrooms,beds, minNights, maxNights, reviewScore, instantBookable, cities);
				return listings;
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
		return null;
	}

	public Listings updateListings(Listings listings, String newName) throws SQLException {
		String update = "UPDATE Listing SET Name=? WHERE listingId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(update);
			updateStmt.setInt(1, listings.getListingId());
			updateStmt.setString(2, newName);
			updateStmt.executeUpdate();

			return listings;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (updateStmt != null) {
				updateStmt.close();
			}
		}
	}

	public List<Listings> getListingsByNameKeywords(String keywords) throws SQLException {
		List<Listings> listings = new ArrayList<Listings>();
		String selectListings = "SELECT * FROM Listings WHERE LOWER(Name) LIKE ?";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectListings);
			selectStmt.setString(1, "%" + keywords.toLowerCase() + "%");
			results = selectStmt.executeQuery();
			while (results.next()) {
				int listingId = results.getInt("ListingId");
				String listingURL = results.getString("ListingURL");
				String resultName = results.getString("Name");
				String description = results.getString("Description");
				String neighborhoodOverview = results.getString("NeighborhoodOverview");
				String pictureURL = results.getString("PictureURL");
				int hostId = results.getInt("HostId");
				int neighborhoodId = results.getInt("NeighborhoodId");
				Double latitude = results.getDouble("Latitude");
				Double longtitude = results.getDouble("Longtitude");
				int propertyTypeId = results.getInt("PropertyTypeId");
				String roomType = results.getString("RoomType");
				int acommodates = results.getInt("Acommodates");
				String bathroomsText = results.getString("BathroomsText");
				int bedrooms = results.getInt("Bedrooms");
				int beds = results.getInt("Beds");
				int minNights = results.getInt("MinNights");
				int maxNights = results.getInt("MaxNights");
				Double reviewScore = results.getDouble("ReviewScore");
				Boolean instantBookable = results.getBoolean("InstantBookable");
				String cityName = results.getString("CityName");

				Hosts hosts = new HostsDao().getHostsByHostId(hostId);
				Neighborhoods neighborhoods = new NeighborhoodsDao().getNeighborhoodsByNeighborhoodId(neighborhoodId);
				PropertyTypes propertyTypes = new PropertyTypesDao().getPropertyTypesByPropertyTypeId(propertyTypeId);
				RoomType currentRomeType = RoomType.getRoomTypeByValue(roomType);
				Cities cities = new CitiesDao().getCitiesByName(cityName).get(0);

				Listings listing = new Listings(listingId, resultName, listingURL, description, neighborhoodOverview,
						pictureURL, hosts, neighborhoods, latitude, longtitude, propertyTypes, currentRomeType,
						acommodates, bathroomsText, bedrooms,beds,minNights, maxNights, reviewScore, instantBookable, cities);
				listings.add(listing);
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
		return listings;
	}
	
	public List<Listings> getListingsByHostId(long hostId) throws SQLException {
		List<Listings> listings = new ArrayList<Listings>();
		String selectListings = "SELECT * FROM Listings WHERE HostId=?";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectListings);
			selectStmt.setLong(1, hostId);
			results = selectStmt.executeQuery();
			while (results.next()) {
				int listingId = results.getInt("ListingId");
				String listingURL = results.getString("ListingURL");
				String resultName = results.getString("Name");
				String description = results.getString("Description");
				String neighborhoodOverview = results.getString("NeighborhoodOverview");
				String pictureURL = results.getString("PictureURL");
				int neighborhoodId = results.getInt("NeighborhoodId");
				Double latitude = results.getDouble("Latitude");
				Double longtitude = results.getDouble("Longtitude");
				int propertyTypeId = results.getInt("PropertyTypeId");
				String roomType = results.getString("RoomType");
				int acommodates = results.getInt("Acommodates");
				String bathroomsText = results.getString("BathroomsText");
				int bedrooms = results.getInt("Bedrooms");
				int beds = results.getInt("Beds");
				int minNights = results.getInt("MinNights");
				int maxNights = results.getInt("MaxNights");
				Double reviewScore = results.getDouble("ReviewScore");
				Boolean instantBookable = results.getBoolean("InstantBookable");
				String cityName = results.getString("CityName");

				Hosts hosts = new HostsDao().getHostsByHostId(hostId);
				Neighborhoods neighborhoods = new NeighborhoodsDao().getNeighborhoodsByNeighborhoodId(neighborhoodId);
				PropertyTypes propertyTypes = new PropertyTypesDao().getPropertyTypesByPropertyTypeId(propertyTypeId);
				RoomType currentRomeType = RoomType.getRoomTypeByValue(roomType);
				Cities cities = new CitiesDao().getCitiesByName(cityName).get(0);

				Listings listing = new Listings(listingId, resultName, listingURL, description, neighborhoodOverview,
						pictureURL, hosts, neighborhoods, latitude, longtitude, propertyTypes, currentRomeType,
						acommodates, bathroomsText, bedrooms,beds,minNights, maxNights, reviewScore, instantBookable, cities);
				listings.add(listing);
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
		return listings;
	}
}