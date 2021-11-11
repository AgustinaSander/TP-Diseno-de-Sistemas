
package Gestores;

import DAO.IInhabilitadoDAO;
import DAO.InhabilitadoDAOImpl;
import Dominio.DTO.InhabilitadoDTO;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class GestorInhabilitado {
    private static GestorInhabilitado instanciaGInhabilitado = null;
    private static IInhabilitadoDAO inhabilitadoDAO = null;

    
    private GestorInhabilitado(){};
    
    public static GestorInhabilitado getInstanceInhabilitado(){
        if(instanciaGInhabilitado == null)
            instanciaGInhabilitado = new GestorInhabilitado();
   
        return instanciaGInhabilitado;
    }
    
    public Map <Integer, List<InhabilitadoDTO>> obtenerListaInhabilitados(int idTipoHabitacion, Date fechaDesde, Date fechaHasta){
        inhabilitadoDAO = new InhabilitadoDAOImpl();
        Map <Integer, List<InhabilitadoDTO>> listaInhabilitados = inhabilitadoDAO.obtenerListaInhabilitados(idTipoHabitacion, fechaDesde, fechaHasta);
         
        return listaInhabilitados;
    }
}
