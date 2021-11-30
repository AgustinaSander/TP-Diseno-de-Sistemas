
package DAO;

import Dominio.Localidad;
import Dominio.Provincia;
import java.sql.SQLException;
import java.util.List;

public interface IProvinciaDAO {
    public List<Localidad> obtenerLocalidades(String provincia, String pais) throws SQLException;
    public Provincia obtenerProvincia(String provincia, int idPais) throws SQLException;
    public Provincia obtenerProvincia(int idProvincia) throws SQLException;
}
