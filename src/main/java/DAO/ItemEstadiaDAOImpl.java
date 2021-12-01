
package DAO;


import static Conexion.Conexion.close;
import static Conexion.Conexion.getConnection;
import Dominio.Estadia;
import Dominio.Factura;
import Dominio.ItemEstadia;
import Dominio.ItemFactura;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ItemEstadiaDAOImpl implements IItemEstadiaDAO{
    private Connection conexionTransaccional;
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;
    
    public ItemEstadiaDAOImpl(){
    }
    
    public ItemEstadiaDAOImpl(Connection conexionTransaccional){
        this.conexionTransaccional = conexionTransaccional;
    }

    public ItemFactura obtenerItemEstadia(int idItem) throws SQLException{
        ItemFactura item = null;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            stmt = conn.prepareStatement("SELECT * FROM itemestadia AS ie, itemfactura AS if WHERE ie.idItemFactura = ? AND ie.idItemFactura = if.idItemFactura");
            stmt.setInt(1, idItem);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                //Obtenemos la estadia a la que hace referencia
                Estadia estadia = new EstadiaDAOImpl(conn).obtenerEstadia(rs.getInt("idEstadia"));
                
                item = new ItemEstadia( new Date(rs.getDate("desde").getTime()), new Date(rs.getDate("hasta").getTime()), estadia, idItem, rs.getString("descripcion"), rs.getFloat("precioitem"));
            }
           
            
        }finally{
            try {
                close(stmt);
                close(rs);
                if(this.conexionTransaccional == null){
                    close(conn);
                }
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        
        return item;
    }
    
    
}
