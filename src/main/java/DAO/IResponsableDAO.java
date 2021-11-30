
package DAO;

import Dominio.ResponsableDePago;
import java.sql.SQLException;

public interface IResponsableDAO {

    public ResponsableDePago obtenerResponsableDePago(String cuit) throws SQLException;
    public ResponsableDePago obtenerResponsableDePago(int idPersona) throws SQLException;
}
