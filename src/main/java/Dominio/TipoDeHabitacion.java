
package Dominio;

import java.util.List;

public class TipoDeHabitacion {
    private int idTipoHabitacion;
    private String nombre;
    private Float precio;
    private List<Habitacion> listaHabitaciones;
    
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

    public TipoDeHabitacion(int idTipoHabitacion, String nombre, Float precio, List<Habitacion> listaHabitaciones) {
        this.idTipoHabitacion = idTipoHabitacion;
        this.nombre = nombre;
        this.precio = precio;
        this.listaHabitaciones = listaHabitaciones;
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

    public List<Habitacion> getListaHabitaciones() {
        return listaHabitaciones;
    }

    public void setListaHabitaciones(List<Habitacion> listaHabitaciones) {
        this.listaHabitaciones = listaHabitaciones;
    }

    @Override
    public String toString() {
        return "TipoDeHabitacion{" + "idTipoHabitacion=" + idTipoHabitacion + ", nombre=" + nombre + ", precio=" + precio + ", listaHabitaciones=" + listaHabitaciones + '}';
    }

 
}
