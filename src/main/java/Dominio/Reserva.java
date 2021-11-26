
package Dominio;

import java.util.List;

public class Reserva {
    private int idReserva;
    private String nombre;
    private String apellido;
    private String telefono;
    private List<FechaReserva> listaFechaReserva;

    public Reserva() {
    }

    public Reserva(int idReserva, String nombre, String apellido, String telefono) {
        this.idReserva = idReserva;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        
    }

    public Reserva(int idReserva, String nombre, String apellido, String telefono, List<FechaReserva> listaFechaReserva) {
        this.idReserva = idReserva;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.listaFechaReserva = listaFechaReserva;
    }
    
    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<FechaReserva> getListaFechaReserva() {
        return listaFechaReserva;
    }

    public void setListaFechaReserva(List<FechaReserva> listaFechaReserva) {
        this.listaFechaReserva = listaFechaReserva;
    }

    @Override
    public String toString() {
        return "Reserva{" + "idReserva=" + idReserva + ", nombre=" + nombre + ", apellido=" + apellido + ", telefono=" + telefono + ", listaFechaReserva=" + listaFechaReserva + '}';
    }

}
