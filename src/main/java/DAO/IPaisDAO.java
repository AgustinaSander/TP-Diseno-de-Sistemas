
package DAO;

import Dominio.Pais;
import Dominio.Provincia;
import java.sql.SQLException;
import java.util.List;

public interface IPaisDAO {
    public Pais obtenerPais(String nombre) throws SQLException;
    public List<Pais> obtenerPaises() throws SQLException;
    public Pais obtenerNacionalidad(int idPersona) throws SQLException;
    public Pais obtenerPais(int idPais) throws SQLException;
    public List<Provincia> obtenerProvincias(String pais) throws SQLException;
}
