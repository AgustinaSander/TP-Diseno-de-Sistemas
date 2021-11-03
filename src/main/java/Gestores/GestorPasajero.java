
package Gestores;

import DAO.*;
import Dominio.*;
import static Gestores.GestorGeografico.*;
import static Validaciones.Validaciones.esNumero;
import static Validaciones.Validaciones.verificarCUIT;
import static Validaciones.Validaciones.verificarEmail;
import static Validaciones.Validaciones.verificarTelefono;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GestorPasajero {
    private static GestorPasajero instanciaGPasajero = null;
    private static IPasajeroDAO pasajeroDAO = null;
    
    private GestorPasajero(){};
    
    public static GestorPasajero getInstancePasajero(){
        if(instanciaGPasajero == null)
            instanciaGPasajero = new GestorPasajero();
   
        return instanciaGPasajero;
    }
    
    //Validaciones de interfaz Alta de Pasajero
    public boolean validarDatosPasajero(PasajeroDTO datosPasajero){
        //Se validan todos los campos con expresiones regulares
        Boolean bandGestor = true;

        if(datosPasajero.getNombre().length() > 50 || datosPasajero.getNombre().length() == 0){
            bandGestor = false;
        }

        if(datosPasajero.getApellido().length() > 50 || datosPasajero.getApellido().length() == 0){
            bandGestor = false;
        }

        if(datosPasajero.getNumDoc().length() > 10 || datosPasajero.getNumDoc().length() == 0){
            bandGestor = false;
        }

        if(datosPasajero.getCUIT().length() > 15 || !verificarCUIT(datosPasajero.getCUIT())){
            bandGestor = false;
        }

        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date fechaMin = null;
        try {
            fechaMin = sdFormat.parse("1870-01-01");
        } catch (ParseException ex) {
            ex.printStackTrace(System.out);
        }

        if(datosPasajero.getFechaNac()== null ||datosPasajero.getFechaNac().after(new java.util.Date()) || datosPasajero.getFechaNac().before(fechaMin)){
            bandGestor = false;
        }

        if(datosPasajero.getCalle().length() > 50 || datosPasajero.getCalle().length() == 0){
            bandGestor = false;
        }

        if(datosPasajero.getNumero().length() > 10 || datosPasajero.getNumero().length() == 0 || !esNumero(datosPasajero.getNumero())){
            bandGestor = false;
        }

        if(datosPasajero.getDepartamento().length() > 15){
            bandGestor = false;
        }

        if(datosPasajero.getCodigoPostal().length() > 5 || datosPasajero.getCodigoPostal().length() == 0 || !esNumero(datosPasajero.getCodigoPostal())){
            bandGestor = false;
        }

        if(datosPasajero.getLocalidad().equals("No disponible") || datosPasajero.getLocalidad().equals("Seleccionar")){
            bandGestor = false;
        }

        if(datosPasajero.getProvincia().equals("Seleccionar")){
            bandGestor = false;
        }

        if(datosPasajero.getTelefono().length() > 15 || datosPasajero.getTelefono().length() == 0 || !verificarTelefono(datosPasajero.getTelefono())){
            bandGestor = false;
        }

        if(datosPasajero.getEmail().length()>70 || !verificarEmail(datosPasajero.getEmail())){
            bandGestor = false;
        }

        if(datosPasajero.getOcupacion().length()>50 || datosPasajero.getOcupacion().length()==0){
            bandGestor = false;
        }

        if(datosPasajero.getNacionalidad().equals("Seleccionar")){
            bandGestor = false;
        }
            
        return bandGestor;
    }
    
    //Verificar si existe un pasajero con mismo tipoDoc y numDoc
    public boolean verificarExistenciaPasajero(PasajeroDTO pasajeroDTO){
        Boolean existe = false;
        
        pasajeroDAO = new PasajeroDAOImpl();
        try {
            existe = pasajeroDAO.verificarExistenciaPasajero(pasajeroDTO.getTipoDoc().name(),pasajeroDTO.getNumDoc());
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        
        if(!existe){
            try {
                crearPasajero(pasajeroDTO);
            } catch (ParseException ex) {
                ex.printStackTrace(System.out);
            }
        }
        
        return existe;
    }
    
    public Pasajero crearObjetoPasajero(PasajeroDTO pasajeroDTO){
        
        //Creo el objeto de pais
        int idPais = getInstanceGeo().obtenerIdPais(pasajeroDTO.getPais());
        Pais pais = new Pais(idPais, pasajeroDTO.getPais(),pasajeroDTO.getPais());
        //Creo el objeto nacionalidad
        int idNacionalidad = getInstanceGeo().obtenerIdPais(pasajeroDTO.getNacionalidad());
        Pais nacionalidad = new Pais(idNacionalidad,pasajeroDTO.getNacionalidad(),pasajeroDTO.getNacionalidad());
        //Creo el objeto provincia
        int idProvincia = getInstanceGeo().obtenerIdProvincia(pasajeroDTO.getProvincia());
        Provincia provincia = new Provincia(idProvincia, pais, pasajeroDTO.getProvincia());
        //Creo el objeto localidad
        int idLocalidad = getInstanceGeo().obtenerIdLocalidad(pasajeroDTO.getLocalidad());
        Localidad localidad = new Localidad(idLocalidad, provincia, pasajeroDTO.getLocalidad());

        //Creo el objeto direccion
        Direccion direccion = new Direccion(0, localidad, pasajeroDTO.getCalle(), Integer.parseInt(pasajeroDTO.getNumero()), pasajeroDTO.getDepartamento(), Integer.parseInt(pasajeroDTO.getCodigoPostal()));
        
        
        //El gestor geografico crea el objeto direccion
        //Direccion direccion = getInstanceGeo().crearObjetoDireccion(pasajeroDTO);
        
        //Creo el objeto pasajero
        Pasajero pasajero = new Pasajero(pasajeroDTO.getApellido(),pasajeroDTO.getNombre(),pasajeroDTO.getTipoDoc(),pasajeroDTO.getNumDoc(),pasajeroDTO.getFechaNac(), pasajeroDTO.getEmail(),pasajeroDTO.getOcupacion(),nacionalidad,0,pasajeroDTO.getCUIT(),pasajeroDTO.getPosIva(),pasajeroDTO.getTelefono(),direccion);
        
        return pasajero;
    }
    
    //Agregar nuevo pasajero
    public boolean crearPasajero(PasajeroDTO pasajeroDTO) throws ParseException{
        try {
            //Creo un objeto Pasajero con los datos de pasajeroDTO
            Pasajero pasajero = crearObjetoPasajero(pasajeroDTO);
            
            //PasajeroDAO crea el pasajero 
            pasajeroDAO.crearPasajero(pasajero);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        
        return true;
    }
    
    
    //Buscar pasajeros cuyos datos coincidan con los criterios de busqueda
    public List<Pasajero> buscarPasajeros(BusquedaDTO busquedaDTO){
        ArrayList <Pasajero> resPasajeros = null;
    
        try {

            pasajeroDAO = new PasajeroDAOImpl();
            
            //Si algun atributo esta vacio lo seteo en null
            if( busquedaDTO.getNombre().length()== 0 ){
                busquedaDTO.setNombre(null);
            }
            if( busquedaDTO.getApellido().length()== 0 ){
                busquedaDTO.setApellido(null);
            }
            if( busquedaDTO.getNumDoc().length()== 0 ){
                busquedaDTO.setNumDoc(null);
            }
            
            //PasajeroDAO obtiene los pasajeros que coincidan con los criterios de busqueda
            resPasajeros = (ArrayList<Pasajero>) pasajeroDAO.obtenerPasajeros(busquedaDTO);
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
       
        return resPasajeros;
    }
   
    //Modificar pasajero
    public Boolean modificarPasajero(Pasajero pasajero) throws ParseException{ 
        try {
            pasajeroDAO = new PasajeroDAOImpl();
            pasajeroDAO.modificarPasajero(pasajero);
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            return false;
        }
        return true;
    }
    
}
