
package Paneles;

import Dominio.DTO.EstadiaDTO;
import Dominio.DTO.PasajeroDTO;
import static Gestores.GestorEstadias.getInstanceEstadias;
import Validaciones.BusquedaFacturacion;
import static Validaciones.Validaciones.calcularEdad;
import java.awt.Color;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ElegirResponsableDePago extends javax.swing.JDialog {

    int idUltimaEstadia = 0;
    EstadiaDTO ultimaEstadia = null;
    PasajeroDTO responsable = null;
    String hora = null;
    List<PasajeroDTO> listaOcupantes = null;
    
    public ElegirResponsableDePago(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Facturacion");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        aceptarBtn = new javax.swing.JButton();
        cancelarBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaOcupantes = new javax.swing.JTable();
        facturarTerceroBtn = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        nroHabField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        horaField = new javax.swing.JTextField();
        buscarBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        aceptarBtn.setText("Aceptar");
        aceptarBtn.setEnabled(false);
        aceptarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aceptarBtnActionPerformed(evt);
            }
        });

        cancelarBtn.setText("Cancelar");
        cancelarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarBtnActionPerformed(evt);
            }
        });

        tablaOcupantes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombres", "Apellido", "Tipo de Documento", "Numero de Documento", "Responsable de Pago"
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
        jScrollPane1.setViewportView(tablaOcupantes);

        facturarTerceroBtn.setText("Facturar a nombre de un tercero");
        facturarTerceroBtn.setEnabled(false);
        facturarTerceroBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                facturarTerceroBtnActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel4.setText("Ocupantes");

        jLabel2.setText("Numero de Habitacion *");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel1.setText("Seleccione la habitacion y el horario de salida");

        jLabel3.setText("Hora de Salida *");

        buscarBtn.setText("Buscar");
        buscarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 765, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(208, 208, 208))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(342, 342, 342))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(buscarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(nroHabField, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(horaField, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(facturarTerceroBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cancelarBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(aceptarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 673, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(nroHabField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(horaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(buscarBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(aceptarBtn)
                    .addComponent(cancelarBtn)
                    .addComponent(facturarTerceroBtn))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void aceptarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aceptarBtnActionPerformed
        //Verifico que hayan seleccionado solo UN responsable de pago
        int filaResponsable = -1;
        int cont = 0;
        int i=0;
        while(i < tablaOcupantes.getRowCount()){
            if(tablaOcupantes.getValueAt(i, 4) != null && tablaOcupantes.getValueAt(i,4) != Boolean.FALSE){
                //Guardo el responsable seleccionado
                if(cont == 0 ){
                    filaResponsable = i;
                }
                cont++;
            }
            i++; 
        }
        
        if(cont == 0){
            JOptionPane.showMessageDialog(this, "Seleccione un responsable de pago.", "Error en responsable de pago",JOptionPane.ERROR_MESSAGE);
        }
        else if(cont > 1){
            JOptionPane.showMessageDialog(this, "Seleccione solo un responsable de pago.", "Error en responsable de pago",JOptionPane.ERROR_MESSAGE);
        }
        else{
            int edadResponsable = calcularEdad(listaOcupantes.get(filaResponsable).getFechaNac());
            if(edadResponsable >= 18){
                responsable = listaOcupantes.get(filaResponsable);
                //Se muestra la interfaz Facturacion
                new Facturacion(null, true, ultimaEstadia, responsable, hora).setVisible(true);
            }
            else{
                //Es menor de edad
                JOptionPane.showMessageDialog(this, "La persona seleccionada como responsable es menor de edad. Por favor seleccione otra.", "Responsable menor de edad",JOptionPane.ERROR_MESSAGE);

            }
        }
        
        
        
    }//GEN-LAST:event_aceptarBtnActionPerformed

    private void cancelarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarBtnActionPerformed
        Object[] opciones = {"SI","NO"};
        int confirmacion = JOptionPane.showOptionDialog(this, "¿Desea cancelar la generacion de la factura?","Cancelar facturacion",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null,opciones,null);
        if(confirmacion == JOptionPane.OK_OPTION){
            this.dispose();
        }
    }//GEN-LAST:event_cancelarBtnActionPerformed

    private void facturarTerceroBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_facturarTerceroBtnActionPerformed
        this.dispose();
        //TENGO QUE VER DE PASARLE LA ESTADIA Y LA HORA DESPUES
        new BusquedaResponsableDePago(null, true).setVisible(true);
    }//GEN-LAST:event_facturarTerceroBtnActionPerformed

    private void buscarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarBtnActionPerformed
        //Validamos que los campos se hayan completado correctamente
        BusquedaFacturacion validacionDatos = getInstanceEstadias().validarDatosFacturacion(nroHabField.getText(), horaField.getText());
        String texto = "";
        if(!validacionDatos.getValidos()){
            //Hay campos que no son validos
            //Si la hora no es valida
            if(!validacionDatos.getHora()){
                horaField.setBorder(BorderFactory.createLineBorder(Color.RED));
                texto += "Error en campo Hora. El formato es HH:MM.\n";
            }
            else{
                horaField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            }
            
            //Si el nro no es valido
            if(!validacionDatos.getNroHabitacion()){
                nroHabField.setBorder(BorderFactory.createLineBorder(Color.RED));
                texto += "Error en campo Numero de Habitacion. Asegurese que la habitacion ingresada exista.";
            }
            else{
                nroHabField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            }
            JOptionPane.showMessageDialog(this, texto, "Error al completar los datos",JOptionPane.ERROR_MESSAGE);
        }
        else{
            horaField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            nroHabField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            
            hora = horaField.getText();
            
            //Recupero la ultima estadia
            ultimaEstadia = getInstanceEstadias().obtenerUltimaEstadia(nroHabField.getText());
            listaOcupantes = ultimaEstadia.getListaPasajeros();
            
            System.out.println(ultimaEstadia);
            
            DefaultTableModel tabla = (DefaultTableModel) tablaOcupantes.getModel();
            tabla.setRowCount(0);
            
            for(PasajeroDTO pas : listaOcupantes){
                tabla.addRow(new Object[]{pas.getNombre(),pas.getApellido(),pas.getTipoDoc(),pas.getNumDoc()});
            }
            //Se habilita el boton Facturar a nombre de un tercero
            facturarTerceroBtn.setEnabled(true);
            aceptarBtn.setEnabled(true);
        }
    }//GEN-LAST:event_buscarBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aceptarBtn;
    private javax.swing.JButton buscarBtn;
    private javax.swing.JButton cancelarBtn;
    private javax.swing.JButton facturarTerceroBtn;
    private javax.swing.JTextField horaField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField nroHabField;
    private javax.swing.JTable tablaOcupantes;
    // End of variables declaration//GEN-END:variables
}
