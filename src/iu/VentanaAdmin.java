/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iu;

import controlador.ControladorAdmin;
import controlador.VistaAdmin;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.Administrador;
import modelo.Juego;
import modelo.Sesion;

/**
 *
 * @author Bruno
 */
public class VentanaAdmin extends javax.swing.JDialog implements VistaAdmin {

    /**
     * Creates new form VentanaAdmin
     */
    private ControladorAdmin controlador;
    public VentanaAdmin(java.awt.Frame parent, boolean modal, Administrador admin) {
        super(parent, modal);
        initComponents();
        controlador = new ControladorAdmin(this, admin);
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        list_jugadoresEnPartida = new javax.swing.JList();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        list_juegosEnLinea = new javax.swing.JList();

        jLabel1.setText("jLabel1");

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel3.setText("Juegos en linea: ");

        jScrollPane1.setViewportView(list_jugadoresEnPartida);

        jLabel7.setText("Jugadores en la partida: ");

        list_juegosEnLinea.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                list_juegosEnLineaMouseClicked(evt);
            }
        });
        list_juegosEnLinea.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                list_juegosEnLineaValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(list_juegosEnLinea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void list_juegosEnLineaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_list_juegosEnLineaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_list_juegosEnLineaMouseClicked

    private void list_juegosEnLineaValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_list_juegosEnLineaValueChanged
        // TODO add your handling code here:
        
    }//GEN-LAST:event_list_juegosEnLineaValueChanged




    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList list_juegosEnLinea;
    private javax.swing.JList list_jugadoresEnPartida;
    // End of variables declaration//GEN-END:variables


    @Override
    public void mostrarSesiones(ArrayList<Sesion> sesiones) {
        list_juegosEnLinea.setListData(sesiones.toArray());
    }

    @Override
    public void salir() {
        controlador.salir();
    }
    
    @Override
    public void mostrarJugadores() {
        Sesion s = (Sesion) list_juegosEnLinea.getSelectedValue();
        controlador.detallesJugadores(s);
    }

    @Override
    public void detallesPartida(String detalles) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    @Override
    public void detallesJugadores(String detalles) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mostrarNombreAdmin(String nombre) {
        setTitle("Administrador: " + nombre);
    }

    @Override
    public void error(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }
    
    
}
