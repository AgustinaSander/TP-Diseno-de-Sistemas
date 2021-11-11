
package Gestores;

import DAO.EstadiaDAOImpl;
import DAO.IEstadiaDAO;
import Dominio.DTO.EstadiaDTO;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class GestorEstadias {
    private static GestorEstadias instanciaGEstadias = null;
    private static IEstadiaDAO estadiaDAO = null;
    
    private GestorEstadias(){};
    
    public static GestorEstadias getInstanceEstadias(){
        if(instanciaGEstadias == null)
            instanciaGEstadias = new GestorEstadias();
   
        return instanciaGEstadias;
    }
    
    public Map<Integer, List<EstadiaDTO>> obtenerListaEstadias(int idTipoHabitacion, Date fechaDesde, Date fechaHasta){
        estadiaDAO = new EstadiaDAOImpl();
        Map <Integer, List<EstadiaDTO>> listaEstadias = estadiaDAO.obtenerListaEstadias(idTipoHabitacion, fechaDesde, fechaHasta);
        return listaEstadias;
    }
}
