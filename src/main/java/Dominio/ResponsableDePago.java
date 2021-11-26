
package Dominio;

import Enum.PosicionIVA;

public class ResponsableDePago extends Persona{
    private String razonSocial;

    public ResponsableDePago() {
    }

    public ResponsableDePago(String razonSocial, int idPersona) {
        super(idPersona);
        this.razonSocial = razonSocial;
    }

    public ResponsableDePago(String razonSocial, int idPersona, String CUIT, PosicionIVA posIva, String telefono, Direccion direccion) {
        super(idPersona, CUIT, posIva, telefono, direccion);
        this.razonSocial = razonSocial;
    }
    
    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getCUIT() {
        return CUIT;
    }

    public void setCUIT(String CUIT) {
        this.CUIT = CUIT;
    }

    public PosicionIVA getPosIva() {
        return posIva;
    }

    public void setPosIva(PosicionIVA posIva) {
        this.posIva = posIva;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "ResponsableDePago{" + "razonSocial=" + razonSocial + "} Persona{ "+ super.toString() +"}";
    }

    
    
}
