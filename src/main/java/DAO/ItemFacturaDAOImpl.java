
package DAO;

import static Conexion.Conexion.close;
import static Conexion.Conexion.getConnection;
import Dominio.Factura;
import Dominio.ItemFactura;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ItemFacturaDAOImpl implements IItemFacturaDAO{
    private Connection conexionTransaccional;
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;
    
    public ItemFacturaDAOImpl(){
    }
    
    public ItemFacturaDAOImpl(Connection conexionTransaccional){
        this.conexionTransaccional = conexionTransaccional;
    }
    
    @Override
    public List<ItemFactura> obtenerItemsFactura(Factura factura) throws SQLException{
        List<ItemFactura> itemsFactura = new ArrayList<>();
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            
            stmt = conn.prepareStatement("SELECT * FROM itemfactura WHERE idFactura = ?");
            stmt.setInt(1, factura.getIdFactura());
            rs = stmt.executeQuery();
            
            while(rs.next()){
                //Me fijo si ese item es de estadia o de servicio
                ItemFactura itemFactura = new ItemEstadiaDAOImpl(conn).obtenerItemEstadia(rs.getInt("idItemFactura"));
                if(itemFactura == null){
                    itemFactura = new ItemServicioDAOImpl(conn).obtenerItemServicio(rs.getInt("idItemFactura"));
                }
                itemsFactura.add(itemFactura);
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
        return itemsFactura; 
    }
}
