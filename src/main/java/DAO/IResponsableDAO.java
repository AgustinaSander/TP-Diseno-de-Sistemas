
package DAO;

import Dominio.ResponsableDePago;

public interface IResponsableDAO {

    public ResponsableDePago obtenerResponsableDePago(String cuit);
    
}
