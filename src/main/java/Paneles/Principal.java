
package Paneles;

public class Principal extends javax.swing.JFrame {

    public Principal() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("HOTEL PREMIER");
        
        //String tipoFactura, int idFactura, String nombreCliente, String cuit, String posIva, String direccion, String telefono, String email, List<ItemDTO> items
        new ArchivoFactura("FACTURA A", 4, "Agustina Sander","27423313876","Consumidor Final", "Castelli 1621, Santa Fe, Santa Fe","+5493424227082","asander00@hotmail.com",null).crearPlantilla();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        pasajerosCombo = new javax.swing.JComboBox<>();
        pagosCombo = new javax.swing.JComboBox<>();
        habitacionesCombo = new javax.swing.JComboBox<>();
        jComboBox4 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pasajerosCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PASAJEROS", "Gestionar pasajero", "Alta pasajero" }));
        pasajerosCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pasajerosComboActionPerformed(evt);
            }
        });

        pagosCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PAGOS", "Facturar", "Gestionar responsable", "Alta responsable", "Ingresar pago" }));
        pagosCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pagosComboActionPerformed(evt);
            }
        });

        habitacionesCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "HABITACIONES", "Reservar", "Ocupar", "Cancelar" }));
        habitacionesCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                habitacionesComboActionPerformed(evt);
            }
        });

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LISTADOS", "Listar cheques", "Listar ingresos" }));
        jComboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(habitacionesCombo, 0, 343, Short.MAX_VALUE)
                    .addComponent(jComboBox4, 0, 343, Short.MAX_VALUE)
                    .addComponent(pasajerosCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pagosCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(159, Short.MAX_VALUE)
                .addComponent(pasajerosCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(pagosCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(habitacionesCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pasajerosComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pasajerosComboActionPerformed
        if(pasajerosCombo.getSelectedItem() == "Alta pasajero"){
            new AltaPasajero(this,true).setVisible(true);
        }
        else if(pasajerosCombo.getSelectedItem() == "Gestionar pasajero"){
            new GestionarPasajero(this,true).setVisible(true);
        }
            
    }//GEN-LAST:event_pasajerosComboActionPerformed

    private void pagosComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pagosComboActionPerformed
        if(pagosCombo.getSelectedItem() == "Facturar"){
            new ElegirResponsableDePago(this,true).setVisible(true);
        }
    }//GEN-LAST:event_pagosComboActionPerformed

    private void habitacionesComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_habitacionesComboActionPerformed
        if(habitacionesCombo.getSelectedItem() == "Ocupar"){
            new SeleccionFechasEstadoHabitacion(this,true,"Ocupar Habitacion").setVisible(true);
        }
    }//GEN-LAST:event_habitacionesComboActionPerformed

    private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox4ActionPerformed

    /**
     * @param args the command line arguments
     */
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> habitacionesCombo;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JComboBox<String> pagosCombo;
    private javax.swing.JComboBox<String> pasajerosCombo;
    // End of variables declaration//GEN-END:variables
}
