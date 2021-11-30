
package Gestores;

import DAO.IReservaDAO;
import DAO.ReservaDAOImpl;
import Dominio.DTO.HabitacionDTO;
import Dominio.DTO.ReservaDTO;
import Dominio.FechaReserva;
import Dominio.Reserva;
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
        List<Reserva> reservas = reservaDAO.buscarReservas(hab.getId(), fechasReservadas);
        List<ReservaDTO> listaReservas = new ArrayList<>();
        List<FechaReserva> listaFechasReservas = new ArrayList<>();
        
        for(Reserva res : reservas){
            for(FechaReserva fecha : res.getListaFechaReserva()){
                if(fecha.getHabitacion().getIdHabitacion() == hab.getId()){
                    listaFechasReservas.add(fecha);
                    listaReservas.add(new ReservaDTO(res.getIdReserva(), fecha.getFechaIngreso(), fecha.getFechaEgreso(), res.getNombre(),res.getApellido(), res.getTelefono()));
                }
            }
        }
        
        return listaReservas;
    }
}
