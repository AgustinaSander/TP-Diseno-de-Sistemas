
package DAO;

import Dominio.DTO.EstadiaDTO;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IEstadiaDAO {
    
    public Map <Integer, List<EstadiaDTO>> obtenerListaEstadias(int idTipoHabitacion, Date fechaDesde, Date fechaHasta);
}
