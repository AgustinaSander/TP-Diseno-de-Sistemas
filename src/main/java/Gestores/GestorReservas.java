
package Gestores;

import DAO.IReservaDAO;
import DAO.ReservaDAOImpl;
import Dominio.DTO.HabitacionDTO;
import Dominio.DTO.ReservaDTO;
import Dominio.FechaReserva;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GestorReservas {
    private static GestorReservas instanciaGReservas = null;
    private static IReservaDAO reservaDAO = null;
    
    private GestorReservas(){};
    
    public static GestorReservas getInstanceReservas(){
        if(instanciaGReservas == null)
            instanciaGReservas = new GestorReservas();
   
        return instanciaGReservas;
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
