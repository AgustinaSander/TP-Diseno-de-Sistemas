
package Dominio.DTO;

import Dominio.Direccion;
import Enum.PosicionIVA;

public class ResponsableDTO {
    private String razonSocial;
    private int idPersona;
    private String CUIT;
    private PosicionIVA posIva;
    private String telefono;
    private Direccion direccion;

    public ResponsableDTO() {
    }

    public ResponsableDTO(String razonSocial, int idPersona, String CUIT, PosicionIVA posIva, String telefono, Direccion direccion) {
        this.razonSocial = razonSocial;
        this.idPersona = idPersona;
        this.CUIT = CUIT;
        this.posIva = posIva;
        this.telefono = telefono;
        this.direccion = direccion;
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
        return "ResponsableDTO{" + "razonSocial=" + razonSocial + ", idPersona=" + idPersona + ", CUIT=" + CUIT + ", posIva=" + posIva + ", telefono=" + telefono + ", direccion=" + direccion + '}';
    }
    
    
    
}
