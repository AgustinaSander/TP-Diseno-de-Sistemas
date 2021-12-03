
package DAO;

import static Conexion.Conexion.close;
import static Conexion.Conexion.getConnection;
import Dominio.Habitacion;
import Dominio.TipoDeHabitacion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class HabitacionDAOImpl implements IHabitacionDAO{

    private Connection conexionTransaccional;
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;

    public HabitacionDAOImpl() {
    }

    public HabitacionDAOImpl(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }
    
    @Override
    public Habitacion obtenerHabitacion(int idHabitacion) throws SQLException{
        Habitacion hab = null;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            
            stmt = conn.prepareStatement("SELECT h.id AS idHabitacion, h.nro, h.idTipoHabitacion, t.* FROM habitacion AS h, tipodehabitacion AS t WHERE h.id = ? AND h.idTipoHabitacion = t.id");
            stmt.setInt(1,idHabitacion);
            rs = stmt.executeQuery();
            while(rs.next()){
                hab = new Habitacion(rs.getInt("idHabitacion"),rs.getString("nro"));
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
        
        return hab;
    }

    @Override
    public Habitacion obtenerHabitacion(String nroHabitacion) throws SQLException{
        Habitacion habitacion = null;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
             
            stmt = conn.prepareStatement("SELECT * FROM `habitacion` WHERE nro = ?");
            stmt.setString(1, nroHabitacion);
            rs = stmt.executeQuery();
            while(rs.next()){
                habitacion = new Habitacion(rs.getInt("id"), rs.getString("nro"));
            }
            
        }finally{
            try {
                if(this.conexionTransaccional == null){
                    close(stmt);
                    close(rs);
                    close(conn);
                }
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return habitacion;
    }
    
    @Override
    public List<Habitacion> obtenerHabitaciones() throws SQLException{
        List<Habitacion> habitaciones = new ArrayList<>();
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
             
            stmt = conn.prepareStatement("SELECT * FROM `habitacion`");
            rs = stmt.executeQuery();
            while(rs.next()){
                habitaciones.add(new Habitacion(rs.getInt("id"), rs.getString("nro")));
            }
            
        }finally{
            try {
                if(this.conexionTransaccional == null){
                    close(stmt);
                    close(rs);
                    close(conn);
                }
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return habitaciones;
    }

    @Override
    public List<Habitacion> obtenerHabitacionesDeUnTipo(int idTipo) throws SQLException{
        List<Habitacion> habitaciones = new ArrayList<>();
        
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            
            stmt = conn.prepareStatement("SELECT * FROM habitacion WHERE idTipoHabitacion = ?");
            stmt.setInt(1,idTipo);
            rs = stmt.executeQuery();
            while(rs.next()){
                habitaciones.add(new Habitacion(rs.getInt("id"), rs.getString("nro")));
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
        
        return habitaciones;
    }
    
}
