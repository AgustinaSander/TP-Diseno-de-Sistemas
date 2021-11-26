
package Dominio.DTO;

import java.util.Date;
import java.util.List;

public class EstadiaDTO {
    private int idEstadia;
    private int idHabitacion;
    private int idTipoHabitacion;
    private String tipoHabitacion;
    private Date fechaDesde;
    private Date fechaHasta;
    private Float precio;
    private List<PasajeroDTO> listaPasajeros;
    
    public EstadiaDTO() {
    }

    public EstadiaDTO(int idHabitacion, Date fechaDesde, Date fechaHasta) {
        this.idHabitacion = idHabitacion;
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
    }

    public EstadiaDTO(int idEstadia, int idHabitacion, int idTipoHabitacion, String tipoHabitacion, Date fechaDesde, Date fechaHasta, Float precio) {
        this.idEstadia = idEstadia;
        this.idHabitacion = idHabitacion;
        this.idTipoHabitacion = idTipoHabitacion;
        this.tipoHabitacion = tipoHabitacion;
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

    public int getIdTipoHabitacion() {
        return idTipoHabitacion;
    }

    public void setIdTipoHabitacion(int idTipoHabitacion) {
        this.idTipoHabitacion = idTipoHabitacion;
    }

    public String getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(String tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
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
        return "EstadiaDTO{" + "idEstadia=" + idEstadia + ", idHabitacion=" + idHabitacion + ", idTipoHabitacion=" + idTipoHabitacion + ", tipoHabitacion=" + tipoHabitacion + ", fechaDesde=" + fechaDesde + ", fechaHasta=" + fechaHasta + ", precio=" + precio + ", listaPasajeros=" + listaPasajeros + '}';
    }


    
}
