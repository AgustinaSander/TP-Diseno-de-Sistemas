
package Dominio.DTO;

import Enum.TipoDocumento;

public class GestionarPasajeroDTO {
    private int id;
    private int idDireccion;
    private String nombre;
    private String apellido;
    private TipoDocumento tipoDoc;
    private String numDoc;

    public GestionarPasajeroDTO() {
    }

    public GestionarPasajeroDTO(String nombre, String apellido, TipoDocumento tipoDoc, String numDoc) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoDoc = tipoDoc;
        this.numDoc = numDoc;
    }

    public GestionarPasajeroDTO(int id, int idDireccion, String nombre, String apellido, TipoDocumento tipoDoc, String numDoc) {
        this.id = id;
        this.idDireccion = idDireccion;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoDoc = tipoDoc;
        this.numDoc = numDoc;
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

    @Override
    public String toString() {
        return "BusquedaDTO{" + "nombre=" + nombre + ", apellido=" + apellido + ", tipoDoc=" + tipoDoc + ", numDoc=" + numDoc + '}';
    }
    
    
}
