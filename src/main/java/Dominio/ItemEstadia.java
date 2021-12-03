
package Dominio;

import java.util.Date;

public class ItemEstadia extends ItemFactura{
    private Estadia estadia;
    private Boolean extra;
    
    public ItemEstadia() {
    }

    public ItemEstadia(Estadia estadia, Boolean extra) {
        this.estadia = estadia;
        this.extra = extra;
    }

    public ItemEstadia(Estadia estadia, int idItemFactura, String descripcion, float precioItem, float precioUnitario, int cantidad, Boolean extra) {
        super(idItemFactura, descripcion, precioItem, precioUnitario, cantidad);
        this.estadia = estadia;
        this.extra = extra;
    }

    public Estadia getEstadia() {
        return estadia;
    }

    public void setEstadia(Estadia estadia) {
        this.estadia = estadia;
    }

    public int getIdItemFactura() {
        return idItemFactura;
    }

    public void setIdItemFactura(int idItemFactura) {
        this.idItemFactura = idItemFactura;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPrecioItem() {
        return precioItem;
    }

    public void setPrecioItem(float precioItem) {
        this.precioItem = precioItem;
    }

    public float getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(float precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Boolean getExtra() {
        return extra;
    }

    public void setExtra(Boolean extra) {
        this.extra = extra;
    }

    @Override
    public String toString() {
        return "ItemEstadia{" + "estadia=" + estadia + ", extra=" + extra + '}';
    }

    
   
}
