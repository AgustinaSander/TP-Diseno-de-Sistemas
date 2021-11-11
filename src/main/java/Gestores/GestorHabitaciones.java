package Gestores;

import DAO.EstadiaDAOImpl;
import DAO.HabitacionDAOImpl;
import DAO.IEstadiaDAO;
import DAO.IHabitacionDAO;
import DAO.IReservaDAO;
import DAO.ReservaDAOImpl;
import Dominio.DTO.EstadiaDTO;
import Dominio.DTO.EstadoHabitacionDTO;
import Dominio.DTO.HabitacionDTO;
import Dominio.DTO.InhabilitadoDTO;
import Dominio.DTO.ReservaDTO;
import static Gestores.GestorEstadias.getInstanceEstadias;
import static Gestores.GestorInhabilitado.getInstanceInhabilitado;
import static Gestores.GestorReservas.getInstanceReservas;
import Validaciones.FechasEstadoHabitaciones;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import static java.util.Objects.isNull;


public class GestorHabitaciones {
    private static GestorHabitaciones instanciaGHabitaciones = null;
    private static IHabitacionDAO habitacionDAO = null;
    private static IEstadiaDAO estadiaDAO = null;

    
    private GestorHabitaciones(){};
    
    public static GestorHabitaciones getInstanceHabitaciones(){
        if(instanciaGHabitaciones == null)
            instanciaGHabitaciones = new GestorHabitaciones();
   
        return instanciaGHabitaciones;
    }
    
    public FechasEstadoHabitaciones validarFechas(Date fechaDesde, Date fechaHasta){
       FechasEstadoHabitaciones fechasValidas = new FechasEstadoHabitaciones();
       
        if(isNull(fechaDesde)){
            fechasValidas.setFechaDesdeValido(false);
            fechasValidas.setValidos(false);
        }
        if(isNull(fechaHasta)){
            fechasValidas.setFechaHastaValido(false);
            fechasValidas.setValidos(false);
        }
        if(fechasValidas.getValidos()){
            if(fechaDesde.after(fechaHasta)){
                fechasValidas.setDesdeMenorAHasta(false);
                fechasValidas.setValidos(false);            
            }
            if(fechaHasta.after(new java.util.Date())){
                fechasValidas.setHastaMenorADateToday(false);
                fechasValidas.setValidos(false);
            }
        }
        
        return fechasValidas;
    }
    
    public List <String> obtenerTiposDeHabitaciones(){
        
        habitacionDAO = new HabitacionDAOImpl();
        
        List<String> tiposHabitaciones = null;
        try {
            tiposHabitaciones = habitacionDAO.obtenerTiposDeHabitaciones();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
 
        return tiposHabitaciones;
    }
    
    public List<HabitacionDTO> obtenerHabitaciones(String tipoHabitacion){
        
        habitacionDAO = new HabitacionDAOImpl();
        
        Map <Integer ,String> aux = new HashMap<>();
        try {
            int id = habitacionDAO.obtenerIdTipoHabitacion(tipoHabitacion);
            aux = habitacionDAO.obtenerHabitaciones(id);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        List <HabitacionDTO> habitaciones = new ArrayList<>();
        for(Map.Entry<Integer,String> entry : aux.entrySet()){
            habitaciones.add(new HabitacionDTO(entry.getKey(),entry.getValue()));
        }
        return habitaciones;
    }
    
    public void mostrarEstadoHabitaciones(String tipoHabitacion, Date fechaDesde, Date fechaHasta){
        
        habitacionDAO = new HabitacionDAOImpl();
        Map <Integer,String> habitaciones = new HashMap<>();
        Map <Integer, List<EstadiaDTO>> listaEstadias = new HashMap<>();
        Map <Integer, List<ReservaDTO>> listaReservas = new HashMap<>();
        Map <Integer, List<InhabilitadoDTO>> listaInhabilitados = new HashMap<>();

        try {
            //Obtengo el id del tipoHabitacion seleccionado
            int idTipoHabitacion = habitacionDAO.obtenerIdTipoHabitacion(tipoHabitacion);
            
            //Obtengo las habitaciones de ese tipo <idHabitacion, nombre>
            habitaciones = habitacionDAO.obtenerHabitaciones(idTipoHabitacion);
            
            //Llamo a los respectivos gestores para obtener las listas ???????? CAMBIAR DIAGRAMA DE SECUENCIA(AGREGAR)
            //Obtengo la lista de estadias de todas las habitaciones de ese tipo
            
            listaEstadias = getInstanceEstadias().obtenerListaEstadias(idTipoHabitacion, fechaDesde, fechaHasta);
            
            //Obtengo la lista de reservas de todas las habitaciones de ese tipo
            listaReservas = getInstanceReservas().obtenerListaReservas(idTipoHabitacion, fechaDesde, fechaHasta);
            
            //Obtengo la lista de inhabilitados de todas las habitaciones de ese tipo
            listaInhabilitados = getInstanceInhabilitado().obtenerListaInhabilitados(idTipoHabitacion, fechaDesde, fechaHasta);
            
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        
        //Uno las tres listas devolviendo un Map <idHabitacion, List <id(Reserva,inhabilitado,estadia), fecha, estado>>
        Map <Integer, List<EstadoHabitacionDTO>> listaEstados = new HashMap<>();
        
        //Obtengo la lista de fechas entre FechaDesde y FechaHasta
        List<String> listaFechas = obtenerFechasIntermedias(fechaDesde, fechaHasta);
        List<EstadoHabitacionDTO> aux = new ArrayList<>();
        for(String fecha : listaFechas){
            aux.add(new EstadoHabitacionDTO(fecha));
        }
        
        //Agrego todas las claves que van a ser los idHabitaciones con sus respectivas listas de objetos EstadoHabitacionDTO inicializadas unicamente con la fecha
        for(Map.Entry<Integer, String> entry : habitaciones.entrySet()){
            listaEstados.put(entry.getKey(), aux);
        }
        
        //Recorro la lista de estadias y voy agregando las mismas a la lista de estados
        for(Map.Entry<Integer, List<EstadiaDTO>> entry: listaEstadias.entrySet()){
            //Para cada objeto EstadiaDTO de la habitacion
            for(EstadiaDTO e : entry.getValue()){
                
            }
        }
        
        
        
    }
    
    public List<String> obtenerFechasIntermedias(Date fechaDesde, Date fechaHasta){       
        List<String> fechas = new ArrayList<>();
    
        Calendar comienzo = Calendar.getInstance();
        comienzo.setTime(fechaDesde);
        Calendar fin = Calendar.getInstance();
        fin.setTime(fechaHasta);
        while(comienzo.before(fin)){
            fechas.add(new SimpleDateFormat("dd-MM-yyyy").format(comienzo.getTime()));
            comienzo.add(Calendar.DAY_OF_YEAR,1);
        }
        
        return fechas;
    }
}
