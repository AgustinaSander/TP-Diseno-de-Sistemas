
package DAO;

import java.sql.SQLException;
import java.util.List;

public interface IPaisDAO {
    public int obtenerIdPais(String nombre) throws SQLException;
    public List<String> obtenerPaises() throws SQLException;
    public String obtenerNacionalidad(int idPais) throws SQLException;
    public String obtenerPais(int idPais) throws SQLException;
    public List<String> obtenerProvincias(String pais) throws SQLException;
}
