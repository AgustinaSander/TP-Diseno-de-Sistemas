//
package DAO;

import Dominio.FechaReserva;
import Dominio.Reserva;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IReservaDAO {
    public Map <Integer, List<FechaReserva>> obtenerListaReservas(int idTipoHabitacion, Date fechaDesde, Date fechaHasta);
    public List<FechaReserva> buscarReservas(int idHab, List<Date> fechas);
}
