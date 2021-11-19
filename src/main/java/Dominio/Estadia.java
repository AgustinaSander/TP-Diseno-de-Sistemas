
package Dominio;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public class Estadia {
    private int idEstadia;
    private Date fechaIngreso;
    private LocalTime horaIngreso;
    private Date fechaEgreso;
    private LocalTime horaEgreso;
    private Habitacion habitacion;
    private List<OcupadaPor> listaOcupadaPor;
    
    public Estadia() {
    }

    public Estadia(int idEstadia, Date fechaIngreso, LocalTime horaIngreso, Date fechaEgreso, LocalTime horaEgreso, Habitacion habitacion) {
        this.idEstadia = idEstadia;
        this.fechaIngreso = fechaIngreso;
        this.horaIngreso = horaIngreso;
        this.fechaEgreso = fechaEgreso;
        this.horaEgreso = horaEgreso;
        this.habitacion = habitacion;
    }

    public Estadia(int idEstadia, Date fechaIngreso, LocalTime horaIngreso, Date fechaEgreso, LocalTime horaEgreso, Habitacion habitacion, List<OcupadaPor> listaOcupadaPor) {
        this.idEstadia = idEstadia;
        this.fechaIngreso = fechaIngreso;
        this.horaIngreso = horaIngreso;
        this.fechaEgreso = fechaEgreso;
        this.horaEgreso = horaEgreso;
        this.habitacion = habitacion;
        this.listaOcupadaPor = listaOcupadaPor;
    }

    
    
    public int getIdEstadia() {
        return idEstadia;
    }

    public void setIdEstadia(int idEstadia) {
        this.idEstadia = idEstadia;
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

    public List<OcupadaPor> getListaOcupadaPor() {
        return listaOcupadaPor;
    }

    public void setListaOcupadaPor(List<OcupadaPor> listaOcupadaPor) {
        this.listaOcupadaPor = listaOcupadaPor;
    }

    @Override
    public String toString() {
        return "Estadia{" + "idEstadia=" + idEstadia + ", fechaIngreso=" + fechaIngreso + ", horaIngreso=" + horaIngreso + ", fechaEgreso=" + fechaEgreso + ", horaEgreso=" + horaEgreso + ", habitacion=" + habitacion + ", listaOcupadaPor=" + listaOcupadaPor + '}';
    }

    

  
    
    
}
