package software2assignment.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.time.Instant;
import java.io.IOException;
import java.sql.SQLException;

/**
 * ConnectDB is used to manage the connection between the application and the
 * database.
 * 
 * @author Michael Vonica michaelvonica@gmail.com
 */
public class ConnectDB {
    //variables used to connect to database
    private static final String databaseName = "Test";
    //private static final String URL = "jdbc:mysql://wgudb.ucertify.com:3306/" + databaseName;
    private static final String URL = "jdbc:sqlserver://localhost;encrypt=true;databaseName=Test;trustServerCertificate=true";
    private static final String username = "sa";
    private static final String password = "CatDog";
    private static final String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static Connection conn;
    
    /**
     * Establishes a connection to the database.
     * 
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws Exception 
     */
    public static Connection makeConnection() throws ClassNotFoundException, SQLException, Exception {
        Class.forName(driver);
        conn = DriverManager.getConnection(URL, username, password);
        System.out.println("Connection successful.");
        return conn;
    }
    
    /**
     * Terminates a connection to the database.
     * 
     * @throws SQLException 
     */
    public static void closeConnection() throws SQLException {
        conn.close();
        System.out.println("Connection closed.");
    }
}
