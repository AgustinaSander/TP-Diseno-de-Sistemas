
package DAO;

import static Conexion.Conexion.getConnection;
import Dominio.DTO.ReservaDTO;
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
    public Map<Integer, List<ReservaDTO>> obtenerListaReservas(int idTipoHabitacion, Date fechaDesde, Date fechaHasta) {
         Map <Integer, List<ReservaDTO>> reservas = new HashMap <>();
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
                    System.out.println(reservas.get(idHabitacion));
                    reservas.get(idHabitacion).add(new ReservaDTO(rs.getInt("idReserva"), rs.getDate("fechaDesde"), rs.getDate("fechaHasta"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("telefono")));
                }
                else{
                    List <ReservaDTO> listaReservas = new ArrayList<>();
                    listaReservas.add(new ReservaDTO(rs.getInt("idReserva"), rs.getDate("fechaDesde"), rs.getDate("fechaHasta"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("telefono")));
                    reservas.put(idHabitacion, listaReservas);
                }
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        
        return reservas;
    }
    
}
