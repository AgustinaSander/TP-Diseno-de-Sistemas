
package Dominio;

public abstract class ItemFactura {
    protected int idItemFactura;
    protected String descripcion;
    protected float precioItem;
    protected float precioUnitario;
    protected int cantidad;

    public ItemFactura() {
    }

    public ItemFactura(int idItemFactura, String descripcion, float precioItem, float precioUnitario, int cantidad) {
        this.idItemFactura = idItemFactura;
        this.descripcion = descripcion;
        this.precioItem = precioItem;
        this.precioUnitario = precioUnitario;
        this.cantidad = cantidad;
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
        return "ItemFactura{" + "idItemFactura=" + idItemFactura + ", descripcion=" + descripcion + ", precioItem=" + precioItem + ", precioUnitario=" + precioUnitario + ", cantidad=" + cantidad + '}';
    }

   
    
}
