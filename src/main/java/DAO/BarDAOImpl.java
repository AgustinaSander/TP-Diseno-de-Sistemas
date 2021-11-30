
package DAO;

import static Conexion.Conexion.close;
import static Conexion.Conexion.getConnection;
import Dominio.Bar;
import Dominio.ItemBar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BarDAOImpl implements IBarDAO{
    private Connection conexionTransaccional;
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;

    public BarDAOImpl() {
    }

    public BarDAOImpl(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }

    public Bar obtenerBar(int idServicio) throws SQLException {
        Bar bar = null;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            
            stmt = conn.prepareStatement("SELECT * FROM bar AS b, servicio AS ser, itembar AS i WHERE b.idServicio = ? AND b.idServicio = ser.idServicio AND b.idItemBar = i.idItemBar");
            stmt.setInt(1, idServicio);
            
            rs = stmt.executeQuery();
            while(rs.next()){
                ItemBar itemBar = new ItemBar(rs.getInt("idItemBar"), rs.getString("descripcionBar"), rs.getFloat("precioUnitario"));          
                bar = new Bar(itemBar, rs.getInt("idServicio"), rs.getString("descripcion"), rs.getDate("fecha"), rs.getFloat("precioTotal"), rs.getInt("cantidad"));
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
        
        return bar;
    }
    
    
    
}
