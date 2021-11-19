
package DAO;

import static Conexion.Conexion.close;
import static Conexion.Conexion.getConnection;
import Dominio.Estadia;
import Dominio.Habitacion;
import Dominio.OcupadaPor;
import Dominio.TipoDeHabitacion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;



public class EstadiaDAOImpl implements IEstadiaDAO{

    private Connection conexionTransaccional;
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;
    
    @Override
    public List<Estadia> obtenerListaEstadias(int idTipoHabitacion, Date fechaDesde, Date fechaHasta) throws SQLException{
        List<Estadia> listaEstadias = new ArrayList <>();
        try {
            conn = getConnection();
            
            java.sql.Date desde = new java.sql.Date(fechaDesde.getTime());
            java.sql.Date hasta = new java.sql.Date(fechaHasta.getTime());
            
            stmt = conn.prepareStatement("SELECT e.*, h.* FROM estadia AS e ,habitacion AS h WHERE e.idHabitacion = h.id AND h.idTipoHabitacion = ? AND (((e.fechaIngreso <= ?) AND (e.fechaEgreso >= ?)) OR ((e.fechaIngreso <= ?) AND (e.fechaEgreso BETWEEN ? AND ?)) OR ((e.fechaIngreso BETWEEN ? AND ?) AND (e.fechaEgreso BETWEEN ? AND ?)) OR ((e.fechaIngreso >= ?) AND (e.fechaEgreso >= ?)))");
            stmt.setInt(1,idTipoHabitacion);
            stmt.setDate(2,desde);
            stmt.setDate(3,hasta);
            stmt.setDate(4,desde);
            stmt.setDate(5,desde);
            stmt.setDate(6,hasta);
            stmt.setDate(7,desde);
            stmt.setDate(8,hasta);
            stmt.setDate(9,desde);
            stmt.setDate(10,hasta);
            stmt.setDate(11,desde);
            stmt.setDate(12,hasta);
            
            rs = stmt.executeQuery();
            
            while(rs.next()){
                //Creo el objeto TipoDeHabitacion
                TipoDeHabitacion tipoHab = new TipoDeHabitacion(idTipoHabitacion);
                //Creo el objeto Habitacion
                Habitacion hab = new Habitacion(rs.getInt("idHabitacion"), rs.getString("nro"), rs.getFloat("precio"), tipoHab);
                //Creo el objeto estadia
                Estadia estadia = new Estadia(rs.getInt("id"), rs.getDate("fechaIngreso"), rs.getTime("horaIngreso").toLocalTime(), rs.getDate("fechaEgreso"), rs.getTime("horaEgreso").toLocalTime(), hab);
                
                //Agrego la estadia a la lista
                listaEstadias.add(estadia);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally{
            close(rs);
            close(stmt);
            close(conn);
        }
        
        return listaEstadias;
    }

    @Override
    public int crearEstadia(Estadia estadia) throws SQLException{
        int idEstadia = 0;
        try {
            conn = getConnection();
            
            //Conexion transaccional
            if(conn.getAutoCommit()){
                conn.setAutoCommit(false);
            }
            
            stmt = conn.prepareStatement("INSERT INTO `estadia`(`idHabitacion`, `fechaIngreso`, `horaIngreso`, `fechaEgreso`, `horaEgreso`) VALUES (?, ?, ?, ?, ?)");
            stmt.setInt(1, estadia.getHabitacion().getIdHabitacion());
            stmt.setDate(2, new java.sql.Date(estadia.getFechaIngreso().getTime()));
            stmt.setTime(3, Time.valueOf(estadia.getHoraIngreso()));
            stmt.setDate(4, new java.sql.Date(estadia.getFechaEgreso().getTime()));
            stmt.setTime(5, Time.valueOf(estadia.getHoraEgreso()));
            
            stmt.executeUpdate();
            stmt = conn.prepareStatement("SELECT MAX(id) FROM `estadia`;");
            rs = stmt.executeQuery();
            if(rs.next()){
                idEstadia = rs.getInt("MAX(id)");
            }
            
            List<OcupadaPor> listaOcupadaPor = estadia.getListaOcupadaPor();
            for(OcupadaPor o: listaOcupadaPor){
                stmt = conn.prepareStatement("INSERT INTO `ocupadapor`(`idPersona`, `idEstadia`, `esResponsable`) VALUES (?, ?, ?)");
                stmt.setInt(1, o.getPasajero().getIdPersona());
                stmt.setInt(2, idEstadia);
                stmt.setBoolean(3, o.getEsResponsable());
                
                stmt.executeUpdate();
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
            close(rs);
            close(stmt);
            close(conn);
        }
       
        return idEstadia;
    }
    
}
