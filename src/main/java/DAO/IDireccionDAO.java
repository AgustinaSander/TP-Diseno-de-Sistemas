
package DAO;

import Dominio.Direccion;
import java.sql.Connection;
import java.sql.SQLException;

public interface IDireccionDAO {
    public int crearDireccion(Direccion direccion) throws SQLException;
    public void modificarDireccion(Direccion direccion) throws SQLException;
}
