
package DAO;

import Dominio.DTO.GestionarPasajeroDTO;
import static Conexion.Conexion.*;
import Dominio.*;
import Enum.PosicionIVA;
import Enum.TipoDocumento;
import java.sql.*;
import java.util.*;

public class PasajeroDAOImpl implements IPasajeroDAO{
    //Si necesito aplicar transacciones
    private Connection conexionTransaccional;
    private Connection conn = null;
    private PreparedStatement stmt=null;
    private ResultSet rs = null;
    
    public PasajeroDAOImpl(){
    }
    
    public PasajeroDAOImpl(Connection conexionTransaccional){
        this.conexionTransaccional = conexionTransaccional;
    }
    
    @Override
    //Agregar persona a partir del objeto Pasajero
    public int crearPersona(Pasajero pasajero) throws SQLException{
        int resId = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            //Inserto persona
            stmt = conn.prepareStatement("INSERT INTO persona(CUIT,posIVA,telefono,idDireccion) VALUES (?, ?, ?, ?)");
            stmt.setString(1,pasajero.getCUIT());
            stmt.setString(2,pasajero.getPosIva().name());
            stmt.setString(3,pasajero.getTelefono());
            stmt.setInt(4,pasajero.getDireccion().getIdDireccion());
            stmt.executeUpdate();
            
            //Recupero el idPersona generado por la base
            stmt = conn.prepareStatement("SELECT MAX(idPersona) FROM `persona`;");
            rs = stmt.executeQuery();
            if(rs.next()){
                resId = rs.getInt("MAX(idPersona)");
            }
        }finally{
            try {
                close(stmt);
                close(rs);
                if(this.conexionTransaccional == null){
                    close(conn);
                }
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
       return resId;
    }
    
    @Override
    //Agregar pasajero a partir del objeto Pasajero
    public int crearPasajero(Pasajero pasajero) throws SQLException {
        int res = 0;
        
        try {
            conn = getConnection();
            this.conexionTransaccional = conn;
            
            if(conn.getAutoCommit()){
                conn.setAutoCommit(false);
            }
            
            //Invoca a DireccionDAO 
            IDireccionDAO direccionDAO = new DireccionDAOImpl(conn);
            int idDireccion = direccionDAO.crearDireccion(pasajero.getDireccion());
            //Seteo el idDireccion asignado por la base de datos
            Direccion direccion = pasajero.getDireccion();
            direccion.setIdDireccion(idDireccion);
            pasajero.setDireccion(direccion);
            
            //Inserto persona
            int idPersona = crearPersona(pasajero);
            //Seteo el idPersona asignado por la base de datos
            pasajero.setIdPersona(idPersona);
            
            //Inserto pasajero
            stmt = conn.prepareStatement("INSERT INTO pasajero(idPersona,idPais,apellido,nombre,tipoDoc,numDoc,fechaNac,email,ocupacion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setInt(1,pasajero.getIdPersona());
            stmt.setInt(2, pasajero.getNacionalidad().getIdPais());
            stmt.setString(3,pasajero.getApellido());
            stmt.setString(4,pasajero.getNombre());
            stmt.setString(5,pasajero.getTipoDoc().name());
            stmt.setString(6,pasajero.getNumDoc());
            java.sql.Date fechaNac = new java.sql.Date(pasajero.getFechaNac().getTime());
            stmt.setDate(7, fechaNac);
            stmt.setString(8,pasajero.getEmail());
            stmt.setString(9,pasajero.getOcupacion());
            res = stmt.executeUpdate();
            
            conn.commit();
         } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            try {
                conn.rollback();
                System.out.println("Se hace rollback");
            } catch (SQLException ex1) {
                ex1.printStackTrace(System.out);
            }
        }    
        finally{
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        
        return res;
    }

    @Override
    //Verifico si existen pasajeros con mismo tipoDoc y numDoc
    public List<Pasajero> verificarExistenciaPasajero(String tipoDoc, String numDoc) throws SQLException{
        List<Pasajero> listaPasajeros = new ArrayList<>();
        
        try {
            conn = getConnection();
            this.conexionTransaccional = conn;
            
            if(conn.getAutoCommit()){
                conn.setAutoCommit(false);
            }
            
            stmt = conn.prepareStatement("SELECT * FROM pasajero WHERE tipoDoc = ? AND numDoc = ?");
            stmt.setString(1,tipoDoc);
            stmt.setString(2,numDoc);
            rs = stmt.executeQuery();
            while(rs.next()){
                Pasajero pas = obtenerPasajero(rs.getInt("idPersona"));
                listaPasajeros.add(pas);
            }
            
            conn.commit();
         } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            try {
                conn.rollback();
                System.out.println("Se hace rollback");
            } catch (SQLException ex1) {
                ex1.printStackTrace(System.out);
            }
        }    
        finally{
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        
        return listaPasajeros;
    }
    
    @Override
    //Obtengo todos los pasajeros que coinciden con los datos del objeto GestionarPasajeroDTO
    public List<Pasajero> obtenerPasajeros(GestionarPasajeroDTO busquedaDTO) throws SQLException{
        List <Pasajero> resPasajeros = new ArrayList<>();
        List <Integer> idPasajeros = new ArrayList<>();
        try {
            conn = getConnection();
            this.conexionTransaccional = conn;
           
            if(conn.getAutoCommit()){
                conn.setAutoCommit(false);
            }
            
            stmt = conn.prepareStatement("SELECT idPersona FROM pasajero WHERE ((? is null) or (? = apellido)) AND ((? is null) or (? = nombre)) AND ((? is null) or (? = tipoDoc)) AND ((? is null) or (? = numDoc));");
            stmt.setString(1,busquedaDTO.getApellido());
            stmt.setString(2,busquedaDTO.getApellido());
            stmt.setString(3,busquedaDTO.getNombre());
            stmt.setString(4,busquedaDTO.getNombre());
            stmt.setString(5,busquedaDTO.getTipoDoc() == null ? null : busquedaDTO.getTipoDoc().name());
            stmt.setString(6,busquedaDTO.getTipoDoc() == null ? null : busquedaDTO.getTipoDoc().name());
            stmt.setString(7,busquedaDTO.getNumDoc());
            stmt.setString(8,busquedaDTO.getNumDoc());
            rs = stmt.executeQuery();
            GestionarPasajeroDTO pas = null;
            while(rs.next()){                
                //Guardo los idPersona de los pasajeros obtenidos
                idPasajeros.add(rs.getInt("idPersona"));   
            }
            
            for(int id : idPasajeros){
                resPasajeros.add(obtenerPasajero(id));
            }
            
            conn.commit();   
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            try {
                conn.rollback();
                System.out.println("Se hace rollback");
            } catch (SQLException ex1) {
                ex1.printStackTrace(System.out);
            }
        }    
        finally{
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        
        return resPasajeros;
    }

    @Override
    //Modificar el pasajero a partir del objeto Pasajero
    public void modificarPasajero(Pasajero pasajero) throws SQLException{
        try {
            this.conexionTransaccional = getConnection();
            conn = this.conexionTransaccional;
            
            if(conn.getAutoCommit()){
                conn.setAutoCommit(false);
            }
            
            //Modifico la tabla pasajero
            stmt = conn.prepareStatement("UPDATE pasajero SET idPais = ?, apellido = ?, nombre = ?, tipoDoc = ?, numDoc = ?, fechaNac = ?, email = ?, ocupacion = ? WHERE idPersona = ?");
            stmt.setInt(1,pasajero.getNacionalidad().getIdPais());
            stmt.setString(2,pasajero.getApellido());
            stmt.setString(3,pasajero.getNombre());
            stmt.setString(4,pasajero.getTipoDoc().name());
            stmt.setString(5,pasajero.getNumDoc());
            java.sql.Date fechaNac = new java.sql.Date(pasajero.getFechaNac().getTime());
            stmt.setDate(6, fechaNac);
            stmt.setString(7,pasajero.getEmail());
            stmt.setString(8,pasajero.getOcupacion());
            stmt.setInt(9, pasajero.getIdPersona()); 
            stmt.executeUpdate();
            
            //Modifico la tabla direccion
            IDireccionDAO direccionDAO = new DireccionDAOImpl(conn);
            direccionDAO.modificarDireccion(pasajero.getDireccion());
            
            //Modifico la tabla persona
            stmt = conn.prepareStatement("UPDATE persona SET CUIT = ?, posIVA = ?, telefono = ?, idDireccion = ? WHERE idPersona = ?");
            stmt.setString(1,pasajero.getCUIT());
            stmt.setString(2,pasajero.getPosIva().name());
            stmt.setString(3,pasajero.getTelefono());
            stmt.setInt(4, pasajero.getDireccion().getIdDireccion());
            stmt.setInt(5, pasajero.getIdPersona());
            stmt.executeUpdate();
            
            conn.commit();   
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            try {
                conn.rollback();
                System.out.println("Se hace rollback");
            } catch (SQLException ex1) {
                ex1.printStackTrace(System.out);
            }
        }    
        finally{
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
    }
    
    //Obtengo el objeto pasajero a partir del id de la persona
    @Override
    public Pasajero obtenerPasajero(int idPasajero) throws SQLException{
        Pasajero pas = null;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            
            stmt = conn.prepareStatement("SELECT pas.*, per.*, dir.*, l.id AS idLocalidad, l.*, e.id AS idProvincia, e.*, p.*, nac.id AS idNacionalidad, nac.paisnombre AS paisNacionalidad, nac.nacionalidad AS nacionalidadNacionalidad FROM pasajero AS pas, persona AS per, direccion AS dir, localidad as l, estado as e, pais AS p, pais AS nac WHERE pas.idPersona = ? AND pas.idPersona = per.idPersona AND pas.idPais = nac.id AND per.idDireccion = dir.idDireccion AND dir.idLocalidad = l.id AND l.id_provincia = e.id AND e.ubicacionpaisid = p.id");
            stmt.setInt(1,idPasajero);
            rs = stmt.executeQuery();
            while(rs.next()){                
                //Creo el objeto pasajero
                //Obtengo el objeto localidad
                Localidad localidad = new LocalidadDAOImpl(conn).obtenerLocalidad(rs.getInt("idLocalidad"));
                
                //Obtengo el objeto pais para la nacionalidad
                Pais nacionalidad = new PaisDAOImpl(conn).obtenerPais(rs.getInt("idNacionalidad"));        
                
                //Obtengo el objeto direccion
                Direccion direccion = new DireccionDAOImpl(conn).obtenerDireccionPasajero(idPasajero);

                pas = new Pasajero(rs.getString("apellido"),rs.getString("nombre"),TipoDocumento.valueOf(rs.getString("tipoDoc")),rs.getString("numDoc"),new java.util.Date(rs.getDate("fechaNac").getTime()),rs.getString("email"),rs.getString("ocupacion"),nacionalidad,rs.getInt("idPersona"),rs.getString("CUIT"),PosicionIVA.valueOf(rs.getString("posIVA")), rs.getString("telefono"),direccion);
           
            }
        }finally{
            try {
                
                if(this.conexionTransaccional == null){
                    close(stmt);
                    close(conn);
                }
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return pas;
    }
    
    @Override
    public List<OcupadaPor> obtenerOcupantesEstadia(int idEstadia) throws SQLException{
        List<OcupadaPor> pasajeros = new ArrayList<>();
       
        try{
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            
            stmt = conn.prepareStatement("SELECT * FROM ocupadapor AS o, persona AS per, pasajero AS p WHERE o.idEstadia = ? AND per.idPersona = o.idPersona AND o.idPersona = p.idPersona");
            stmt.setInt(1, idEstadia);
            rs = stmt.executeQuery();
            while(rs.next()){
                
                Pasajero pas = new Pasajero(rs.getString("apellido"),rs.getString("nombre"),TipoDocumento.valueOf(rs.getString("tipoDoc")),rs.getString("numDoc"),new java.util.Date(rs.getDate("fechaNac").getTime()),rs.getString("email"),rs.getString("ocupacion"),null,rs.getInt("idPersona"),rs.getString("CUIT"),PosicionIVA.valueOf(rs.getString("posIVA")), rs.getString("telefono"),null);
                //Obtengo la direccion del pasajero con DireccionDAO
                Direccion direccion = new DireccionDAOImpl(conn).obtenerDireccionPasajero(pas.getIdPersona());
                //Le seteo la direccion al pasajero
                pas.setDireccion(direccion);
                
                //Obtengo la nacionalidad del pasajero con PaisDAO
                Pais nacionalidad = new PaisDAOImpl(conn).obtenerNacionalidad(pas.getIdPersona());
                //Le seteo la nacionalidad
                pas.setNacionalidad(nacionalidad);
                        
                //Creo el objeto OcupadaPor y lo agrego a la lista
                OcupadaPor ocupadaPor = new OcupadaPor(pas, rs.getBoolean("esResponsable"));
                pasajeros.add(ocupadaPor);
            }

        }finally{
            try {
                close(stmt);
                close(rs);
                if(this.conexionTransaccional == null){
                    close(conn);
                }
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        
        return pasajeros;
    }
}
