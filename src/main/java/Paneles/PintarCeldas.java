
package Paneles;

import java.awt.Color;
import java.awt.Component;
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
                setBackground(Color.RED);
                break;
            case "Desocupada":
                setBackground(Color.GREEN);
                break;
            case "Inhabilitada":
                setBackground(Color.BLUE);
                break;
            case "Reservada":
                setBackground(Color.YELLOW);
                break;
        }
        
        super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column);
        return this;
    }
    
}
