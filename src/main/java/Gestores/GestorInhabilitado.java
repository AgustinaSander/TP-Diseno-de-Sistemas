
package Gestores;

import DAO.IInhabilitadoDAO;

public class GestorInhabilitado {
    private static GestorInhabilitado instanciaGInhabilitado = null;
    private static IInhabilitadoDAO inhabilitadoDAO = null;

    
    private GestorInhabilitado(){};
    
    public static GestorInhabilitado getInstanceInhabilitado(){
        if(instanciaGInhabilitado == null)
            instanciaGInhabilitado = new GestorInhabilitado();
   
        return instanciaGInhabilitado;
    }
    
    
}
