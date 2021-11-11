
package Dominio.DTO;

import java.util.Date;

public class EstadiaDTO {
    private int idEstadia;
    private Date fechaDesde;
    private Date fechaHasta;

    public EstadiaDTO() {
    }

    public EstadiaDTO(int idEstadia, Date fechaDesde, Date fechaHasta) {
        this.idEstadia = idEstadia;
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
    }

    public int getIdEstadia() {
        return idEstadia;
    }

    public void setIdEstadia(int idEstadia) {
        this.idEstadia = idEstadia;
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
        return "EstadiaDTO{" + "idEstadia=" + idEstadia + ", fechaDesde=" + fechaDesde + ", fechaHasta=" + fechaHasta + '}';
    }
    
    
}
