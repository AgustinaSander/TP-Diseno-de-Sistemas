
package Dominio;

import java.util.Date;
import java.util.List;

public class Sauna extends Servicio{
    private float precioUnitario;

    public Sauna() {
    }

    public Sauna(float precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Sauna(float precioUnitario, int idServicio, String descripcion, Date fecha, float precioTotal, int cantidad) {
        super(idServicio, descripcion, fecha, precioTotal, cantidad);
        this.precioUnitario = precioUnitario;
    }
    
    public Sauna(float precioUnitario, int idServicio, String descripcion, Date fecha, float precioTotal, int cantidad, Estadia estadia, List<ItemServicio> listaItemsServicios) {
        super(idServicio, descripcion, fecha, precioTotal, cantidad, estadia, listaItemsServicios);
        this.precioUnitario = precioUnitario;
    }

    public float getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(float precioUnitario) {
        this.precioUnitario = precioUnitario;
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
        return "Sauna{" + "precioUnitario=" + precioUnitario + '}';
    }
    
    
}
