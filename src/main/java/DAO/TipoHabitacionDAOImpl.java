
package DAO;

import static Conexion.Conexion.close;
import static Conexion.Conexion.getConnection;
import Dominio.FechaReserva;
import Dominio.Habitacion;
import Dominio.Reserva;
import Dominio.TipoDeHabitacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TipoHabitacionDAOImpl implements ITipoHabitacionDAO{
    
    private Connection conexionTransaccional;
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;
    
    @Override
    public List<String> obtenerTiposDeHabitaciones() throws SQLException{
        List<String> tiposHabitaciones = new ArrayList <>();
        try {
            conn = getConnection();
            stmt = conn.prepareStatement("SELECT nombre FROM tipodehabitacion");
            rs = stmt.executeQuery();
            while(rs.next()){
                tiposHabitaciones.add(rs.getString("nombre"));
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

        return tiposHabitaciones;
    }

    @Override
    public List<Habitacion> obtenerHabitaciones(String tipoHabitacion) throws SQLException{
        List<Habitacion> habitaciones = new ArrayList<>();
        TipoDeHabitacion tipoDeHabitacion = new TipoDeHabitacion();
        try {
            conn = getConnection();
            
            //Conexion transaccional
            if(conn.getAutoCommit()){
                conn.setAutoCommit(false);
            }
            //Obtengo el id del tipo de habitacion
            stmt = conn.prepareStatement("SELECT * FROM tipodehabitacion WHERE nombre = ?");
            stmt.setString(1,tipoHabitacion);
            rs = stmt.executeQuery();
            while(rs.next()){
                tipoDeHabitacion.setIdTipoHabitacion(rs.getInt("id"));
                tipoDeHabitacion.setNombre(rs.getString("nombre"));
                tipoDeHabitacion.setPrecio(rs.getFloat("precio"));
            }
            
            //Buscamos las habitaciones de ese tipo de habitacion
            stmt = conn.prepareStatement("SELECT * FROM habitacion WHERE idTipoHabitacion = ? ");
            stmt.setInt(1,tipoDeHabitacion.getIdTipoHabitacion());
            rs = stmt.executeQuery();
            while(rs.next()){
                //Por cada habitacion de ese tipo
                Habitacion h = new Habitacion(rs.getInt("id"), rs.getString("nro"), rs.getFloat("precio"), tipoDeHabitacion);
                
                //Agrego la habitaciones a la lista de habitaciones para retornarla
                habitaciones.add(h);
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
        
        return habitaciones;
    }

    
}
