
package Dominio;

public class ItemServicio extends ItemFactura{
    private Servicio servicio;

    public ItemServicio() {
    }

    public ItemServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public ItemServicio(Servicio servicio, int idItemFactura, String descripcion, float precioItem, float precioUnitario, int cantidad) {
        super(idItemFactura, descripcion, precioItem, precioUnitario, cantidad);
        this.servicio = servicio;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
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

    @Override
    public String toString() {
        return "ItemServicio{" + "servicio=" + servicio + '}';
    }

}
