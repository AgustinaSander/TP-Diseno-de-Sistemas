
package DAO;

import Dominio.Estadia;
import Dominio.Habitacion;
import Dominio.Pasajero;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IEstadiaDAO {
    
    public List<Estadia> obtenerListaEstadias(List<Habitacion> habitaciones, Date fechaDesde, Date fechaHasta) throws SQLException;
    public int crearEstadia(Estadia estadia) throws SQLException;
    public List<Pasajero> obtenerPasajerosEstadia(int idEstadia);
    public Estadia obtenerUltimaEstadia(String nroHabitacion);
}
