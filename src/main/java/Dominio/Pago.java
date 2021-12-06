
package Dominio;

import java.util.Date;

public abstract class Pago {
    protected int idPago;
    protected float importe;
    protected Date fechaPago;
    protected float cotizacion; 
    protected TipoMoneda tipomoneda;

    public Pago() {
    }

    public Pago(int idPago, float importe, Date fechaPago, float cotizacion, TipoMoneda tipomoneda) {
        this.idPago = idPago;
        this.importe = importe;
        this.fechaPago = fechaPago;
        this.cotizacion = cotizacion;
        this.tipomoneda = tipomoneda;
    }

    public Pago(int idPago, float importe, Date fechaPago, float cotizacion) {
        this.idPago = idPago;
        this.importe = importe;
        this.fechaPago = fechaPago;
        this.cotizacion = cotizacion;
    }

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public float getImporte() {
        return importe;
    }

    public void setImporte(float importe) {
        this.importe = importe;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public float getCotizacion() {
        return cotizacion;
    }

    public void setCotizacion(float cotizacion) {
        this.cotizacion = cotizacion;
    }

    public TipoMoneda getTipomoneda() {
        return tipomoneda;
    }

    public void setTipomoneda(TipoMoneda tipomoneda) {
        this.tipomoneda = tipomoneda;
    }

    
    @Override
    public String toString() {
        return "Pago{" + "idPago=" + idPago + ", importe=" + importe + ", fechaPago=" + fechaPago + ", cotizacion=" + cotizacion + '}';
    }
    
    
}
