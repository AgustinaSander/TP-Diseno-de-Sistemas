
package Dominio.DTO;

import java.util.Date;

public class ItemDTO {
    private int idServicio;
    private Boolean esItemServicio;
    private Boolean esItemEstadia;
    private Boolean esItemExtra;
    private Date fecha;
    private String descripcion;
    private int cantidad;
    private Float monto;

    public ItemDTO(int idServicio, Boolean esItemServicio, Boolean esItemEstadia, Boolean esItemExtra, Date fecha, String descripcion, int cantidad, Float monto) {
        this.esItemServicio = esItemServicio;
        this.esItemEstadia = esItemEstadia;
        this.esItemExtra = esItemExtra;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.monto = monto;
        this.idServicio = idServicio;
    }

    public Boolean getEsItemServicio() {
        return esItemServicio;
    }

    public void setEsItemServicio(Boolean esItemServicio) {
        this.esItemServicio = esItemServicio;
    }

    public Boolean getEsItemEstadia() {
        return esItemEstadia;
    }

    public void setEsItemEstadia(Boolean esItemEstadia) {
        this.esItemEstadia = esItemEstadia;
    }

    public Boolean getEsItemExtra() {
        return esItemExtra;
    }

    public void setEsItemExtra(Boolean esItemExtra) {
        this.esItemExtra = esItemExtra;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Float getMonto() {
        return monto;
    }

    public void setMonto(Float monto) {
        this.monto = monto;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    @Override
    public String toString() {
        return "ItemDTO{" + "idServicio=" + idServicio + ", esItemServicio=" + esItemServicio + ", esItemEstadia=" + esItemEstadia + ", esItemExtra=" + esItemExtra + ", fecha=" + fecha + ", descripcion=" + descripcion + ", cantidad=" + cantidad + ", monto=" + monto + '}';
    }
 
}
