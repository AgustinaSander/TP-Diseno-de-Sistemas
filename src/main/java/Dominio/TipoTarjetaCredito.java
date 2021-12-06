
package Dominio;

public class TipoTarjetaCredito {
    private int idTipoCredito;
    private String nombre;

    public TipoTarjetaCredito() {
    }

    public TipoTarjetaCredito(int idTipoCredito, String nombre) {
        this.idTipoCredito = idTipoCredito;
        this.nombre = nombre;
    }

    public int getIdTipoCredito() {
        return idTipoCredito;
    }

    public void setIdTipoCredito(int idTipoCredito) {
        this.idTipoCredito = idTipoCredito;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "TipoTarjetaCredito{" + "idTipoCredito=" + idTipoCredito + ", nombre=" + nombre + '}';
    }
    
    
}
