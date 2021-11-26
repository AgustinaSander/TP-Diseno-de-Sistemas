
package Dominio;

import java.util.Date;
import java.util.List;

public abstract class Factura {
    protected int idFactura;
    protected Date fecha;
    protected float importeNeto;
    protected float importeTotal;
    protected Boolean pagada;
    protected Persona persona;
    protected List<ItemFactura> listaItemsFactura;
    protected Estadia estadia;

    public Factura() {
    }
    
    public Factura(int idFactura, Date fecha, float importeNeto, float importeTotal, Boolean pagada) {
        this.idFactura = idFactura;
        this.fecha = fecha;
        this.importeNeto = importeNeto;
        this.importeTotal = importeTotal;
        this.pagada = pagada;
    }

    public Factura(int idFactura, Date fecha, float importeNeto, float importeTotal, Boolean pagada, Persona persona, List<ItemFactura> listaItemsFactura, Estadia estadia) {
        this.idFactura = idFactura;
        this.fecha = fecha;
        this.importeNeto = importeNeto;
        this.importeTotal = importeTotal;
        this.pagada = pagada;
        this.persona = persona;
        this.listaItemsFactura = listaItemsFactura;
        this.estadia = estadia;
    }

    
    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public float getImporteNeto() {
        return importeNeto;
    }

    public void setImporteNeto(float importeNeto) {
        this.importeNeto = importeNeto;
    }

    public float getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(float importeTotal) {
        this.importeTotal = importeTotal;
    }

    public Boolean getPagada() {
        return pagada;
    }

    public void setPagada(Boolean pagada) {
        this.pagada = pagada;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public List<ItemFactura> getListaItemsFactura() {
        return listaItemsFactura;
    }

    public void setListaItemsFactura(List<ItemFactura> listaItemsFactura) {
        this.listaItemsFactura = listaItemsFactura;
    }

    public Estadia getEstadia() {
        return estadia;
    }

    public void setEstadia(Estadia estadia) {
        this.estadia = estadia;
    }

    @Override
    public String toString() {
        return "Factura{" + "idFactura=" + idFactura + ", fecha=" + fecha + ", importeNeto=" + importeNeto + ", importeTotal=" + importeTotal + ", pagada=" + pagada + ", persona=" + persona + ", listaItemsFactura=" + listaItemsFactura + ", estadia=" + estadia + '}';
    }

    
    
}
