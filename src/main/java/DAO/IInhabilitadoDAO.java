
package DAO;

import Dominio.DTO.InhabilitadoDTO;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IInhabilitadoDAO {
    public Map <Integer, List<InhabilitadoDTO>> obtenerListaInhabilitados(int idTipoHabitacion, Date fechaDesde, Date fechaHasta);
}
