
package Dominio;

public class ItemBar {
    private int idItemBar;
    private String descripcionBar;
    private float precioUnitario;

    public ItemBar() {
    }
    
    public ItemBar(int idItemBar, String descripcionBar, float precioUnitario) {
        this.idItemBar = idItemBar;
        this.descripcionBar = descripcionBar;
        this.precioUnitario = precioUnitario;
    }

    public int getIdItemBar() {
        return idItemBar;
    }

    public void setIdItemBar(int idItemBar) {
        this.idItemBar = idItemBar;
    }

    public String getDescripcionBar() {
        return descripcionBar;
    }

    public void setDescripcionBar(String descripcionBar) {
        this.descripcionBar = descripcionBar;
    }

    public float getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(float precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    @Override
    public String toString() {
        return "ItemBar{" + "idItemBar=" + idItemBar + ", descripcionBar=" + descripcionBar + ", precioUnitario=" + precioUnitario + '}';
    }
    
    
}
