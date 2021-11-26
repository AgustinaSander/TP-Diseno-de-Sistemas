
package DAO;

import Dominio.Habitacion;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IHabitacionDAO{
    public Habitacion obtenerHabitacion(int idHabitacion) throws SQLException;
    public Boolean buscarNroHabitacion(String nroHabitacion);
}
