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
    
    public Map <Integer, List<EstadoHabitacionDTO>> mostrarEstadoHabitaciones(String tipoHabitacion, Date fechaDesde, Date fechaHasta){
        
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
        
        //Agrego todas las claves que van a ser los idHabitaciones
        for(Map.Entry<Integer, String> hab : habitaciones.entrySet()){
            listaEstados.put(hab.getKey(), new ArrayList<>());
            List<String> auxFechas = new ArrayList<>(listaFechas);
            
            //Si hay estadias que sean de esa habitacion
            if(listaEstadias.get(hab.getKey()) != null){
                //Recorro la lista de estadias y voy agregando las mismas a la lista de estados
                List<EstadiaDTO> listaEstadiasHab = listaEstadias.get(hab.getKey());
                
                //Para cada objeto EstadiaDTO de la habitacion
                for(EstadiaDTO e : listaEstadiasHab){  
                    List<EstadoHabitacionDTO> estados = listaEstados.get(hab.getKey());

                    //Obtengo todas las fechas intermedias de la estadia
                    List<String> fechasEstadia = obtenerFechasIntermedias(e.getFechaDesde(),e.getFechaHasta()); //VER QUE PASA SI FECHADESDE = FECHAHASTA

                    for(String fecha : fechasEstadia){
                        if(auxFechas.indexOf(fecha) != -1){
                            estados.add(new EstadoHabitacionDTO(e.getIdEstadia(), fecha, "Ocupada"));
                            auxFechas.remove(auxFechas.indexOf(fecha));
                        }  
                    }

                    //Agrego los estados a la lista final de estados
                    listaEstados.put(hab.getKey(),estados);
                }
            }

            
            //Si hay reservas que sean de esa habitacion
            if(listaReservas.get(hab.getKey()) != null){
                //Recorro la lista de estadias y voy agregando las mismas a la lista de estados
                List<ReservaDTO> listaReservasHab = listaReservas.get(hab.getKey());
                //Para cada objeto ReservaDTO de la habitacion
                for(ReservaDTO e : listaReservasHab){  
                    List<EstadoHabitacionDTO> estados = listaEstados.get(hab.getKey());

                    //Obtengo todas las fechas intermedias de la estadia
                    List<String> fechasReserva = obtenerFechasIntermedias(e.getFechaDesde(),e.getFechaHasta()); //VER QUE PASA SI FECHADESDE = FECHAHASTA

                    for(String fecha : fechasReserva){
                        if(auxFechas.indexOf(fecha) != -1){
                            estados.add(new EstadoHabitacionDTO(e.getIdReserva(), fecha, "Reservada"));
                            auxFechas.remove(auxFechas.indexOf(fecha));
                        }  
                    }

                    //Agrego los estados a la lista final de estados
                    listaEstados.put(hab.getKey(),estados);
                }
            }
            
            //Si hay inhabilitaciones que sean de esa habitacion
            if(listaInhabilitados.get(hab.getKey()) != null){
                //Recorro la lista de inhabilitados y voy agregando las mismas a la lista de estados
                List<InhabilitadoDTO> listaInhabilitadosHab = listaInhabilitados.get(hab.getKey());
                //Para cada objeto InhabilitadoDTO de la habitacion
                for(InhabilitadoDTO e : listaInhabilitadosHab){  
                    List<EstadoHabitacionDTO> estados = listaEstados.get(hab.getKey());

                    //Obtengo todas las fechas intermedias de la inhabilitacion
                    List<String> fechasInhabilitado = obtenerFechasIntermedias(e.getFechaDesde(),e.getFechaHasta()); //VER QUE PASA SI FECHADESDE = FECHAHASTA

                    for(String fecha : fechasInhabilitado){
                        if(auxFechas.indexOf(fecha) != -1){
                            estados.add(new EstadoHabitacionDTO(e.getIdInhabilitado(), fecha, "Inhabilitada"));
                            auxFechas.remove(auxFechas.indexOf(fecha));
                        }  
                    }

                    //Agrego los estados a la lista final de estados
                    listaEstados.put(hab.getKey(),estados);
                }
            }
            
            //Si hay fechas que no tienen estadia, reserva o inhabilitacion se ponen como desocupadas
            
            while(!auxFechas.isEmpty()){
                List<EstadoHabitacionDTO> estados = listaEstados.get(hab.getKey());
                estados.add(new EstadoHabitacionDTO(0,auxFechas.get(0),"Desocupada"));
                listaEstados.put(hab.getKey(), estados);
                auxFechas.remove(0);
            }       
        }
        //System.out.println("Lista estados:" + listaEstados);
        return listaEstados;
    }
    
    public List<String> obtenerFechasIntermedias(Date fechaDesde, Date fechaHasta){
        List<String> fechas = new ArrayList<>();
    
        Calendar comienzo = Calendar.getInstance(); 
        comienzo.setTime(fechaDesde);
        comienzo.set(Calendar.HOUR_OF_DAY, 0);  
        comienzo.set(Calendar.MINUTE, 0);  
        comienzo.set(Calendar.SECOND, 0);  
        comienzo.set(Calendar.MILLISECOND, 0);
        Calendar fin = Calendar.getInstance();
        fin.setTime(fechaHasta);
        fin.set(Calendar.HOUR_OF_DAY, 0);  
        fin.set(Calendar.MINUTE, 0);  
        fin.set(Calendar.SECOND, 0);  
        fin.set(Calendar.MILLISECOND, 0);
        while(comienzo.before(fin)){
            fechas.add(new SimpleDateFormat("dd-MM-yyyy").format(comienzo.getTime()));
            comienzo.add(Calendar.DAY_OF_YEAR,1);
        }
        fechas.add(new SimpleDateFormat("dd-MM-yyyy").format(fin.getTime()));

        return fechas;
    }
}
