
package Dominio;

import Enum.PosicionIVA;
import Enum.TipoDocumento;
import java.util.Date;

public class PasajeroDTO {
    private String apellido;
    private String nombre;
    private TipoDocumento tipoDoc;
    private String numDoc;
    private Date fechaNac;
    private String email;
    private String ocupacion;
    private String nacionalidad;
    private String CUIT;
    private PosicionIVA posIva;
    private String telefono;
    private String pais;
    private String provincia;
    private String localidad;
    private String calle;
    private String numero;
    private String departamento;
    private String codigoPostal;
    private int idPersona;
    private int idDireccion;

    public PasajeroDTO(String apellido, String nombre, TipoDocumento tipoDoc, String numDoc, Date fechaNac, String email, String ocupacion, String nacionalidad, String CUIT, PosicionIVA posIva, String telefono, String pais, String provincia, String localidad, String calle, String numero, String departamento, String codigoPostal) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.tipoDoc = tipoDoc;
        this.numDoc = numDoc;
        this.fechaNac = fechaNac;
        this.email = email;
        this.ocupacion = ocupacion;
        this.nacionalidad = nacionalidad;
        this.CUIT = CUIT;
        this.posIva = posIva;
        this.telefono = telefono;
        this.pais = pais;
        this.provincia = provincia;
        this.localidad = localidad;
        this.calle = calle;
        this.numero = numero;
        this.departamento = departamento;
        this.codigoPostal = codigoPostal;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public int getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(int idDireccion) {
        this.idDireccion = idDireccion;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoDocumento getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(TipoDocumento tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public String getNumDoc() {
        return numDoc;
    }

    public void setNumDoc(String numDoc) {
        this.numDoc = numDoc;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
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

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    @Override
    public String toString() {
        return "PasajeroDTO{" + "apellido=" + apellido + ", nombre=" + nombre + ", tipoDoc=" + tipoDoc + ", numDoc=" + numDoc + ", fechaNac=" + fechaNac + ", email=" + email + ", ocupacion=" + ocupacion + ", nacionalidad=" + nacionalidad + ", CUIT=" + CUIT + ", posIva=" + posIva + ", telefono=" + telefono + ", pais=" + pais + ", provincia=" + provincia + ", localidad=" + localidad + ", calle=" + calle + ", numero=" + numero + ", departamento=" + departamento + ", codigoPostal=" + codigoPostal + '}';
    }
    
    
}
