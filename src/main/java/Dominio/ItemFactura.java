
package Dominio;

public abstract class ItemFactura {
    protected int idItemFactura;
    protected String descripcion;
    protected float precioItem;

    public ItemFactura() {
    }

    public ItemFactura(int idItemFactura, String descripcion, float precioItem) {
        this.idItemFactura = idItemFactura;
        this.descripcion = descripcion;
        this.precioItem = precioItem;
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

    @Override
    public String toString() {
        return "ItemFactura{" + "idItemFactura=" + idItemFactura + ", descripcion=" + descripcion + ", precioItem=" + precioItem + '}';
    }
    
    
}
