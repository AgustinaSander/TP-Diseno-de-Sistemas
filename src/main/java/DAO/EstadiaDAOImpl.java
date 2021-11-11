
package DAO;

import static Conexion.Conexion.close;
import static Conexion.Conexion.getConnection;
import Dominio.DTO.EstadiaDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;



public class EstadiaDAOImpl implements IEstadiaDAO{

    private Connection conexionTransaccional;
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;
    
    @Override
    public Map <Integer, List<EstadiaDTO>> obtenerListaEstadias(int idTipoHabitacion, Date fechaDesde, Date fechaHasta){
        Map <Integer, List<EstadiaDTO>> estadias = new HashMap <>();
        try {
            conn = getConnection();
            
            java.sql.Date desde = new java.sql.Date(fechaDesde.getTime());
            java.sql.Date hasta = new java.sql.Date(fechaHasta.getTime());
            
            stmt = conn.prepareStatement("SELECT e.* FROM estadia AS e ,habitacion AS h WHERE e.idHabitacion = h.id AND h.idTipoHabitacion = ? AND (((e.fechaIngreso <= ?) AND (e.fechaEgreso >= ?)) OR ((e.fechaIngreso BETWEEN ? AND ?) AND (e.fechaEgreso BETWEEN ? AND ?)) OR ((e.fechaIngreso >= ?) AND (e.fechaEgreso >= ?)))");
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
                if(estadias.containsKey(idHabitacion)){
                    estadias.get(idHabitacion).add(new EstadiaDTO(rs.getInt("id"), rs.getDate("fechaIngreso"), rs.getDate("fechaEgreso")));
                }
                else{
                    List <EstadiaDTO> listaEstadias = new ArrayList<>();
                    listaEstadias.add(new EstadiaDTO(rs.getInt("id"), rs.getDate("fechaIngreso"), rs.getDate("fechaEgreso")));
                    estadias.put(idHabitacion, listaEstadias);
                }
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        
        return estadias;
    }
    
}
