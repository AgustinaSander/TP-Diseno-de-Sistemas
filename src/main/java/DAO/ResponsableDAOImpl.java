
package DAO;

import static Conexion.Conexion.close;
import static Conexion.Conexion.getConnection;
import Dominio.Direccion;
import Dominio.Localidad;
import Dominio.Pais;
import Dominio.ResponsableDePago;
import Enum.PosicionIVA;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResponsableDAOImpl implements IResponsableDAO{

    private Connection conexionTransaccional;
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;

    public ResponsableDAOImpl() {
    }

    public ResponsableDAOImpl(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }
    
    @Override
    public ResponsableDePago obtenerResponsableDePago(String cuit) throws SQLException{
        ResponsableDePago responsable = null;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement("SELECT * FROM responsabledepago AS r, persona AS p WHERE r.idPersona = p.idPersona AND p.CUIT = ?");
            stmt.setString(1, cuit);
            rs = stmt.executeQuery();
            
            
            while(rs.next()){
                Direccion direccion = new Direccion (rs.getInt("idDireccion"));
                responsable = new ResponsableDePago(rs.getString("razonSocial"), rs.getInt("idPersona"), rs.getString("CUIT"), PosicionIVA.valueOf(rs.getString("posIVA")), rs.getString("telefono"),direccion);
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
        
        return responsable;
    }
    
    public ResponsableDePago obtenerResponsableDePago(int idPersona) throws SQLException{
        ResponsableDePago responsable = null;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            
            stmt = conn.prepareStatement("SELECT res.*, per.*, dir.*, l.id AS idLocalidad, l.*, e.id AS idProvincia, e.*, p.*, nac.id AS idNacionalidad, nac.paisnombre AS paisNacionalidad, nac.nacionalidad AS nacionalidadNacionalidad FROM responsabledepago AS res, persona AS per, direccion AS dir, localidad as l, estado as e, pais AS p, pais AS nac WHERE res.Persona = ? AND res.idPersona = per.idPersona AND pas.idPais = nac.id AND per.idDireccion = dir.idDireccion AND dir.idLocalidad = l.id AND l.id_provincia = e.id AND e.ubicacionpaisid = p.id");
            stmt.setInt(1,idPersona);
            rs = stmt.executeQuery();
            while(rs.next()){                
                //Creo el objeto pasajero
                //Obtengo el objeto localidad
                Localidad localidad = new LocalidadDAOImpl(conn).obtenerLocalidad(rs.getInt("idLocalidad"));
                
                //Obtengo el objeto pais para la nacionalidad
                Pais nacionalidad = new PaisDAOImpl(conn).obtenerPais(rs.getInt("idNacionalidad"));        
                
                //Obtengo el objeto direccion
                Direccion direccion = new DireccionDAOImpl(conn).obtenerDireccionPasajero(idPersona);

                //String razonSocial, int idPersona, String CUIT, PosicionIVA posIva, String telefono, Direccion direccion
                responsable = new ResponsableDePago(rs.getString("razonSocial"), idPersona, rs.getString("CUIT"), PosicionIVA.valueOf(rs.getString("posIVA")), rs.getString("telefono"), direccion);
           
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
        
        return responsable;
    }
        
}
