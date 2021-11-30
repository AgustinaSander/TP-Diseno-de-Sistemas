
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
    public Provincia obtenerProvincia(int idProvincia) throws SQLException{
        Provincia prov = null;
        try {
            //Si necesito aplicar transacciones
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            
            stmt = conn.prepareStatement("SELECT * FROM estado WHERE id = ?");
            stmt.setInt(1,idProvincia);

            rs = stmt.executeQuery();
           
            while(rs.next()){
                Pais p = new PaisDAOImpl().obtenerPais(rs.getInt("ubicacionpaisid"));
                prov = new Provincia(rs.getInt("id"), p, rs.getString("estadonombre"));
            }
        
        } finally{
            try {
                close(stmt);
                if(this.conexionTransaccional == null){
                    close(conn);
                }
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        
        return prov;
    }
    
    @Override
    public Provincia obtenerProvincia(String nombre, int idPais) throws SQLException{
        
        Provincia prov = null;
        
        try {
            //Si necesito aplicar transacciones
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            
            stmt = conn.prepareStatement("SELECT * FROM estado WHERE estadonombre = ? AND ubicacionpaisid = ?");
            stmt.setString(1,nombre);
            stmt.setInt(2,idPais);

            rs = stmt.executeQuery();
            
            while(rs.next()){
                Pais p = new PaisDAOImpl().obtenerPais(idPais);
                
                prov = new Provincia(rs.getInt("id"), p, rs.getString("estadonombre"));
            }
       
        } finally{
            try {
                close(stmt);
                if(this.conexionTransaccional == null){
                    close(conn);
                }
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
       
        return prov;
    }
    
    @Override
    //Obtener listado de las localidades que pertenecen a una provincia
    public List<Localidad> obtenerLocalidades(String provincia, String pais) throws SQLException{
        List <Localidad> listaLocalidades = new ArrayList();
        
        try {
            this.conexionTransaccional = getConnection();
            conn = conexionTransaccional;
            
            if(conn.getAutoCommit()){
                conn.setAutoCommit(false);
            }
            
            //Obtengo el idPais
            int idPais = new PaisDAOImpl(conn).obtenerPais(pais).getIdPais();
            
            //Obtengo el idProvincia
            Provincia prov = obtenerProvincia(provincia,idPais);
           
            //Obtengo la lista de localidades de esa provincia
            stmt = conn.prepareStatement("SELECT * FROM localidad WHERE id_provincia = ?");
            stmt.setInt(1, prov.getIdProvincia());
            rs = stmt.executeQuery();
            while(rs.next()){
                listaLocalidades.add(new Localidad(rs.getInt("id_provincia"), prov, rs.getString("localidad")));
            }
            
            conn.commit();
        }catch(SQLException ex){
             ex.printStackTrace(System.out);
            try {
                conn.rollback();
                System.out.println("Se hace rollback");
            } catch (SQLException ex1) {
                ex1.printStackTrace(System.out);
            }
        } finally{
            close(stmt);
            close(conn);
        }
        
        return listaLocalidades;
    }
}
