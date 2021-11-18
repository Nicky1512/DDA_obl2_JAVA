package iu;

import controlador.ControladorAdmin;
import controlador.VistaAdmin;
import java.awt.Frame;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.Administrador;
import modelo.Juego;

public class VentanaAdmin extends javax.swing.JDialog implements VistaAdmin {

    private ControladorAdmin controlador;
    private ArrayList<Juego> juegos;

    public VentanaAdmin(Administrador admin) {
        super((Frame) null, false);
        initComponents();
        controlador = new ControladorAdmin(this, admin);
        setLocationRelativeTo(null);
        setTitle("Administrador: " + admin.getNombreCompleto());
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
        listaJugadores = new javax.swing.JList();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listaJuegosActivos = new javax.swing.JList();

        jLabel1.setText("jLabel1");

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Juegos en linea");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel3.setText("Juegos en linea: ");

        listaJugadores.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listaJugadoresValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(listaJugadores);

        jLabel7.setText("Jugadores en la partida: ");

        listaJuegosActivos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listaJuegosActivosMouseClicked(evt);
            }
        });
        listaJuegosActivos.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listaJuegosActivosValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(listaJuegosActivos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 651, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addContainerGap(21, Short.MAX_VALUE))
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

    private void listaJuegosActivosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaJuegosActivosMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_listaJuegosActivosMouseClicked

    private void listaJuegosActivosValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listaJuegosActivosValueChanged
        listaJuegos();
    }//GEN-LAST:event_listaJuegosActivosValueChanged

    private void listaJugadoresValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listaJugadoresValueChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_listaJugadoresValueChanged

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.salir();
    }//GEN-LAST:event_formWindowClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList listaJuegosActivos;
    private javax.swing.JList listaJugadores;
    // End of variables declaration//GEN-END:variables

    @Override
    public void salir() {
        controlador.salir();
    }

    @Override
    public void mostrarNombreAdmin(String nombre) {
        setTitle("Administrador: " + nombre);
    }

    @Override
    public void error(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    @Override
    public void mostrarJuegos(ArrayList<Juego> juegos) {
        this.juegos = juegos;
        ArrayList<String> lista = new ArrayList();
        for (Juego j : juegos) {
            lista.add(j.getDatosJuego());
        }
        listaJuegosActivos.setListData(lista.toArray());
    }

    private void listaJuegos() {
        int selection = listaJuegosActivos.getSelectedIndex();
        Juego juegoSelected = juegos.get(selection);
        listaJugadores.clearSelection();
        listaJugadores.setListData(juegoSelected.getParticipaciones().toArray());
    }

}
