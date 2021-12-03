
package DAO;

import static Conexion.Conexion.close;
import static Conexion.Conexion.getConnection;
import Dominio.Factura;
import Dominio.ItemEstadia;
import Dominio.ItemFactura;
import Dominio.ItemServicio;
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
    
    @Override
    public void crearItemsFactura(List<ItemFactura> listaItemsFactura, int idFactura) throws SQLException{
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            
            for(ItemFactura i: listaItemsFactura){
                stmt = conn.prepareStatement("INSERT INTO `itemfactura`(`idFactura`, `descripcion`, `precioItem`, `precioUnitario`, `cantidad`) VALUES (?,?,?,?,?)");
                stmt.setInt(1,idFactura);
                stmt.setString(2,i.getDescripcion());
                stmt.setFloat(3, i.getPrecioItem());
                stmt.setFloat(4, i.getPrecioUnitario());
                stmt.setInt(5, i.getCantidad());
                stmt.executeUpdate();
                
                //Recupero el idItemFactura generado por la base
                int idItemFactura = 0;
                stmt = conn.prepareStatement("SELECT MAX(idItemFactura) FROM `itemfactura`;");
                rs = stmt.executeQuery();
                if(rs.next()){
                    idItemFactura = rs.getInt("MAX(idItemFactura)");
                }
                
                //Creo el objeto itemEstadia o itemServicio
                if(i instanceof ItemEstadia){
                    //Si es itemEstadia
                    stmt = conn.prepareStatement("INSERT INTO `itemestadia`(`idItemFactura`, `idEstadia`, `extra`) VALUES (?, ?, ?)");
                    stmt.setInt(1,idItemFactura);
                    stmt.setInt(2, ((ItemEstadia) i).getEstadia().getIdEstadia());
                    stmt.setBoolean(3, ((ItemEstadia) i).getExtra());
                    
                    stmt.executeUpdate();
                }
                else{
                    //Si es itemServicio
                    stmt = conn.prepareStatement("INSERT INTO `itemservicio`(`idItemFactura`, `idServicio`) VALUES (?,?)");
                    stmt.setInt(1,idItemFactura);
                    stmt.setInt(2, ((ItemServicio) i).getServicio().getIdServicio());
                    
                    stmt.executeUpdate();
                }
                
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
    }
    
    
}
