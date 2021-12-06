
package Dominio;

import java.util.Date;

public class Moneda extends Pago {

    public Moneda() {
    }

    public Moneda(int idPago, float importe, Date fechaPago, float cotizacion, TipoMoneda tipomoneda) {
        super(idPago, importe, fechaPago, cotizacion, tipomoneda);
    }

    public Moneda(int idPago, float importe, Date fechaPago, float cotizacion) {
        super(idPago, importe, fechaPago, cotizacion);
    }
    
    
}
