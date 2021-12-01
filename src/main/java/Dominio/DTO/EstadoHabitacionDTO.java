
package Dominio.DTO;

import java.util.Date;

public class EstadoHabitacionDTO {
    private int idEstado;
    private Date fecha;
    private String estado;

    public EstadoHabitacionDTO(int idEstado, Date fecha, String estado) {
        this.idEstado = idEstado;
        this.fecha = fecha;
        this.estado = estado;
    }

    public EstadoHabitacionDTO(Date fecha) {
        this.fecha = fecha;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "EstadosHabitacionesDTO{" + "idEstado=" + idEstado + ", fecha=" + fecha + ", estado=" + estado + '}';
    }
    
    
    
}
