
package Conexion;

import java.sql.*;


public class Conexion {
    //URL a base de datos tpDisenoSistemas
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/tpdisenosistemas?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";    
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "";
    
    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }

    public static void close(ResultSet rs) throws SQLException{
        rs.close();
    }
    
    public static void close(PreparedStatement stmt) throws SQLException{
        stmt.close();
    }
    
    public static void close(Connection conn) throws SQLException{
        conn.close();
    }
}
