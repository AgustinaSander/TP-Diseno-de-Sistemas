
package Gestores;

import DAO.EstadiaDAOImpl;
import DAO.FacturaDAOImpl;
import DAO.IEstadiaDAO;
import DAO.IFacturaDAO;
import DAO.IItemFacturaDAO;
import DAO.IServicioDAO;
import DAO.ItemFacturaDAOImpl;
import DAO.ServicioDAOImpl;
import DAO.TipoHabitacionDAOImpl;
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
import Dominio.TipoDeHabitacion;
import static Gestores.GestorHabitaciones.getInstanceHabitaciones;
import static Gestores.GestorPasajero.getInstancePasajero;
import Validaciones.BusquedaFacturacion;
import static Validaciones.Validaciones.obtenerFechasIntermedias;
import static Validaciones.Validaciones.verificarHora;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
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
        
        //Obtengo el tipo de habitacion de la estadia
        TipoDeHabitacion tipoDeHabitacion = null;
        tipoDeHabitacion = new TipoHabitacionDAOImpl().obtenerTipoDeHabitacion(e.getHabitacion().getIdHabitacion());
        
        //Creo la estadiaDTO
        EstadiaDTO estadia = new EstadiaDTO(e.getIdEstadia(), e.getHabitacion().getIdHabitacion(), e.getFechaIngreso(), e.getFechaEgreso(), tipoDeHabitacion.getPrecio());
        
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
 
        //Obtengo la estadia
        Estadia e = null;
        try {
            e = new EstadiaDAOImpl().obtenerEstadia(idEstadia);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        
        //Obtengo el tipo de habitacion de la estadia
        TipoDeHabitacion tipoDeHabitacion = null;
        tipoDeHabitacion = new TipoHabitacionDAOImpl().obtenerTipoDeHabitacion(e.getHabitacion().getIdHabitacion());
        
        //Recupero los servicios de esa estadia
        List<Servicio> serviciosEstadia = e.getListaServicios();
        
        //Recupero las facturas de esa estadia
        List<Factura> facturasEstadia = obtenerFacturas(idEstadia);
        
        //Se realiza una "Resta" para obtener los items que faltan facturar
        
        //Obtengo los itemsEstadia e itemsServicio de la lista de itemsFacturados
        List<ItemEstadia> itemsEstadiaFacturados = new ArrayList<>();
        List<ItemServicio> itemsServicioFacturados = new ArrayList<>();
        
        //Por cada factura de la estadia me fijo cuales son los items facturados y los agrego a la correspondiente lista de ya facturados
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
        
        for(ItemServicio itemServicio : itemsServicioFacturados){
            //Obtengo el id del servicio del item y busco el mismo servicio en la lista de servicios
            for(Servicio s : serviciosEstadia){
                if(s.getIdServicio() == itemServicio.getServicio().getIdServicio()){
                    //Me fijo si se facturo toda la cantidad del servicio consumida
                    if(s.getCantidad() > itemServicio.getCantidad()){
                        //Si no ocurrio actualizo la lista de servicios
                        s.setCantidad(s.getCantidad() - itemServicio.getCantidad());
                        s.setPrecioTotal(s.getPrecioTotal() - itemServicio.getCantidad()*itemServicio.getPrecioUnitario());
                    }
                    else{
                        //Si ya se facturo toda la cantidad lo borro de la lista
                        serviciosEstadia.remove(s);
                    }
                    break;
                }
            }
        }
        
        //Creo los items estadia que no fueron facturados todavia
        List<String> fechasItemEstadia = obtenerFechasIntermedias(e.getFechaIngreso(), e.getFechaEgreso());
        fechasItemEstadia.remove(fechasItemEstadia.size()-1);
        int cantidadNochesEstadia = fechasItemEstadia.size();
        
        Boolean extraFacturado = false;
        for(ItemEstadia ie : itemsEstadiaFacturados){  
            if(ie.getExtra()){
                extraFacturado = true;
            }
            else{
                cantidadNochesEstadia -= ie.getCantidad();
            }     
        } 
        
        for(int i=0; i<cantidadNochesEstadia; i++){
            itemsDTO.add(new ItemDTO(0, false, true, false, null, "Estadia Habitacion " + tipoDeHabitacion.getNombre() , 1, tipoDeHabitacion.getPrecio())); 
        }
        
        if(!extraFacturado){
            if(LocalTime.parse(hora).isAfter(LocalTime.parse("11:00")) && LocalTime.parse(hora).isBefore(LocalTime.parse("18:01")) ){   
                itemsDTO.add(new ItemDTO(0, false, true, true, null, "Extra Estadia medio dia", 1, tipoDeHabitacion.getPrecio()/2));
            }
            else if(LocalTime.parse(hora).isAfter(LocalTime.parse("18:00"))){
                itemsDTO.add(new ItemDTO(0,false, true, true, null, "Extra Estadia dia entero", 1, tipoDeHabitacion.getPrecio()));
            }
        }
        
        for(Servicio s : serviciosEstadia){
            itemsDTO.add(new ItemDTO(s.getIdServicio(), true, false, false, s.getFecha(), s.getDescripcion(), s.getCantidad(), s.getPrecioTotal()));
        }
        
        return itemsDTO;
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
