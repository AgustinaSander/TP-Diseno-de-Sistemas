
package Dominio;

import java.util.List;

public class Reserva {
    private int idReserva;
    private String nombre;
    private String apellido;
    private String telefono;

    public Reserva() {
    }

    public Reserva(int idReserva, String nombre, String apellido, String telefono) {
        this.idReserva = idReserva;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        
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

    
    @Override
    public String toString() {
        return "Reserva{" + "idReserva=" + idReserva + ", nombre=" + nombre + ", apellido=" + apellido + ", telefono=" + telefono + "}";
    }
    
    
}
