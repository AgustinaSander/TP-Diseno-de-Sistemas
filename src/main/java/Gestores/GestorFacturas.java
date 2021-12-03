
package Gestores;

import DAO.EstadiaDAOImpl;
import DAO.FacturaDAOImpl;
import DAO.IFacturaDAO;
import DAO.PasajeroDAOImpl;
import DAO.ResponsableDAOImpl;
import DAO.ServicioDAOImpl;
import Dominio.DTO.ItemDTO;
import Dominio.DTO.PersonaDTO;
import Dominio.Estadia;
import Dominio.Factura;
import Dominio.FacturaA;
import Dominio.FacturaB;
import Dominio.ItemEstadia;
import Dominio.ItemFactura;
import Dominio.ItemServicio;
import Dominio.Pasajero;
import Dominio.Persona;
import Dominio.ResponsableDePago;
import Dominio.Servicio;
import Enum.PosicionIVA;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GestorFacturas {
    private static GestorFacturas instanciaGFacturas = null;
    private static IFacturaDAO facturaDAO = null;
    
    private GestorFacturas(){};
    
    public static GestorFacturas getInstanceFacturas(){
        if(instanciaGFacturas == null)
            instanciaGFacturas = new GestorFacturas();
   
        return instanciaGFacturas;
    }

    public int crearFactura(List<ItemDTO> itemsEstadiaSeleccionados, List<ItemDTO> itemsServicioSeleccionados, PersonaDTO persona, int idEstadia) {
        
        //Calculo el importe neto
        Double importeNeto = 0.0;
        
        for(ItemDTO i : itemsServicioSeleccionados){
            importeNeto += i.getMonto() * i.getCantidad();
        }
        
        for(ItemDTO i : itemsEstadiaSeleccionados){
            importeNeto += i.getMonto();
        }
        
        //Calculo el importe total
        //Me fijo la posicion frente al iva del pasajero
        PosicionIVA posIva = persona.getPosIva();
        Double importeTotal = importeNeto;
        Double importeIva = 0.0;
        
        //Creo el objeto Factura
        //Recupero la persona que es responsable de la factura
        Persona per = null;
        
        if(persona.getEsPasajero()){
            try {
                Pasajero p = new PasajeroDAOImpl().obtenerPasajero(persona.getIdPersona());
                per = p;
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        else{
            try {
                ResponsableDePago r = new ResponsableDAOImpl().obtenerResponsableDePago(persona.getIdPersona());
                per = r;
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        
        Factura f = null;
        if(posIva == PosicionIVA.IVA_RESPONSABLE_INSCRIPTO){
            //Factura tipo A
            importeIva = importeNeto * 0.21;
            importeTotal += importeIva;
            
            f = new FacturaA(importeIva.floatValue(), 0 , new Date(), importeNeto.floatValue(), importeTotal.floatValue(), false, per, null); 
        }
        else{
            //Factura tipo B
            f = new FacturaB(0 , new Date(), importeNeto.floatValue(), importeTotal.floatValue(), false, per, null);
        }
       
        //Creo la lista de items de la factura
        List<ItemFactura> itemsDeFactura = new ArrayList<>();
         
        //Obtengo el objeto estadia
        Estadia e = null;
        try {
            e = new EstadiaDAOImpl().obtenerEstadia(idEstadia);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        
        f.setEstadia(e);
        
        //Guardo los items estadia
        int cantDiasEstadia = 0;
        ItemDTO aux = null;
        for(ItemDTO i : itemsEstadiaSeleccionados){
            //System.out.println(i);
            if(i.getEsItemExtra()){
                ItemFactura item = new ItemEstadia(e, 0, i.getDescripcion(), i.getCantidad() * i.getMonto(), i.getMonto(), i.getCantidad(), i.getEsItemExtra());
                itemsDeFactura.add(item);
            }
            else{
                aux = i;
                cantDiasEstadia++;
            }
        }
        
        if(cantDiasEstadia != 0){
            ItemFactura itemE = new ItemEstadia(e, 0, aux.getDescripcion(), cantDiasEstadia * aux.getMonto(), aux.getMonto(), cantDiasEstadia, aux.getEsItemExtra());
            itemsDeFactura.add(itemE);  
        }
        
        //Guardo los items servicio
        for(ItemDTO i : itemsServicioSeleccionados){
            
            //Obtengo el objeto servicio
            Servicio s = null;
            try {
                s = new ServicioDAOImpl().obtenerServicio(i.getIdServicio());
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }

            ItemFactura item = new ItemServicio(s, 0, i.getDescripcion(), i.getCantidad() * i.getMonto(), i.getMonto(), i.getCantidad());
            itemsDeFactura.add(item);
        }
        
        //Seteo a la factura los items facturados
        /*for(ItemFactura i : itemsDeFactura){
            System.out.println(i);
        }*/
        
        f.setListaItemsFactura(itemsDeFactura);
        
        int idFactura = 0;
        try {
            //Voy a facturaDAO y guardo la factura
            idFactura = new FacturaDAOImpl().crearFactura(f);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        
        return idFactura;
    }
    
    
    
}
