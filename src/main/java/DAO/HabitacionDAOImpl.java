
package DAO;

import static Conexion.Conexion.close;
import static Conexion.Conexion.getConnection;
import Dominio.Habitacion;
import Dominio.TipoDeHabitacion;
import java.sql.*;
import java.util.List;


public class HabitacionDAOImpl implements IHabitacionDAO{

    private Connection conexionTransaccional;
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;
    
   
    @Override
    public Habitacion obtenerHabitacion(int idHabitacion) throws SQLException{
        Habitacion hab = null;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement("SELECT h.id AS idHabitacion, h.nro, h.idTipoHabitacion, h.precio AS precioHabitacion, t.* FROM habitacion AS h, tipodehabitacion AS t WHERE h.id = ? AND h.idTipoHabitacion = t.id");
            stmt.setInt(1,idHabitacion);
            rs = stmt.executeQuery();
            while(rs.next()){
                TipoDeHabitacion tipo = new TipoDeHabitacion(rs.getInt("idTipoHabitacion"), rs.getString("nombre"), rs.getFloat("precio"));
                hab = new Habitacion(rs.getInt("idHabitacion"),rs.getString("nro"), rs.getFloat("precioHabitacion"), tipo);
            }
            
        } finally {
            try {
                close(stmt);
                close(rs);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return hab;
    }

    @Override
    public Boolean buscarNroHabitacion(String nroHabitacion) {
        Boolean nroExistente = false;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement("SELECT COUNT(*) FROM `habitacion` WHERE nro = ?");
            stmt.setString(1, nroHabitacion);
            rs = stmt.executeQuery();
            while(rs.next()){
                if(rs.getInt("COUNT(*)") > 0){
                    nroExistente = true;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return nroExistente;
    }

}
