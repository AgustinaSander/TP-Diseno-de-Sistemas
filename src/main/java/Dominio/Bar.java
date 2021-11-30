
package Dominio;

import java.util.Date;
import java.util.List;

public class Bar extends Servicio{
    private ItemBar itemBar;

    public Bar(ItemBar itemBar) {
        this.itemBar = itemBar;
    }

    public Bar(ItemBar itemBar, int idServicio, String descripcion, Date fecha, float precioTotal, int cantidad) {
        super(idServicio, descripcion, fecha, precioTotal, cantidad);
        this.itemBar = itemBar;
    }

    public ItemBar getItemBar() {
        return itemBar;
    }

    public void setItemBar(ItemBar itemBar) {
        this.itemBar = itemBar;
    }

    @Override
    public String toString() {
        return "Bar{" + "itemBar=" + itemBar + "} Servicio{ "+ super.toString() +'}';
    }
    
    
}
