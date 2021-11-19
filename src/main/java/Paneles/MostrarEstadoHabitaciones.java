/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Paneles;

import Dominio.DTO.EstadiaDTO;
import Dominio.DTO.EstadoHabitacionDTO;
import Dominio.DTO.HabitacionDTO;
import Dominio.DTO.ReservaDTO;
import static Gestores.GestorHabitaciones.getInstanceHabitaciones;
import static Gestores.GestorReservas.getInstanceReservas;
import java.awt.BorderLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Agustina
 */
public class MostrarEstadoHabitaciones extends javax.swing.JDialog {
    
    private JTable tabla;
    private JComboBox tipoHab;
    private Date desde;
    private Date hasta;
    private List<HabitacionDTO> listaHabitaciones;
    
    public MostrarEstadoHabitaciones(java.awt.Frame parent, boolean modal, Date fechaDesde, Date fechaHasta, String titulo) {
        super(parent, modal);

        this.desde = fechaDesde;
        this.hasta = fechaHasta;
        
        //Obtenemos los tipos de habitaciones disponibles
        List <String> tipoDeHabitaciones = getInstanceHabitaciones().obtenerTiposDeHabitaciones();

        JComboBox tipoHabCombo = new JComboBox();
        this.tipoHab = tipoHabCombo;
        tipoHabCombo.setBounds(40,60,800,20);
        this.add(tipoHabCombo, BorderLayout.CENTER);
        for(String tipo: tipoDeHabitaciones){
            tipoHabCombo.addItem(tipo);
        }
        tipoHabCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipoHabComboActionPerformed(evt);
            }
        });
        
        
        //Seleccionamos por default la Individual Estandar
        tipoHabCombo.setSelectedIndex(0); //Al realizar una seleccion se ejecuta el evento del tipoHabCombo

        initComponents();
        
        //Pongo los valores de las fechas en el titulo
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        desdeFecha.setText(sdf.format(fechaDesde));
        hastaFecha.setText(sdf.format(fechaHasta));
        this.setTitle(titulo);
        this.setLocationRelativeTo(null);
        
    }

    public void mostrarTablaTipoHab(String tipoHab){
        
        //Obtenemos un map con clave idHabitacion y valor una lista de objetos EstadoHabitacionDTO que indican el estado de esa habitacion en una fecha
        //Cada habitacion va a tener asociada una lista con X estados (X va a ser la cantidad de dias del rango de fechas seleccionado)
        Map <Integer, List<EstadoHabitacionDTO>> listaEstados = getInstanceHabitaciones().mostrarEstadoHabitaciones(tipoHab, desde, hasta);
        
        //Obtengo la lista de habitaciones de ese tipo
        List<HabitacionDTO> habitaciones = getInstanceHabitaciones().obtenerHabitaciones(tipoHab);
        this.listaHabitaciones = habitaciones;
        
        //Obtengo un arreglo con todas las fechas intermedias del intervalo
        List<String> listaFechas = getInstanceHabitaciones().obtenerFechasIntermedias(desde, hasta);
       
        //Creo la tabla
        String [] nombreColumnas = new String[habitaciones.size()+1];
        nombreColumnas[0] = "Fecha";
        for(int i=1; i<=habitaciones.size(); i++){
            nombreColumnas[i] = habitaciones.get(i-1).getNombre();
        }
        Object[][] datos = new Object [listaFechas.size()][habitaciones.size()+1];
        
        
        for(int row = 0; row < listaFechas.size(); row++){
            datos[row][0] = listaFechas.get(row);
            List<String> estados = new ArrayList<>();
            
            for(int col = 1; col <= habitaciones.size(); col++){
                //Completo los datos de la tabla
                datos[row][col] = obtenerEstado(listaFechas.get(row), habitaciones.get(col-1), listaEstados.get(habitaciones.get(col-1).getId()));
            }
        }
        
        DefaultTableModel tableModel = new DefaultTableModel(datos, nombreColumnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
               //Las celdas no pueden editarse
               return false;
            }
        };
        
        JTable table = new JTable();
        table.setModel(tableModel);
        this.tabla = table;
        pintarCeldas();
        table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        table.setRowSelectionAllowed(false);
        table.setColumnSelectionAllowed(false);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setCellSelectionEnabled(true);
        
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(40,100,800,300);
        this.setLayout(null);
        this.setSize(800,300);
        this.add(scroll, BorderLayout.CENTER);
        
    }
    
    //Busca el estado de la habitacion en una fecha
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
    
    public void pintarCeldas(){
        PintarCeldas pintar = new PintarCeldas();
        
        for(int i=0; i<tabla.getRowCount(); i++){
            for(int j=1; j<tabla.getColumnCount(); j++){
                tabla.getColumnModel().getColumn(j).setCellRenderer(pintar);
            }
        }
    }
    
    public Boolean verificarEstadoSeleccion(int [] filas, int columna){
        //Verifico que la habitacion no este ocupada o inhabilitada en el rango seleccionado
        Boolean band = true;
        DefaultTableModel dtm = (DefaultTableModel) tabla.getModel();
        for(int i=filas[0]; i<=filas[filas.length-1]; i++){
            if(dtm.getValueAt(i, columna) == "Ocupada" || dtm.getValueAt(i, columna) == "Inhabilitada"){
                band = false;
                break;
            }
        }
        return band;
    }
    
    public List<ReservaDTO> verificarExistenciaReservas(int [] filas, int columna) throws ParseException{
        List<ReservaDTO> reservas = new ArrayList<>();
        List <Date> fechasReservadas = new ArrayList<>();
        HabitacionDTO hab = null;
        
        DefaultTableModel dtm = (DefaultTableModel) tabla.getModel();
        for(int i=filas[0]; i<=filas[filas.length-1]; i++){
            if(dtm.getValueAt(i, columna) == "Reservada"){
                //Obtengo la reserva de esa fecha y habitacion
                hab = listaHabitaciones.get(columna-1);
                String fecha = dtm.getValueAt(i, 0).toString();
                fechasReservadas.add(new SimpleDateFormat("dd-MM-yyyy").parse(fecha));
            }
        }
        
        //Si hay fechas reservadas las busco, sino devuelvo la lista de reservas vacia
        if(!fechasReservadas.isEmpty()){
            reservas = getInstanceReservas().buscarReservas(hab, fechasReservadas);
        }
        return reservas;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        aceptarBtn = new javax.swing.JButton();
        cancelarBtn = new javax.swing.JButton();
        desdeFecha = new javax.swing.JLabel();
        hastaFecha = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel1.setText("Estado de habitaciones desde ");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel4.setText("hasta");

        jPanel2.setBackground(java.awt.Color.yellow);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 19, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel5.setText("Reservada");

        jPanel4.setBackground(java.awt.Color.red);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 19, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel6.setText("Ocupada");

        jPanel5.setBackground(java.awt.Color.green);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 19, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel7.setText("Desocupada");

        jPanel6.setBackground(java.awt.Color.blue);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 19, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel8.setText("Inhabilitada");

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

        desdeFecha.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        desdeFecha.setText(" ");

        hastaFecha.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        hastaFecha.setText(" ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(141, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addGap(70, 70, 70)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addGap(61, 61, 61)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addGap(60, 60, 60)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)
                        .addGap(141, 141, 141))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(cancelarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(aceptarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(desdeFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(hastaFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
                    .addComponent(desdeFecha)
                    .addComponent(hastaFecha))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 404, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(aceptarBtn)
                    .addComponent(cancelarBtn))
                .addGap(23, 23, 23))
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

    private void aceptarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aceptarBtnActionPerformed
        //Ver seleccion de fechas hecha por el usuario
        int [] filasSeleccionadas = tabla.getSelectedRows();
        int columnaSeleccionada = tabla.getSelectedColumn();
        
        Boolean seleccionValida = verificarEstadoSeleccion(filasSeleccionadas, columnaSeleccionada);
        if(seleccionValida){
            try {
                //La habitacion no esta ocupada ni inhabilitada en esas fechas
                //Vemos si hay alguna reserva en el intervalo seleccionado
                List <ReservaDTO> reservasExistentes = verificarExistenciaReservas(filasSeleccionadas, columnaSeleccionada);
                
                //Si hay alguna reserva
                if(!reservasExistentes.isEmpty()){
                    Object[] opciones = {"Volver","Ocupar Igual"};
                    String texto = "La habitacion esta reservada. La reserva esta a nombre de: \n";
                    for(ReservaDTO r: reservasExistentes){
                        texto += "- "+r.getApellido()+" "+r.getNombre()+", Telefono: "+r.getTelefono()+" del dia "+r.getFechaDesde()+" hasta "+r.getFechaHasta()+"\n";
                    }
                    int confirmacion = JOptionPane.showOptionDialog(this, texto ,"Habitacion reservada",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE, null,opciones,null);
                    //Si presiona "Ocupar Igual"
                    if(confirmacion == JOptionPane.NO_OPTION){
                        //Se pintan las celdas seleccionadas para representar que van a ser ocupadas
                        for(int i=0; i<filasSeleccionadas.length; i++){
                            tabla.getModel().setValueAt("Ocupada", filasSeleccionadas[i] , columnaSeleccionada);
                        }
                        //Se muestra un mensaje de confirmacion
                        JOptionPane.showOptionDialog(null, "Presione cualquier tecla y continue..", null, JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE , null, null, null);
                        //Cerramos la interfaz
                        this.dispose();
                        
                        //Abrimos interfaz de BusquedaPasajerosOcupantes
                        //Creamos un objeto EstadiaDTO para pasarle el idHab, fechaDesde y fechaHasta
                        
                        DefaultTableModel dtm = (DefaultTableModel) tabla.getModel();
                        int idHab = listaHabitaciones.get(columnaSeleccionada-1).getId();
                        Date fechaDesde = new SimpleDateFormat("dd-MM-yyyy").parse( dtm.getValueAt(filasSeleccionadas[0], 0).toString() );
                        Date fechaHasta = new SimpleDateFormat("dd-MM-yyyy").parse( dtm.getValueAt(filasSeleccionadas[filasSeleccionadas.length - 1], 0).toString() );
                        
                        new BusquedaPasajerosOcupantes(null, true, new EstadiaDTO(idHab, fechaDesde, fechaHasta)).setVisible(true);
                        
                    }
                }
                else{
                    //Abrimos interfaz de BusquedaPasajerosOcupantes
                    //Creamos un objeto EstadiaDTO para pasarle el idHab, fechaDesde y fechaHasta

                    DefaultTableModel dtm = (DefaultTableModel) tabla.getModel();
                    int idHab = listaHabitaciones.get(columnaSeleccionada-1).getId();
                    Date fechaDesde = new SimpleDateFormat("dd-MM-yyyy").parse( dtm.getValueAt(filasSeleccionadas[0], 0).toString() );
                    Date fechaHasta = new SimpleDateFormat("dd-MM-yyyy").parse( dtm.getValueAt(filasSeleccionadas[filasSeleccionadas.length - 1], 0).toString() );
                    this.dispose();
                    //La habitacion esta desocupada en ese rango de fechas
                    new BusquedaPasajerosOcupantes(null, true, new EstadiaDTO(idHab, fechaDesde, fechaHasta)).setVisible(true);
                }
            } catch (ParseException ex) {
                ex.printStackTrace(System.out);
            }
        }
        else{
            JOptionPane.showMessageDialog(this,"La habitacion no esta disponible en las fechas seleccionadas","Habitacion no disponible",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_aceptarBtnActionPerformed

    private void cancelarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarBtnActionPerformed
        Object[] opciones = {"SI","NO"};
        int confirmacion = JOptionPane.showOptionDialog(this, "¿Desea cancelar el check-in?","Cancelar check-in",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null,opciones,null);
        if(confirmacion == JOptionPane.OK_OPTION){
            this.dispose();
            new SeleccionFechasEstadoHabitacion(null,true,"Ocupar Habitacion").setVisible(true);
        }
    }//GEN-LAST:event_cancelarBtnActionPerformed

    private void tipoHabComboActionPerformed(java.awt.event.ActionEvent evt){
        //Se realiza la obtencion de datos y carga de la tabla
        mostrarTablaTipoHab(tipoHab.getSelectedItem().toString());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aceptarBtn;
    private javax.swing.JButton cancelarBtn;
    private javax.swing.JLabel desdeFecha;
    private javax.swing.JLabel hastaFecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    // End of variables declaration//GEN-END:variables
}
