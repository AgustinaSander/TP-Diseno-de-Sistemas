
package Gestores;

import DAO.IInhabilitadoDAO;
import DAO.InhabilitadoDAOImpl;
import Dominio.Inhabilitado;
import java.util.Date;
import java.util.List;


public class GestorInhabilitado {
    private static GestorInhabilitado instanciaGInhabilitado = null;
    private static IInhabilitadoDAO inhabilitadoDAO = null;

    
    private GestorInhabilitado(){};
    
    public static GestorInhabilitado getInstanceInhabilitado(){
        if(instanciaGInhabilitado == null)
            instanciaGInhabilitado = new GestorInhabilitado();
   
        return instanciaGInhabilitado;
    }
    
    public List<Inhabilitado> obtenerListaInhabilitados(int idTipoHabitacion, Date fechaDesde, Date fechaHasta){
        inhabilitadoDAO = new InhabilitadoDAOImpl();
        List<Inhabilitado> listaInhabilitados = inhabilitadoDAO.obtenerListaInhabilitados(idTipoHabitacion, fechaDesde, fechaHasta);
         
        return listaInhabilitados;
    }
}
