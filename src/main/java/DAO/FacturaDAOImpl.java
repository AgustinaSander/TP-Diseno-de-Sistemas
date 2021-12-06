
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
                //Por cada factura, obtengo 
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
                //Busco la lista de items de esa factura
                List<ItemFactura> listaItems = new ItemFacturaDAOImpl(conn).obtenerItemsFactura(factura);
                factura.setListaItemsFactura(listaItems);
                
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
    
    @Override
    public int crearFactura(Factura factura) throws SQLException{
        int idFactura = 0;
        
        try {
            conn = getConnection();
            this.conexionTransaccional = conn;
            
            if(conn.getAutoCommit()){
                conn.setAutoCommit(false);
            }
            
            stmt = conn.prepareStatement("INSERT INTO `factura`(`idPersona`, `idNotaDeCredito`, `idEstadia`, `fecha`, `importeNeto`, `importeTotal`, `pagada`, `tipoFactura`, `montoIVA`) VALUES (?, null, ?, ?, ?, ?, false, ?, ?)");
            stmt.setInt(1, factura.getPersona().getIdPersona());
            stmt.setInt(2, factura.getEstadia().getIdEstadia());
            java.sql.Date fecha = new java.sql.Date(factura.getFecha().getTime());
            stmt.setDate(3, fecha);
            stmt.setFloat(4, factura.getImporteNeto());
            stmt.setFloat(5, factura.getImporteTotal());
            if(factura instanceof FacturaA){
                stmt.setString(6,"A");
            }
            else{
                stmt.setString(6,"B");
            }
            stmt.setFloat(7, factura.getImporteTotal() - factura.getImporteNeto());
            
            stmt.executeUpdate();
            
            //Recupero el idFactura generado por la base
            stmt = conn.prepareStatement("SELECT MAX(idFactura) FROM `factura`;");
            rs = stmt.executeQuery();
            if(rs.next()){
                idFactura = rs.getInt("MAX(idFactura)");
            }
            
            //Agregamos los items de la factura con ItemFacturaDAO
            new ItemFacturaDAOImpl(conn).crearItemsFactura(factura.getListaItemsFactura(), idFactura);
            
            
            conn.commit();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            try {
                conn.rollback();
                System.out.println("Se hace rollback");
            } catch (SQLException ex1) {
                ex1.printStackTrace(System.out);
            }
        }    
        finally{
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        
        return idFactura;
    }
    
    
}
