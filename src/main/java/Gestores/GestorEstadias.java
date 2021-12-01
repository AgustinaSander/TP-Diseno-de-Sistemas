
package Gestores;

import DAO.EstadiaDAOImpl;
import DAO.FacturaDAOImpl;
import DAO.IEstadiaDAO;
import DAO.IFacturaDAO;
import DAO.IItemFacturaDAO;
import DAO.IServicioDAO;
import DAO.ItemFacturaDAOImpl;
import DAO.ServicioDAOImpl;
import Dominio.DTO.EstadiaDTO;
import Dominio.DTO.GestionarPasajeroDTO;
import Dominio.DTO.ItemDTO;
import Dominio.DTO.PasajeroDTO;
import Dominio.Estadia;
import Dominio.Factura;
import Dominio.Habitacion;
import Dominio.ItemEstadia;
import Dominio.ItemFactura;
import Dominio.ItemServicio;
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
        EstadiaDTO estadia = new EstadiaDTO(e.getIdEstadia(), e.getHabitacion().getIdHabitacion(),e.getFechaIngreso(), e.getFechaEgreso() ,e.getHabitacion().getPrecio());
        
        List<PasajeroDTO> listaPasajeros = new ArrayList<>();
        for(OcupadaPor o : e.getListaOcupadaPor()){
            Pasajero p = o.getPasajero();
            PasajeroDTO pas = new PasajeroDTO(p.getApellido(),p.getNombre(),p.getTipoDoc(), p.getNumDoc(), p.getFechaNac(), p.getEmail(), p.getCUIT(), p.getPosIva(), p.getTelefono() ,p.getIdPersona(), 0);
            listaPasajeros.add(pas);
        }
        
        estadia.setListaPasajeros(listaPasajeros);
        return estadia;
    }

    public List<ItemDTO> obtenerItemsAFacturar(int idEstadia, String hora) {
        List<ItemDTO> itemsDTO = new ArrayList<>();
 
        //Recupero los servicios de esa estadia
        List<Servicio> serviciosEstadia = obtenerServicios(idEstadia);
        
        //Recupero las facturas de esa estadia
        List<Factura> facturasEstadia = obtenerFacturas(idEstadia);
        
        //Se realiza una "Resta" para obtener los items que faltan facturar
        //Primero genero los items de estadia que falta facturar
        //Obtengo los itemsEstadia e itemsServicio de la lista de itemsFacturados
        List<ItemEstadia> itemsEstadiaFacturados = new ArrayList<>();
        List<ItemServicio> itemsServicioFacturados = new ArrayList<>();
        
        for(Factura f : facturasEstadia){
            List<ItemFactura> items = f.getListaItemsFactura();
            for(ItemFactura i : items){
                //Si es item estadia lo guardo en lista itemsEstadiaFacturados
                if(i instanceof ItemEstadia){
                    itemsEstadiaFacturados.add((ItemEstadia) i);
                }
                else{
                //Sino en lista itemsServicioEstadias
                    itemsServicioFacturados.add((ItemServicio) i);
                }
            }
        } 
        
        //Tengo la lista de todos los servicios consumidos en la estadia, entonces por cada item servicio voy viendo el servicio que tiene dentro y lo saco de la lista de servicios consumidos en la estadia => me van a quedar solo los que no fueron facturados
        List<Servicio> serviciosNoFacturados = new ArrayList<>();
        
        for(ItemServicio itemServicio : itemsServicioFacturados){
            //Obtengo el id del servicio del item y busco el mismo servicio en la lista de servicios
            for(Servicio s : serviciosEstadia){
                if(s.getIdServicio() == itemServicio.getServicio().getIdServicio()){
                    //Me fijo si se facturo toda la cantidad del servicio consumida
                    //Si no ocurrio lo agrego a la lista de serviciosNoFacturados
                    if(s.getCantidad() > itemServicio.getCantidad()){
                        
                        serviciosNoFacturados.add()
                    }
                }
            }
            
        }
        
        
        return itemsDTO;
    }
    
    public List<Servicio> obtenerServicios(int idEstadia){
        IServicioDAO servicioDAO = new ServicioDAOImpl();
        List<Servicio> servicios = new ArrayList<>();
        try {
            servicios = servicioDAO.obtenerServiciosEstadia(idEstadia);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return servicios;
    }
    
    public List<Factura> obtenerFacturas(int idEstadia){
        IFacturaDAO facturaDAO = new FacturaDAOImpl();
        List<Factura> facturas = new ArrayList<>();
        try {
            facturas = facturaDAO.obtenerFacturasEstadia(idEstadia);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        
        return facturas;
    }
    
}
