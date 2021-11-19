package Gestores;

import DAO.EstadiaDAOImpl;
import DAO.HabitacionDAOImpl;
import DAO.IEstadiaDAO;
import DAO.IHabitacionDAO;
import DAO.IReservaDAO;
import DAO.ReservaDAOImpl;

import Dominio.DTO.EstadoHabitacionDTO;
import Dominio.DTO.HabitacionDTO;
import Dominio.DTO.ReservaDTO;

import Dominio.Estadia;
import Dominio.Habitacion;
import Dominio.Inhabilitado;
import Dominio.Reserva;
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
        
        //Se guardan todas las habitaciones de ese tipo con el formato <idHab, nombreHab>
        Map <Integer,String> habitaciones = new HashMap<>();
        //Se guarda la lista de estadias de ese tipo de habitacion dentro del rango de fechas
        List<Estadia> listaEstadias = new ArrayList<>();
        //Se guarda la lista de reservas de ese tipo de habitacion dentro del rango de fechas
        Map <Integer, List<ReservaDTO>> listaReservas = new HashMap<>();
        //Se guarda la lista de inhabilitadas de ese tipo de habitacion dentro del rango de fechas
        List<Inhabilitado> listaInhabilitados = new ArrayList<>();

        try {
            //Obtengo el id del tipoHabitacion seleccionado
            int idTipoHabitacion = habitacionDAO.obtenerIdTipoHabitacion(tipoHabitacion);
            
            //Obtengo las habitaciones de ese tipo <idHabitacion, nombre>
            habitaciones = habitacionDAO.obtenerHabitaciones(idTipoHabitacion);
            
            //Llamo a los respectivos gestores para obtener las listas
            //Obtengo la lista de estadias de todas las habitaciones de ese tipo
            listaEstadias = getInstanceEstadias().obtenerListaEstadias(idTipoHabitacion, fechaDesde, fechaHasta);
            
            //Obtengo la lista de reservas de todas las habitaciones de ese tipo
            listaReservas = getInstanceReservas().obtenerListaReservas(idTipoHabitacion, fechaDesde, fechaHasta);
            
            //Obtengo la lista de inhabilitados de todas las habitaciones de ese tipo
            listaInhabilitados = getInstanceInhabilitado().obtenerListaInhabilitados(idTipoHabitacion, fechaDesde, fechaHasta);
            
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        
        //Los estados de las habitaciones se guardan en un map <idHabitacion, List <id(Reserva,inhabilitado,estadia), fecha, estado>>
        Map <Integer, List<EstadoHabitacionDTO>> listaEstados = new HashMap<>();
        
        //Obtengo la lista de fechas entre FechaDesde y FechaHasta
        List<String> listaFechas = obtenerFechasIntermedias(fechaDesde, fechaHasta);
        
        //Agrego todas las claves que van a ser los idHabitaciones
        for(Map.Entry<Integer, String> hab : habitaciones.entrySet()){
            listaEstados.put(hab.getKey(), new ArrayList<>());
            
            //Creo una lista de fechas auxiliar para ir borrando las que ya fueron guardadas
            List<String> auxFechas = new ArrayList<>(listaFechas);
            
            //Filtro las estadias de esa habitacion
            List<Estadia> estadiasDeHab = recuperarEstadiasDeUnaHabitacion(listaEstadias, hab.getKey());
            
            //Si hay estadias que sean de esa habitacion
            if(!estadiasDeHab.isEmpty()){
                //Recorro la lista de estadias y voy agregando las mismas a la lista de estados
                //Para cada objeto Estadia de la habitacion
                for(Estadia e : estadiasDeHab){  
                    
                    //Obtengo la lista de estados de esa habitacion. Aca voy a ir agregando las estadias
                    List<EstadoHabitacionDTO> estados = listaEstados.get(hab.getKey());

                    //Obtengo todas las fechas intermedias de la estadia
                    List<String> fechasEstadia = obtenerFechasIntermedias(e.getFechaIngreso(),e.getFechaEgreso()); 

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
                    List<String> fechasReserva = obtenerFechasIntermedias(e.getFechaDesde(),e.getFechaHasta());
                    
                    //Verifico que todas las fechas de reserva esten disponibles (por si en algun momento alguien ocupa una parte de las fechas de la reserva)
                    Boolean fechasExistentes = true;
                    for(String fecha : fechasReserva){
                        if(!auxFechas.contains(fecha)){
                            fechasExistentes = false;
                        }
                    }
                    
                    //Si estan todas las fechas de la reserva, se muestran las fechas como reservadas
                    if(fechasExistentes){
                        for(String fecha : fechasReserva){
                            if(auxFechas.indexOf(fecha) != -1){
                                estados.add(new EstadoHabitacionDTO(e.getIdReserva(), fecha, "Reservada"));
                                auxFechas.remove(auxFechas.indexOf(fecha));
                            }  
                        }
                    }
                    else{
                        //Si no estan todas las fechas disponibles, se colocan las demas como "Desocupada" para descartar la reserva
                        for(String fecha : fechasReserva){
                            if(auxFechas.indexOf(fecha) != -1){
                                estados.add(new EstadoHabitacionDTO(0, fecha, "Desocupada"));
                                auxFechas.remove(auxFechas.indexOf(fecha));
                            }  
                        }
                    }
                    
                    
                    //Agrego los estados a la lista final de estados
                    listaEstados.put(hab.getKey(),estados);
                }
            }
            
            //Filtro las inhabilitaciones de esa habitacion
            List<Inhabilitado> inhabilitacionesDeHab = recuperarInhabilitadosDeUnaHabitacion(listaInhabilitados, hab.getKey());
            
            //Si hay inhabilitaciones que sean de esa habitacion
            if(!inhabilitacionesDeHab.isEmpty()){
                //Para cada objeto Inhabilitado de la habitacion
                for(Inhabilitado e : inhabilitacionesDeHab){  
                    
                    //Obtengo la lista de estados de esa habitacion. Aca voy a ir agregando las inhabilitaciones
                    List<EstadoHabitacionDTO> estados = listaEstados.get(hab.getKey());

                    //Obtengo todas las fechas intermedias de la inhabilitacion
                    List<String> fechasInhabilitado = obtenerFechasIntermedias(e.getFechaInicio(),e.getFechaFin());

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
      
        return listaEstados;
    }
    
    public List<Estadia> recuperarEstadiasDeUnaHabitacion(List<Estadia> lista, int idHabitacion) {
        List<Estadia> estadias = new ArrayList<>();
        for(Estadia e: lista){
            if(e.getHabitacion().getIdHabitacion() == idHabitacion){
                estadias.add(e);
            }
        }
        return estadias;
    }
    
    public List<Inhabilitado> recuperarInhabilitadosDeUnaHabitacion(List<Inhabilitado> lista, int idHabitacion) {
        List<Inhabilitado> inhabilitados = new ArrayList<>();
        for(Inhabilitado i : lista){
            if(i.getHabitacion().getIdHabitacion() == idHabitacion){
                inhabilitados.add(i);
            }
        }
        return inhabilitados;
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

    Habitacion obtenerHabitacion(int idHabitacion) {
        habitacionDAO = new HabitacionDAOImpl();
        return habitacionDAO.obtenerHabitacion(idHabitacion);
    }

    
}
