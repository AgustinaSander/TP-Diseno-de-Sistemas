
package DAO;

import static Conexion.Conexion.close;
import static Conexion.Conexion.getConnection;
import Dominio.Localidad;
import Dominio.Pais;
import Dominio.Provincia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    //Obtener la localidad
    public Localidad obtenerLocalidad(int idLocalidad) throws SQLException{
        Localidad localidad = null;
        
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            stmt = conn.prepareStatement("SELECT l.id AS idLocalidad, l.*, e.id AS idProvincia, e.*, p.* FROM localidad as l, estado as e, pais AS p WHERE l.id = ? AND l.id_provincia = e.id AND e.ubicacionpaisid = p.id");
            stmt.setInt(1,idLocalidad);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                //Obtengo el objeto pais
                Pais pais = new Pais(rs.getInt("ubicacionpaisid"),rs.getString("paisnombre"),rs.getString("nacionalidad"));
            
                //Obtengo el objeto provincia
                Provincia provincia = new Provincia(rs.getInt("id_provincia"), pais, rs.getString("estadonombre"));

                //Obtengo el objeto localidad
                localidad = new Localidad(rs.getInt("idLocalidad"),provincia, rs.getString("localidad"));
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
        return localidad;
    }
    
    @Override
    public Localidad obtenerLocalidad(String nombre, int idProvincia) throws SQLException{
        Localidad localidad = null;
        try {
            //Si necesito aplicar transacciones
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            
            stmt = conn.prepareStatement("SELECT * FROM localidad WHERE localidad = ? AND id_provincia = ?");
            stmt.setString(1,nombre);
            stmt.setInt(2,idProvincia);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Provincia provincia = new ProvinciaDAOImpl().obtenerProvincia(idProvincia);
               
                localidad = new Localidad(rs.getInt("id"), provincia, rs.getString("localidad"));
            }
        } 
        finally{
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
        
        return localidad;
    }
    
    
}
