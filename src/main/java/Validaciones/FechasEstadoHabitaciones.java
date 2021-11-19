
package Validaciones;

public class FechasEstadoHabitaciones {
    private Boolean validos;
    private Boolean fechaHastaValido;
    private Boolean fechaDesdeValido;
    private Boolean desdeMenorAHasta;

    public FechasEstadoHabitaciones() {
        this.validos = true;
        this.fechaHastaValido = true;
        this.fechaDesdeValido = true;
        this.desdeMenorAHasta = true;
    }

    public Boolean getValidos() {
        return validos;
    }

    public void setValidos(Boolean validos) {
        this.validos = validos;
    }

    public Boolean getFechaHastaValido() {
        return fechaHastaValido;
    }

    public void setFechaHastaValido(Boolean fechaHastaValido) {
        this.fechaHastaValido = fechaHastaValido;
    }

    public Boolean getFechaDesdeValido() {
        return fechaDesdeValido;
    }

    public void setFechaDesdeValido(Boolean fechaDesdeValido) {
        this.fechaDesdeValido = fechaDesdeValido;
    }

    public Boolean getDesdeMenorAHasta() {
        return desdeMenorAHasta;
    }

    public void setDesdeMenorAHasta(Boolean desdeMenorAHasta) {
        this.desdeMenorAHasta = desdeMenorAHasta;
    }
    
}
