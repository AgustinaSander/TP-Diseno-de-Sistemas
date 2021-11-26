
package Dominio;

public class ItemFactura {
    private int idItemFactura;
    private Boolean facturado;
    private String descripcion;
    private float precioItem;
    private Factura factura;

    public ItemFactura() {
    }

    public ItemFactura(int idItemFactura, Boolean facturado, String descripcion, float precioItem, Factura factura) {
        this.idItemFactura = idItemFactura;
        this.facturado = facturado;
        this.descripcion = descripcion;
        this.precioItem = precioItem;
        this.factura = factura;
    }

    public int getIdItemFactura() {
        return idItemFactura;
    }

    public void setIdItemFactura(int idItemFactura) {
        this.idItemFactura = idItemFactura;
    }

    public Boolean getFacturado() {
        return facturado;
    }

    public void setFacturado(Boolean facturado) {
        this.facturado = facturado;
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

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    @Override
    public String toString() {
        return "ItemFactura{" + "idItemFactura=" + idItemFactura + ", facturado=" + facturado + ", descripcion=" + descripcion + ", precioItem=" + precioItem + ", factura=" + factura + '}';
    }
    
    
}
