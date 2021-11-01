
package Dominio;

public class Pais {
    private int idPais;
    private String nombrePais;
    private String nacionalidad;

    public Pais() {
    }

    public Pais(int idPais, String nombrePais, String nacionalidad) {
        this.idPais = idPais;
        this.nombrePais = nombrePais;
        this.nacionalidad = nacionalidad;
    }

    public int getIdPais() {
        return idPais;
    }

    public void setIdPais(int idPais) {
        this.idPais = idPais;
    }

    public String getNombrePais() {
        return nombrePais;
    }

    public void setNombrePais(String nombrePais) {
        this.nombrePais = nombrePais;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    @Override
    public String toString() {
        return "Pais{" + "idPais=" + idPais + ", nombrePais=" + nombrePais + ", nacionalidad=" + nacionalidad + '}';
    }
    
    
}
