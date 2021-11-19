
package Gestores;

import DAO.EstadiaDAOImpl;
import DAO.IEstadiaDAO;
import Dominio.DTO.EstadiaDTO;
import Dominio.DTO.GestionarPasajeroDTO;

import Dominio.Estadia;
import Dominio.Habitacion;
import Dominio.OcupadaPor;
import Dominio.Pasajero;
import static Gestores.GestorHabitaciones.getInstanceHabitaciones;
import static Gestores.GestorPasajero.getInstancePasajero;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class GestorEstadias {
    private static GestorEstadias instanciaGEstadias = null;
    private static IEstadiaDAO estadiaDAO = null;
    
    private GestorEstadias(){};
    
    public static GestorEstadias getInstanceEstadias(){
        if(instanciaGEstadias == null)
            instanciaGEstadias = new GestorEstadias();
   
        return instanciaGEstadias;
    }
    
    public List<Estadia> obtenerListaEstadias(int idTipoHabitacion, Date fechaDesde, Date fechaHasta){
        estadiaDAO = new EstadiaDAOImpl();
        List<Estadia> listaEstadias = new ArrayList<>();
        try {
            listaEstadias = estadiaDAO.obtenerListaEstadias(idTipoHabitacion, fechaDesde, fechaHasta);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }

        return listaEstadias;
    }

    public int crearEstadia(List<GestionarPasajeroDTO> pasajerosSeleccionados, EstadiaDTO estadiaDTO) {
        //Obtengo la habitacion
        Habitacion habitacion = getInstanceHabitaciones().obtenerHabitacion(estadiaDTO.getIdHabitacion());
        //Creo el objeto Estadia
        Estadia estadia = new Estadia(0,estadiaDTO.getFechaDesde(),LocalTime.parse("12:00:00"),estadiaDTO.getFechaHasta(), LocalTime.parse("10:00:00"), habitacion);
        
        //Creo la lista de objetos OcupadaPor
        List<OcupadaPor> listaOcupadaPor = new ArrayList<>();
        for(GestionarPasajeroDTO pas : pasajerosSeleccionados){
            Pasajero p = getInstancePasajero().obtenerPasajero(pas.getId());
            OcupadaPor ocupadaPor = new OcupadaPor(p, estadia, pas.getResponsableHabitacion());
            listaOcupadaPor.add(ocupadaPor);
        }
        //Se la agrego al objeto estadia
        estadia.setListaOcupadaPor(listaOcupadaPor);
        
        //Se crea la estadia en la base de datos
        estadiaDAO = new EstadiaDAOImpl();
        int idEstadia = 0;
        try {
            idEstadia = estadiaDAO.crearEstadia(estadia);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        
        return idEstadia;
    }
}
