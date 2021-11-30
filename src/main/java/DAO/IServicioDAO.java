
package DAO;

import Dominio.Servicio;
import java.sql.SQLException;
import java.util.List;

public interface IServicioDAO {
    public List<Servicio> obtenerServiciosEstadia(int idEstadia) throws SQLException;
    
}
