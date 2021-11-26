
package Dominio;

public class TipoPrenda {
    private int idTipoPrenda;
    private String descripcionPrenda;
    private float precioUnitario;

    public TipoPrenda() {
    }

    public TipoPrenda(int idTipoPrenda, String descripcionPrenda, float precioUnitario) {
        this.idTipoPrenda = idTipoPrenda;
        this.descripcionPrenda = descripcionPrenda;
        this.precioUnitario = precioUnitario;
    }

    public int getIdTipoPrenda() {
        return idTipoPrenda;
    }

    public void setIdTipoPrenda(int idTipoPrenda) {
        this.idTipoPrenda = idTipoPrenda;
    }

    public String getDescripcionPrenda() {
        return descripcionPrenda;
    }

    public void setDescripcionPrenda(String descripcionPrenda) {
        this.descripcionPrenda = descripcionPrenda;
    }

    public float getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(float precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    @Override
    public String toString() {
        return "TipoPrenda{" + "idTipoPrenda=" + idTipoPrenda + ", descripcionPrenda=" + descripcionPrenda + ", precioUnitario=" + precioUnitario + '}';
    }
    
    
}
