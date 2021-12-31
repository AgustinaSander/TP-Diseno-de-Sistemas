
package Gestores;

import DAO.IResponsableDAO;
import DAO.ResponsableDAOImpl;
import Dominio.DTO.PersonaDTO;
import Dominio.ResponsableDePago;
import static Validaciones.Validaciones.verificarCUIT;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GestorResponsablePago {
    private static GestorResponsablePago instanciaGResponsable = null;
    private static IResponsableDAO responsableDAO = null;
    
    private GestorResponsablePago(){};
    
    public static GestorResponsablePago getInstanceResponsable(){
        if(instanciaGResponsable == null)
            instanciaGResponsable = new GestorResponsablePago();
   
        return instanciaGResponsable;
    }

    public Boolean validarCUIT(String cuit) {
     
        return verificarCUIT(cuit);
    }
    
    public PersonaDTO obtenerResponsableDePago(String cuit){
        responsableDAO = new ResponsableDAOImpl();
        ResponsableDePago responsable = null;
        try {
            responsable = responsableDAO.obtenerResponsableDePago(cuit);
        } catch (SQLException ex) {
            Logger.getLogger(GestorResponsablePago.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        if(responsable != null){
            PersonaDTO responsableDTO = new PersonaDTO(false, responsable.getCUIT(), responsable.getPosIva(), responsable.getTelefono(), responsable.getRazonSocial(), responsable.getIdPersona(), responsable.getDireccion().getIdDireccion());
            return responsableDTO;
        }     
        else{
            return null;
        }
    }
    
}
