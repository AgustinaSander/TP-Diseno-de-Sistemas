
package Gestores;

import DAO.IReservaDAO;
import DAO.ReservaDAOImpl;
import Dominio.DTO.HabitacionDTO;
import Dominio.DTO.ReservaDTO;
import Dominio.FechaReserva;
import Dominio.Reserva;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
        Map <Integer, List<FechaReserva>> listaFechasReservas = reservaDAO.obtenerListaReservas(idTipoHabitacion, fechaDesde, fechaHasta);
        Map <Integer, List<ReservaDTO>> listaReservas = new HashMap<>();
        
        for(Map.Entry<Integer, List<FechaReserva>> hab : listaFechasReservas.entrySet()){
            
            List <ReservaDTO> res = new ArrayList<>();
            
            for(int i=0; i<hab.getValue().size(); i++){
                FechaReserva fr = hab.getValue().get(i);
                ReservaDTO r = new ReservaDTO(fr.getReserva().getIdReserva(), fr.getFechaIngreso(), fr.getFechaEgreso(), fr.getReserva().getNombre(), fr.getReserva().getApellido(), fr.getReserva().getTelefono());
                res.add(r);
            }
            
            listaReservas.put(hab.getKey(), res);
        }
        
        return listaReservas;
    }

    public List<ReservaDTO> buscarReservas(HabitacionDTO hab, List<Date> fechasReservadas) {
        reservaDAO = new ReservaDAOImpl();
        List<FechaReserva> reservas = reservaDAO.buscarReservas(hab.getId(), fechasReservadas);
        List<ReservaDTO> listaReservas = new ArrayList<>();
        
        //Convierto a reservaDTO y retorno la lista a la interfaz
        
        for (FechaReserva fr: reservas){
            listaReservas.add(new ReservaDTO(fr.getReserva().getIdReserva(), fr.getFechaIngreso(), fr.getFechaEgreso(), fr.getReserva().getNombre(), fr.getReserva().getApellido(), fr.getReserva().getTelefono()));
        }
        return listaReservas;
    }
}
