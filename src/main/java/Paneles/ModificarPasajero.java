/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Paneles;

import Dominio.CamposAltaPasajero;
import Dominio.GestionarPasajeroDTO;
import Dominio.PasajeroDTO;
import Enum.PosicionIVA;
import Enum.TipoDocumento;
import static Gestores.GestorGeografico.*;
import static Gestores.GestorPasajero.*;
import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

public class ModificarPasajero extends javax.swing.JDialog {
    //Pasajero seleccionado
    int idPasajero = 0;
    int idDireccion = 0;
    PasajeroDTO datosPasajeroDTO = null;
    
    public ModificarPasajero(java.awt.Frame parent, boolean modal, GestionarPasajeroDTO gestionarPasajeroDTO) {
        super(parent, modal);
        initComponents();
        this.idPasajero = gestionarPasajeroDTO.getId();
        this.idDireccion = gestionarPasajeroDTO.getIdDireccion();
        this.setLocationRelativeTo(null);
        this.setTitle("Modificar Pasajero");
        
        //SE CARGAN LAS LISTAS DESPLEGABLES
        //Cargo los tipos de documentos
        tipoDocCombo.removeAllItems();
        TipoDocumento[] tipoDocList = TipoDocumento.values();
        Arrays.sort(tipoDocList);
        for(TipoDocumento tipoDoc: tipoDocList){
            tipoDocCombo.addItem(tipoDoc.name());
        }
        
        //Cargo las posiciones frente al iva
        posIVACombo.removeAllItems();
        PosicionIVA[] posIVAList = PosicionIVA.values();
        for(PosicionIVA posIVA: posIVAList){
            posIVACombo.addItem(posIVA.name());
        }
      
        
        //Cargo las listas de ubicaciones 
        //El gestor geografico obtiene todos los paises
        List <String> listaPaises = getInstanceGeo().obtenerPaises();
        Collections.sort(listaPaises);
        //Cargo los paises y nacionalidades
        paisCombo.removeAllItems();
        nacionalidadCombo.removeAllItems();
        nacionalidadCombo.addItem("Seleccionar");
        for(String pais : listaPaises){
            paisCombo.addItem(pais);
            nacionalidadCombo.addItem(pais);
        }
        
        this.datosPasajeroDTO = getInstancePasajero().buscarPasajero(idPasajero);
        cargarDatos();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        nombreField = new javax.swing.JTextField();
        calleField = new javax.swing.JTextField();
        ocupacionField = new javax.swing.JTextField();
        apellidoField = new javax.swing.JTextField();
        numDocField = new javax.swing.JTextField();
        CUITField = new javax.swing.JTextField();
        nacionalidadCombo = new javax.swing.JComboBox<>();
        tipoDocCombo = new javax.swing.JComboBox<>();
        provinciaCombo = new javax.swing.JComboBox<>();
        numeroField = new javax.swing.JTextField();
        deptoField = new javax.swing.JTextField();
        codigoPostalField = new javax.swing.JTextField();
        emailField = new javax.swing.JTextField();
        telefonoField = new javax.swing.JTextField();
        posIVACombo = new javax.swing.JComboBox<>();
        paisCombo = new javax.swing.JComboBox<>();
        localidadCombo = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        borrarBtn = new javax.swing.JButton();
        siguienteBtn = new javax.swing.JButton();
        fechaNacField = new com.toedter.calendar.JDateChooser();
        cancelarBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel1.setText("Datos Personales");

        jLabel2.setText("Nombres (*)");

        jLabel3.setText("Apellido (*)");

        jLabel4.setText("<html>Tipo de<br>documento (*)</html>");

        jLabel6.setText("CUIT");

        jLabel7.setText("Nacionalidad (*)");

        jLabel8.setText("<html>Fecha de<br> nacimiento (*)</html>");

        jLabel9.setText("Pais (*)");

        jLabel10.setText("Provincia (*)");

        jLabel11.setText("Localidad (*)");

        jLabel12.setText("Calle (*)");

        jLabel13.setText("Numero (*)");

        jLabel14.setText("<html>Dpto/<br>Piso</html>");

        jLabel16.setText("Telefono (*)");

        jLabel17.setText("Email");

        jLabel18.setText("Ocupacion (*)");

        jLabel19.setText("Posicion frente al IVA (*)");

        jLabel21.setText("<html>Numero de <br> documento (*)</html>");

        nombreField.setFocusCycleRoot(true);
        nombreField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreFieldActionPerformed(evt);
            }
        });

        calleField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calleFieldActionPerformed(evt);
            }
        });

        ocupacionField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ocupacionFieldActionPerformed(evt);
            }
        });

        apellidoField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apellidoFieldActionPerformed(evt);
            }
        });

        numDocField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numDocFieldActionPerformed(evt);
            }
        });

        CUITField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CUITFieldActionPerformed(evt);
            }
        });

        nacionalidadCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nacionalidadComboActionPerformed(evt);
            }
        });

        provinciaCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                provinciaComboActionPerformed(evt);
            }
        });

        numeroField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numeroFieldActionPerformed(evt);
            }
        });

        deptoField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deptoFieldActionPerformed(evt);
            }
        });

        codigoPostalField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                codigoPostalFieldActionPerformed(evt);
            }
        });

        emailField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailFieldActionPerformed(evt);
            }
        });

        telefonoField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                telefonoFieldActionPerformed(evt);
            }
        });

        posIVACombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                posIVAComboActionPerformed(evt);
            }
        });

        paisCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paisComboActionPerformed(evt);
            }
        });

        localidadCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                localidadComboActionPerformed(evt);
            }
        });

        jLabel22.setText("<html>Codigo<br>Postal (*)</html>");

        borrarBtn.setText("Borrar");
        borrarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrarBtnActionPerformed(evt);
            }
        });

        siguienteBtn.setText("Siguiente");
        siguienteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siguienteBtnActionPerformed(evt);
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel18))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(telefonoField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(ocupacionField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(fechaNacField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(nombreField)
                                        .addComponent(tipoDocCombo, 0, 200, Short.MAX_VALUE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(39, 39, 39)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(numDocField, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(CUITField))
                                    .addComponent(nacionalidadCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(apellidoField)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(129, 129, 129)
                                .addComponent(deptoField, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)
                                .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(codigoPostalField, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel19)
                                            .addGap(7, 7, 7))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel17)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(numeroField, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(posIVACombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(emailField)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(7, 7, 7))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel9)
                                    .addGap(49, 49, 49)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(45, 45, 45)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(calleField, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(paisCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(provinciaCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addComponent(localidadCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(26, 26, 26))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(411, 411, 411)
                .addComponent(cancelarBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(borrarBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(siguienteBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(25, 25, 25))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(291, 291, 291)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(nombreField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(apellidoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(CUITField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tipoDocCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numDocField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(nacionalidadCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(provinciaCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(localidadCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(paisCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel12)
                                        .addComponent(jLabel13)
                                        .addComponent(numeroField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(deptoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(calleField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(codigoPostalField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(15, 15, 15)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel17)
                                    .addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(telefonoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel19)
                                    .addComponent(ocupacionField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(posIVACombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(borrarBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(siguienteBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cancelarBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(fechaNacField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(7, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void cargarDatos(){
        
        nombreField.setText(datosPasajeroDTO.getNombre());
        apellidoField.setText(datosPasajeroDTO.getApellido());
        tipoDocCombo.setSelectedItem(datosPasajeroDTO.getTipoDoc().name());
        numDocField.setText(datosPasajeroDTO.getNumDoc());
        CUITField.setText(datosPasajeroDTO.getCUIT());
        fechaNacField.setDate(datosPasajeroDTO.getFechaNac());
        nacionalidadCombo.setSelectedItem(datosPasajeroDTO.getNacionalidad());
        paisCombo.setSelectedItem(datosPasajeroDTO.getPais());
        provinciaCombo.setSelectedItem(datosPasajeroDTO.getProvincia());
        localidadCombo.setSelectedItem(datosPasajeroDTO.getLocalidad());
        calleField.setText(datosPasajeroDTO.getCalle());
        numeroField.setText(datosPasajeroDTO.getNumero());
        deptoField.setText(datosPasajeroDTO.getDepartamento());
        codigoPostalField.setText(datosPasajeroDTO.getCodigoPostal());
        telefonoField.setText(datosPasajeroDTO.getTelefono());
        emailField.setText(datosPasajeroDTO.getEmail());
        ocupacionField.setText(datosPasajeroDTO.getOcupacion());
        posIVACombo.setSelectedItem(datosPasajeroDTO.getPosIva().name());
        
    }
    
    private void nombreFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreFieldActionPerformed

    private void calleFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calleFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_calleFieldActionPerformed

    private void ocupacionFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ocupacionFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ocupacionFieldActionPerformed

    private void apellidoFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apellidoFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_apellidoFieldActionPerformed

    private void numDocFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numDocFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_numDocFieldActionPerformed

    private void CUITFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CUITFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CUITFieldActionPerformed

    private void nacionalidadComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nacionalidadComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nacionalidadComboActionPerformed

    private void provinciaComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_provinciaComboActionPerformed
        //Cuando seleccionan una provincia, se cargan las localidades
        //Obtengo el elemento seleccionado
        String seleccion = (String) provinciaCombo.getSelectedItem();
        List <String> localidades = null;
        //Le pido al gestorGeografico las localidades de la provincia seleccionada
        if(!"Seleccionar".equals(seleccion)){
            localidades = getInstanceGeo().obtenerLocalidades(seleccion);
            Collections.sort(localidades);
            localidadCombo.removeAllItems();
            localidadCombo.addItem("Seleccionar");
          
            for(String localidad: localidades){
                localidadCombo.addItem(localidad);
            }
        }
        else{
            localidadCombo.removeAllItems();
            localidadCombo.addItem("No disponible");
        }
    }//GEN-LAST:event_provinciaComboActionPerformed

    private void numeroFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numeroFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_numeroFieldActionPerformed

    private void deptoFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deptoFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_deptoFieldActionPerformed

    private void codigoPostalFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_codigoPostalFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_codigoPostalFieldActionPerformed

    private void emailFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailFieldActionPerformed

    private void telefonoFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_telefonoFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_telefonoFieldActionPerformed

    private void posIVAComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_posIVAComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_posIVAComboActionPerformed

    private void paisComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paisComboActionPerformed
        //Cuando seleccionan un pais se cargan las provincias
        //Obtengo el elemento seleccionado
        String seleccion = (String) paisCombo.getSelectedItem();
        //Le pido al gestorGeografico las provincias del pais seleccionado
        List <String> provincias = getInstanceGeo().obtenerProvincias(seleccion);
        Collections.sort(provincias);
        provinciaCombo.removeAllItems();
        provinciaCombo.addItem("Seleccionar");
        for(String provincia: provincias){
            provinciaCombo.addItem(provincia);
        }
    }//GEN-LAST:event_paisComboActionPerformed

    private void localidadComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_localidadComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_localidadComboActionPerformed

    private void borrarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarBtnActionPerformed
        JOptionPane.showMessageDialog(this, "Funcionalidad Dar Baja Pasajero no disponible", null,JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_borrarBtnActionPerformed

    private void siguienteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siguienteBtnActionPerformed
        PasajeroDTO pasajeroDTO = new PasajeroDTO(apellidoField.getText(), nombreField.getText(), TipoDocumento.valueOf(String.valueOf(tipoDocCombo.getSelectedItem())), numDocField.getText(), fechaNacField.getDate(), emailField.getText(), ocupacionField.getText(), String.valueOf(nacionalidadCombo.getSelectedItem()), CUITField.getText(), PosicionIVA.valueOf(String.valueOf(posIVACombo.getSelectedItem())), telefonoField.getText(), String.valueOf(paisCombo.getSelectedItem()), String.valueOf(provinciaCombo.getSelectedItem()), String.valueOf(localidadCombo.getSelectedItem()), calleField.getText(), numeroField.getText(), deptoField.getText(), codigoPostalField.getText());
        
        //El gestor valida los datos ingresados y devuelve un booleano con el resultado
        CamposAltaPasajero validacionesCampos = getInstancePasajero().validarDatosPasajero(pasajeroDTO);
        
        //Si hay datos incorrectos se procede a validar nuevamente los datos obteniendo los mensajes de error
        List<String> mensajesError = new ArrayList();
        if(!validacionesCampos.getValidos()){  
            //Se validan todos los campos con expresiones regulares
            String errorLargo = "Error en longitud del campo ?. Debe tener como minimo ? caracteres y como maximo ?.";
            String errorSeleccion = "Error en campo ?. Seleccione un valor.";

            if(!validacionesCampos.getNombreValido()){
                nombreField.setBorder(BorderFactory.createLineBorder(Color.RED));
                String error = errorLargo.replaceFirst("\\?","Nombre");
                error = error.replaceFirst("\\?","1");
                error = error.replaceFirst("\\?","50");
                mensajesError.add(error);
            }
            else{
                nombreField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            }

            if(!validacionesCampos.getApellidoValido()){
                apellidoField.setBorder(BorderFactory.createLineBorder(Color.RED));
                String error = errorLargo.replaceFirst("\\?","Apellido");
                error = error.replaceFirst("\\?","1");
                error = error.replaceFirst("\\?","50");
                mensajesError.add(error);
            }
            else{
                apellidoField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            }

            if(!validacionesCampos.getNumDocValido()){
                numDocField.setBorder(BorderFactory.createLineBorder(Color.RED));
                String error = errorLargo.replaceFirst("\\?","Numero de Documento");
                error = error.replaceFirst("\\?","1");
                error = error.replaceFirst("\\?","10");
                mensajesError.add(error);
            }
            else{
                numDocField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            }

            if(!validacionesCampos.getCUITValido()){
                CUITField.setBorder(BorderFactory.createLineBorder(Color.RED));
                String error = "Error en el campo CUIT. Debe tener como maximo 15 caracteres.";
                mensajesError.add(error);
            }
            else{
                CUITField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            }

            SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaMin = null;
            try {
                fechaMin = sdFormat.parse("1870-01-01");
            } catch (ParseException ex) {
                ex.printStackTrace(System.out);
            }

            if(!validacionesCampos.getFechaNacValido()){
                fechaNacField.setBorder(BorderFactory.createLineBorder(Color.RED));
                String error = "Error en campo Fecha de Nacimiento. Es un campo obligatorio y su valor debe estar entre 01/01/1870 y el dia de hoy.";
                mensajesError.add(error);
            }
            else{
                fechaNacField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            }

            if(!validacionesCampos.getCalleValido()){
                calleField.setBorder(BorderFactory.createLineBorder(Color.RED));
                String error = errorLargo.replaceFirst("\\?","Calle");
                error = error.replaceFirst("\\?","1");
                error = error.replaceFirst("\\?","50");
                mensajesError.add(error);
            }
            else{
                calleField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            }

            if(!validacionesCampos.getNumeroValido()){
                numeroField.setBorder(BorderFactory.createLineBorder(Color.RED));
                String error = errorLargo.replaceFirst("\\?","Numero");
                error = error.replaceFirst("\\?","1");
                error = error.replaceFirst("\\?","10");
                error += " Debe ser un numero.";
                mensajesError.add(error);
            }
            else{
                numeroField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            }

            if(!validacionesCampos.getDepartamentoValido()){
                deptoField.setBorder(BorderFactory.createLineBorder(Color.RED));
                String error = "Error en el campo Depto/Piso. Debe tener como maximo 15 caracteres.";
                mensajesError.add(error);
            }
            else{
                deptoField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            }

            if(!validacionesCampos.getCodigoPostalValido()){
                codigoPostalField.setBorder(BorderFactory.createLineBorder(Color.RED));
                String error = errorLargo.replaceFirst("\\?","Codigo Postal");
                error = error.replaceFirst("\\?","1");
                error = error.replaceFirst("\\?","5");
                error += " Debe ser un numero.";
                mensajesError.add(error);
            }
            else{
                codigoPostalField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            }

            if(!validacionesCampos.getLocalidadValido()){
                localidadCombo.setBorder(BorderFactory.createLineBorder(Color.RED));
                mensajesError.add(errorSeleccion.replaceFirst("\\?", "Localidad"));
            }
            else{
                localidadCombo.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            }

            if(!validacionesCampos.getProvinciaValido()){
                provinciaCombo.setBorder(BorderFactory.createLineBorder(Color.RED));
                mensajesError.add(errorSeleccion.replaceFirst("\\?", "Provincia"));
            }
            else{
                provinciaCombo.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            }

            if(!validacionesCampos.getTelefonoValido()){
                telefonoField.setBorder(BorderFactory.createLineBorder(Color.RED));
                String error = "Error en el campo Telefono. Un ejemplo de formato valido es: +5493424000000.";
                mensajesError.add(error);
            }
            else{
                telefonoField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            }

            if(!validacionesCampos.getEmailValido()){
                emailField.setBorder(BorderFactory.createLineBorder(Color.RED));
                String error = "Error en el campo Email. El formato debe ser valido y contener como maximo 70 caracteres.";
                mensajesError.add(error);
            }
            else{
                emailField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            }

            if(!validacionesCampos.getOcupacionValido()){
                ocupacionField.setBorder(BorderFactory.createLineBorder(Color.RED));
                String error = errorLargo.replaceFirst("\\?","Ocupacion");
                error = error.replaceFirst("\\?","1");
                error = error.replaceFirst("\\?","50");
                mensajesError.add(error);
            }
            else{
                ocupacionField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            }

            if(!validacionesCampos.getNacionalidadValido()){
                nacionalidadCombo.setBorder(BorderFactory.createLineBorder(Color.RED));
                mensajesError.add(errorSeleccion.replaceFirst("\\?", "Nacionalidad"));
            }
            else{
                nacionalidadCombo.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            }
            
            //Mostramos el mensaje de error
            String mensaje = "";
            for(String m : mensajesError){
                mensaje +="- "+ m + "\n";
            }
            JOptionPane.showMessageDialog(this, mensaje, "Error al completar los datos",JOptionPane.ERROR_MESSAGE);
        }
        else{
            //No hay errores
            //Si cambiaron el tipo de documento o el numero de documento verificamos si ya existe un pasajero
            Boolean existePasajero = false;
            if(!datosPasajeroDTO.getTipoDoc().name().equals(pasajeroDTO.getTipoDoc().name()) || !datosPasajeroDTO.getNumDoc().equals(pasajeroDTO.getNumDoc())){
                existePasajero = getInstancePasajero().verificarExistenciaPasajero(pasajeroDTO);
            }
            
            if(existePasajero){
                Object[] opciones = {"Corregir","Aceptar Igualmente"};
                int confirmacion = JOptionPane.showOptionDialog(this, "¡CUIDADO!\n¡El tipo y numero de documento ya existen en el sistema!","Modificacion",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE, null,opciones,null);

                //Hay pasajero con mismo dni pero se quiere modificar igual
                if(confirmacion != JOptionPane.OK_OPTION){
                    //MODIFICAR PASAJERO      
                    //Pasamos los datos al gestor pasajero para que invoque al metodo modificarPasajero
                    pasajeroDTO.setIdPersona(idPasajero);
                    pasajeroDTO.setIdDireccion(idDireccion);
                    Boolean pasajeroModificado = false;
                    
                    try {
                        //Llamo al metodo modificarPasajero del gestor pasajeros
                        pasajeroModificado = getInstancePasajero().modificarPasajero(pasajeroDTO);
                    } catch (ParseException ex) {
                        ex.printStackTrace(System.out);
                    }

                    //Mostrar el cartel solo si se modifico exitosamente el pasajero
                    if(pasajeroModificado){
                        JOptionPane.showMessageDialog(this, "El pasajero "+nombreField.getText()+" "+apellidoField.getText()+" ha sido modificado satisfactoriamente .","Modificacion Exitosa",JOptionPane.PLAIN_MESSAGE);
                        this.dispose();
                        new GestionarPasajero(null,true).setVisible(true);
                    }
                    else{
                        JOptionPane.showMessageDialog(this, "No se ha podido modificar el pasajero","Error en modificacion",JOptionPane.ERROR_MESSAGE);
                   
                    }
                }
            }
            else{
                //No hay pasajero con mismo dni por lo que se procede a modificarlo
                //MODIFICAR PASAJERO
                //Pasamos los datos al gestor pasajero para que invoque al metodo modificarPasajero
                pasajeroDTO.setIdPersona(idPasajero);
                pasajeroDTO.setIdDireccion(idDireccion);
                Boolean pasajeroModificado = false;

                try {
                    //Llamo al metodo modificarPasajero del gestor pasajeros
                    pasajeroModificado = getInstancePasajero().modificarPasajero(pasajeroDTO);
                } catch (ParseException ex) {
                    ex.printStackTrace(System.out);
                }

                //Mostrar el cartel solo si se modifico exitosamente el pasajero
                if(pasajeroModificado){
                    JOptionPane.showMessageDialog(this, "El pasajero "+nombreField.getText()+" "+apellidoField.getText()+" ha sido modificado satisfactoriamente .","Modificacion Exitosa",JOptionPane.PLAIN_MESSAGE);
                    this.dispose();
                    new GestionarPasajero(null,true).setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(this, "No se ha podido modificar el pasajero","Error en modificacion",JOptionPane.ERROR_MESSAGE);

                }
            }
        }
    }//GEN-LAST:event_siguienteBtnActionPerformed

    
    private void cancelarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarBtnActionPerformed
        Object[] opciones = {"SI","NO"};
        int confirmacion = JOptionPane.showOptionDialog(this, "¿Desea cancelar la modificacion del pasajero?","Cancelar modificacion",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null,opciones,null);
        if(confirmacion == JOptionPane.OK_OPTION){
            this.dispose();
            new GestionarPasajero(null,true).setVisible(true);
        }
    }//GEN-LAST:event_cancelarBtnActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField CUITField;
    private javax.swing.JTextField apellidoField;
    private javax.swing.JButton borrarBtn;
    private javax.swing.JTextField calleField;
    private javax.swing.JButton cancelarBtn;
    private javax.swing.JTextField codigoPostalField;
    private javax.swing.JTextField deptoField;
    private javax.swing.JTextField emailField;
    private com.toedter.calendar.JDateChooser fechaNacField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JComboBox<String> localidadCombo;
    public javax.swing.JComboBox<String> nacionalidadCombo;
    private javax.swing.JTextField nombreField;
    private javax.swing.JTextField numDocField;
    private javax.swing.JTextField numeroField;
    private javax.swing.JTextField ocupacionField;
    public javax.swing.JComboBox<String> paisCombo;
    private javax.swing.JComboBox<String> posIVACombo;
    public javax.swing.JComboBox<String> provinciaCombo;
    private javax.swing.JButton siguienteBtn;
    private javax.swing.JTextField telefonoField;
    public javax.swing.JComboBox<String> tipoDocCombo;
    // End of variables declaration//GEN-END:variables
}
