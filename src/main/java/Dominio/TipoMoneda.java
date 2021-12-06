
package Dominio;

public class TipoMoneda {
    private int idTipoMoneda;
    private String nombreMoneda;
    private char simbolo;
    private float cotización; 

    public TipoMoneda() {
    }
    
    public TipoMoneda(int idTipoMoneda, String nombreMoneda, char simbolo, float cotización) {
        this.idTipoMoneda = idTipoMoneda;
        this.nombreMoneda = nombreMoneda;
        this.simbolo = simbolo;
        this.cotización = cotización;
    }

    public int getIdTipoMoneda() {
        return idTipoMoneda;
    }

    public void setIdTipoMoneda(int idTipoMoneda) {
        this.idTipoMoneda = idTipoMoneda;
    }

    public String getNombreMoneda() {
        return nombreMoneda;
    }

    public void setNombreMoneda(String nombreMoneda) {
        this.nombreMoneda = nombreMoneda;
    }

    public char getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(char simbolo) {
        this.simbolo = simbolo;
    }

    public float getCotización() {
        return cotización;
    }

    public void setCotización(float cotización) {
        this.cotización = cotización;
    }

    @Override
    public String toString() {
        return "TipoMoneda{" + "idTipoMoneda=" + idTipoMoneda + ", nombreMoneda=" + nombreMoneda + ", simbolo=" + simbolo + ", cotizaci\u00f3n=" + cotización + '}';
    }
    
    
    
}
