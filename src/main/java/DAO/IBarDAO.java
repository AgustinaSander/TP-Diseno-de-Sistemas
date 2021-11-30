
package DAO;

import Dominio.Bar;
import java.sql.SQLException;


public interface IBarDAO {
    public Bar obtenerBar(int idServicio) throws SQLException;
}
