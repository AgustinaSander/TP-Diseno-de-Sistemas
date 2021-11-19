
package Dominio;

public class OcupadaPor {
    private Pasajero pasajero;
    private Estadia estadia;
    private Boolean esResponsable;

    public OcupadaPor() {
    }

    public OcupadaPor(Pasajero pasajero, Estadia estadia, Boolean esResponsable) {
        this.pasajero = pasajero;
        this.estadia = estadia;
        this.esResponsable = esResponsable;
    }

    public Pasajero getPasajero() {
        return pasajero;
    }

    public void setPasajero(Pasajero pasajero) {
        this.pasajero = pasajero;
    }

    public Estadia getEstadia() {
        return estadia;
    }

    public void setEstadia(Estadia estadia) {
        this.estadia = estadia;
    }

    public Boolean getEsResponsable() {
        return esResponsable;
    }

    public void setEsResponsable(Boolean esResponsable) {
        this.esResponsable = esResponsable;
    }

    @Override
    public String toString() {
        return "OcupadaPor{" + "pasajero=" + pasajero + ", estadia=" + estadia + ", esResponsable=" + esResponsable + '}';
    }
    
    
}
