
package Dominio;

import java.util.Date;
import java.util.List;

public class LavadoYPlanchado extends Servicio{
    private TipoPrenda tipoPrenda;

    public LavadoYPlanchado() {
    }

    public LavadoYPlanchado(TipoPrenda tipoPrenda) {
        this.tipoPrenda = tipoPrenda;
    }

    public LavadoYPlanchado(TipoPrenda tipoPrenda, int idServicio, String descripcion, Date fecha, float precioTotal, int cantidad) {
        super(idServicio, descripcion, fecha, precioTotal, cantidad);
        this.tipoPrenda = tipoPrenda;
    }
    
    public LavadoYPlanchado(TipoPrenda tipoPrenda, int idServicio, String descripcion, Date fecha, float precioTotal, int cantidad, Estadia estadia, List<ItemServicio> listaItemsServicios) {
        super(idServicio, descripcion, fecha, precioTotal, cantidad, estadia, listaItemsServicios);
        this.tipoPrenda = tipoPrenda;
    }

    public TipoPrenda getTipoPrenda() {
        return tipoPrenda;
    }

    public void setTipoPrenda(TipoPrenda tipoPrenda) {
        this.tipoPrenda = tipoPrenda;
    }

    @Override
    public String toString() {
        return "LavadoYPlanchado{" + "tipoPrenda=" + tipoPrenda + '}';
    }
    
    
}
