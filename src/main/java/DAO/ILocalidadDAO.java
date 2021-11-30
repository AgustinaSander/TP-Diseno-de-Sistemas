
package DAO;

import Dominio.Localidad;
import java.sql.SQLException;
import java.util.List;

public interface ILocalidadDAO {
    public Localidad obtenerLocalidad(int idLocalidad) throws SQLException;
    public Localidad obtenerLocalidad(String localidad, int idProvincia) throws SQLException;
}
