
package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author raviMukti at Aftwork
 */
public class DBConfig {
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_PATH = "jdbc:mysql://localhost/kampus_app";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "123456";
    private static Connection conn = null;
  
    public static Connection getConnection() throws SQLException, ClassNotFoundException{
        Class.forName(JDBC_DRIVER);
        Connection connection = DriverManager.getConnection(DB_PATH,DB_USER,DB_PASS);
        return connection;
    }
    
    //Method dbConnect()
    public static void dbConnect() throws ClassNotFoundException, SQLException{
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_PATH, DB_USER, DB_PASS);
        } catch (SQLException e) {
            System.out.println("Ada kesalahan " + e);
        }
    }
    
    //Method dbDisconnect()
    public static void dbDisconnect() throws ClassNotFoundException, SQLException {
        try {
            if (conn != null && conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            throw e;
        }
    }
    
    //DB Execute Query
    public static ResultSet dbExecuteQuery(String querySmt) throws SQLException, ClassNotFoundException {
        //Deklarasi
        Statement stmt = null;
        ResultSet rs = null;
        try {
            dbConnect();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(querySmt);
        } catch (SQLException e) {
            System.out.println("Ada Kesalahan " + e);
        }finally{
            dbDisconnect();
        }
        return rs;
    }
    
    //DB Execute Update
    public static void dbExecuteUpdate(String sqlSmt) throws SQLException, ClassNotFoundException{
        //Deklarasi
        Statement stmt = null;
        try {
            dbConnect();
            stmt = conn.createStatement();
            stmt.executeUpdate(sqlSmt);
        } catch (SQLException e) {
            System.out.println("Ada Kesalahan " + e);
        }finally{
            dbDisconnect();
        }
    }
}

