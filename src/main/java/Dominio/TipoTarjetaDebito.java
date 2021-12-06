
package Dominio;

public class TipoTarjetaDebito {
    private int idTipoDebito;
    private String nombre;

    public TipoTarjetaDebito(int idTipoDebito, String nombre) {
        this.idTipoDebito = idTipoDebito;
        this.nombre = nombre;
    }

    public int getIdTipoDebito() {
        return idTipoDebito;
    }

    public void setIdTipoDebito(int idTipoDebito) {
        this.idTipoDebito = idTipoDebito;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "TipoTarjetaDebito{" + "idTipoDebito=" + idTipoDebito + ", nombre=" + nombre + '}';
    }
    
    
}
