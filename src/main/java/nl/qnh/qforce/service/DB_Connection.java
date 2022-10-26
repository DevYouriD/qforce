package nl.qnh.qforce.service;

import java.sql.*;

/**
 * The class that creates a connection to the embedded database and provides functionality to write data to said database
 */
public class DB_Connection {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:./src/main/resources/data/analytics";

    // Database credentials
    static final String USER = "sa";
    static final String PASS = "";
    /**
     * Method that provides functionality to insert data into the embedded database when the fetchDataByName Api-endpoint is called
     * @param DataToInsert parameter that gets written to the embedded database along with a timestamp
     */
    public void insertIntoTableDataByName(String DataToInsert){
        Connection conn = null;
        Statement stmt = null;

        try {
            //Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //Execute a query
            PreparedStatement sql = conn.prepareStatement("INSERT INTO ANALYTICS_BY_NAME (DataByNameApiCall) VALUES (?)");
            sql.setString(1, DataToInsert);
            sql.executeUpdate();

            System.out.println("Inserted records into table ANALYTICS_BY_NAME");

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class
            e.printStackTrace();
        } finally {
            //Close resources
            try {
                if (stmt!=null)
                    conn.close();
            } catch (SQLException se) { }
        }
    }
    /**
     * Method that provides functionality to insert data into the embedded database when the fetchDataById Api-endpoint is called
     * @param DataToInsert parameter that gets written to the embedded database along with a timestamp
     */
    public void insertIntoTableDataById(String DataToInsert){
        Connection conn = null;
        Statement stmt = null;

        try {
            //Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //Execute a query
            PreparedStatement sql = conn.prepareStatement("INSERT INTO ANALYTICS_BY_ID (DataByIdApiCall) VALUES (?)");
            sql.setString(1, DataToInsert);
            sql.executeUpdate();

            System.out.println("Inserted records into the table ANALYTICS_BY_ID");

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt!=null)
                    conn.close();
            } catch (SQLException se) { }
        }
    }
}