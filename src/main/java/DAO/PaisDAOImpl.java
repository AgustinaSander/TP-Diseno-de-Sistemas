
package DAO;

import static Conexion.Conexion.*;
import Dominio.Pais;
import Dominio.Provincia;
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
    public Pais obtenerPais(String nombre) throws SQLException{
        Pais pais = null;
        try {
            //Si necesito aplicar transacciones
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            
            stmt = conn.prepareStatement("SELECT * FROM pais WHERE paisnombre = ?");
            stmt.setString(1,nombre);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                pais = new Pais(rs.getInt("id"), rs.getString("paisnombre"), rs.getString("nacionalidad"));
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
        
        return pais;
    }
    
    @Override
    //Obtengo los nombres de todos los paises de la bd
    public List<Pais> obtenerPaises() throws SQLException{
        List <Pais> listaPaises = new ArrayList();
        
        try{
            conn = getConnection();
            stmt = conn.prepareStatement("SELECT * FROM pais");
            rs = stmt.executeQuery();
            while(rs.next()){
                listaPaises.add(new Pais(rs.getInt("id"), rs.getString("paisnombre"), rs.getString("nacionalidad")));
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
    //Obtener pais de nacionalidad de una persona
    public Pais obtenerNacionalidad(int idPersona) throws SQLException{
        Pais nacionalidad = null;
        
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            
            stmt = conn.prepareStatement("SELECT p.* FROM pasajero AS pas, pais AS p WHERE pas.idPersona = ? AND pas.idPais = p.id");
            stmt.setInt(1,idPersona);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                nacionalidad = new Pais(rs.getInt("id"), rs.getString("paisnombre"), rs.getString("nacionalidad"));
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
    //Obtener el pais con idPais
    public Pais obtenerPais(int idPais) throws SQLException{
        Pais pais = null;
        
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            
            stmt = conn.prepareStatement("SELECT * FROM pais WHERE id = ?");
            stmt.setInt(1,idPais);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                pais = new Pais(rs.getInt("id"), rs.getString("paisnombre"), rs.getString("nacionalidad"));
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
        
        return pais;
    }
    
    @Override
    //Obtener las provincias que componen un pais a partir del nombre del pais
    public List<Provincia> obtenerProvincias(String pais) throws SQLException{
        List <Provincia> listaProvincias = new ArrayList();
        
        try {
            this.conexionTransaccional = getConnection();
            
            //Obtengo el idPais
            Pais p = obtenerPais(pais);
            int idPais = p.getIdPais();
            
            //Obtengo las provincias que lo componen
            stmt = conn.prepareStatement("SELECT * FROM estado WHERE ubicacionpaisid = ?");
            stmt.setInt(1,idPais);
            rs = stmt.executeQuery();
            while(rs.next()){
                listaProvincias.add(new Provincia(rs.getInt("id"), p ,rs.getString("estadonombre")));
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

