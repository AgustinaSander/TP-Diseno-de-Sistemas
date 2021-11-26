
package Dominio;

import java.util.Date;
import java.util.List;

public class FacturaB extends Factura{

    public FacturaB() {
    }

    public FacturaB(int idFactura, Date fecha, float importeNeto, float importeTotal, Boolean pagada) {
        super(idFactura, fecha, importeNeto, importeTotal, pagada);
    }

    public FacturaB(int idFactura, Date fecha, float importeNeto, float importeTotal, Boolean pagada, Persona persona, List<ItemFactura> listaItemsFactura, Estadia estadia) {
        super(idFactura, fecha, importeNeto, importeTotal, pagada, persona, listaItemsFactura, estadia);
    }
    
    
}
