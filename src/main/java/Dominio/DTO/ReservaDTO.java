package Dominio.DTO;

import java.util.Date;

public class ReservaDTO {
    private int idReserva;
    private Date fechaDesde;
    private Date fechaHasta;
    private String nombre;
    private String apellido;
    private String telefono;

    public ReservaDTO() {
    }

    public ReservaDTO(int idReserva, Date fechaDesde, Date fechaHasta, String nombre, String apellido, String telefono) {
        this.idReserva = idReserva;
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
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

    public Date getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
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
        return "ReservaDTO{" + "idReserva=" + idReserva + ", fechaDesde=" + fechaDesde + ", fechaHasta=" + fechaHasta + ", nombre=" + nombre + ", apellido=" + apellido + ", telefono=" + telefono + '}';
    }

    
    
}
