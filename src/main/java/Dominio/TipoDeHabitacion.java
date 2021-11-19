
package Dominio;

public class TipoDeHabitacion {
    private int idTipoHabitacion;
    private String nombre;
    private Float precio;

    public TipoDeHabitacion() {
    }

    public TipoDeHabitacion(int idTipoHabitacion) {
        this.idTipoHabitacion = idTipoHabitacion;
    }

    public TipoDeHabitacion(int idTipoHabitacion, String nombre, Float precio) {
        this.idTipoHabitacion = idTipoHabitacion;
        this.nombre = nombre;
        this.precio = precio;
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

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "TipoDeHabitacion{" + "idTipoHabitacion=" + idTipoHabitacion + ", nombre=" + nombre + ", precio=" + precio + '}';
    }
    
    
}
