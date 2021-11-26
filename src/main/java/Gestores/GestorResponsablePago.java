
package Gestores;

import DAO.IResponsableDAO;
import DAO.ResponsableDAOImpl;
import Dominio.DTO.ResponsableDTO;
import Dominio.ResponsableDePago;
import static Validaciones.Validaciones.verificarCUIT;

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
    
    public ResponsableDTO obtenerResponsableDePago(String cuit){
        responsableDAO = new ResponsableDAOImpl();
        ResponsableDePago responsable = responsableDAO.obtenerResponsableDePago(cuit);
        System.out.println(responsable);
        //String razonSocial, int idPersona, String CUIT, PosicionIVA posIva, String telefono, Direccion direccion
        ResponsableDTO responsableDTO = new ResponsableDTO(responsable.getRazonSocial(), responsable.getIdPersona(), responsable.getCUIT(),responsable.getPosIva(), responsable.getTelefono(), responsable.getDireccion());
                
       return responsableDTO;
    }
    
}
