
package Dominio;

public class Habitacion {
    private int idHabitacion;
    private String numeroHabitacion;
    private float precio;
    private TipoDeHabitacion tipo;

    public Habitacion() {
    }

    public Habitacion(int idHabitacion, String numeroHabitacion, float precio, TipoDeHabitacion tipo) {
        this.idHabitacion = idHabitacion;
        this.numeroHabitacion = numeroHabitacion;
        this.precio = precio;
        this.tipo = tipo;
    }

    public int getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(int idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public String getNumeroHabitacion() {
        return numeroHabitacion;
    }

    public void setNumeroHabitacion(String numeroHabitacion) {
        this.numeroHabitacion = numeroHabitacion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public TipoDeHabitacion getTipo() {
        return tipo;
    }

    public void setTipo(TipoDeHabitacion tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Habitacion{" + "idHabitacion=" + idHabitacion + ", numeroHabitacion=" + numeroHabitacion + ", precio=" + precio + ", tipo=" + tipo + '}';
    }
    
    
}
