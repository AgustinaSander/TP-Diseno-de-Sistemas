
package DAO;

import Dominio.Factura;
import java.sql.SQLException;
import java.util.List;

public interface IFacturaDAO {
    public List<Factura> obtenerFacturasEstadia(int idEstadia) throws SQLException;
}
