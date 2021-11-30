
package DAO;

import Dominio.LavadoYPlanchado;
import java.sql.SQLException;

public interface ILavadoYPlanchadoDAO {
    public LavadoYPlanchado obtenerLavadoYPlanchado(int idServicio) throws SQLException;
}
