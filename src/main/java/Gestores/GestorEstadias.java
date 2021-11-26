
package Gestores;

import DAO.EstadiaDAOImpl;
import DAO.IEstadiaDAO;
import DAO.IServicioDAO;
import DAO.ServicioDAOImpl;
import Dominio.DTO.EstadiaDTO;
import Dominio.DTO.GestionarPasajeroDTO;
import Dominio.DTO.PasajeroDTO;
import Dominio.DTO.ServicioDTO;

import Dominio.Estadia;
import Dominio.Habitacion;
import Dominio.OcupadaPor;
import Dominio.Pasajero;
import Dominio.Servicio;
import static Gestores.GestorHabitaciones.getInstanceHabitaciones;
import static Gestores.GestorPasajero.getInstancePasajero;
import Validaciones.BusquedaFacturacion;
import static Validaciones.Validaciones.verificarHora;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class GestorEstadias {
    private static GestorEstadias instanciaGEstadias = null;
    private static IEstadiaDAO estadiaDAO = null;
    
    private GestorEstadias(){};
    
    public static GestorEstadias getInstanceEstadias(){
        if(instanciaGEstadias == null)
            instanciaGEstadias = new GestorEstadias();
   
        return instanciaGEstadias;
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
            OcupadaPor ocupadaPor = new OcupadaPor(p, pas.getResponsableHabitacion());
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

    public BusquedaFacturacion validarDatosFacturacion(String nroHabitacion, String hora) {
        Boolean horaValida = verificarHora(hora);
        Boolean nroValido = false;
        
        if(nroHabitacion != null){
           nroValido = getInstanceHabitaciones().validarNroHabitacion(nroHabitacion);
        }
        
        BusquedaFacturacion validacionDatos = new BusquedaFacturacion();
        validacionDatos.setHora(horaValida);
        validacionDatos.setNroHabitacion(nroValido);
        validacionDatos.setValidos(horaValida && nroValido);
        
        return validacionDatos;
    }

    public List<PasajeroDTO> obtenerPasajerosEstadia(int idEstadia) {
        estadiaDAO = new EstadiaDAOImpl();
        List <PasajeroDTO> ocupantes = new ArrayList<>();
        List <Pasajero> listaOcupantes = estadiaDAO.obtenerPasajerosEstadia(idEstadia);
        
        for(Pasajero p: listaOcupantes){
            ocupantes.add(new PasajeroDTO(p.getApellido(), p.getNombre(), p.getTipoDoc(), p.getNumDoc(), p.getFechaNac(), p.getEmail(), p.getCUIT(), p.getPosIva(), p.getTelefono(), p.getIdPersona(), p.getDireccion().getIdDireccion()));
        }
        
        return ocupantes;
    }

    public EstadiaDTO obtenerUltimaEstadia(String nroHabitacion) {
        //Obtener estadia
        estadiaDAO = new EstadiaDAOImpl();
        Estadia e = estadiaDAO.obtenerUltimaEstadia(nroHabitacion);
        
        //Creo la estadiaDTO
        EstadiaDTO estadia = new EstadiaDTO(e.getIdEstadia(), e.getHabitacion().getIdHabitacion(), e.getHabitacion().getTipo().getIdTipoHabitacion(), e.getHabitacion().getTipo().getNombre(), e.getFechaIngreso(), e.getFechaEgreso(), e.getHabitacion().getPrecio());
        
        List<PasajeroDTO> listaPasajeros = new ArrayList<>();
        for(OcupadaPor o : e.getListaOcupadaPor()){
            Pasajero p = o.getPasajero();
            PasajeroDTO pas = new PasajeroDTO(p.getApellido(),p.getNombre(),p.getTipoDoc(), p.getNumDoc(), p.getFechaNac(), p.getEmail(), p.getCUIT(), p.getPosIva(), p.getTelefono() ,p.getIdPersona(), 0);
            listaPasajeros.add(pas);
        }
        
        estadia.setListaPasajeros(listaPasajeros);
        return estadia;
    }

    public List<ServicioDTO> obtenerServicios(int idEstadia) {
        List<ServicioDTO> servicios = new ArrayList<>();
        
        IServicioDAO servicioDAO = new ServicioDAOImpl();
        List<Servicio> serviciosEstadia = servicioDAO.obtenerServicios(idEstadia);
        
        return servicios;
    }
}
