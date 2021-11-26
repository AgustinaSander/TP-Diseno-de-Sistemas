
package Dominio;

public class OcupadaPor {
    private Pasajero pasajero;
    private Boolean esResponsable;

    public OcupadaPor() {
    }

    public OcupadaPor(Pasajero pasajero, Boolean esResponsable) {
        this.pasajero = pasajero;
        this.esResponsable = esResponsable;
    }

    public Pasajero getPasajero() {
        return pasajero;
    }

    public void setPasajero(Pasajero pasajero) {
        this.pasajero = pasajero;
    }

    public Boolean getEsResponsable() {
        return esResponsable;
    }

    public void setEsResponsable(Boolean esResponsable) {
        this.esResponsable = esResponsable;
    }

    @Override
    public String toString() {
        return "OcupadaPor{" + "pasajero=" + pasajero + ", esResponsable=" + esResponsable + '}';
    }

    
    
    
}
