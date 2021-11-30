
package DAO;

import static Conexion.Conexion.close;
import static Conexion.Conexion.getConnection;
import Dominio.LavadoYPlanchado;
import Dominio.Sauna;
import Dominio.TipoPrenda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LavadoYPlanchadoDAOImpl implements ILavadoYPlanchadoDAO{
    private Connection conexionTransaccional;
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;

    public LavadoYPlanchadoDAOImpl() {
    }

    public LavadoYPlanchadoDAOImpl(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }

    public LavadoYPlanchado obtenerLavadoYPlanchado(int idServicio) throws SQLException{ 
        LavadoYPlanchado lyp = null;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            
            stmt = conn.prepareStatement("SELECT * FROM lavadoyplanchado AS l, servicio AS ser, tipoprenda AS t WHERE l.idServicio = ? AND l.idServicio = ser.idServicio AND l.idTipoPrenda = t.idTipoPrenda");
            stmt.setInt(1, idServicio);
            
            rs = stmt.executeQuery();
            while(rs.next()){
                TipoPrenda tipoPrenda = new TipoPrenda(rs.getInt("idTipoPrenda"), rs.getString("descripcionPrenda"), rs.getFloat("precioUnitario"));
                lyp = new LavadoYPlanchado(tipoPrenda, rs.getInt("idServicio"), rs.getString("descripcion"), rs.getDate("fecha"), rs.getFloat("precioTotal"), rs.getInt("cantidad"));
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
        
        return lyp;
    }
  
}
