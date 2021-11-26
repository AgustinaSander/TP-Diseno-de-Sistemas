package Gestores;

import DAO.EstadiaDAOImpl;
import DAO.HabitacionDAOImpl;
import DAO.IEstadiaDAO;
import DAO.IHabitacionDAO;
import DAO.IInhabilitadoDAO;
import DAO.IReservaDAO;
import DAO.ITipoHabitacionDAO;
import DAO.InhabilitadoDAOImpl;
import DAO.ReservaDAOImpl;
import DAO.TipoHabitacionDAOImpl;

import Dominio.DTO.EstadoHabitacionDTO;
import Dominio.DTO.HabitacionDTO;

import Dominio.Estadia;
import Dominio.FechaReserva;
import Dominio.Habitacion;
import Dominio.Inhabilitado;
import Dominio.Reserva;
import Dominio.TipoDeHabitacion;
import Validaciones.FechasEstadoHabitaciones;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import static java.util.Objects.isNull;
import java.util.logging.Level;
import java.util.logging.Logger;



public class GestorHabitaciones {
    private static GestorHabitaciones instanciaGHabitaciones = null;
    private static IHabitacionDAO habitacionDAO = null;
    private static ITipoHabitacionDAO tipoHabitacionDAO = null;

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
        
        tipoHabitacionDAO = new TipoHabitacionDAOImpl();
        
