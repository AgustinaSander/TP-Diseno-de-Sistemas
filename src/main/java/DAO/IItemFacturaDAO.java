
package DAO;

import Dominio.Factura;
import Dominio.ItemFactura;
import java.sql.SQLException;
import java.util.List;

public interface IItemFacturaDAO {
    public List<ItemFactura> obtenerItemsFactura(Factura factura) throws SQLException;
    public void crearItemsFactura(List<ItemFactura> listaItemsFactura, int idFactura) throws SQLException;
}
