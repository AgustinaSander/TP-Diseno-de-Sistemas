
package Dominio.DTO;

import java.util.Date;

public class EstadiaDTO {
    private int idHabitacion;
    private Date fechaDesde;
    private Date fechaHasta;

    public EstadiaDTO() {
    }

    public EstadiaDTO(int idHabitacion, Date fechaDesde, Date fechaHasta) {
        this.idHabitacion = idHabitacion;
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
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

    @Override
    public String toString() {
        return "EstadiaDTO{" + "idHabitacion=" + idHabitacion + ", fechaDesde=" + fechaDesde + ", fechaHasta=" + fechaHasta + '}';
    }
    
    
}