        List<String> tiposHabitaciones = null;
        try {
            tiposHabitaciones = tipoHabitacionDAO.obtenerTiposDeHabitaciones();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
 
        return tiposHabitaciones;
    }
    
    
    public Map <Integer, List<EstadoHabitacionDTO>> mostrarEstadoHabitaciones(String tipoHabitacion, Date fechaDesde, Date fechaHasta){
        
        tipoHabitacionDAO = new TipoHabitacionDAOImpl();
        habitacionDAO = new HabitacionDAOImpl();
        
        //Se guardan todas las habitaciones de ese tipo
        List<Habitacion> habitaciones = new ArrayList<>();
        //Se guarda la lista de estadias de ese tipo de habitacion dentro del rango de fechas
        List<Estadia> listaEstadias = new ArrayList<>();
        //Se guarda la lista de reservas de ese tipo de habitacion dentro del rango de fechas
        List<Reserva> listaReservas = new ArrayList<>();
        //Se guarda la lista de inhabilitadas de ese tipo de habitacion dentro del rango de fechas
        List<Inhabilitado> listaInhabilitados = new ArrayList<>();

        try {
            //Obtengo las habitaciones de ese tipo 
            habitaciones = tipoHabitacionDAO.obtenerHabitaciones(tipoHabitacion);
            
            //Obtengo la lista de estadias de todas las habitaciones de ese tipo
            IEstadiaDAO estadiaDAO = new EstadiaDAOImpl();
            listaEstadias = estadiaDAO.obtenerListaEstadias(habitaciones, fechaDesde, fechaHasta);
            
            //Obtengo la lista de reservas de todas las habitaciones de ese tipo
            IReservaDAO reservaDAO = new ReservaDAOImpl();
            listaReservas = reservaDAO.obtenerListaReservas(habitaciones, fechaDesde, fechaHasta);
            
            //Obtengo la lista de inhabilitados de todas las habitaciones de ese tipo
            IInhabilitadoDAO inhabilitadoDAO = new InhabilitadoDAOImpl();
            listaInhabilitados = inhabilitadoDAO.obtenerListaInhabilitados(habitaciones, fechaDesde, fechaHasta);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        
        //Los estados de las habitaciones se guardan en un map <idHabitacion, List <id(Reserva,inhabilitado,estadia), fecha, estado>>
        Map <Integer, List<EstadoHabitacionDTO>> listaEstados = new HashMap<>();
        
        //Obtengo la lista de fechas entre FechaDesde y FechaHasta
        List<String> listaFechas = obtenerFechasIntermedias(fechaDesde, fechaHasta);
        
        //Agrego todas las claves que van a ser los idHabitaciones
        for(Habitacion hab : habitaciones){
            listaEstados.put(hab.getIdHabitacion(), new ArrayList<>());
            
            //Creo una lista de fechas auxiliar para ir borrando las que ya fueron guardadas
            List<String> auxFechas = new ArrayList<>(listaFechas);
            
            //Filtro las estadias de esa habitacion
            List<Estadia> estadiasDeHab = recuperarEstadiasDeUnaHabitacion(listaEstadias, hab.getIdHabitacion());
            
            //Si hay estadias que sean de esa habitacion
            if(!estadiasDeHab.isEmpty()){
                //Recorro la lista de estadias y voy agregando las mismas a la lista de estados
                //Para cada objeto Estadia de la habitacion
                for(Estadia e : estadiasDeHab){  
                    
                    //Obtengo la lista de estados de esa habitacion. Aca voy a ir agregando las estadias
                    List<EstadoHabitacionDTO> estados = listaEstados.get(hab.getIdHabitacion());

                    //Obtengo todas las fechas intermedias de la estadia
                    List<String> fechasEstadia = obtenerFechasIntermedias(e.getFechaIngreso(),e.getFechaEgreso()); 

                    for(String fecha : fechasEstadia){
                        if(auxFechas.indexOf(fecha) != -1){
                            estados.add(new EstadoHabitacionDTO(e.getIdEstadia(), fecha, "Ocupada"));
                            auxFechas.remove(auxFechas.indexOf(fecha));
                        }  
                    }

                    //Agrego los estados a la lista final de estados
                    listaEstados.put(hab.getIdHabitacion(),estados);
                }
            }

            //Si hay reservas que sean de esa habitacion
            for(Reserva reserva : listaReservas){
                
                List<FechaReserva> listaFechaReserva = reserva.getListaFechaReserva();
                for(FechaReserva fr : listaFechaReserva){
                    
                    if(fr.getHabitacion().getIdHabitacion() == hab.getIdHabitacion()){
                        //Hay reserva para la habitacion
                        //Obtengo la lista de estados de esa habitacion. Aca voy a ir agregando las reservas
                        List<EstadoHabitacionDTO> estados = listaEstados.get(hab.getIdHabitacion());

                        //Obtengo todas las fechas intermedias de la reserva
                        List<String> fechasReserva = obtenerFechasIntermedias(fr.getFechaIngreso(),fr.getFechaEgreso()); 

                        for(String fecha : fechasReserva){
                            if(auxFechas.indexOf(fecha) != -1){
                                estados.add(new EstadoHabitacionDTO(reserva.getIdReserva(), fecha, "Reservada"));
                                auxFechas.remove(auxFechas.indexOf(fecha));
                            }  
                        }
                        
                    }
                }
            }

            
            //Filtro las inhabilitaciones de esa habitacion
            List<Inhabilitado> inhabilitacionesDeHab = recuperarInhabilitadosDeUnaHabitacion(listaInhabilitados, hab.getIdHabitacion());
            
            //Si hay inhabilitaciones que sean de esa habitacion
            if(!inhabilitacionesDeHab.isEmpty()){
                //Para cada objeto Inhabilitado de la habitacion
                for(Inhabilitado e : inhabilitacionesDeHab){  
                    
                    //Obtengo la lista de estados de esa habitacion. Aca voy a ir agregando las inhabilitaciones
                    List<EstadoHabitacionDTO> estados = listaEstados.get(hab.getIdHabitacion());

                    //Obtengo todas las fechas intermedias de la inhabilitacion
                    List<String> fechasInhabilitado = obtenerFechasIntermedias(e.getFechaInicio(),e.getFechaFin());

                    for(String fecha : fechasInhabilitado){
                        if(auxFechas.indexOf(fecha) != -1){
                            estados.add(new EstadoHabitacionDTO(e.getIdInhabilitado(), fecha, "Inhabilitada"));
                            auxFechas.remove(auxFechas.indexOf(fecha));
                        }  
                    }

                    //Agrego los estados a la lista final de estados
                    listaEstados.put(hab.getIdHabitacion(),estados);
                }
            }
            
            //Si hay fechas que no tienen estadia, reserva o inhabilitacion se ponen como desocupadas
            
            while(!auxFechas.isEmpty()){
                List<EstadoHabitacionDTO> estados = listaEstados.get(hab.getIdHabitacion());
                estados.add(new EstadoHabitacionDTO(0,auxFechas.get(0),"Desocupada"));
                listaEstados.put(hab.getIdHabitacion(), estados);
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
        Habitacion habitacion = null;
        
        try {
            habitacion = habitacionDAO.obtenerHabitacion(idHabitacion);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        
        return habitacion;
    }

    Boolean validarNroHabitacion(String nroHabitacion) {
        //Me fijo si existe una habitacion con ese nro
        habitacionDAO = new HabitacionDAOImpl();
        return habitacionDAO.buscarNroHabitacion(nroHabitacion);
    }

    public List<HabitacionDTO> obtenerHabitaciones(String tipoHab) {
        List<HabitacionDTO> habitaciones = new ArrayList<>();
        try {
            List<Habitacion> hab = new TipoHabitacionDAOImpl().obtenerHabitaciones(tipoHab);
            for(Habitacion h : hab){
                habitaciones.add(new HabitacionDTO(h.getIdHabitacion(), h.getNumeroHabitacion()));
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return habitaciones;
    }

    
}
