
package Dominio;

import java.util.List;

public class Habitacion {
    private int idHabitacion;
    private String numeroHabitacion;
    private float precio;
    private TipoDeHabitacion tipo;
    private List<FechaReserva> listaFechaReserva;
    private List<Inhabilitado> listaInhabilitados;
    private List<Estadia> listaEstadias;
    
    public Habitacion() {
    }

    public Habitacion(int idHabitacion, String numeroHabitacion, float precio, TipoDeHabitacion tipo) {
        this.idHabitacion = idHabitacion;
        this.numeroHabitacion = numeroHabitacion;
        this.precio = precio;
        this.tipo = tipo;
    }

    public Habitacion(int idHabitacion, String numeroHabitacion, float precio, TipoDeHabitacion tipo, List<FechaReserva> listaFechaReserva) {
        this.idHabitacion = idHabitacion;
        this.numeroHabitacion = numeroHabitacion;
        this.precio = precio;
        this.tipo = tipo;
        this.listaFechaReserva = listaFechaReserva;
    }

    public Habitacion(int idHabitacion, String numeroHabitacion, float precio, TipoDeHabitacion tipo, List<FechaReserva> listaFechaReserva, List<Inhabilitado> listaInhabilitados, List<Estadia> listaEstadias) {
        this.idHabitacion = idHabitacion;
        this.numeroHabitacion = numeroHabitacion;
        this.precio = precio;
        this.tipo = tipo;
        this.listaFechaReserva = listaFechaReserva;
        this.listaInhabilitados = listaInhabilitados;
        this.listaEstadias = listaEstadias;
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

    public List<FechaReserva> getListaFechaReserva() {
        return listaFechaReserva;
    }

    public void setListaFechaReserva(List<FechaReserva> listaFechaReserva) {
        this.listaFechaReserva = listaFechaReserva;
    }

    public List<Inhabilitado> getListaInhabilitados() {
        return listaInhabilitados;
    }

    public void setListaInhabilitados(List<Inhabilitado> listaInhabilitados) {
        this.listaInhabilitados = listaInhabilitados;
    }

    public List<Estadia> getListaEstadias() {
        return listaEstadias;
    }

    public void setListaEstadias(List<Estadia> listaEstadias) {
        this.listaEstadias = listaEstadias;
    }

    @Override
    public String toString() {
        return "Habitacion{" + "idHabitacion=" + idHabitacion + ", numeroHabitacion=" + numeroHabitacion + ", precio=" + precio + ", tipo=" + tipo + ", listaFechaReserva=" + listaFechaReserva + ", listaInhabilitados=" + listaInhabilitados + ", listaEstadias=" + listaEstadias + '}';
    }

    
   
    
    
    
}
