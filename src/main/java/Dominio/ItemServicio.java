
package Dominio;

public class ItemServicio extends ItemFactura{
    private int cantidad;
    private float precioUnitario;
    private Servicio servicio;

    public ItemServicio() {
    }

    public ItemServicio(int cantidad, float precioUnitario, Servicio servicio) {
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.servicio = servicio;
    }

    public ItemServicio(int cantidad, float precioUnitario, Servicio servicio, int idItemFactura, String descripcion, float precioItem) {
        super(idItemFactura, descripcion, precioItem);
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.servicio = servicio;
    }

   

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(float precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    @Override
    public String toString() {
        return "ItemServicio{" + "cantidad=" + cantidad + ", precioUnitario=" + precioUnitario + ", servicio=" + servicio + '}';
    }
    
    
}
