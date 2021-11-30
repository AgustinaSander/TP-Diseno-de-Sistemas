
package DAO;

import static Conexion.Conexion.close;
import static Conexion.Conexion.getConnection;
import Dominio.FechaReserva;
import Dominio.Habitacion;
import Dominio.Reserva;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReservaDAOImpl implements IReservaDAO{
    private Connection conexionTransaccional;
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;


    @Override
    //Obtengo de la lista de reservas de la habitacion que se encuentran dentro del rango de fechas
    public List<Reserva> obtenerListaReservas(Habitacion habitacion, Date fechaDesde, Date fechaHasta) {
        List<Reserva> reservas = new ArrayList<>();
        try {
            conn = getConnection();
            
            //Conexion transaccional
            if(conn.getAutoCommit()){
                conn.setAutoCommit(false);
            }
            
            java.sql.Date desde = new java.sql.Date(fechaDesde.getTime());
            java.sql.Date hasta = new java.sql.Date(fechaHasta.getTime());
            
            //Obtengo los idReserva de las reservas que contienen a esa habitacion
            List<Integer> idReservas = new ArrayList<>();
            stmt = conn.prepareStatement("SELECT * FROM fechareserva WHERE idHabitacion = ?");
            stmt.setInt(1, habitacion.getIdHabitacion());
            rs = stmt.executeQuery();
            while(rs.next()){
                if(!idReservas.contains(rs.getInt("idReserva"))){
                    idReservas.add(rs.getInt("idReserva"));
                }
            }
          
            //Por idReserva voy creando la lista de FechaReserva de esa Reserva
            for(int id : idReservas){
                //Obtengo las fechaReserva con ese idReserva que esten dentro del rango de fechas
                stmt = conn.prepareStatement("SELECT r.id AS idReserva, r.*, h.*, f.* FROM fechareserva AS f, habitacion AS h, reserva AS r WHERE f.idReserva = ? AND f.idHabitacion = ? AND f.idHabitacion = h.id AND f.idReserva = r.id  AND (((f.fechaDesde <= ?) AND (f.fechaHasta >= ?)) OR ((f.fechaDesde <= ?) AND (f.fechaHasta BETWEEN ? AND ?)) OR ((f.fechaDesde BETWEEN ? AND ?) AND (f.fechaHasta BETWEEN ? AND ?)) OR ((f.fechaDesde >= ?) AND (f.fechaHasta >= ?)));");
                stmt.setInt(1,id);
                stmt.setInt(2,habitacion.getIdHabitacion());
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
                
                Reserva reserva = new Reserva();
                
                List<FechaReserva> listaFechaReserva = new ArrayList<>();
                while(rs.next()){
                    reserva.setIdReserva(rs.getInt("idReserva"));
                    reserva.setApellido(rs.getString("apellido"));
                    reserva.setNombre(rs.getString("nombre"));
                    reserva.setTelefono(rs.getString("telefono"));
                    
                    if(habitacion.getIdHabitacion() == rs.getInt("idHabitacion")){
                        //Creo el objeto FechaReserva y lo agrego a la lista
                        FechaReserva fr = new FechaReserva(rs.getDate("fechaDesde"), rs.getTime("horaDesde").toLocalTime(), rs.getDate("fechaHasta"), rs.getTime("horaHasta").toLocalTime(), habitacion);
                        
                        listaFechaReserva.add(fr);
                        break;
                    }
                }
                reserva.setListaFechaReserva(listaFechaReserva);
                reservas.add(reserva);
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
        
        return reservas;
    }

    
    public List<Reserva> buscarReservas(int idHab, List<Date> fechas){
        List<Reserva> reservas = new ArrayList<>();
        for(Date f: fechas){
            try {
                conn = getConnection();
                stmt = conn.prepareStatement("SELECT * FROM `fechareserva` AS f ,`reserva` AS r WHERE idHabitacion = ? AND f.idReserva=r.id AND ? BETWEEN fechaDesde AND fechaHasta ");
                stmt.setInt(1, idHab);
                stmt.setDate(2, new java.sql.Date(f.getTime()));
                rs = stmt.executeQuery();
                
                while(rs.next()){
                    Reserva reserva = new Reserva(rs.getInt("idReserva"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("telefono"));
                    //Recupero la habitacion con ese idHabitacion
                    Habitacion habitacion = new HabitacionDAOImpl(conn).obtenerHabitacion(idHab);
                    
                    FechaReserva fechaRes = new FechaReserva(rs.getDate("fechaDesde"), rs.getTime("horaDesde").toLocalTime(), rs.getDate("fechaHasta"), rs.getTime("horaHasta").toLocalTime(), habitacion);
                    List<FechaReserva> listaFechasReserva = new ArrayList<>();
                    listaFechasReserva.add(fechaRes);
                    reserva.setListaFechaReserva(listaFechasReserva);
                    
                    //Si ya se agrego la reserva a la lista de reservas
                    Boolean agregada = false;
                    
                    if(reservas.size()>0){
                        for(Reserva r : reservas){
                            if(r.getIdReserva() == reserva.getIdReserva()){
                                agregada = true;
                                break;
                            }
                        }
                        if(!agregada){
                            reservas.add(reserva);
                        }
                    }
                    else{
                        reservas.add(reserva);
                        agregada = true;
                    }
                }

            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        
        return reservas;
    }
}
