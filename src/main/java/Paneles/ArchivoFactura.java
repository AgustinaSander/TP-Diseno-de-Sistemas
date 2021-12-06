package Paneles;

import Dominio.Factura;
import Dominio.FacturaA;
import Dominio.ItemFactura;
import Dominio.Pasajero;
import Dominio.Persona;
import Dominio.ResponsableDePago;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ArchivoFactura {

    private Factura f;
    private Persona cliente;
    private List<ItemFactura> items;

    Document documento;
    FileOutputStream archivo;

    public ArchivoFactura(Factura f, Persona cliente, List<ItemFactura> items) {
        this.f = f;
        this.cliente = cliente;
        this.items = items;

        documento = new Document();
    }

    public void crearPlantilla() {
        try {
            String nombreArchivo = "factura-" + f.getIdFactura() + ".pdf";
            archivo = new FileOutputStream(nombreArchivo);
            PdfWriter.getInstance(documento, archivo);
            documento.open();

            Paragraph t = null;
            if (f instanceof FacturaA) {
                t = new Paragraph("Factura A - Num: " + f.getIdFactura() + " - Generada el dia: " + new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
            } else {
                t = new Paragraph("Factura B - Num: " + f.getIdFactura() + " - Generada el dia: " + new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
            }
            t.setAlignment(1);
            Font fuenteTitulo = new Font();
            fuenteTitulo.setStyle(Font.NORMAL);
            t.setFont(fuenteTitulo);

            documento.add(t);
            documento.add(new Paragraph("Hotel Premier\nBv. Galvez 1250, Santa Fe, Santa Fe\nwww.hotelpremier.com.ar\n+5493424224287 - hotelpremier@gmail.com"));
            documento.add(Chunk.NEWLINE);

            String dir = cliente.getDireccion().getCalle() + " " + cliente.getDireccion().getNumero() + " " + cliente.getDireccion().getDepartamento() + ", " + cliente.getDireccion().getLocalidad().getNombreLocalidad() + ", " + cliente.getDireccion().getLocalidad().getProvincia().getNombreProvincia() + ", " + cliente.getDireccion().getLocalidad().getProvincia().getPais().getNombrePais();

            if (cliente instanceof Pasajero) {
                documento.add(new Paragraph("CLIENTE\n" + ((Pasajero) cliente).getNombre() + " " + ((Pasajero) cliente).getApellido() + "\n" + cliente.getCUIT() + "\n" + cliente.getPosIva() + "\n" + dir + "\n" + cliente.getTelefono() + " - " + ((Pasajero) cliente).getEmail()));
            } else {
                documento.add(new Paragraph("CLIENTE\n" + ((ResponsableDePago) cliente).getRazonSocial() + "\n" + cliente.getCUIT() + "\n" + cliente.getPosIva() + "\n" + dir + "\n" + cliente.getTelefono()));
            }

            float[] columnWidths = {7f, 2f, 2f, 2f};

            PdfPTable table = new PdfPTable(columnWidths);

            table.setWidthPercentage(90f);
            documento.add(Chunk.NEWLINE);
            documento.add(Chunk.NEWLINE);
            insertCell(table, "Descripcion", Element.ALIGN_LEFT, 1, fuenteTitulo);
            insertCell(table, "Unidades", Element.ALIGN_LEFT, 1, fuenteTitulo);
            insertCell(table, "Precio Unitario", Element.ALIGN_LEFT, 1, fuenteTitulo);
            insertCell(table, "Precio", Element.ALIGN_RIGHT, 1, fuenteTitulo);
            table.setHeaderRows(1);

            for (ItemFactura i : items) {
                insertCell(table, i.getDescripcion(), Element.ALIGN_LEFT, 1, fuenteTitulo);
                insertCell(table, String.valueOf(i.getCantidad()), Element.ALIGN_LEFT, 1, fuenteTitulo);
                insertCell(table, String.valueOf(i.getPrecioUnitario()), Element.ALIGN_LEFT, 1, fuenteTitulo);
                insertCell(table, String.valueOf(i.getPrecioItem()), Element.ALIGN_RIGHT, 1, fuenteTitulo);
            }
            insertCell(table, " ", Element.ALIGN_LEFT, 1, fuenteTitulo);
            insertCell(table, " ", Element.ALIGN_LEFT, 1, fuenteTitulo);
            insertCell(table, "Total parcial", Element.ALIGN_LEFT, 1, fuenteTitulo);
            insertCell(table, String.valueOf(f.getImporteNeto()), Element.ALIGN_RIGHT, 1, fuenteTitulo);

            insertCell(table, " ", Element.ALIGN_LEFT, 1, fuenteTitulo);
            insertCell(table, " ", Element.ALIGN_LEFT, 1, fuenteTitulo);
            insertCell(table, "IVA", Element.ALIGN_LEFT, 1, fuenteTitulo);
            if (f instanceof FacturaA) {
                insertCell(table, "21.00%", Element.ALIGN_RIGHT, 1, fuenteTitulo);
                insertCell(table, " ", Element.ALIGN_LEFT, 1, fuenteTitulo);
                insertCell(table, " ", Element.ALIGN_LEFT, 1, fuenteTitulo);
                insertCell(table, "Total impuestos", Element.ALIGN_LEFT, 1, fuenteTitulo);
                insertCell(table, String.valueOf(f.getImporteTotal() - f.getImporteNeto()), Element.ALIGN_RIGHT, 1, fuenteTitulo);
            } else {
                insertCell(table, "0.00%", Element.ALIGN_RIGHT, 1, fuenteTitulo);
                insertCell(table, " ", Element.ALIGN_LEFT, 1, fuenteTitulo);
                insertCell(table, " ", Element.ALIGN_LEFT, 1, fuenteTitulo);
                insertCell(table, "Total impuestos", Element.ALIGN_LEFT, 1, fuenteTitulo);
                insertCell(table, "0.00", Element.ALIGN_RIGHT, 1, fuenteTitulo);
            }
            insertCell(table, " ", Element.ALIGN_LEFT, 1, fuenteTitulo);
            insertCell(table, " ", Element.ALIGN_LEFT, 1, fuenteTitulo);
            insertCell(table, "TOTAL FACTURA", Element.ALIGN_LEFT, 1, fuenteTitulo);
            insertCell(table, String.valueOf(f.getImporteTotal()), Element.ALIGN_RIGHT, 1, fuenteTitulo);

            documento.add(table);

            documento.close();
            
            //Abro el archivo generado
            abrirarchivo("D:/Agustina/tpDisenoSistemas-main"+"/"+"factura-" + f.getIdFactura() + ".pdf");
            
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        } catch (DocumentException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public void abrirarchivo(String archivo) {
        try {
            File objetofile = new File(archivo);
            Desktop.getDesktop().open(objetofile);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    private void insertCell(PdfPTable table, String text, int align, int colspan, Font font) {
        //create a new cell with the specified Text and Font
        PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font));
        //set the cell alignment
        cell.setHorizontalAlignment(align);
        //set the cell column span in case you want to merge two or more cells
        cell.setColspan(colspan);
        //in case there is no text and you wan to create an empty row
        if (text.trim().equalsIgnoreCase("")) {
            cell.setMinimumHeight(10f);
        }
        //add the call to the table
        table.addCell(cell);
    }

}
