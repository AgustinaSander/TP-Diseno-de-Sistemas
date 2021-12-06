
package Conexion;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Conexion {
    //URL a base de datos tpDisenoSistemas
    private static final String JDBC_URL = "jdbc:mysql://localhost:3307/tpdisenosistemas?useSSL=false&useTimezone=true&serverTimezone=America/Argentina/Buenos_Aires&allowPublicKeyRetrieval=true";    
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "";
    
    public static Connection getConnection(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
        } catch (SQLException ex) {
            System.out.println("Error en la conexion a la base de datos. Asegurese de tenerla conectada.");
        }
        return conn;
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
