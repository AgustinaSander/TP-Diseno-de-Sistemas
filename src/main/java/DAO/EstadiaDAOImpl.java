
package DAO;

import static Conexion.Conexion.close;
import static Conexion.Conexion.getConnection;
import Dominio.Direccion;
import Dominio.Estadia;
import Dominio.Habitacion;
import Dominio.OcupadaPor;
import Dominio.Pasajero;
import Dominio.TipoDeHabitacion;
import Enum.PosicionIVA;
import Enum.TipoDocumento;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class EstadiaDAOImpl implements IEstadiaDAO{

    private Connection conexionTransaccional;
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;
    
    @Override
    public List<Estadia> obtenerListaEstadias(List<Habitacion> habitaciones, Date fechaDesde, Date fechaHasta) throws SQLException{
        List<Estadia> listaEstadias = new ArrayList <>();
        try {
            conn = getConnection();
            
            //Conexion transaccional
            if(conn.getAutoCommit()){
                conn.setAutoCommit(false);
            }
            
            java.sql.Date desde = new java.sql.Date(fechaDesde.getTime());
            java.sql.Date hasta = new java.sql.Date(fechaHasta.getTime());
            
            for(Habitacion h : habitaciones){
                stmt = conn.prepareStatement("SELECT e.*, h.* FROM estadia AS e, habitacion AS h WHERE e.idHabitacion = ? AND h.id = ? AND (((e.fechaIngreso <= ?) AND (e.fechaEgreso >= ?)) OR ((e.fechaIngreso <= ?) AND (e.fechaEgreso BETWEEN ? AND ?)) OR ((e.fechaIngreso BETWEEN ? AND ?) AND (e.fechaEgreso BETWEEN ? AND ?)) OR ((e.fechaIngreso >= ?) AND (e.fechaEgreso >= ?)))");
                stmt.setInt(1,h.getIdHabitacion());
                stmt.setInt(2,h.getIdHabitacion());
                stmt.setDate(3,desde);
                stmt.setDate(4,hasta);
                stmt.setDate(5,desde);
                stmt.setDate(6,desde);
                stmt.setDate(7,hasta);
                stmt.setDate(8,desde);
                stmt.setDate(9,hasta);
                stmt.setDate(10,desde);
                stmt.setDate(11,hasta);
                stmt.setDate(12,desde);
                stmt.setDate(13,hasta);
                
                rs = stmt.executeQuery();
                
                while(rs.next()){ 
                    //Creo el objeto estadia
                    Estadia estadia = new Estadia(rs.getInt("id"), rs.getDate("fechaIngreso"), rs.getTime("horaIngreso").toLocalTime(), rs.getDate("fechaEgreso"), rs.getTime("horaEgreso").toLocalTime(), h);

                    //Agrego la estadia a la lista
                    listaEstadias.add(estadia);
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
            try {
                close(stmt);
                close(rs);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
       
        return idEstadia;
    }

    @Override
    public List<Pasajero> obtenerPasajerosEstadia(int idEstadia) {
        List<Pasajero> listaOcupantes = new ArrayList<>();
        try {
            conn = getConnection();
            
            //Se recuperan los ocupantes de esa estadia
            stmt = conn.prepareStatement("SELECT * FROM ocupadapor AS o, pasajero AS p, persona AS per WHERE o.idEstadia = ? AND o.idPersona = p.idPersona AND o.idPersona = per.idPersona");
            stmt.setInt(1, idEstadia);
            rs = stmt.executeQuery();    
            
            while(rs.next()){
                Direccion direccion = new Direccion (rs.getInt("idDireccion"));
                listaOcupantes.add(new Pasajero(rs.getString("apellido"), rs.getString("nombre"), TipoDocumento.valueOf(rs.getString("tipoDoc")),rs.getString("numDoc"), rs.getDate("fechaNac"), rs.getString("email"), rs.getInt("idPersona"), rs.getString("CUIT"), PosicionIVA.valueOf(rs.getString("posIVA")), rs.getString("telefono"), direccion));
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);  
        } finally{
            try {
                close(stmt);
                close(rs);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        
        return listaOcupantes;
    }

    public Estadia obtenerUltimaEstadia(String nroHabitacion) {
        Estadia estadia = new Estadia();
        try {
            //Se obtiene la ultima estadia de esa habitacion
            conn = getConnection();
            
            stmt = conn.prepareStatement("SELECT *, e.id AS idEstadia, th.nombre AS nombreTipo, p.nombre AS nombrePasajero, h.precio AS precioHabitacion FROM estadia AS e, habitacion AS h, ocupadapor AS o, pasajero AS p, tipodehabitacion AS th, persona AS per WHERE h.nro =? AND h.id = e.idHabitacion AND e.id =o.idEstadia AND o.idPersona = p.idPersona AND th.id = h.idTipoHabitacion AND o.idPersona = per.idPersona AND fechaEgreso = (SELECT MAX(fechaEgreso) FROM estadia AS e WHERE h.nro = ? AND h.id = e.idHabitacion);");
            stmt.setString(1,nroHabitacion);
            stmt.setString(2,nroHabitacion);
            
            rs = stmt.executeQuery();
            List<OcupadaPor> listaOcupadaPor = new ArrayList<>();
            
            while(rs.next()){
                //Tengo que setear la estadia
                Pasajero p = new Pasajero(rs.getString("apellido"), rs.getString("nombrePasajero"), TipoDocumento.valueOf(rs.getString("tipoDoc")), rs.getString("numDoc"), rs.getDate("fechaNac"), rs.getString("email"), rs.getString("ocupacion"), null, rs.getInt("idPersona"), rs.getString("CUIT"), PosicionIVA.valueOf(rs.getString("posIVA")), rs.getString("telefono"), null);
                
                OcupadaPor op = new OcupadaPor(p, rs.getBoolean("esResponsable"));
                listaOcupadaPor.add(op);
                
                //Seteo la habitacion
                Habitacion hab = new Habitacion(rs.getInt("idHabitacion"), rs.getString("nro"), rs.getFloat("precio"), new TipoDeHabitacion(rs.getInt("idTipoHabitacion"), rs.getString("nombreTipo"), rs.getFloat("precioHabitacion")));
                
                //Seteo estadia
                estadia.setIdEstadia(rs.getInt("idEstadia"));
                estadia.setFechaIngreso(rs.getDate("fechaIngreso"));
                estadia.setFechaEgreso(rs.getDate("fechaEgreso"));
                estadia.setHoraEgreso(rs.getTime("horaEgreso").toLocalTime());
                estadia.setHoraIngreso(rs.getTime("horaIngreso").toLocalTime());
                estadia.setHabitacion(hab);
                estadia.setListaOcupadaPor(listaOcupadaPor);
                
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out); 
        } finally{
            try {
                close(stmt);
                close(rs);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        
        return estadia;
    }
    
}
