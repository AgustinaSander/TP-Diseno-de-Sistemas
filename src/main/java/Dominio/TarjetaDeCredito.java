
package Dominio;

import java.util.Date;

public class TarjetaDeCredito extends Pago {
    TipoTarjetaCredito tipoTarjetaCredito;

    public TarjetaDeCredito(TipoTarjetaCredito tipoTarjetaCredito) {
        this.tipoTarjetaCredito = tipoTarjetaCredito;
    }

    public TarjetaDeCredito(TipoTarjetaCredito tipoTarjetaCredito, int idPago, float importe, Date fechaPago, float cotizacion, TipoMoneda tipomoneda) {
        super(idPago, importe, fechaPago, cotizacion, tipomoneda);
        this.tipoTarjetaCredito = tipoTarjetaCredito;
    }

    public TarjetaDeCredito(TipoTarjetaCredito tipoTarjetaCredito, int idPago, float importe, Date fechaPago, float cotizacion) {
        super(idPago, importe, fechaPago, cotizacion);
        this.tipoTarjetaCredito = tipoTarjetaCredito;
    }

    public TipoTarjetaCredito getTipoTarjetaCredito() {
        return tipoTarjetaCredito;
    }

    public void setTipoTarjetaCredito(TipoTarjetaCredito tipoTarjetaCredito) {
        this.tipoTarjetaCredito = tipoTarjetaCredito;
    }

    @Override
    public String toString() {
        return "TarjetaDeCredito{" + "tipoTarjetaCredito=" + tipoTarjetaCredito + '}';
    }
    
    
}
