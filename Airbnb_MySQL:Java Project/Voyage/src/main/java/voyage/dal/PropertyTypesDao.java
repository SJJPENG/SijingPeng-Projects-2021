package voyage.dal;

import voyage.model.Hosts;
import voyage.model.PropertyTypes;

import java.sql.*;

public class PropertyTypesDao {
    protected ConnectionManager connectionManager;
    private static PropertyTypesDao instance = null;

    protected PropertyTypesDao() {
        connectionManager = new ConnectionManager();
    }

    public static PropertyTypesDao getInstance() {
        if (instance == null) {
            instance = new PropertyTypesDao();
        }
        return instance;
    }
    public PropertyTypes create(PropertyTypes  propertyTypes) throws SQLException {
        String insertPropertyTypes =
                "INSERT INTO PropertyTypes(PropertyTypeId,propertyType) " +
                        "VALUES(?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertPropertyTypes);
            insertStmt.setInt(1, propertyTypes.getPropertyId());
            insertStmt.setString(2,propertyTypes.getPropertyType());
            insertStmt.executeUpdate();
            return propertyTypes;
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
    public PropertyTypes  getPropertyTypesByPropertyTypeId (int propertyTypeId) throws SQLException {
        String selectPropertyTypes = "SELECT PropertyTypeId,PropertyType FROM PropertyTypes WHERE PropertyTypeId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectPropertyTypes);
            selectStmt.setInt(1, propertyTypeId);
            // Note that we call executeQuery(). This is used for a SELECT statement
            // because it returns a result set. For more information, see:
            // http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
            // http://docs.oracle.com/javase/7/docs/api/java/sql/ResultSet.html
            results = selectStmt.executeQuery();
            // You can iterate the result set (although the example below only retrieves
            // the first record). The cursor is initially positioned before the row.
            // Furthermore, you can retrieve fields by name and by type.
            if(results.next()) {
                int resultPropertyTypeId = results.getInt("PropertyTypeId");
                String propertyType = results.getString("PropertyType");
                PropertyTypes propertyTypes = new PropertyTypes(resultPropertyTypeId,propertyType);
                return propertyTypes;
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



    public PropertyTypes delete(PropertyTypes propertyTypes) throws SQLException {
        String deletePropertyTypes = "DELETE FROM PropertyTypes WHERE PropertyTypeId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deletePropertyTypes );
            deleteStmt.setLong(1, propertyTypes.getPropertyId());
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
