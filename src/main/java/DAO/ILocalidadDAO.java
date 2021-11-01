
package DAO;

import Dominio.Localidad;
import java.sql.SQLException;
import java.util.List;

public interface ILocalidadDAO {
    public List<String> obtenerLocalidad(int idLocalidad) throws SQLException;
    public int obtenerIdLocalidad(String localidad) throws SQLException;
}
