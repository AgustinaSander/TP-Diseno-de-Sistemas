
package Paneles;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class PintarCeldas extends DefaultTableCellRenderer{
    private int fila;
    private int col;

    public PintarCeldas() {
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
        switch (table.getValueAt(row, column).toString()){
            case "Ocupada" :
                setBackground(new Color(255, 69, 69));
                break;
            case "Desocupada":
                setBackground(new Color(166, 255, 148));
                break;
            case "Inhabilitada":
                setBackground(new Color(148, 159, 255));
                break;
            case "Reservada":
                setBackground(new Color(255, 240, 138));
                break;
        }
        
        super.setHorizontalAlignment( JLabel.CENTER );
        super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column);
        return this;
    }
    
}
