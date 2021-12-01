
package Dominio;

import java.util.Date;
import java.util.List;

public class FacturaB extends Factura{

    public FacturaB() {
    }

    public FacturaB(int idFactura, Date fecha, float importeNeto, float importeTotal, Boolean pagada) {
        super(idFactura, fecha, importeNeto, importeTotal, pagada);
    }

    public FacturaB(int idFactura, Date fecha, float importeNeto, float importeTotal, Boolean pagada, Persona persona, NotaDeCredito notaCredito) {
        super(idFactura, fecha, importeNeto, importeTotal, pagada, persona, notaCredito);
    }

    public FacturaB(int idFactura, Date fecha, float importeNeto, float importeTotal, Boolean pagada, Persona persona, NotaDeCredito notaCredito, List<ItemFactura> listaItemsFactura, Estadia estadia) {
        super(idFactura, fecha, importeNeto, importeTotal, pagada, persona, notaCredito, listaItemsFactura, estadia);
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

    @Override
    public String toString() {
        return "FacturaB{ Factura { "+ super.toString() +"}";
    }
    
}
