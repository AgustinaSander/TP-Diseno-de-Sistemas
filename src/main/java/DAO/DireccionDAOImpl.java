
package DAO;

import static Conexion.Conexion.close;
import static Conexion.Conexion.getConnection;
import Dominio.Direccion;
import Dominio.Localidad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DireccionDAOImpl implements IDireccionDAO{
    
    //Si necesito aplicar transacciones
    private Connection conexionTransaccional;
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;
    
    public DireccionDAOImpl(){
    }
    
    public DireccionDAOImpl(Connection conexionTransaccional){
        this.conexionTransaccional = conexionTransaccional;
    }
    
    @Override
    //Agregar direccion a la bd a partir de un objeto Direccion
    public int crearDireccion(Direccion direccion) throws SQLException{
        
        int resId = 0;
        
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            
            //Inserto direccion
            stmt = conn.prepareStatement("INSERT INTO direccion(idLocalidad,calle,numero,departamento,codigoPostal) VALUES (?, ?, ?, ?, ?)");
            stmt.setInt(1,direccion.getLocalidad().getIdLocalidad());
            stmt.setString(2,direccion.getCalle());
            stmt.setInt(3,direccion.getNumero());
            stmt.setString(4,direccion.getDepartamento());
            stmt.setInt(5,direccion.getCodigoPostal());
            
            stmt.executeUpdate();
            //Recupero el idDireccion generado por la base
            stmt = conn.prepareStatement("SELECT MAX(idDireccion) FROM `direccion`;");
            rs = stmt.executeQuery();
            if(rs.next()){
                resId = rs.getInt("MAX(idDireccion)");
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
        
        return resId;
    }
    

    @Override
    //Modificar una direccion de la bd a partir de un objeto Direccion
    public void modificarDireccion(Direccion direccion) throws SQLException{
        try {
            //Si necesito aplicar transacciones
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            
            stmt = conn.prepareStatement("UPDATE direccion SET idLocalidad = ?, calle = ?, numero = ?, departamento = ?, codigoPostal = ? WHERE idDireccion = ?");
            stmt.setInt(1,direccion.getLocalidad().getIdLocalidad());
            stmt.setString(2,direccion.getCalle());
            stmt.setInt(3,direccion.getNumero());
            stmt.setString(4,direccion.getDepartamento());
            stmt.setInt(5,direccion.getCodigoPostal());
            stmt.setInt(6,direccion.getIdDireccion());
           
            stmt.executeUpdate();
            
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
    }
    
    public Direccion obtenerDireccionPasajero(int idPersona) throws SQLException{
        Direccion direccion = null;
        try {
            //Si necesito aplicar transacciones
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
        
            stmt = conn.prepareStatement("SELECT d.* FROM persona AS p, direccion AS d WHERE p.idPersona = ? AND p.idDireccion = d.idDireccion");
            stmt.setInt(1,idPersona);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                //Obtenemos la localidad mediante LocalidadDAO
                Localidad localidad = new LocalidadDAOImpl(conn).obtenerLocalidad(rs.getInt("idLocalidad"));
                
                //Creo el objeto direccion
                direccion = new Direccion(rs.getInt("idDireccion"), localidad, rs.getString("calle"), rs.getInt("numero"), rs.getString("departamento"), rs.getInt("codigoPostal"));
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
        
        return direccion;
    }
}
