
package Dominio.DTO;

public class HabitacionDTO {
    private int id;
    private String numero;

    public HabitacionDTO(int id, String numero) {
        this.id = id;
        this.numero = numero;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setnumero(String numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "HabitacionDTO{" + "id=" + id + ", numero=" + numero + '}';
    }
    
    
}
