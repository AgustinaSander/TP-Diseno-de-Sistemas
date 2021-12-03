
package Paneles;

import Dominio.DTO.EstadiaDTO;
import Dominio.DTO.ItemDTO;
import Dominio.DTO.PersonaDTO;
import static Gestores.GestorEstadias.getInstanceEstadias;
import static Gestores.GestorFacturas.getInstanceFacturas;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class Facturacion extends javax.swing.JDialog {

    private Double montoTotal;
    private Double montoServicio;
    private Double montoEstadia;
    private PersonaDTO pasajero;
    private int idEstadia;
    private List<ItemDTO> itemsEstadia;
    private List<ItemDTO> itemsServicio;
    
    public Facturacion(java.awt.Frame parent, boolean modal, EstadiaDTO estadia, PersonaDTO pasajero, String hora) {
        super(parent, modal);
        
        List<ItemDTO> itemsAFacturar = getInstanceEstadias().obtenerItemsAFacturar(estadia.getIdEstadia(), hora);
        //Si no quedan items por facturar
        if(itemsAFacturar.size() == 0){
            JOptionPane.showMessageDialog(this, "No quedan items por facturar", "Items facturados",JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        }
        
        initComponents();
        this.setTitle("Facturacion");
        this.setLocationRelativeTo(null);
        this.montoTotal = 0.0;
        this.montoServicio = 0.0;
        this.montoEstadia = 0.0;
        this.pasajero = pasajero;
        this.idEstadia = estadia.getIdEstadia();
        
        if(pasajero.getEsPasajero()){
            responsableField.setText(pasajero.getNombre() + " " + pasajero.getApellido());
        }
        else{
            responsableField.setText(pasajero.getRazonSocial());
        }
        
       
        
        //Lista donde voy a guardar solo los items estadia
        List<ItemDTO> itemsEstadia = new ArrayList<>();
        
        //Lista donde voy a guardar solo los items servicio
        List<ItemDTO> itemsServicio = new ArrayList<>();
        
        DefaultTableModel tablaServicio = (DefaultTableModel) tablaServicios.getModel();
        tablaServicio.setRowCount(0);
        DefaultTableModel tablaEstadias = (DefaultTableModel) tablaEstadia.getModel();
        tablaEstadias.setRowCount(0);
        
        for(ItemDTO i : itemsAFacturar){
            if(i.getEsItemServicio()){
                //Recupero los servicios de esa estadia
                itemsServicio.add(i);
                tablaServicio.addRow(new Object[]{i.getFecha(), i.getDescripcion(), i.getCantidad(), i.getMonto()});
            }
            else{
                //Recupero las estadias
                itemsEstadia.add(i);
                tablaEstadias.addRow(new Object[]{i.getDescripcion(), i.getCantidad(), i.getMonto()});
            }
        }
      
        this.itemsEstadia = itemsEstadia;
        this.itemsServicio = itemsServicio;

        tablaServicios.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            
            public void valueChanged(ListSelectionEvent lse) {
                if (!lse.getValueIsAdjusting()) {
                    montoServicio = 0.0;
                    //Obtengo las filas seleccionadas y los montos de las mismas
                    int [] filasSeleccionadas = tablaServicios.getSelectedRows();
                    for(int i=0; i< filasSeleccionadas.length; i++){
                        montoServicio += ((Number) tablaServicios.getValueAt(filasSeleccionadas[i], 3)).doubleValue() * (Integer)tablaServicios.getValueAt(filasSeleccionadas[i], 2);
                    }
                    actualizarMonto();
                }
            }
        });
        
        tablaEstadia.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent lse) {
                if (!lse.getValueIsAdjusting()) {
                    montoEstadia = 0.0;
                    //Obtengo las filas seleccionadas y los montos de las mismas
                    int [] filasSeleccionadas = tablaEstadia.getSelectedRows();
                    for(int i=0; i< filasSeleccionadas.length; i++){
                        montoEstadia += ((Number) tablaEstadia.getValueAt(filasSeleccionadas[i], 2)).doubleValue();
                    }
                    actualizarMonto();
                }
            }
        });    
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        responsableField = new javax.swing.JLabel();
        responsableField1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaEstadia = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        montoField = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaServicios = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cancelarBtn = new javax.swing.JButton();
        aceptarBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        responsableField.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        responsableField.setText("jLabel1");

        responsableField1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        responsableField1.setText("Seleccione los elementos a incluir en la facturacion");

        tablaEstadia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Descripcion", "Cantidad", "Monto"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaEstadia.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tablaEstadia);
        if (tablaEstadia.getColumnModel().getColumnCount() > 0) {
            tablaEstadia.getColumnModel().getColumn(0).setMinWidth(150);
        }

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setText("Monto total acumulado : $");

        montoField.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        montoField.setText("0.00");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setText("Responsable de Pago : ");

        tablaServicios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha", "Descripcion", "Cantidad", "Monto"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaServicios.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tablaServicios);
        if (tablaServicios.getColumnModel().getColumnCount() > 0) {
            tablaServicios.getColumnModel().getColumn(1).setMinWidth(150);
        }

        jLabel3.setText("ESTADIA");

        jLabel4.setText("SERVICIOS");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(124, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(responsableField1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(responsableField, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(42, 42, 42))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 571, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(montoField)
                            .addGap(195, 195, 195))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 571, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(responsableField)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(responsableField1)
                .addGap(4, 4, 4)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(4, 4, 4)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(montoField)))
        );

        cancelarBtn.setText("Cancelar");
        cancelarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarBtnActionPerformed(evt);
            }
        });

        aceptarBtn.setText("Aceptar");
        aceptarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aceptarBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cancelarBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(aceptarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelarBtn)
                    .addComponent(aceptarBtn))
                .addGap(0, 10, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void actualizarMonto(){
        Double montoTotal = montoServicio + montoEstadia;
        montoField.setText(montoTotal.toString());
    }
    
    private void cancelarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarBtnActionPerformed
        Object[] opciones = {"SI","NO"};
        int confirmacion = JOptionPane.showOptionDialog(this, "¿Desea cancelar la facturacion?","Cancelar facturacion",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null,opciones,null);
        if(confirmacion == JOptionPane.OK_OPTION){
            this.dispose();
        }
    }//GEN-LAST:event_cancelarBtnActionPerformed

    private void aceptarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aceptarBtnActionPerformed
        if(tablaEstadia.getSelectedRowCount() + tablaServicios.getSelectedRowCount() == 0){
            JOptionPane.showMessageDialog(this, "Seleccione los items a facturar.", "Seleccion de items",JOptionPane.ERROR_MESSAGE);
        }
        else{
            //Guardo los items seleccionados en una lista
            List<ItemDTO> itemsEstadiaSeleccionados = new ArrayList<>();
            List<ItemDTO> itemsServicioSeleccionados = new ArrayList<>();
            
            //Obtengo los item estadia seleccionados
            int [] filasSeleccionadas = tablaEstadia.getSelectedRows();
            for(int i=0; i < filasSeleccionadas.length; i++){
                itemsEstadiaSeleccionados.add(itemsEstadia.get(filasSeleccionadas[i]));
            }
            
            //Obtengo los item servicio seleccionados
            filasSeleccionadas = tablaServicios.getSelectedRows();
            for(int i=0; i < filasSeleccionadas.length ; i++){
                itemsServicioSeleccionados.add(itemsServicio.get(filasSeleccionadas[i]));
            }

            int idFactura = getInstanceFacturas().crearFactura(itemsEstadiaSeleccionados, itemsServicioSeleccionados, pasajero , idEstadia);
            if(idFactura > 0){
                JOptionPane.showMessageDialog(this, "Factura generada correctamente.", "Factura exitosa",JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(this, "Error al generar la factura.", "Factura no generada",JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_aceptarBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aceptarBtn;
    private javax.swing.JButton cancelarBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel montoField;
    private javax.swing.JLabel responsableField;
    private javax.swing.JLabel responsableField1;
    private javax.swing.JTable tablaEstadia;
    private javax.swing.JTable tablaServicios;
    // End of variables declaration//GEN-END:variables
}
