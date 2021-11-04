package Dominio;

public class Localidad {
    private int idLocalidad;
    private Provincia provincia;
    private String nombreLocalidad;

    public Localidad(int idLocalidad) {
        this.idLocalidad = idLocalidad;
    }

    public Localidad(int idLocalidad, Provincia provincia, String nombreLocalidad) {
        this.idLocalidad = idLocalidad;
        this.provincia = provincia;
        this.nombreLocalidad = nombreLocalidad;
    }

    public int getIdLocalidad() {
        return idLocalidad;
    }

    public void setIdLocalidad(int idLocalidad) {
        this.idLocalidad = idLocalidad;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    public String getNombreLocalidad() {
        return nombreLocalidad;
    }

    public void setNombreLocalidad(String nombreLocalidad) {
        this.nombreLocalidad = nombreLocalidad;
    }

    @Override
    public String toString() {
        return "Localidad{" + "idLocalidad=" + idLocalidad + ", Provincia=" + provincia + ", nombreLocalidad=" + nombreLocalidad + '}';
    }
    
    
}
