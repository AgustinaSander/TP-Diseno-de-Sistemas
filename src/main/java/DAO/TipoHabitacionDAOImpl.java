
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
    public List<TipoDeHabitacion> obtenerTiposDeHabitaciones() throws SQLException{
        List<TipoDeHabitacion> tiposHabitaciones = new ArrayList <>();
        try {
            conn = getConnection();
            
            if(conn.getAutoCommit()){
                conn.setAutoCommit(false);
            }
            
            stmt = conn.prepareStatement("SELECT * FROM tipodehabitacion");
            rs = stmt.executeQuery();
            while(rs.next()){ 
                TipoDeHabitacion tipoHab = new TipoDeHabitacion(rs.getInt("id"), rs.getString("nombre"), rs.getFloat("precio"));
                //Llamo al HabitacionDAO para obtener la lista de habitaciones de ese tipo
                List<Habitacion> listaHabitaciones = new HabitacionDAOImpl(conn).obtenerHabitacionesDeUnTipo(rs.getInt("id"));
                tipoHab.setListaHabitaciones(listaHabitaciones);
                tiposHabitaciones.add(tipoHab);
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

        return tiposHabitaciones;
    }

    @Override
    public TipoDeHabitacion obtenerHabitacionesDeUnTipo(String tipoHabitacion) throws SQLException{
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
            
            //Buscamos las habitaciones de ese tipo de habitacion con HabitacionDAO
            habitaciones = new HabitacionDAOImpl(conn).obtenerHabitacionesDeUnTipo(tipoDeHabitacion.getIdTipoHabitacion());
            tipoDeHabitacion.setListaHabitaciones(habitaciones);
            
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
        
        return tipoDeHabitacion;
    }

    
}
