
package Dominio;

import java.util.Date;
import java.util.List;

public class NotaDeCreditoA extends NotaDeCredito{
    private Float montoIVA;

    public NotaDeCreditoA(Float montoIVA) {
        this.montoIVA = montoIVA;
    }

    public NotaDeCreditoA(Float montoIVA, int idNotaCredito, Date fecha, Float importeNeto, Float importeTotal) {
        super(idNotaCredito, fecha, importeNeto, importeTotal);
        this.montoIVA = montoIVA;
    }

    public NotaDeCreditoA(Float montoIVA, int idNotaCredito, Date fecha, Float importeNeto, Float importeTotal, List<Factura> listaFacturas) {
        super(idNotaCredito, fecha, importeNeto, importeTotal, listaFacturas);
        this.montoIVA = montoIVA;
    }

    
    
    public Float getMontoIVA() {
        return montoIVA;
    }

    public void setMontoIVA(Float montoIVA) {
        this.montoIVA = montoIVA;
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

    @Override
    public String toString() {
        return "NotaDeCreditoA{" + "montoIVA=" + montoIVA + "} NotaDeCredito{ "+ super.toString() +"}";
    }
    
    
}
