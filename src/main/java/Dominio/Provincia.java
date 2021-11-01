
package Dominio;

public class Provincia {
    private int idProvincia;
    private Pais pais;
    private String nombreProvincia;

    public Provincia(int idProvincia, Pais pais, String nombreProvincia) {
        this.idProvincia = idProvincia;
        this.pais = pais;
        this.nombreProvincia = nombreProvincia;
    }

    public int getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(int idProvincia) {
        this.idProvincia = idProvincia;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public String getNombreProvincia() {
        return nombreProvincia;
    }

    public void setNombreProvincia(String nombreProvincia) {
        this.nombreProvincia = nombreProvincia;
    }

    @Override
    public String toString() {
        return "Provincia{" + "idProvincia=" + idProvincia + ", Pais=" + pais + ", nombreProvincia=" + nombreProvincia + '}';
    }
    
    
}
