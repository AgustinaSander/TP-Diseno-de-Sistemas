
package DAO;

import static Conexion.Conexion.getConnection;
import Dominio.Direccion;
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
    
    @Override
    public ResponsableDePago obtenerResponsableDePago(String cuit) {
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
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        
        return responsable;
    }
    
}
