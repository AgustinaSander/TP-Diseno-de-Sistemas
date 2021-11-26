//
package DAO;

import Dominio.FechaReserva;
import Dominio.Habitacion;
import Dominio.Reserva;
import java.util.Date;
import java.util.List;

public interface IReservaDAO {
    public List<Reserva> obtenerListaReservas(List<Habitacion> habitaciones, Date fechaDesde, Date fechaHasta);
    public List<FechaReserva> buscarReservas(int idHab, List<Date> fechas);
}
