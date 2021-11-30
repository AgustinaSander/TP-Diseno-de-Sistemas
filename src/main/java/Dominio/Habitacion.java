
package Dominio;

public class Habitacion {
    private int idHabitacion;
    private String numeroHabitacion;
    private float precio;

    public Habitacion() {
    }

    public Habitacion(int idHabitacion, String numeroHabitacion, float precio) {
        this.idHabitacion = idHabitacion;
        this.numeroHabitacion = numeroHabitacion;
        this.precio = precio;
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

    @Override
    public String toString() {
        return "Habitacion{" + "idHabitacion=" + idHabitacion + ", numeroHabitacion=" + numeroHabitacion + ", precio=" + precio + '}';
    }
    
}
