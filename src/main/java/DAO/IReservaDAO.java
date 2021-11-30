//
package DAO;

import Dominio.FechaReserva;
import Dominio.Habitacion;
import Dominio.Reserva;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface IReservaDAO {
    public List<Reserva> obtenerListaReservas(Habitacion habitacion, Date fechaDesde, Date fechaHasta) throws SQLException;
    public List<Reserva> buscarReservas(int idHab, List<Date> fechas);
}
