
package DAO;

import Dominio.Servicio;
import java.util.List;

public interface IServicioDAO {
    public List<Servicio> obtenerServicios(int idEstadia);
    
}
