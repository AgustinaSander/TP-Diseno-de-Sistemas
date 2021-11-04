
package Dominio;

import Enum.PosicionIVA;
import Enum.TipoDocumento;
import java.util.Date;

public class Pasajero extends Persona{
    private String apellido;
    private String nombre;
    private TipoDocumento tipoDoc;
    private String numDoc;
    private Date fechaNac;
    private String email;
    private String ocupacion;
    private Pais nacionalidad;

    public Pasajero() {
    }

    public Pasajero(String apellido, String nombre, TipoDocumento tipoDoc, String numDoc, Date fechaNac, String email, String ocupacion, Pais nacionalidad, int idPersona, String CUIT, PosicionIVA posIva, String telefono, Direccion direccion) {
        super(idPersona, CUIT, posIva, telefono, direccion);
        this.apellido = apellido;
        this.nombre = nombre;
        this.tipoDoc = tipoDoc;
        this.numDoc = numDoc;
        this.fechaNac = fechaNac;
        this.email = email;
        this.ocupacion = ocupacion;
        this.nacionalidad = nacionalidad;
    }

    public Pasajero(int idPersona, String apellido, String nombre, TipoDocumento tipoDoc, String numDoc, Date fechaNac, String email, String ocupacion) {
        super(idPersona);
        this.apellido = apellido;
        this.nombre = nombre;
        this.tipoDoc = tipoDoc;
        this.numDoc = numDoc;
        this.fechaNac = fechaNac;
        this.email = email;
        this.ocupacion = ocupacion;
    }

    public Pasajero(String apellido, String nombre, TipoDocumento tipoDoc, String numDoc,  Date fechaNac, String email, String ocupacion, String CUIT, PosicionIVA posIva, String telefono) {
        super(CUIT, posIva, telefono);
        this.apellido = apellido;
        this.nombre = nombre;
        this.tipoDoc = tipoDoc;
        this.numDoc = numDoc;
        this.fechaNac = fechaNac;
        this.email = email;
        this.ocupacion = ocupacion;
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

    public Pais getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(Pais nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    @Override
    public PosicionIVA getPosIva() {
        return super.getPosIva(); 
    }

    @Override
    public String toString() {
        return "Pasajero{" + "apellido=" + apellido + ", nombre=" + nombre + ", tipoDoc=" + tipoDoc + ", numDoc=" + numDoc + ", fechaNac=" + fechaNac + ", email=" + email + ", ocupacion=" + ocupacion + ", nacionalidad=" + nacionalidad + "} Persona{ "+ super.toString() +"}";
    }
}
