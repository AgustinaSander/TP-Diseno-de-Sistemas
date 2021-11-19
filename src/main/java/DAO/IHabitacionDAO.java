
package DAO;

import Dominio.Habitacion;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IHabitacionDAO{
    public List <String> obtenerTiposDeHabitaciones() throws SQLException;
    public int obtenerIdTipoHabitacion(String tipoHabitacion) throws SQLException;
    public Map <Integer,String> obtenerHabitaciones(int idTipoHabitacion) throws SQLException;
    public Habitacion obtenerHabitacion(int idHabitacion);
}
