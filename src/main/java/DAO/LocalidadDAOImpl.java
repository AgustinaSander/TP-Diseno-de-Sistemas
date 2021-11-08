
package DAO;

import static Conexion.Conexion.close;
import static Conexion.Conexion.getConnection;
import Dominio.Localidad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LocalidadDAOImpl implements ILocalidadDAO{

    private Connection conexionTransaccional;
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;
    
    public LocalidadDAOImpl() {
    }
    
    public LocalidadDAOImpl(Connection conexionTransaccional){
        this.conexionTransaccional = conexionTransaccional;
    }
    
    @Override
    //Obtener nombre de la localidad y idProvincia a partir de idLocalidad
    public List<String> obtenerLocalidad(int idLocalidad) throws SQLException{
        List<String> datos = new ArrayList<>();
        
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            stmt = conn.prepareStatement("SELECT * FROM localidad WHERE idLocalidad = ?");
            stmt.setInt(1,idLocalidad);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                datos.add(rs.getString("nombreLocalidad"));
                datos.add(Integer.toString(rs.getInt("idProvincia")));
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
        return datos;
    }
    
    @Override
    public int obtenerIdLocalidad(String nombre, int idProvincia) throws SQLException{
        int idLocalidad = 0;
        try {
            //Si necesito aplicar transacciones
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            
            stmt = conn.prepareStatement("SELECT id FROM localidad WHERE localidad = ? AND id_provincia = ?");
            stmt.setString(1,nombre);
            stmt.setInt(2,idProvincia);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                idLocalidad = rs.getInt("id");
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
        
        return idLocalidad;
    }
    
}
