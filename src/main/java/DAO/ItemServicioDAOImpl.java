
package DAO;


import static Conexion.Conexion.close;
import static Conexion.Conexion.getConnection;
import Dominio.Estadia;
import Dominio.Factura;
import Dominio.ItemFactura;
import Dominio.ItemServicio;
import Dominio.Servicio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ItemServicioDAOImpl implements IItemServicioDAO{
    private Connection conexionTransaccional;
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;
    
    public ItemServicioDAOImpl(){
    }
    
    public ItemServicioDAOImpl(Connection conexionTransaccional){
        this.conexionTransaccional = conexionTransaccional;
    }
    
    public ItemFactura obtenerItemServicio(int idItem) throws SQLException{
        ItemFactura item = null;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            stmt = conn.prepareStatement("SELECT * FROM itemservicio AS its, itemfactura AS it WHERE its.idItemFactura = ? AND its.idItemFactura = it.idItemFactura");
            stmt.setInt(1, idItem);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                //Obtenemos el servicio al que hace referencia
                Servicio servicio = new ServicioDAOImpl(conn).obtenerServicio(rs.getInt("idServicio"));
               
                item = new ItemServicio(servicio, idItem, rs.getString("descripcion"), rs.getFloat("precioItem"), rs.getFloat("precioUnitario"),rs.getInt("cantidad"));
            }
        }finally{
            try {
                close(stmt);
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
