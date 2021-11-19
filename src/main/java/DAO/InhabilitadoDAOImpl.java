
package DAO;

import static Conexion.Conexion.*;
import Dominio.Habitacion;
import Dominio.Inhabilitado;
import Dominio.TipoDeHabitacion;
import Enum.MotivoInhabilitado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InhabilitadoDAOImpl implements IInhabilitadoDAO{
    private Connection conexionTransaccional;
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;
    
    @Override
    public List<Inhabilitado> obtenerListaInhabilitados(int idTipoHabitacion, Date fechaDesde, Date fechaHasta){
        List<Inhabilitado> listaInhabilitados = new ArrayList <>();
        try {
            conn = getConnection();
            
            java.sql.Date desde = new java.sql.Date(fechaDesde.getTime());
            java.sql.Date hasta = new java.sql.Date(fechaHasta.getTime());
            
            stmt = conn.prepareStatement("SELECT i.*, h.* FROM inhabilitado AS i ,habitacion AS h WHERE i.idHabitacion = h.id AND h.idTipoHabitacion = ? AND (((i.fechaDesde <= ?) AND (i.fechaHasta >= ?)) OR ((i.fechaDesde BETWEEN ? AND ?) AND (i.fechaHasta BETWEEN ? AND ?)) OR ((i.fechaDesde >= ?) AND (i.fechaHasta >= ?)))");
            stmt.setInt(1,idTipoHabitacion);
            stmt.setDate(2,desde);
            stmt.setDate(3,hasta);
            stmt.setDate(4,desde);
            stmt.setDate(5,hasta);
            stmt.setDate(6,desde);
            stmt.setDate(7,hasta);
            stmt.setDate(8,desde);
            stmt.setDate(9,hasta);
            
            rs = stmt.executeQuery();
            
            while(rs.next()){
                //int idInhabilitado, Date fechaInicio, Date fechaFin, MotivoInhabilitado motivo, Habitacion habitacion
                //Creo el objeto TipoDeHabitacion
                TipoDeHabitacion tipoHab = new TipoDeHabitacion(idTipoHabitacion);
                //Creo el objeto Habitacion
                Habitacion hab = new Habitacion(rs.getInt("idHabitacion"), rs.getString("nro"),rs.getFloat("precio"), tipoHab);
                //Creo el objeto Inhabilitado
                Inhabilitado inhabilitado = new Inhabilitado(rs.getInt("id"), rs.getDate("fechaDesde"), rs.getDate("fechaHasta"), MotivoInhabilitado.valueOf(rs.getString("motivo")), hab);
                
                //Lo agrego a la lista de inhabilitados
                listaInhabilitados.add(inhabilitado);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        
        return listaInhabilitados;
    }
}
