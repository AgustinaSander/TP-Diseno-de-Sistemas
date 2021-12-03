
package Dominio.DTO;

public class TipoDeHabitacionDTO {
    private int idTipoHabitacion;
    private String nombre;
    private int capacidad;

    public TipoDeHabitacionDTO(int idTipoHabitacion, String nombre, int capacidad) {
        this.idTipoHabitacion = idTipoHabitacion;
        this.nombre = nombre;
        this.capacidad = capacidad;
    }

    public int getIdTipoHabitacion() {
        return idTipoHabitacion;
    }

    public void setIdTipoHabitacion(int idTipoHabitacion) {
        this.idTipoHabitacion = idTipoHabitacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    @Override
    public String toString() {
        return "TipoDeHabitacionDTO{" + "idTipoHabitacion=" + idTipoHabitacion + ", nombre=" + nombre + ", capacidad=" + capacidad + '}';
    }
           
}
