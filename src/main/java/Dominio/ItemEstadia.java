
package Dominio;

import java.util.Date;

public class ItemEstadia extends ItemServicio{
    private Date desde;
    private Date hasta;
    private Estadia estadia;

    public ItemEstadia() {
    }

    public ItemEstadia(Date desde, Date hasta, Estadia estadia) {
        this.desde = desde;
        this.hasta = hasta;
        this.estadia = estadia;
    }

    public Date getDesde() {
        return desde;
    }

    public void setDesde(Date desde) {
        this.desde = desde;
    }

    public Date getHasta() {
        return hasta;
    }

    public void setHasta(Date hasta) {
        this.hasta = hasta;
    }

    public Estadia getEstadia() {
        return estadia;
    }

    public void setEstadia(Estadia estadia) {
        this.estadia = estadia;
    }

    @Override
    public String toString() {
        return "ItemEstadia{" + "desde=" + desde + ", hasta=" + hasta + ", estadia=" + estadia + '}';
    }
    
    
}
