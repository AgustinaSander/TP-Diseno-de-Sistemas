
package Gestores;

import DAO.IReservaDAO;
import DAO.ReservaDAOImpl;
import Dominio.DTO.ReservaDTO;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class GestorReservas {
    private static GestorReservas instanciaGReservas = null;
    private static IReservaDAO reservaDAO = null;
    
    private GestorReservas(){};
    
    public static GestorReservas getInstanceReservas(){
        if(instanciaGReservas == null)
            instanciaGReservas = new GestorReservas();
   
        return instanciaGReservas;
    }
    
    public Map<Integer, List<ReservaDTO>> obtenerListaReservas(int idTipoHabitacion, Date fechaDesde, Date fechaHasta){
        reservaDAO = new ReservaDAOImpl();
        Map <Integer, List<ReservaDTO>> listaReservas = reservaDAO.obtenerListaReservas(idTipoHabitacion, fechaDesde, fechaHasta);
        return listaReservas;
    }
}
