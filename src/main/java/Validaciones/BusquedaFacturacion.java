
package Validaciones;

public class BusquedaFacturacion {
    private Boolean validos;
    private Boolean nroHabitacion;
    private Boolean hora;

    public BusquedaFacturacion() {
        this.validos = true;
        this.nroHabitacion = true;
        this.hora = true;
    }

    public Boolean getValidos() {
        return validos;
    }

    public void setValidos(Boolean validos) {
        this.validos = validos;
    }

    public Boolean getNroHabitacion() {
        return nroHabitacion;
    }

    public void setNroHabitacion(Boolean nroHabitacion) {
        this.nroHabitacion = nroHabitacion;
    }

    public Boolean getHora() {
        return hora;
    }

    public void setHora(Boolean hora) {
        this.hora = hora;
    }
    
    
}
