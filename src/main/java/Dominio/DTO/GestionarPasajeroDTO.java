
package Dominio.DTO;

import Enum.TipoDocumento;
import java.util.Date;

public class GestionarPasajeroDTO {
    private int id;
    private int idDireccion;
    private String nombre;
    private String apellido;
    private TipoDocumento tipoDoc;
    private String numDoc;
    private Date fechaNac;
    private Boolean responsableHabitacion;

    public GestionarPasajeroDTO() {
    }

    public GestionarPasajeroDTO(String nombre, String apellido, TipoDocumento tipoDoc, String numDoc) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoDoc = tipoDoc;
        this.numDoc = numDoc;
    }

    public GestionarPasajeroDTO(int id, int idDireccion, String nombre, String apellido, TipoDocumento tipoDoc, String numDoc, Date fechaNac) {
        this.id = id;
        this.idDireccion = idDireccion;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoDoc = tipoDoc;
        this.numDoc = numDoc;
        this.fechaNac = fechaNac;
    }

    
    
    public GestionarPasajeroDTO(int id, int idDireccion, String nombre, String apellido, TipoDocumento tipoDoc, String numDoc, Date fechaNac, Boolean responsable) {
        this.id = id;
        this.idDireccion = idDireccion;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoDoc = tipoDoc;
        this.numDoc = numDoc;
        this.fechaNac = fechaNac;
        this.responsableHabitacion = responsable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(int idDireccion) {
        this.idDireccion = idDireccion;
    }
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
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

    public Boolean getResponsableHabitacion() {
        return responsableHabitacion;
    }

    public void setResponsableHabitacion(Boolean responsableHabitacion) {
        this.responsableHabitacion = responsableHabitacion;
    }

    @Override
    public String toString() {
        return "GestionarPasajeroDTO{" + "id=" + id + ", idDireccion=" + idDireccion + ", nombre=" + nombre + ", apellido=" + apellido + ", tipoDoc=" + tipoDoc + ", numDoc=" + numDoc + ", fechaNac=" + fechaNac + ", responsableHabitacion=" + responsableHabitacion + '}';
    }

    
    
}
