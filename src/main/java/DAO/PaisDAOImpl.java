
package DAO;

import static Conexion.Conexion.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class PaisDAOImpl implements IPaisDAO{
    private Connection conexionTransaccional = null;
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;
    
    public PaisDAOImpl() {
    }

    public PaisDAOImpl(Connection conexionTransaccional){
        this.conexionTransaccional = conexionTransaccional;
    }
    
    @Override
    public int obtenerIdPais(String nombre) throws SQLException{
        int idPais = 0;
        try {
            //Si necesito aplicar transacciones
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            
            stmt = conn.prepareStatement("SELECT id FROM pais WHERE paisnombre = ?");
            stmt.setString(1,nombre);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                idPais = rs.getInt("id");
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
        
        return idPais;
    }
    
    @Override
    //Obtengo los nombres de todos los paises de la bd
    public List<String> obtenerPaises() throws SQLException{
        List <String> listaPaises = new ArrayList();
        
        try{
            conn = getConnection();
            stmt = conn.prepareStatement("SELECT paisnombre FROM pais");
            rs = stmt.executeQuery();
            while(rs.next()){
                listaPaises.add(rs.getString("paisnombre"));
            }
        }finally{
             try {
                close(stmt);
                close(rs);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        
        return listaPaises;
    }
    
    @Override
    //Obtener nacionalidad a partir del idPais
    public String obtenerNacionalidad(int idPais) throws SQLException{
        String nacionalidad = "";
        
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            stmt = conn.prepareStatement("SELECT * FROM pais WHERE idPais = ?");
            stmt.setInt(1,idPais);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                nacionalidad = rs.getString("nacionalidad");
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
        return nacionalidad;
    }
    
    @Override
    //Obtener el nombre del pais con id = idPais
    public String obtenerPais(int idPais) throws SQLException{
        String nacionalidad = "";
        
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            stmt = conn.prepareStatement("SELECT * FROM pais WHERE idPais = ?");
            stmt.setInt(1,idPais);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                nacionalidad = rs.getString("nombrePais");
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
        return nacionalidad;
    }
    
    @Override
    //Obtener las provincias que componen un pais a partir del nombre del pais
    public List<String> obtenerProvincias(String pais) throws SQLException{
        List <String> listaProvincias = new ArrayList();
        
        try {
            this.conexionTransaccional = getConnection();
            
            //Obtengo el idPais
            int idPais = obtenerIdPais(pais);
            
            //Obtengo las provincias que lo componen
            stmt = conn.prepareStatement("SELECT estadonombre FROM estado WHERE ubicacionpaisid = ?");
            stmt.setInt(1,idPais);
            rs = stmt.executeQuery();
            while(rs.next()){
                listaProvincias.add(rs.getString("estadonombre"));
            }
        } finally{
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
        
        return listaProvincias;
    }
}

