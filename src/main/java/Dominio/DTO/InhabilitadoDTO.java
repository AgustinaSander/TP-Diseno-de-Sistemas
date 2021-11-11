
package Dominio.DTO;

import java.util.Date;

public class InhabilitadoDTO {
    private int idInhabilitado;
    private Date fechaDesde;
    private Date fechaHasta;

    public InhabilitadoDTO(int idInhabilitado, Date fechaDesde, Date fechaHasta) {
        this.idInhabilitado = idInhabilitado;
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
    }

    public InhabilitadoDTO() {
    }

    public int getIdInhabilitado() {
        return idInhabilitado;
    }

    public void setIdInhabilitado(int idInhabilitado) {
        this.idInhabilitado = idInhabilitado;
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
        return "InhabilitadoDTO{" + "idInhabilitado=" + idInhabilitado + ", fechaDesde=" + fechaDesde + ", fechaHasta=" + fechaHasta + '}';
    }
    
    
}
