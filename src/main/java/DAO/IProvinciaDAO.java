
package DAO;

import Dominio.Provincia;
import java.sql.SQLException;
import java.util.List;

public interface IProvinciaDAO {
    public List<String> obtenerLocalidades(String provincia, String pais) throws SQLException;
public int obtenerIdProvincia(String provincia, int idPais) throws SQLException;
}
