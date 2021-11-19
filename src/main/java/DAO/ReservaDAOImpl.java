
package DAO;

import static Conexion.Conexion.getConnection;
import Dominio.DTO.ReservaDTO;
import Dominio.FechaReserva;
import Dominio.Reserva;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReservaDAOImpl implements IReservaDAO{
    private Connection conexionTransaccional;
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;

    @Override
    public Map<Integer, List<FechaReserva>> obtenerListaReservas(int idTipoHabitacion, Date fechaDesde, Date fechaHasta) {
         Map <Integer, List<FechaReserva>> reservas = new HashMap <>();
        try {
            conn = getConnection();
            
            java.sql.Date desde = new java.sql.Date(fechaDesde.getTime());
            java.sql.Date hasta = new java.sql.Date(fechaHasta.getTime());
            
            stmt = conn.prepareStatement("SELECT f.*, r.* FROM fechareserva AS f, habitacion AS h, reserva AS r WHERE f.idHabitacion = h.id AND h.idTipoHabitacion = ? AND f.idReserva = r.id AND (((f.fechaDesde <= ?) AND (f.fechaHasta >= ?)) OR ((f.fechaDesde <= ?) AND (f.fechaHasta BETWEEN ? AND ?)) OR ((f.fechaDesde BETWEEN ? AND ?) AND (f.fechaHasta BETWEEN ? AND ?)) OR ((f.fechaDesde >= ?) AND (f.fechaHasta >= ?)));");
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
                int idHabitacion = rs.getInt("idHabitacion");
                if(reservas.containsKey(idHabitacion)){
                    //Lista de reservas de esa habitacion
                    List <FechaReserva> listaFechasReservas = reservas.get(idHabitacion);
                    //Creo el objeto reserva, el objeto habitacion lo pongo en null porque no lo necesito
                    Reserva reserva = new Reserva(rs.getInt("idReserva"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("telefono"));
                    //Creo el objeto fechaReserva que contiene la reserva
                    FechaReserva fechaReserva = new FechaReserva(rs.getDate("fechaDesde"), rs.getTime("horaDesde").toLocalTime(), rs.getDate("fechaHasta"), rs.getTime("horaHasta").toLocalTime(), null, reserva);

                    reservas.get(idHabitacion).add(fechaReserva);
                }
                else{
                    //Lista para guardar las reservas de esa habitacion
                    List <FechaReserva> listaFechasReservas = new ArrayList<>();
                    //Creo el objeto reserva, el objeto habitacion lo pongo en null porque no lo necesito
                    Reserva reserva = new Reserva(rs.getInt("idReserva"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("telefono"));
                    //Creo el objeto fechaReserva que contiene la reserva
                    FechaReserva fechaReserva = new FechaReserva(rs.getDate("fechaDesde"), rs.getTime("horaDesde").toLocalTime(), rs.getDate("fechaHasta"), rs.getTime("horaHasta").toLocalTime(), null, reserva);
                    listaFechasReservas.add(fechaReserva);

                    reservas.put(idHabitacion, listaFechasReservas);
                }
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        
        return reservas;
    }
    
    
    public List<FechaReserva> buscarReservas(int idHab, List<Date> fechas){
        List<FechaReserva> fechasReservas = new ArrayList<>();
        for(Date f: fechas){
            try {
                conn = getConnection();
                stmt = conn.prepareStatement("SELECT * FROM `fechareserva` AS f ,`reserva` AS r WHERE idHabitacion = ? AND f.idReserva=r.id AND ? BETWEEN fechaDesde AND fechaHasta ");
                stmt.setInt(1, idHab);
                stmt.setDate(2, new java.sql.Date(f.getTime()));
                rs = stmt.executeQuery();
                while(rs.next()){
                    Reserva r = new Reserva(rs.getInt("idReserva"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("telefono"));
                    FechaReserva fechaRes = new FechaReserva(rs.getDate("fechaDesde"), rs.getTime("horaDesde").toLocalTime(), rs.getDate("fechaHasta"), rs.getTime("horaHasta").toLocalTime(), null, r);
                    
                    //Si ya se agrego la fechaReserva a la lista de fechasReservas
                    Boolean agregada = false;
                    for(FechaReserva fr : fechasReservas){
                        if(fr.getReserva().getIdReserva() == r.getIdReserva()){
                            agregada = true;
                            break;
                        }
                    }
                    if(!agregada){
                        fechasReservas.add(fechaRes);
                    }
                }

            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        System.out.println("Fechas:" + fechasReservas);
        return fechasReservas;
    }
}
