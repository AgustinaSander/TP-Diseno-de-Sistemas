
package Paneles;

import Dominio.DTO.EstadiaDTO;
import Dominio.DTO.GestionarPasajeroDTO;
import Enum.TipoDocumento;
import static Gestores.GestorEstadias.getInstanceEstadias;
import static Gestores.GestorPasajero.*;
import static Validaciones.Validaciones.calcularEdad;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class BusquedaPasajerosOcupantes extends javax.swing.JDialog {
    private List<GestionarPasajeroDTO> resPasajeros = null;
    private EstadiaDTO estadia;
    
    public BusquedaPasajerosOcupantes(java.awt.Frame parent, boolean modal, EstadiaDTO estadiaDTO) {
        super(parent, modal);
        
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Busqueda de Pasajeros Ocupantes");
        this.estadia = estadiaDTO;
        
        //Cargo los valores del Tipo de Documento
        tipoDocCombo.removeAllItems();
        tipoDocCombo.addItem("Seleccionar");
        TipoDocumento[] tipoDocList = TipoDocumento.values();
        Arrays.sort(tipoDocList);
        for(TipoDocumento tipoDoc: tipoDocList){
            tipoDocCombo.addItem(tipoDoc.name());
        }
        tipoDocCombo.setSelectedItem("Seleccionar");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        apellidoField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tipoDocCombo = new javax.swing.JComboBox<>();
        nombreField = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        numDocField = new javax.swing.JTextField();
        buscarBtn = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        resultadosTabla = new javax.swing.JTable();
        cancelarBtn = new javax.swing.JButton();
        aceptarBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel1.setText("Busqueda de pasajero");

        jLabel2.setText("Apellido");

        apellidoField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apellidoFieldActionPerformed(evt);
            }
        });

        jLabel4.setText("<html>Tipo de<br>documento</html>");

        nombreField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreFieldActionPerformed(evt);
            }
        });

        jLabel21.setText("<html>Numero de <br> documento</html>");

        jLabel3.setText("Nombres");

        numDocField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numDocFieldActionPerformed(evt);
            }
        });

        buscarBtn.setText("Buscar");
        buscarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarBtnActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel5.setText("Resultado de busqueda");

        resultadosTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Apellido", "Nombres", "Tipo de documento", "Numero de documento", "Responsable de habitacion"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        resultadosTabla.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        resultadosTabla.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(resultadosTabla);
        if (resultadosTabla.getColumnModel().getColumnCount() > 0) {
            resultadosTabla.getColumnModel().getColumn(4).setMinWidth(100);
        }

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
            .addGroup(layout.createSequentialGroup()
                .addGap(358, 358, 358)
                .addComponent(jLabel3)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(487, 487, 487)
                        .addComponent(cancelarBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(buscarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(aceptarBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                                .addGap(25, 25, 25))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tipoDocCombo, 0, 181, Short.MAX_VALUE)
                            .addComponent(apellidoField))
                        .addGap(26, 26, 26)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nombreField)
                            .addComponent(numDocField))
                        .addGap(26, 26, 26))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 705, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(apellidoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nombreField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(tipoDocCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(numDocField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buscarBtn)
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelarBtn)
                    .addComponent(aceptarBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void apellidoFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apellidoFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_apellidoFieldActionPerformed

    private void nombreFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreFieldActionPerformed

    private void numDocFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numDocFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_numDocFieldActionPerformed

    private void buscarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarBtnActionPerformed
        //Se realiza la busqueda de los pasajeros registrados segun la informacion ingresada
        TipoDocumento tipoDoc = null;
        if(!"Seleccionar".equals(String.valueOf(tipoDocCombo.getSelectedItem()))) 
            tipoDoc = TipoDocumento.valueOf(String.valueOf(tipoDocCombo.getSelectedItem()));
        
        //Creo un objeto GestionarPasajeroDTO con los campos que se deben completar
        GestionarPasajeroDTO busquedaDTO = new GestionarPasajeroDTO(nombreField.getText(), apellidoField.getText(),tipoDoc ,numDocField.getText());
        
        //El gestor de pasajeros realiza la busqueda 
        resPasajeros = getInstancePasajero().buscarPasajeros(busquedaDTO);
        
        DefaultTableModel tabla = (DefaultTableModel) resultadosTabla.getModel();
        tabla.setRowCount(0);
        resultadosTabla.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        resultadosTabla.setColumnSelectionAllowed(false);
        resultadosTabla.setAutoCreateRowSorter(rootPaneCheckingEnabled);
        //Si no hay resultados
        if(resPasajeros.isEmpty()){
            JOptionPane.showMessageDialog(this, "No existen pasajeros que coincidan con los criterios de busqueda.", "Pasajero no encontrado",JOptionPane.ERROR_MESSAGE);      
        }
        else{
            //Completo la tabla con los pasajeros encontrados
            for(GestionarPasajeroDTO pas : resPasajeros){
                tabla.addRow(new Object[]{pas.getApellido(),pas.getNombre(),pas.getTipoDoc(),pas.getNumDoc()});
            }
        }
    }//GEN-LAST:event_buscarBtnActionPerformed

    private void aceptarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aceptarBtnActionPerformed
        if(resultadosTabla.getSelectedRowCount() == 0 ){
            //Si no se selecciono ningun pasajero
            JOptionPane.showMessageDialog(this, "Seleccione al menos un pasajero.", "Realice una seleccion",JOptionPane.ERROR_MESSAGE);
        }
        else{
            //Si se selecciono al menos uno
            //Tengo que ver si seleccionaron solo UN responsable de habitacion
            int filaResponsable = -1;
            int cont = 0;
            int i=0;
            while(i < resultadosTabla.getRowCount()){
                if(resultadosTabla.getValueAt(i, 4) != null && resultadosTabla.getValueAt(i,4) != Boolean.FALSE){
                    //Guardo el responsable seleccionado
                    if(cont == 0 ){
                        filaResponsable = i;
                    }
                    cont++;
                }
                i++; 
            }
            
            if(cont >= 2){
                //Seleccionaron mas de un responsable, se muestra error
                JOptionPane.showMessageDialog(this, "Seleccione unicamente un responsable de la habitacon.", "Unico responsable",JOptionPane.ERROR_MESSAGE);
            }
            else{
                //Si seleccionaron uno tengo que asegurarme que sea alguno de los pasajeros seleccionados
                //Obtengo los pasajeros seleccionados
                int[] filasSeleccionadas = resultadosTabla.getSelectedRows();   
                
                Boolean responsableValido = false;
                List<GestionarPasajeroDTO> pasajerosSeleccionados = new ArrayList<>();
                GestionarPasajeroDTO pasajeroResponsable = null;
                
                for(int j = 0; j < filasSeleccionadas.length ; j++){
                    //Si la filaResponsable no esta entre filasSeleccionadas
                    if(filasSeleccionadas[j] == filaResponsable){
                        responsableValido = true;
                        pasajeroResponsable = resPasajeros.get(filaResponsable);
                        pasajeroResponsable.setResponsableHabitacion(true);
                        pasajerosSeleccionados.add(pasajeroResponsable);
                    }
                    else{
                        GestionarPasajeroDTO p = resPasajeros.get(filasSeleccionadas[j]);
                        p.setResponsableHabitacion(false);
                        pasajerosSeleccionados.add(p);
                    }
                }
                
                if(responsableValido){
                    //Si se selecciono un responsable de pago de la lista de pasajeros seleccionados
                    //Valido que sea mayor de 18
                    int edadResponsable = calcularEdad(pasajeroResponsable.getFechaNac());
                    if(edadResponsable >= 18){
                        int idEstadia = 0;
                        
                        Object[] opciones = {"SEGUIR CARGANDO","CARGAR OTRA HABITACION","SALIR"};
                        int confirmacion = JOptionPane.showOptionDialog(this, "Elija una opcion","Seleccion",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE, null,opciones,null);
                        switch(confirmacion){
                            case JOptionPane.OK_OPTION: //Se vuelve a mostrar la interfaz de busqueda de pasajeros para seguir agregando
                                                        break;
                            case JOptionPane.NO_OPTION: //Se guarda la estadia y se vuelve a la interfaz de SeleccionFechasEstadoHabitacion
                                                        System.out.println("Cargar otra habitacion");
                                                        idEstadia = getInstanceEstadias().crearEstadia(pasajerosSeleccionados, estadia);
                                                        if(idEstadia != 0 ){
                                                            //Se creo exitosamente la estadia
                                                            JOptionPane.showMessageDialog(this, "La habitacion ha sido ocupada con exito.", "Check-in exitoso",JOptionPane.INFORMATION_MESSAGE);
                                                            this.dispose();
                                                            new SeleccionFechasEstadoHabitacion(null,true,"Ocupar Habitacion").setVisible(true);
                                                        }
                                                        else{
                                                            JOptionPane.showMessageDialog(this, "No se ha podido ocupar la habitacion.", "Error en check-in",JOptionPane.ERROR_MESSAGE);
                                                            this.dispose();
                                                        }
                                                        break;
                            case JOptionPane.CANCEL_OPTION: //Se guarda la estadia y se vuelve a la interfaz principal
                                                            System.out.println("Salir");
                                                            idEstadia = getInstanceEstadias().crearEstadia(pasajerosSeleccionados, estadia);
                                                            if(idEstadia != 0 ){
                                                                //Se creo exitosamente la estadia
                                                                JOptionPane.showMessageDialog(this, "La habitacion ha sido ocupada con exito.", "Check-in exitoso",JOptionPane.INFORMATION_MESSAGE);
                                                                this.dispose();
                                                            }
                                                            else{
                                                                JOptionPane.showMessageDialog(this, "No se ha podido ocupar la habitacion.", "Error en check-in",JOptionPane.ERROR_MESSAGE);
                                                                this.dispose();
                                                            }
                                                            break;
                        }       
                        
                    }
                    else{
                        //Es menor de edad
                        JOptionPane.showMessageDialog(this, "La persona seleccionada como responsable es menor de edad. Por favor seleccione otra.", "Responsable menor de edad",JOptionPane.ERROR_MESSAGE);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(this, "Seleccione como responsable de la habitacion a alguno de los pasajeros seleccionados.", "Seleccion responsable",JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_aceptarBtnActionPerformed

    
    private void cancelarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarBtnActionPerformed
        Object[] opciones = {"SI","NO"};
        int confirmacion = JOptionPane.showOptionDialog(this, "¿Desea cancelar el check-in?","Cancelar check-in",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null,opciones,null);
        if(confirmacion == JOptionPane.OK_OPTION){
            this.dispose();
        }
    }//GEN-LAST:event_cancelarBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aceptarBtn;
    private javax.swing.JTextField apellidoField;
    private javax.swing.JButton buscarBtn;
    private javax.swing.JButton cancelarBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField nombreField;
    private javax.swing.JTextField numDocField;
    private javax.swing.JTable resultadosTabla;
    private javax.swing.JComboBox<String> tipoDocCombo;
    // End of variables declaration//GEN-END:variables
}
