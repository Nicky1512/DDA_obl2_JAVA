/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package iu;

import controlador.ControladorJuego;
import controlador.VistaJuego;
import java.awt.Frame;
import modelo.Juego;
import modelo.Mazo;
import modelo.Participacion;

/**
 *
 * @author Conan
 */
public class VentanaJuego extends javax.swing.JDialog implements VistaJuego {

    /**
     * Creates new form VentanaJuego
     */
    private ControladorJuego controlador;
    public VentanaJuego(Participacion p) {
        super((Frame)null, false);
        initComponents();
        controlador = new ControladorJuego();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void mostrarJuego() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mostrarCartas(Mazo mazo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void terminarJuego() {
        controlador.salir();
    }

    @Override
    public void explusarJugador() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void empezarJuego() {
        controlador.empezarJuego();
    }

    @Override
    public void observarCartas() {
        controlador.ObservarCartas();
    }

    @Override
    public void apostar(double a) {
        controlador.apostar(a);
    }

    @Override
    public void pasar() {
        controlador.pasar();
    }



    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
