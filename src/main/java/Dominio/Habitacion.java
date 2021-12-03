
package Dominio;

public class Habitacion {
    private int idHabitacion;
    private String numeroHabitacion;

    public Habitacion() {
    }

    public Habitacion(int idHabitacion, String numeroHabitacion) {
        this.idHabitacion = idHabitacion;
        this.numeroHabitacion = numeroHabitacion;
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

    @Override
    public String toString() {
        return "Habitacion{" + "idHabitacion=" + idHabitacion + ", numeroHabitacion=" + numeroHabitacion + '}';
    }
    
}
