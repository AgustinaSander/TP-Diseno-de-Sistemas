
package DAO;

import static Conexion.Conexion.close;
import static Conexion.Conexion.getConnection;
import Dominio.Sauna;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SaunaDAOImpl implements ISaunaDAO{
    private Connection conexionTransaccional;
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;

    public SaunaDAOImpl() {
    }

    public SaunaDAOImpl(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }

    public Sauna obtenerSauna(int idServicio) throws SQLException {
        Sauna sauna = null;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            
            stmt = conn.prepareStatement("SELECT * FROM sauna AS s, servicio AS ser WHERE s.idServicio = ? AND s.idServicio = ser.idServicio");
            stmt.setInt(1, idServicio);
            
            rs = stmt.executeQuery();
            while(rs.next()){
                sauna = new Sauna(rs.getFloat("precioUnitario"), rs.getInt("idServicio"), rs.getString("descripcion"), rs.getDate("fecha"), rs.getFloat("precioTotal"), rs.getInt("cantidad"));
            }
        }finally{
            try {
                close(stmt);
                if(rs != null){
                    close(rs);
                }
                if(this.conexionTransaccional == null){
                    close(conn);
                }
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        
        return sauna;
    }
    
    
    
}
