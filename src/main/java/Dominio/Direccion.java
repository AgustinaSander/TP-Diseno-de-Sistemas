
package Dominio;

public class Direccion {
    private int idDireccion;
    private Localidad localidad;
    private String calle;
    private int numero;
    private String departamento;
    private int codigoPostal;
    

    public Direccion(int idDireccion, Localidad localidad, String calle, int numero, String departamento, int codigoPostal) {
        this.idDireccion = idDireccion;
        this.calle = calle;
        this.numero = numero;
        this.departamento = departamento;
        this.codigoPostal = codigoPostal;
        this.localidad = localidad;
    }

    public Direccion(Localidad localidad, String calle, int numero, String departamento, int codigoPostal) {
        this.localidad = localidad;
        this.calle = calle;
        this.numero = numero;
        this.departamento = departamento;
        this.codigoPostal = codigoPostal;
    }

    
    public Direccion() {
    }

    public int getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(int idDireccion) {
        this.idDireccion = idDireccion;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public int getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public Localidad getLocalidad() {
        return localidad;
    }

    public void setIdLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }

    @Override
    public String toString() {
        return "Direccion{" + "idDireccion=" + idDireccion + ", calle=" + calle + ", numero=" + numero + ", departamento=" + departamento + ", codigoPostal=" + codigoPostal + ", Localidad=" + localidad + '}';
    }

    
}
