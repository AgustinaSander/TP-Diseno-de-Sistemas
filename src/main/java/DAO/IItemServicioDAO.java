
package DAO;


import Dominio.ItemFactura;
import java.sql.SQLException;

public interface IItemServicioDAO {
    public ItemFactura obtenerItemServicio(int idItem) throws SQLException;
}
