
package Dominio;

import java.util.Date;

public class TarjetaDeDebito extends Pago {
    TipoTarjetaDebito tipoTarjetaDebito;

    public TarjetaDeDebito(TipoTarjetaDebito tipoTarjetaDebito) {
        this.tipoTarjetaDebito = tipoTarjetaDebito;
    }

    public TarjetaDeDebito(TipoTarjetaDebito tipoTarjetaDebito, int idPago, float importe, Date fechaPago, float cotizacion, TipoMoneda tipomoneda) {
        super(idPago, importe, fechaPago, cotizacion, tipomoneda);
        this.tipoTarjetaDebito = tipoTarjetaDebito;
    }

    public TarjetaDeDebito(TipoTarjetaDebito tipoTarjetaDebito, int idPago, float importe, Date fechaPago, float cotizacion) {
        super(idPago, importe, fechaPago, cotizacion);
        this.tipoTarjetaDebito = tipoTarjetaDebito;
    }

    public TipoTarjetaDebito getTipoTarjetaDebito() {
        return tipoTarjetaDebito;
    }

    public void setTipoTarjetaDebito(TipoTarjetaDebito tipoTarjetaDebito) {
        this.tipoTarjetaDebito = tipoTarjetaDebito;
    }

    @Override
    public String toString() {
        return "TarjetaDeDebito{" + "tipoTarjetaDebito=" + tipoTarjetaDebito + '}';
    }
    
    
}
