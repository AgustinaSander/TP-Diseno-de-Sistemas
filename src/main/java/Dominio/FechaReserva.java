
package Dominio;

import java.time.LocalTime;
import java.util.Date;

public class FechaReserva {
    private Date fechaIngreso;
    private LocalTime horaIngreso;
    private Date fechaEgreso;
    private LocalTime horaEgreso;
    private Habitacion habitacion;
    
    public FechaReserva() {
    }

    public FechaReserva(Date fechaIngreso, LocalTime horaIngreso, Date fechaEgreso, LocalTime horaEgreso, Habitacion habitacion) {
        this.fechaIngreso = fechaIngreso;
        this.horaIngreso = horaIngreso;
        this.fechaEgreso = fechaEgreso;
        this.horaEgreso = horaEgreso;
        this.habitacion = habitacion;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public LocalTime getHoraIngreso() {
        return horaIngreso;
    }

    public void setHoraIngreso(LocalTime horaIngreso) {
        this.horaIngreso = horaIngreso;
    }

    public Date getFechaEgreso() {
        return fechaEgreso;
    }

    public void setFechaEgreso(Date fechaEgreso) {
        this.fechaEgreso = fechaEgreso;
    }

    public LocalTime getHoraEgreso() {
        return horaEgreso;
    }

    public void setHoraEgreso(LocalTime horaEgreso) {
        this.horaEgreso = horaEgreso;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    @Override
    public String toString() {
        return "FechaReserva{" + "fechaIngreso=" + fechaIngreso + ", horaIngreso=" + horaIngreso + ", fechaEgreso=" + fechaEgreso + ", horaEgreso=" + horaEgreso + ", habitacion=" + habitacion + '}';
    }


    
}
