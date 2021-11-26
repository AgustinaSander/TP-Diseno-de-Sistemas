
package DAO;

import Dominio.Habitacion;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface ITipoHabitacionDAO {
    public List <String> obtenerTiposDeHabitaciones() throws SQLException;
    public List<Habitacion> obtenerHabitaciones(String tipoHabitacion) throws SQLException;
}
