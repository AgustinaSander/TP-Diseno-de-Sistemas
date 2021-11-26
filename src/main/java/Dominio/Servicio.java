
package Dominio;

import java.util.Date;
import java.util.List;

public abstract class Servicio {
    protected int idServicio;
    protected String descripcion;
    protected Date fecha;
    protected float precioTotal;
    protected int cantidad;
    protected Estadia estadia;
    protected List<ItemServicio> listaItemsServicios;

    public Servicio(int idServicio, String descripcion, Date fecha, float precioTotal, int cantidad) {
        this.idServicio = idServicio;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.precioTotal = precioTotal;
        this.cantidad = cantidad;
    }
    
    public Servicio(int idServicio, String descripcion, Date fecha, float precioTotal, int cantidad, Estadia estadia, List<ItemServicio> listaItemsServicios) {
        this.idServicio = idServicio;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.precioTotal = precioTotal;
        this.cantidad = cantidad;
        this.estadia = estadia;
        this.listaItemsServicios = listaItemsServicios;
    }

    public Servicio() {
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public float getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(float precioTotal) {
        this.precioTotal = precioTotal;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Estadia getEstadia() {
        return estadia;
    }

    public void setEstadia(Estadia estadia) {
        this.estadia = estadia;
    }

    public List<ItemServicio> getListaItemsServicios() {
        return listaItemsServicios;
    }

    public void setListaItemsServicios(List<ItemServicio> listaItemsServicios) {
        this.listaItemsServicios = listaItemsServicios;
    }

    @Override
    public String toString() {
        return "Servicio{" + "idServicio=" + idServicio + ", descripcion=" + descripcion + ", fecha=" + fecha + ", precioTotal=" + precioTotal + ", cantidad=" + cantidad + ", estadia=" + estadia + ", listaItemsServicios=" + listaItemsServicios + '}';
    }
    
    
}
