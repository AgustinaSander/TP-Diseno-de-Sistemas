/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Paneles;

import Dominio.DTO.EstadoHabitacionDTO;
import Dominio.DTO.HabitacionDTO;
import static Gestores.GestorHabitaciones.getInstanceHabitaciones;
import java.awt.Dimension;
import java.util.*;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import javax.swing.JTable;


/**
 *
 * @author Agustina
 */
public class MostrarEstadoHabitaciones extends javax.swing.JDialog {

    public MostrarEstadoHabitaciones(java.awt.Frame parent, boolean modal, Date fechaDesde, Date fechaHasta, String titulo) {
        super(parent, modal);
        initComponents();
        this.setTitle(titulo);
        this.setLocationRelativeTo(null);
        
        //Obtenemos los tipos de habitaciones disponibles
        List <String> tipoDeHabitaciones = getInstanceHabitaciones().obtenerTiposDeHabitaciones();
        List <JPanel> paneles = new ArrayList<>();
        /*
        for(String tipo: tipoDeHabitaciones){
            jTabbedPane1.addTab(tipo, paneles.get(paneles.size()-1));
        }
        
        //Seleccionamos por default la Individual Estandar
        jTabbedPane1.setSelectedIndex(0);
        */
        
        //Obtenemos las habitaciones de ese tipo
        Map <Integer, List<EstadoHabitacionDTO>> listaEstados = getInstanceHabitaciones().mostrarEstadoHabitaciones("Individual Estandar", fechaDesde, fechaHasta);
        
        //System.out.println(listaEstados);
        List<HabitacionDTO> habitaciones = getInstanceHabitaciones().obtenerHabitaciones("Individual Estandar");
        List<String> listaFechas = getInstanceHabitaciones().obtenerFechasIntermedias(fechaDesde, fechaHasta);
       
        String [] nombreColumnas = new String[habitaciones.size()];
        for(int i=0; i<habitaciones.size(); i++){
            nombreColumnas[i] = habitaciones.get(i).getNombre();
        }
        
        Object[][] datos = new Object [listaFechas.size()][habitaciones.size()];
        
        for(int row = 0; row < listaFechas.size(); row++){
            List<String> estados = new ArrayList<>();
            for(int col = 0; col < habitaciones.size(); col++){
               datos[row][col] = obtenerEstado(listaFechas.get(row), habitaciones.get(col), listaEstados.get(habitaciones.get(col).getId()));
            }
        }
        JTable table = new JTable(datos, nombreColumnas);
        JScrollPane scroll = new JScrollPane(table);
        getContentPane().add(scroll);
        
    }

    public String obtenerEstado(String fecha, HabitacionDTO hab, List<EstadoHabitacionDTO> estados){
        Boolean estadoEncontrado = false;
        String estado = null;
        
        int cont = 0;
        while(!estadoEncontrado){
            if(estados.get(cont).getFecha().equals(fecha)){
                estadoEncontrado = true;
                estado = estados.get(cont).getEstado();
            }
            else{
                cont++;
            }
        }
        return estado;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Estado de habitaciones desde ");

        jLabel2.setText("14/04/2000");

        jLabel3.setText("14/04/2000");

        jLabel4.setText("hasta");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(248, 248, 248)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addContainerGap(259, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addContainerGap(499, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
