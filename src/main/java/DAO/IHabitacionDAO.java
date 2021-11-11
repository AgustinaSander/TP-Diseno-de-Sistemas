
package DAO;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IHabitacionDAO{
    public List <String> obtenerTiposDeHabitaciones() throws SQLException;
    public int obtenerIdTipoHabitacion(String tipoHabitacion) throws SQLException;
    public Map <Integer,String> obtenerHabitaciones(int idTipoHabitacion) throws SQLException;
}
