
package DAO;

import static Conexion.Conexion.close;
import static Conexion.Conexion.getConnection;
import Dominio.Factura;
import Dominio.FacturaA;
import Dominio.FacturaB;
import Dominio.ItemFactura;
import Dominio.Persona;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FacturaDAOImpl implements IFacturaDAO{
    private Connection conexionTransaccional;
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;

    public FacturaDAOImpl() {
    }

    public FacturaDAOImpl(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }
    
    
    
    @Override
    public List<Factura> obtenerFacturasEstadia(int idEstadia) throws SQLException {
        List<Factura> facturas = new ArrayList<>();
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            
            IItemFacturaDAO itemFacturaDAO = new ItemFacturaDAOImpl(conn);

            stmt = conn.prepareStatement("SELECT * FROM factura WHERE idEstadia = ?");
            stmt.setInt(1,idEstadia);
            rs = stmt.executeQuery();
            Factura factura = null;
            while(rs.next()){
                //Por cada factura, obtengo sus items en el ItemFacturaDAO
                if(rs.getString("tipoFactura") == "A"){
                    //Creo factura de tipo A
                    factura = new FacturaA(rs.getFloat("montoIVA"), rs.getInt("idFactura"), rs.getDate("fecha"), rs.getFloat("importeNeto"), rs.getFloat("importeTotal"), rs.getBoolean("pagada"));
                }
                else{
                    //Creo factura de tipo B
                    factura = new FacturaB(rs.getInt("idFactura"), rs.getDate("fecha"), rs.getFloat("importeNeto"), rs.getFloat("importeTotal"), rs.getBoolean("pagada"));
                }
                
                //Seteo nota de credito en null
                factura.setNotaCredito(null);
                    
                //Me fijo si la persona de esa factura es pasajero o responsable de pago
                Persona persona = new PasajeroDAOImpl(conn).obtenerPasajero(rs.getInt("idPersona"));
                if(persona == null){
                    persona = new ResponsableDAOImpl(conn).obtenerResponsableDePago(rs.getInt("idPersona"));
                }

                factura.setPersona(persona);
                facturas.add(factura);
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
        
        return facturas;
    }
}
