
package DAO;

import Dominio.Estadia;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IEstadiaDAO {
    
    public List<Estadia> obtenerListaEstadias(int idTipoHabitacion, Date fechaDesde, Date fechaHasta) throws SQLException;
    public int crearEstadia(Estadia estadia) throws SQLException;
}
