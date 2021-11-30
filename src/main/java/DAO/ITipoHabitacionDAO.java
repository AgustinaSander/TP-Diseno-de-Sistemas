
package DAO;

import Dominio.TipoDeHabitacion;
import java.sql.SQLException;
import java.util.List;

public interface ITipoHabitacionDAO {
    public List <TipoDeHabitacion> obtenerTiposDeHabitaciones() throws SQLException;
    public TipoDeHabitacion obtenerHabitacionesDeUnTipo(String tipoHabitacion) throws SQLException;
}
