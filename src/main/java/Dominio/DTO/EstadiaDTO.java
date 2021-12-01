
package Dominio.DTO;

import java.util.Date;
import java.util.List;

public class EstadiaDTO {
    private int idEstadia;
    private int idHabitacion;
    private Date fechaDesde;
    private Date fechaHasta;
    private Float precio;
    private List<PasajeroDTO> listaPasajeros;
    private List<ServicioDTO> listaServicios;
    
    public EstadiaDTO() {
    }

    public EstadiaDTO(int idEstadia, Date fechaDesde, Date fechaHasta) {
        this.idEstadia = idEstadia;
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
    }

    public EstadiaDTO(int idEstadia, int idHabitacion, Date fechaDesde, Date fechaHasta, Float precio) {
        this.idEstadia = idEstadia;
        this.idHabitacion = idHabitacion;
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
        this.precio = precio;
    }

    public int getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(int idHabitacion) {
        this.idHabitacion = idHabitacion;
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

    public int getIdEstadia() {
        return idEstadia;
    }

    public void setIdEstadia(int idEstadia) {
        this.idEstadia = idEstadia;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public List<PasajeroDTO> getListaPasajeros() {
        return listaPasajeros;
    }

    public void setListaPasajeros(List<PasajeroDTO> listaPasajeros) {
        this.listaPasajeros = listaPasajeros;
    }

    @Override
    public String toString() {
        return "EstadiaDTO{" + "idEstadia=" + idEstadia + ", idHabitacion=" + idHabitacion + ", fechaDesde=" + fechaDesde + ", fechaHasta=" + fechaHasta + ", precio=" + precio + ", listaPasajeros=" + listaPasajeros + '}';
    }


    
}
