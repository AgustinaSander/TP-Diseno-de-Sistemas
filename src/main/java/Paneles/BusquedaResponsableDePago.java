
package Paneles;

import Dominio.DTO.EstadiaDTO;
import Dominio.DTO.PersonaDTO;
import static Gestores.GestorResponsablePago.getInstanceResponsable;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

public class BusquedaResponsableDePago extends javax.swing.JDialog {

    private EstadiaDTO estadia;
    private String hora;
    private PersonaDTO responsable;
    
    public BusquedaResponsableDePago(java.awt.Frame parent, boolean modal, EstadiaDTO estadia, String hora) {
        super(parent, modal);
        initComponents();
        this.setTitle("Facturacion");
        this.setLocationRelativeTo(null);
        this.estadia = estadia;
        this.hora = hora;
        
        cuitField.addFocusListener(new FocusListener() {
            @Override
            public void focusLost(FocusEvent e) {
                //Cuando se pierde el foco se valida lo ingresado
                //Se valida el cuit
                if(validarFormatoCuit()){
                    validarExistenciaCuit();
                }
            }

            @Override
            public void focusGained(FocusEvent e) {
                
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cuitField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        razonSocialField = new javax.swing.JTextField();
        aceptarBtn = new javax.swing.JButton();
        cancelarBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel1.setText("Ingrese el CUIT del Responsable de Pago");

        jLabel2.setText("CUIT");

        jLabel4.setText("Razon Social");

        razonSocialField.setEditable(false);

        aceptarBtn.setText("Aceptar");
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(67, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(188, 188, 188))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cancelarBtn)
                                .addGap(18, 18, 18)
                                .addComponent(aceptarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(cuitField, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(razonSocialField, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(35, 35, 35))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel1)
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(razonSocialField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(cuitField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(aceptarBtn)
                    .addComponent(cancelarBtn))
                .addGap(47, 47, 47))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void aceptarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aceptarBtnActionPerformed

        if(cuitField.getText().length() == 0){
            JOptionPane.showMessageDialog(this, "Funcionalidad Dar Alta Responsable de Pago no disponible", null,JOptionPane.ERROR_MESSAGE);
        }
        else if(!validarFormatoCuit()){
            JOptionPane.showMessageDialog(this, "El cuit ingresado no es valido", null,JOptionPane.ERROR_MESSAGE);
        }else if(!validarExistenciaCuit()){
            JOptionPane.showMessageDialog(this, "El cuit ingresado no se encuentra registrado en la base de datos", null,JOptionPane.ERROR_MESSAGE);
        }else{
            //Ir a interfaz facturacion
            new Facturacion(null, true, estadia, responsable, hora).setVisible(true);
        }
        
    }//GEN-LAST:event_aceptarBtnActionPerformed

    private void cancelarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarBtnActionPerformed
        Object[] opciones = {"SI","NO"};
        int confirmacion = JOptionPane.showOptionDialog(this, "¿Desea cancelar la seleccion del tercero?","Cancelar seleccion",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null,opciones,null);
        if(confirmacion == JOptionPane.OK_OPTION){
            this.dispose();
        }
    }//GEN-LAST:event_cancelarBtnActionPerformed

    private Boolean validarFormatoCuit(){
        Boolean cuitValido = false;
        if(cuitField.getText().length() != 0){
                    cuitValido = getInstanceResponsable().validarCUIT(cuitField.getText());
                  
                    if(cuitValido){
                        cuitField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                    }
                    else{
                        cuitField.setBorder(BorderFactory.createLineBorder(Color.RED));
                        razonSocialField.setText("");
                        
                    }
                }
        else{
            cuitField.setBorder(BorderFactory.createLineBorder(Color.RED));
            razonSocialField.setText("");
            cuitValido = false;
        }
        return cuitValido;
    }

    private Boolean validarExistenciaCuit(){
        //Se busca la razon social
        responsable = getInstanceResponsable().obtenerResponsableDePago(cuitField.getText());
        if(responsable != null){
            razonSocialField.setText(responsable.getRazonSocial());
            return true;
        }
        else{
            cuitField.setBorder(BorderFactory.createLineBorder(Color.RED));
            razonSocialField.setText("");
            return false;
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aceptarBtn;
    private javax.swing.JButton cancelarBtn;
    private javax.swing.JTextField cuitField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField razonSocialField;
    // End of variables declaration//GEN-END:variables
}
