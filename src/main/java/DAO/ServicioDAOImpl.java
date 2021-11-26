
package DAO;

import static Conexion.Conexion.close;
import static Conexion.Conexion.getConnection;
import Dominio.Bar;
import Dominio.ItemBar;
import Dominio.LavadoYPlanchado;
import Dominio.Sauna;
import Dominio.Servicio;
import Dominio.TipoPrenda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServicioDAOImpl implements IServicioDAO{
    private Connection conexionTransaccional;
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;

    @Override
    public List<Servicio> obtenerServicios(int idEstadia) {
        List<Servicio> servicios = new ArrayList<>();
        try {
            conn = getConnection();
            
            //Conexion transaccional
            if(conn.getAutoCommit()){
                conn.setAutoCommit(false);
            }
            
            stmt = conn.prepareStatement("SELECT * FROM servicio WHERE idEstadia = ?");
            stmt.setInt(1, idEstadia);
            rs = stmt.executeQuery();
            while(rs.next()){
                //Me fijo que tipo de servicio es a partir del idServicio
                Boolean tipoDeServicioEncontrado = false;
                
                //En tabla sauna
                PreparedStatement stmt2 = conn.prepareStatement("SELECT * FROM sauna WHERE idServicio = ?");
                stmt2.setInt(1, rs.getInt("idServicio"));
                ResultSet rs2 = stmt2.executeQuery();
                while(rs2.next()){
                    //El servicio era de tipo sauna
                    Sauna sauna = new Sauna(rs2.getFloat("precioUnitario"), rs.getInt("idServicio"), rs.getString("descripcion"), rs.getDate("fecha"), rs.getFloat("precioTotal"),rs.getInt("cantidad"));
                    servicios.add(sauna);
                    tipoDeServicioEncontrado = true;
                }
                
                if(!tipoDeServicioEncontrado){
                    //En tabla lavadoyplanchado
                    stmt2 = conn.prepareStatement("SELECT * FROM lavadoyplanchado AS l, tipoprenda AS t  WHERE l.idServicio = ? AND l.idTipoPrenda = t.idTipoPrenda");
                    stmt2.setInt(1, rs.getInt("idServicio"));
                    rs2 = stmt2.executeQuery();
                    while(rs2.next()){
                        //El servicio era de tipo lavadoyplanchado
                        TipoPrenda tipoPrenda = new TipoPrenda(rs2.getInt("idTipoPrenda"), rs2.getString("descripcionPrenda"), rs2.getFloat("precioUnitario"));
                        LavadoYPlanchado lavado = new LavadoYPlanchado(tipoPrenda, rs.getInt("idServicio"), rs.getString("descripcion"), rs.getDate("fecha"), rs.getFloat("precioTotal"),rs.getInt("cantidad"));
                        servicios.add(lavado);
                        tipoDeServicioEncontrado = true;
                    }
                }
                
                if(!tipoDeServicioEncontrado){
                    //En tabla bar
                    stmt2 = conn.prepareStatement("SELECT * FROM bar AS b, itembar AS i  WHERE b.idServicio = ? AND b.idItemBar = i.idItemBar");
                    stmt2.setInt(1, rs.getInt("idServicio"));
                    rs2 = stmt2.executeQuery();
                    while(rs2.next()){
                        //El servicio era de tipo bar
                        ItemBar itemBar = new ItemBar(rs2.getInt("idItemBar"), rs2.getString("descripcionBar"), rs2.getFloat("precioUnitario"));
                        Bar bar = new Bar(itemBar, rs.getInt("idServicio"), rs.getString("descripcion"), rs.getDate("fecha"), rs.getFloat("precioTotal"),rs.getInt("cantidad"));
                        servicios.add(bar);
                        tipoDeServicioEncontrado = true;
                    }
                }  
            }
            conn.commit();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            try {
                conn.rollback();
                System.out.println("Se hace rollback");
            } catch (SQLException ex1) {
                ex1.printStackTrace(System.out);
            }
        } finally{
            try {
                close(stmt);
                close(rs);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        
        return servicios;
    }
}
