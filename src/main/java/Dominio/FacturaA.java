
package Dominio;

import java.util.Date;
import java.util.List;

public class FacturaA extends Factura{
    private float montoIVA;

    public FacturaA() {
    }

    public FacturaA(float montoIVA) {
        this.montoIVA = montoIVA;
    }

    public FacturaA(float montoIVA, int idFactura, Date fecha, float importeNeto, float importeTotal, Boolean pagada) {
        super(idFactura, fecha, importeNeto, importeTotal, pagada);
        this.montoIVA = montoIVA;
    }

    public FacturaA(float montoIVA, int idFactura, Date fecha, float importeNeto, float importeTotal, Boolean pagada, Persona persona) {
        super(idFactura, fecha, importeNeto, importeTotal, pagada, persona);
        this.montoIVA = montoIVA;
    }

    public FacturaA(float montoIVA, int idFactura, Date fecha, float importeNeto, float importeTotal, Boolean pagada, Persona persona, NotaDeCredito notaCredito) {
        super(idFactura, fecha, importeNeto, importeTotal, pagada, persona, notaCredito);
        this.montoIVA = montoIVA;
    }

    public float getMontoIVA() {
        return montoIVA;
    }

    public void setMontoIVA(float montoIVA) {
        this.montoIVA = montoIVA;
    }

    @Override
    public String toString() {
        return "FacturaA{" + "montoIVA=" + montoIVA + "} Factura { "+ super.toString() +"}";
    }
    
    
}
