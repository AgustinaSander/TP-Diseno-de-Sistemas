
package Gestores;

import Validaciones.CamposAltaPasajero;
import Dominio.DTO.PasajeroDTO;
import Dominio.DTO.GestionarPasajeroDTO;
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
import java.util.List;

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
    public CamposAltaPasajero validarDatosPasajero(PasajeroDTO datosPasajero){
        CamposAltaPasajero campos = new CamposAltaPasajero();
        
        //Se validan todos los campos con expresiones regulares

        if(datosPasajero.getNombre().length() > 50 || datosPasajero.getNombre().length() == 0){
            campos.setNombreValido(Boolean.FALSE);
            campos.setValidos(Boolean.FALSE);
        }

        if(datosPasajero.getApellido().length() > 50 || datosPasajero.getApellido().length() == 0){
            campos.setApellidoValido(Boolean.FALSE);
            campos.setValidos(Boolean.FALSE);

        }

        if(datosPasajero.getNumDoc().length() > 10 || datosPasajero.getNumDoc().length() == 0){
            campos.setNumDocValido(Boolean.FALSE);
            campos.setValidos(Boolean.FALSE);
        }

        if(datosPasajero.getCUIT().length() > 15 || !verificarCUIT(datosPasajero.getCUIT())){
            campos.setCUITValido(Boolean.FALSE);
            campos.setValidos(Boolean.FALSE);
        }

        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date fechaMin = null;
        try {
            fechaMin = sdFormat.parse("1870-01-01");
        } catch (ParseException ex) {
            ex.printStackTrace(System.out);
        }

        if(datosPasajero.getFechaNac()== null || datosPasajero.getFechaNac().after(new java.util.Date()) || datosPasajero.getFechaNac().before(fechaMin)){
            campos.setFechaNacValido(Boolean.FALSE);
            campos.setValidos(Boolean.FALSE);
        }

        if(datosPasajero.getCalle().length() > 50 || datosPasajero.getCalle().length() == 0){
             campos.setCalleValido(Boolean.FALSE);
             campos.setValidos(Boolean.FALSE);
        }

        if(datosPasajero.getNumero().length() > 10 || datosPasajero.getNumero().length() == 0 || !esNumero(datosPasajero.getNumero())){
             campos.setNumeroValido(Boolean.FALSE);
             campos.setValidos(Boolean.FALSE);
        }

        if(datosPasajero.getDepartamento().length() > 15){
            campos.setDepartamentoValido(Boolean.FALSE);
            campos.setValidos(Boolean.FALSE);
        }

        if(datosPasajero.getCodigoPostal().length() > 5 || datosPasajero.getCodigoPostal().length() == 0 || !esNumero(datosPasajero.getCodigoPostal())){
            campos.setCodigoPostalValido(Boolean.FALSE);
            campos.setValidos(Boolean.FALSE);
        }

        if(datosPasajero.getLocalidad().equals("No disponible") || datosPasajero.getLocalidad().equals("Seleccionar")){
            campos.setLocalidadValido(Boolean.FALSE);
            campos.setValidos(Boolean.FALSE);
        }

        if(datosPasajero.getProvincia().equals("Seleccionar")){
            campos.setProvinciaValido(Boolean.FALSE);
            campos.setValidos(Boolean.FALSE);
        }

        if(datosPasajero.getTelefono().length() > 15 || datosPasajero.getTelefono().length() == 0 || !verificarTelefono(datosPasajero.getTelefono())){
            campos.setTelefonoValido(Boolean.FALSE);
            campos.setValidos(Boolean.FALSE);
        }

        if(datosPasajero.getEmail().length()>70 || !verificarEmail(datosPasajero.getEmail())){
            campos.setEmailValido(Boolean.FALSE);
            campos.setValidos(Boolean.FALSE);
        }

        if(datosPasajero.getOcupacion().length()>50 || datosPasajero.getOcupacion().length()==0){
            campos.setOcupacionValido(Boolean.FALSE);
            campos.setValidos(Boolean.FALSE);
        }

        if(datosPasajero.getNacionalidad().equals("Seleccionar")){
            campos.setNacionalidadValido(Boolean.FALSE);
            campos.setValidos(Boolean.FALSE);
        }
            
        return campos;
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
        
        return existe;
    }
    
    public Pasajero crearObjetoPasajero(PasajeroDTO pasajeroDTO){
        
        //DEVOLVER TODO EL OBJETO
        //Creo el objeto de pais
        int idPais = getInstanceGeo().obtenerIdPais(pasajeroDTO.getPais());
        Pais pais = new Pais(idPais, pasajeroDTO.getPais(),pasajeroDTO.getPais());
        //Creo el objeto nacionalidad
        int idNacionalidad = getInstanceGeo().obtenerIdPais(pasajeroDTO.getNacionalidad());
        Pais nacionalidad = new Pais(idNacionalidad,pasajeroDTO.getNacionalidad(),pasajeroDTO.getNacionalidad());
        //Creo el objeto provincia
        int idProvincia = getInstanceGeo().obtenerIdProvincia(pasajeroDTO.getProvincia(),idPais);
        Provincia provincia = new Provincia(idProvincia, pais, pasajeroDTO.getProvincia());
        //Creo el objeto localidad
        int idLocalidad = getInstanceGeo().obtenerIdLocalidad(pasajeroDTO.getLocalidad(), pasajeroDTO.getProvincia(), pasajeroDTO.getPais());
        Localidad localidad = new Localidad(idLocalidad, provincia, pasajeroDTO.getLocalidad());

        //Creo el objeto direccion
        Direccion direccion = new Direccion(pasajeroDTO.getIdDireccion(), localidad, pasajeroDTO.getCalle(), Integer.parseInt(pasajeroDTO.getNumero()), pasajeroDTO.getDepartamento(), Integer.parseInt(pasajeroDTO.getCodigoPostal()));
        
        //Creo el objeto pasajero
        Pasajero pasajero = new Pasajero(pasajeroDTO.getApellido(),pasajeroDTO.getNombre(),pasajeroDTO.getTipoDoc(),pasajeroDTO.getNumDoc(),pasajeroDTO.getFechaNac(), pasajeroDTO.getEmail(),pasajeroDTO.getOcupacion(),nacionalidad,pasajeroDTO.getIdPersona(),pasajeroDTO.getCUIT(),pasajeroDTO.getPosIva(),pasajeroDTO.getTelefono(),direccion);
        
        return pasajero;
    }
    
    //Agregar nuevo pasajero
    public boolean crearPasajero(PasajeroDTO pasajeroDTO) throws ParseException{
        try {
            //Creo un objeto Pasajero con los datos de pasajeroDTO
            Pasajero pasajero = new Pasajero(pasajeroDTO.getApellido(), pasajeroDTO.getNombre(), pasajeroDTO.getTipoDoc(),pasajeroDTO.getNumDoc(), pasajeroDTO.getFechaNac(), pasajeroDTO.getEmail(), pasajeroDTO.getOcupacion(), pasajeroDTO.getCUIT(), pasajeroDTO.getPosIva(), pasajeroDTO.getTelefono());
            
            //Obtengo el id del pais que va a ser la nacionalidad y creo un objeto Pais
            int idNacionalidad = getInstanceGeo().obtenerIdPais(pasajeroDTO.getNacionalidad());
            Pais nacionalidad = new Pais(idNacionalidad);
            pasajero.setNacionalidad(nacionalidad);
            
            //Obtengo el id de la localidad y creo un objeto Localidad
            int idLocalidad = getInstanceGeo().obtenerIdLocalidad(pasajeroDTO.getLocalidad(), pasajeroDTO.getProvincia(), pasajeroDTO.getPais());
            Localidad localidad = new Localidad(idLocalidad);
            
            //Creo objeto direccion 
            Direccion direccion = new Direccion(localidad, pasajeroDTO.getCalle(), Integer.parseInt(pasajeroDTO.getNumero()), pasajeroDTO.getDepartamento(), Integer.parseInt(pasajeroDTO.getCodigoPostal()));
            
            pasajero.setDireccion(direccion);
            //PasajeroDAO crea el pasajero 
            pasajeroDAO.crearPasajero(pasajero);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        
        return true;
    }
    
    
    //Buscar pasajeros cuyos datos coincidan con los criterios de busqueda
    public List<GestionarPasajeroDTO> buscarPasajeros(GestionarPasajeroDTO busquedaDTO){
        List <GestionarPasajeroDTO> listaPasajerosEncontrados = null;
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
            listaPasajerosEncontrados = pasajeroDAO.obtenerPasajeros(busquedaDTO);
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
       
        return listaPasajerosEncontrados;
    }
   
    //Modificar pasajero
    public Boolean modificarPasajero(PasajeroDTO pasajeroDTO) throws ParseException{ 
        try {
            pasajeroDAO = new PasajeroDAOImpl();
            pasajeroDAO.modificarPasajero(crearObjetoPasajero(pasajeroDTO));
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            return false;
        }
        return true;
    }
    
    public PasajeroDTO buscarPasajero(int idPasajero){
        pasajeroDAO = new PasajeroDAOImpl();
        Pasajero pasajero = pasajeroDAO.obtenerPasajero(idPasajero);
        
        //Creo el objeto pasajeroDTO a partir de pasajero
        PasajeroDTO pasajeroDTO = new PasajeroDTO(pasajero.getApellido(), pasajero.getNombre(), pasajero.getTipoDoc(), pasajero.getNumDoc(), pasajero.getFechaNac(), pasajero.getEmail(), pasajero.getOcupacion(),pasajero.getNacionalidad().getNombrePais(),pasajero.getCUIT(),pasajero.getPosIva(),pasajero.getTelefono(), pasajero.getDireccion().getLocalidad().getProvincia().getPais().getNombrePais(),pasajero.getDireccion().getLocalidad().getProvincia().getNombreProvincia(), pasajero.getDireccion().getLocalidad().getNombreLocalidad(), pasajero.getDireccion().getCalle(),String.valueOf(pasajero.getDireccion().getNumero()),pasajero.getDireccion().getDepartamento(), String.valueOf(pasajero.getDireccion().getCodigoPostal()));
        
        return pasajeroDTO;
    }
    
}
