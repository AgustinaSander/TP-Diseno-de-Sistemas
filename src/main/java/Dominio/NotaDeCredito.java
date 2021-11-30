
package Dominio;

import java.util.Date;
import java.util.List;

public abstract class NotaDeCredito {
    protected int idNotaCredito;
    protected Date fecha;
    protected Float importeNeto;
    protected Float importeTotal;
    protected List<Factura> listaFacturas;

    public NotaDeCredito() {
    }

    public NotaDeCredito(int idNotaCredito, Date fecha, Float importeNeto, Float importeTotal) {
        this.idNotaCredito = idNotaCredito;
        this.fecha = fecha;
        this.importeNeto = importeNeto;
        this.importeTotal = importeTotal;
    }

    public NotaDeCredito(int idNotaCredito, Date fecha, Float importeNeto, Float importeTotal, List<Factura> listaFacturas) {
        this.idNotaCredito = idNotaCredito;
        this.fecha = fecha;
        this.importeNeto = importeNeto;
        this.importeTotal = importeTotal;
        this.listaFacturas = listaFacturas;
    }

    public int getIdNotaCredito() {
        return idNotaCredito;
    }

    public void setIdNotaCredito(int idNotaCredito) {
        this.idNotaCredito = idNotaCredito;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Float getImporteNeto() {
        return importeNeto;
    }

    public void setImporteNeto(Float importeNeto) {
        this.importeNeto = importeNeto;
    }

    public Float getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(Float importeTotal) {
        this.importeTotal = importeTotal;
    }

    public List<Factura> getListaFacturas() {
        return listaFacturas;
    }

    public void setListaFacturas(List<Factura> listaFacturas) {
        this.listaFacturas = listaFacturas;
    }

    @Override
    public String toString() {
        return "NotaDeCredito{" + "idNotaCredito=" + idNotaCredito + ", fecha=" + fecha + ", importeNeto=" + importeNeto + ", importeTotal=" + importeTotal + ", listaFacturas=" + listaFacturas + '}';
    }

    
}
