
package Dominio;

import java.util.Date;

public class Cheque extends Pago {
    private String nroCheque;
    private String plaza;
    private Date fechaCobro;
    private Banco banco;

    public Cheque(String nroCheque, String plaza, Date fechaCobro, Banco banco) {
        this.nroCheque = nroCheque;
        this.plaza = plaza;
        this.fechaCobro = fechaCobro;
        this.banco = banco;
    }

    public Cheque(String nroCheque, String plaza, Date fechaCobro, Banco banco, int idPago, float importe, Date fechaPago, float cotizacion, TipoMoneda tipomoneda) {
        super(idPago, importe, fechaPago, cotizacion, tipomoneda);
        this.nroCheque = nroCheque;
        this.plaza = plaza;
        this.fechaCobro = fechaCobro;
        this.banco = banco;
    }

    public Cheque(String nroCheque, String plaza, Date fechaCobro, Banco banco, int idPago, float importe, Date fechaPago, float cotizacion) {
        super(idPago, importe, fechaPago, cotizacion);
        this.nroCheque = nroCheque;
        this.plaza = plaza;
        this.fechaCobro = fechaCobro;
        this.banco = banco;
    }

    public String getNroCheque() {
        return nroCheque;
    }

    public void setNroCheque(String nroCheque) {
        this.nroCheque = nroCheque;
    }

    public String getPlaza() {
        return plaza;
    }

    public void setPlaza(String plaza) {
        this.plaza = plaza;
    }

    public Date getFechaCobro() {
        return fechaCobro;
    }

    public void setFechaCobro(Date fechaCobro) {
        this.fechaCobro = fechaCobro;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    @Override
    public String toString() {
        return "Cheque{" + "nroCheque=" + nroCheque + ", plaza=" + plaza + ", fechaCobro=" + fechaCobro + ", banco=" + banco + '}';
    }
 
    
    
}
