
package DAO;

import Dominio.DTO.ReservaDTO;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IReservaDAO {
    public Map <Integer, List<ReservaDTO>> obtenerListaReservas(int idTipoHabitacion, Date fechaDesde, Date fechaHasta);
}
