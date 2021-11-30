
package DAO;

import Dominio.Habitacion;
import Dominio.Inhabilitado;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;


public interface IInhabilitadoDAO {
    public List<Inhabilitado> obtenerListaInhabilitados(Habitacion habitacion, Date fechaDesde, Date fechaHasta) throws SQLException;
}
