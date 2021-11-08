
package Gestores;
import DAO.*;
import java.sql.*;
import java.util.*;


public class GestorGeografico {
    private static GestorGeografico instanciaGGeografico = null;
    
    //private static Connection conn = null;
    private static IPaisDAO paisDAO = null;
    private static IProvinciaDAO provinciaDAO = null;
    private static ILocalidadDAO localidadDAO = null;
    
    private GestorGeografico(){};
    
    public static GestorGeografico getInstanceGeo(){
        if(instanciaGGeografico == null)
            instanciaGGeografico = new GestorGeografico();
   
        return instanciaGGeografico;
    }
    
    //Obtener listado de todos los paises de la base de datos
    public  List<String> obtenerPaises(){
        //PaisDAO obtiene los paises de la base de datos
        List <String> listaPaises = null;
        try {
            paisDAO = new PaisDAOImpl();
            listaPaises = paisDAO.obtenerPaises();   
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return listaPaises;
    }
    
    //Obtener el listado de provincias que componen un pais
    public List<String> obtenerProvincias(String pais){
        //PaisDAO obtiene las provincias de la base de datos
        List <String> listaProvincias = null;
        try {
            paisDAO = new PaisDAOImpl();
            listaProvincias = paisDAO.obtenerProvincias(pais);
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return listaProvincias;
    }
    
    //Obtener listado de localidades que componen una provincia que pertenece a un pais
    public List<String> obtenerLocalidades(String provincia, String pais){
        //ProvinciaDAO obtiene las localidades de la base de datos
        List <String> listaLocalidades = null;
        try {
            provinciaDAO = new ProvinciaDAOImpl();
            listaLocalidades = provinciaDAO.obtenerLocalidades(provincia, pais);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return listaLocalidades;
    }
    
    //Obtener IdPais a partir del nombre
    public int obtenerIdPais(String pais){
        //PaisDAO se encarga
        int idPais = 0;
        
        try {
            paisDAO = new PaisDAOImpl();
            idPais = paisDAO.obtenerIdPais(pais);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return idPais;
    }
    
    //Obtener IdProvincia a partir del nombre
    public int obtenerIdProvincia(String provincia, int idPais){
        //ProvinciaDAO se encarga
        int idProvincia = 0;
        
        try {
            provinciaDAO = new ProvinciaDAOImpl();
            idProvincia = provinciaDAO.obtenerIdProvincia(provincia, idPais);
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return idProvincia;
    }
    
    //Obtener IdLocalidad a partir del nombre
    public int obtenerIdLocalidad(String localidad, String provincia, String pais){
        //LocalidadDAO se encarga
        int idLocalidad = 0;
        
        try {
            //Obtengo el id del pais y de la provincia a la que pertenece la localidad
            int idPais = obtenerIdPais(pais);
            int idProvincia = obtenerIdProvincia(provincia, idPais);
            
            //Obtengo el id de la localidad con su nombre y el id de la provincia a la que pertenece
            localidadDAO = new LocalidadDAOImpl();
            idLocalidad = localidadDAO.obtenerIdLocalidad(localidad, idProvincia);
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return idLocalidad;
    }
    
}
