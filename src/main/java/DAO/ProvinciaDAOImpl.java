
package DAO;

import static Conexion.Conexion.close;
import static Conexion.Conexion.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProvinciaDAOImpl implements IProvinciaDAO{

    private Connection conexionTransaccional;
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;
    
    public ProvinciaDAOImpl() {
    }
    
    public ProvinciaDAOImpl(Connection conexionTransaccional){
        this.conexionTransaccional = conexionTransaccional;
    }
    
    @Override
    public int obtenerIdProvincia(String nombre) throws SQLException{
        int idProvincia = 0;
        try {
            //Si necesito aplicar transacciones
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            
            stmt = conn.prepareStatement("SELECT id FROM estado WHERE estadonombre = ?");
            stmt.setString(1,nombre);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                idProvincia = rs.getInt("id");
            }
        } 
        finally{
            try {
                close(stmt);
                if(this.conexionTransaccional == null){
                    close(conn);
                }
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        
        return idProvincia;
    }
    
    @Override
    //Obtener listado de las localidades que pertenecen a la provincia con idProvincia
    public List<String> obtenerLocalidades(String provincia) throws SQLException{
        List <String> listaLocalidades = new ArrayList();
        try {
            this.conexionTransaccional = getConnection();
           
            //Obtengo el idProvincia
            int idProvincia = obtenerIdProvincia(provincia);
            
            //Obtengo la lista de localidades de esa provincia
            stmt = conn.prepareStatement("SELECT localidad FROM localidades WHERE id_provincia = ?");
            stmt.setInt(1,idProvincia);
            rs = stmt.executeQuery();
            while(rs.next()){
                listaLocalidades.add(rs.getString("localidad"));
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
        
        return listaLocalidades;
    }
}
