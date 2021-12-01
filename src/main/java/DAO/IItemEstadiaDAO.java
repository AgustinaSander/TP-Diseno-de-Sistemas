
package DAO;


import Dominio.Factura;
import Dominio.ItemFactura;
import java.sql.SQLException;
import java.util.List;

public interface IItemEstadiaDAO {
    public ItemFactura obtenerItemEstadia(int idItem) throws SQLException;
}
