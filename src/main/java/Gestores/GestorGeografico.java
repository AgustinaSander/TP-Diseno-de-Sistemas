
package Gestores;
import DAO.*;
import Dominio.Localidad;
import Dominio.Pais;
import Dominio.Provincia;
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
        List <String> nombrePaises = new ArrayList<>();
        List <Pais> listaPaises = new ArrayList<>();
        try {
            paisDAO = new PaisDAOImpl();
            listaPaises = paisDAO.obtenerPaises();   
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        
        for(Pais p : listaPaises){
            nombrePaises.add(p.getNombrePais());
        }
        
        return nombrePaises;
    }
    
    //Obtener el listado de provincias que componen un pais
    public List<String> obtenerProvincias(String pais){
        //PaisDAO obtiene las provincias de la base de datos
        List <String> listaNombreProvincias = new ArrayList<>();
        List <Provincia> listaProvincias = new ArrayList<>();
        try {
            paisDAO = new PaisDAOImpl();
            listaProvincias = paisDAO.obtenerProvincias(pais);
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        
        for(Provincia prov : listaProvincias){
            listaNombreProvincias.add(prov.getNombreProvincia());
        }
        
        
        return listaNombreProvincias;
    }
    
    //Obtener listado de localidades que componen una provincia que pertenece a un pais
    public List<String> obtenerLocalidades(String provincia, String pais){
        //ProvinciaDAO obtiene las localidades de la base de datos
        List <String> listaNombreLocalidades = new ArrayList<>();
        List <Localidad> listaLocalidades = new ArrayList<>();
        try {
            provinciaDAO = new ProvinciaDAOImpl();
           
            listaLocalidades = provinciaDAO.obtenerLocalidades(provincia, pais);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        
        for(Localidad l : listaLocalidades){
            listaNombreLocalidades.add(l.getNombreLocalidad());
        }
        
        return listaNombreLocalidades;
    }
    
    //Obtener Pais a partir del nombre
    public Pais obtenerPais(String pais){
        //PaisDAO se encarga
        Pais p = null;
        try {
            paisDAO = new PaisDAOImpl();
            p = paisDAO.obtenerPais(pais);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return p;
    }
    
    //Obtener provincia a partir del nombre
    public Provincia obtenerProvincia(String provincia, int idPais){
        //ProvinciaDAO se encarga
        Provincia prov = null;
        
        try {
            provinciaDAO = new ProvinciaDAOImpl();
            prov = provinciaDAO.obtenerProvincia(provincia, idPais);
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return prov;
    }
    
    //Obtener localidad a partir del nombre
    public Localidad obtenerLocalidad(String localidad, String provincia, String pais){
        //LocalidadDAO se encarga
        Localidad l = null;
        
        try {
            //Obtengo el id del pais y de la provincia a la que pertenece la localidad
            int idPais = obtenerPais(pais).getIdPais();
            int idProvincia = obtenerProvincia(provincia, idPais).getIdProvincia();
            
            //Obtengo la localidad
            l = new LocalidadDAOImpl().obtenerLocalidad(localidad, idProvincia);
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return l;
    }
    
}
