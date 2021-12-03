
package Paneles;

import Dominio.DTO.ItemDTO;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;


public class ArchivoFactura {
  
    private String tipoFactura;
    private int idFactura;
    private String nombreCliente;
    private String cuit;
    private String posIva;
    private String direccion;
    private String telefono;
    private String email;
    private List<ItemDTO> items;
    
    Document documento;
    FileOutputStream archivo;

    public ArchivoFactura(String tipoFactura, int idFactura, String nombreCliente, String cuit, String posIva, String direccion, String telefono, String email, List<ItemDTO> items) {
        this.tipoFactura = tipoFactura;
        this.idFactura = idFactura;
        this.nombreCliente = nombreCliente;
        this.cuit = cuit;
        this.posIva = posIva;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.items = items;
        
        documento = new Document();
    }
    
    public void crearPlantilla(){
        try {
            archivo = new FileOutputStream("factura-"+this.idFactura+".pdf");
            PdfWriter.getInstance(documento, archivo);
            documento.open();
            Paragraph t = new Paragraph(this.tipoFactura +" - Num: "+this.idFactura+" - Generada el dia: "+new Date());
            t.setAlignment(1);
            Font fuenteTitulo = new Font();
            fuenteTitulo.setStyle(Font.BOLD);
            t.setFont(fuenteTitulo);
                    
            documento.add(t);
            documento.add(new Paragraph("Hotel Premier\nBv. Galvez 1250, Santa Fe, Santa Fe\nwww.hotelpremier.com.ar\n+5493424224287 - hotelpremier@gmail.com"));
            documento.add(Chunk.NEWLINE);
            documento.add(new Paragraph("CLIENTE\n"+this.nombreCliente+"\n"+this.cuit+"\n"+this.posIva+"\n"+this.direccion+"\n"+this.telefono+" - "+this.email));
            
           
            float[] columnWidths = {1.5f, 2f, 5f, 2f};
            
            PdfPTable table = new PdfPTable(columnWidths);
            
            table.setWidthPercentage(90f);

            insertCell(table, "Descripcion", Element.ALIGN_RIGHT, 1, new Font(FontFamily.TIMES_ROMAN, 12, Font.BOLD, new BaseColor(0, 0, 0)));
            insertCell(table, "Unidades", Element.ALIGN_LEFT, 1, new Font(FontFamily.TIMES_ROMAN, 12, Font.BOLD, new BaseColor(0, 0, 0)));
            insertCell(table, "Precio Unitario", Element.ALIGN_LEFT, 1, new Font(FontFamily.TIMES_ROMAN, 12, Font.BOLD, new BaseColor(0, 0, 0)));
            insertCell(table, "Precio", Element.ALIGN_RIGHT, 1, new Font(FontFamily.TIMES_ROMAN, 12, Font.BOLD, new BaseColor(0, 0, 0)));
            table.setHeaderRows(1);
            documento.add(table);
            documento.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        } catch (DocumentException ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    private void insertCell(PdfPTable table, String text, int align, int colspan, Font font){
   
        //create a new cell with the specified Text and Font
        PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font));
        //set the cell alignment
        cell.setHorizontalAlignment(align);
        //set the cell column span in case you want to merge two or more cells
        cell.setColspan(colspan);
        //in case there is no text and you wan to create an empty row
        if(text.trim().equalsIgnoreCase("")){
         cell.setMinimumHeight(10f);
        }
        //add the call to the table
        table.addCell(cell);

   }
 
}
