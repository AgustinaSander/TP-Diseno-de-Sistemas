
package DAO;

import static Conexion.Conexion.*;
import Dominio.DTO.InhabilitadoDTO;
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
    public Map <Integer, List<InhabilitadoDTO>> obtenerListaInhabilitados(int idTipoHabitacion, Date fechaDesde, Date fechaHasta){
        Map <Integer, List<InhabilitadoDTO>> inhabilitados = new HashMap <>();
        try {
            conn = getConnection();
            
            java.sql.Date desde = new java.sql.Date(fechaDesde.getTime());
            java.sql.Date hasta = new java.sql.Date(fechaHasta.getTime());
            
            stmt = conn.prepareStatement("SELECT i.* FROM inhabilitado AS i ,habitacion AS h WHERE i.idHabitacion = h.id AND h.idTipoHabitacion = ? AND (((i.fechaDesde <= ?) AND (i.fechaHasta >= ?)) OR ((i.fechaDesde BETWEEN ? AND ?) AND (i.fechaHasta BETWEEN ? AND ?)) OR ((i.fechaDesde >= ?) AND (i.fechaHasta >= ?)))");
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
                int idHabitacion = rs.getInt("idHabitacion");
                if(inhabilitados.containsKey(idHabitacion)){
                    System.out.println(inhabilitados.get(idHabitacion));
                    inhabilitados.get(idHabitacion).add(new InhabilitadoDTO(rs.getInt("id"), rs.getDate("fechaDesde"), rs.getDate("fechaHasta")));
                }
                else{
                    List <InhabilitadoDTO> listaEstadias = new ArrayList<>();
                    listaEstadias.add(new InhabilitadoDTO(rs.getInt("id"), rs.getDate("fechaDesde"), rs.getDate("fechaHasta")));
                    inhabilitados.put(idHabitacion, listaEstadias);
                }
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        
        return inhabilitados;
    }
}
