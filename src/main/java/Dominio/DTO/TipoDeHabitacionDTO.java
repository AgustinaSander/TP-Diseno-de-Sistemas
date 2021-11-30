
package Dominio.DTO;

public class TipoDeHabitacionDTO {
    private int idTipoHabitacion;
    private String nombre;

    public TipoDeHabitacionDTO(int idTipoHabitacion, String nombre) {
        this.idTipoHabitacion = idTipoHabitacion;
        this.nombre = nombre;
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

    @Override
    public String toString() {
        return "TipoDeHabitacionDTO{" + "idTipoHabitacion=" + idTipoHabitacion + ", nombre=" + nombre + '}';
    }
    
    
           
}
