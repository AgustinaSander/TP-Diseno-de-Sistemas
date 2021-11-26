
package DAO;

import static Conexion.Conexion.*;
import Dominio.Habitacion;
import Dominio.Inhabilitado;
import Enum.MotivoInhabilitado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InhabilitadoDAOImpl implements IInhabilitadoDAO{
    private Connection conexionTransaccional;
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;
    
    @Override
    public List<Inhabilitado> obtenerListaInhabilitados(List<Habitacion> habitaciones, Date fechaDesde, Date fechaHasta) throws SQLException{
        List<Inhabilitado> listaInhabilitados = new ArrayList <>();
        try {
            conn = getConnection();
            
            //Conexion transaccional
            if(conn.getAutoCommit()){
                conn.setAutoCommit(false);
            }
            
            java.sql.Date desde = new java.sql.Date(fechaDesde.getTime());
            java.sql.Date hasta = new java.sql.Date(fechaHasta.getTime());
            
            for(Habitacion h : habitaciones){
            
                stmt = conn.prepareStatement("SELECT i.*, h.* FROM inhabilitado AS i ,habitacion AS h WHERE i.idHabitacion = ? AND h.id = ? AND (((i.fechaDesde <= ?) AND (i.fechaHasta >= ?)) OR ((i.fechaDesde BETWEEN ? AND ?) AND (i.fechaHasta BETWEEN ? AND ?)) OR ((i.fechaDesde >= ?) AND (i.fechaHasta >= ?)))");
                stmt.setInt(1,h.getIdHabitacion());
                stmt.setInt(2,h.getIdHabitacion());
                stmt.setDate(3,desde);
                stmt.setDate(4,hasta);
                stmt.setDate(5,desde);
                stmt.setDate(6,hasta);
                stmt.setDate(7,desde);
                stmt.setDate(8,hasta);
                stmt.setDate(9,desde);
                stmt.setDate(10,hasta);

                rs = stmt.executeQuery();

                while(rs.next()){
                    //Creo el objeto Inhabilitado
                    Inhabilitado inhabilitado = new Inhabilitado(rs.getInt("id"), rs.getDate("fechaDesde"), rs.getDate("fechaHasta"), MotivoInhabilitado.valueOf(rs.getString("motivo")), h);
                    //Lo agrego a la lista de inhabilitados
                    listaInhabilitados.add(inhabilitado);
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
        
        return listaInhabilitados;
    }
}
