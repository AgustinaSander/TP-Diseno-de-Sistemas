
package Dominio;

import Enum.MotivoInhabilitado;
import java.util.Date;

public class Inhabilitado {
    private int idInhabilitado;
    private Date fechaInicio;
    private Date fechaFin;
    private MotivoInhabilitado motivo;
    private Habitacion habitacion;

    public Inhabilitado() {
    }

    public Inhabilitado(int idInhabilitado, Date fechaInicio, Date fechaFin, MotivoInhabilitado motivo, Habitacion habitacion) {
        this.idInhabilitado = idInhabilitado;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.motivo = motivo;
        this.habitacion = habitacion;
    }

    public int getIdInhabilitado() {
        return idInhabilitado;
    }

    public void setIdInhabilitado(int idInhabilitado) {
        this.idInhabilitado = idInhabilitado;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public MotivoInhabilitado getMotivo() {
        return motivo;
    }

    public void setMotivo(MotivoInhabilitado motivo) {
        this.motivo = motivo;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    @Override
    public String toString() {
        return "Inhabilitado{" + "idInhabilitado=" + idInhabilitado + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", motivo=" + motivo + ", habitacion=" + habitacion + '}';
    }

    
    
}
