package voyage.dal;

import voyage.dal.HostsDao;
import voyage.model.Hosts;

import java.sql.*;

public class HostsDao {
	protected ConnectionManager connectionManager;
	private static HostsDao instance = null;
	protected HostsDao() {
		connectionManager = new ConnectionManager();
	}
	public static HostsDao getInstance() {
		if(instance == null) {
			instance = new HostsDao();
		}
		return instance;
	}
    public Hosts create(Hosts hosts) throws SQLException {
        String insertHosts =
                "INSERT INTO Hosts(HostId,HostURL,Name,Location,About,ThumbnailURL,PictureURL) " +
                        "VALUES(?,?,?,?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertHosts);
            insertStmt.setLong(1, hosts.getHostId());
            insertStmt.setString(2,hosts.getHostURL());
            insertStmt.setString(3,hosts.getName());
            insertStmt.setString(4,hosts.getLocation());
            insertStmt.setString(5,hosts.getAbout());
            insertStmt.setString(6,hosts.getThumbnailURL());
            insertStmt.setString(7,hosts.getPictureURL());
            insertStmt.executeUpdate();
            return hosts;
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
    public Hosts getHostsByHostId(long hostId) throws SQLException {
        String selectHosts= "SELECT HostId,HostURL,Name,Location,About,ThumbnailURL,PictureURL FROM Hosts WHERE HostId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectHosts);
            selectStmt.setLong(1, hostId);
            // Note that we call executeQuery(). This is used for a SELECT statement
            // because it returns a result set. For more information, see:
            // http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
            // http://docs.oracle.com/javase/7/docs/api/java/sql/ResultSet.html
            results = selectStmt.executeQuery();
            // You can iterate the result set (although the example below only retrieves
            // the first record). The cursor is initially positioned before the row.
            // Furthermore, you can retrieve fields by name and by type.
            if(results.next()) {
                long resultHostId = results.getLong("HostId");
                String hostURL = results.getString("HostURL");
                String name = results.getString("Name");
                String location = results.getString("Location");
                String about = results.getString("About");
                String thumbnailURL = results.getString("ThumbnailURL");
                String pictureURL = results.getString("PictureURL");
                Hosts host = new Hosts(resultHostId,hostURL,name,location,about,thumbnailURL,pictureURL);
                return host;
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

    public Hosts getHostsByHostsName(String name) throws SQLException {
        String selectHosts= "SELECT HostId,HostURL,Name,Location,About,ThumbnailURL,PictureURL FROM Hosts WHERE Name=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectHosts);
            selectStmt.setString(1, name);
            // Note that we call executeQuery(). This is used for a SELECT statement
            // because it returns a result set. For more information, see:
            // http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
            // http://docs.oracle.com/javase/7/docs/api/java/sql/ResultSet.html
            results = selectStmt.executeQuery();
            // You can iterate the result set (although the example below only retrieves
            // the first record). The cursor is initially positioned before the row.
            // Furthermore, you can retrieve fields by name and by type.
            if(results.next()) {
                long hostId = results.getLong("HostId");
                String hostURL = results.getString("HostURL");
                String resultName = results.getString("Name");
                String location = results.getString("Location");
                String about = results.getString("About");
                String thumbnailURL = results.getString("ThumbnailURL");
                String pictureURL = results.getString("PictureURL");
                Hosts host = new Hosts(hostId,hostURL,resultName,location,about,thumbnailURL,pictureURL);
                return host;
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


    public Hosts delete(Hosts hosts) throws SQLException {
        String deleteHosts = "DELETE FROM Hosts WHERE HostId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteHosts );
            deleteStmt.setLong(1, hosts.getHostId());
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
