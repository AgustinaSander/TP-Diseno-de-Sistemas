
package DAO;

import Dominio.Inhabilitado;
import java.util.Date;
import java.util.List;


public interface IInhabilitadoDAO {
    public List<Inhabilitado> obtenerListaInhabilitados(int idTipoHabitacion, Date fechaDesde, Date fechaHasta);
}
