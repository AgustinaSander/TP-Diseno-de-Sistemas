
package DAO;

import Dominio.Habitacion;
import java.sql.SQLException;
import java.util.List;

public interface IHabitacionDAO{
    public Habitacion obtenerHabitacion(int idHabitacion) throws SQLException;
    public Habitacion obtenerHabitacion(String nroHabitacion) throws SQLException;
    public List<Habitacion> obtenerHabitacionesDeUnTipo(int idTipo) throws SQLException;
    
}
