
package DAO;

import Dominio.Sauna;
import java.sql.SQLException;

public interface ISaunaDAO {
    public Sauna obtenerSauna(int idServicio) throws SQLException;
}
