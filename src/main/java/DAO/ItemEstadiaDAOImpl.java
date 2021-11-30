
package DAO;


import static Conexion.Conexion.close;
import static Conexion.Conexion.getConnection;
import Dominio.ItemFactura;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            stmt = conn.prepareStatement("SELECT * FROM itemestadia WHERE idItemFactura = ?");
            stmt.setInt(1, idItem);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                //Date desde, Date hasta, Estadia estadia, int idItemFactura, String descripcion, float precioIte
                item = new ItemEstadia()
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
