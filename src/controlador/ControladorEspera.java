/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import iu.VentanaJuego;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.HistoricoJugador;
import modelo.Juego;
import modelo.Jugador;
import modelo.Sistema;
import modelo.excepciones.JuegoException;
import observador.Observable;
import observador.Observador;

public class ControladorEspera implements Observador {

    private VistaEspera vistaEspera;
    private Sistema sistema = Sistema.getInstancia();
    private Jugador jugador;
    

    public ControladorEspera(VistaEspera vista, Jugador jugador) {
        this.vistaEspera = vista;
        this.jugador = jugador;
        sistema.agregar(this);
        ingresarJugadorJuego();
    }

    public void ingresarJugadorJuego() {
        try {
            Sistema.getInstancia().ingresarJugador(new HistoricoJugador(jugador));
        } catch (JuegoException ex) {
            Logger.getLogger(ControladorEspera.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void actualizar(Object evento, Observable origen) {
        
        if (evento.equals(Sistema.Eventos.nuevoJugador)) {
            mostrarFaltan();
            verificarInicio();
        }
        if (evento.equals(Sistema.Eventos.nuevoJuego)) {
            empezarJuego();
        }
        if (evento.equals(Sistema.Eventos.quitarJugador)) {
            mostrarFaltan();
        }
    }

    public void mostrarFaltan() {
        Juego j = sistema.getJuegoAIniciar();
        int cantJugadoresConectados = j.getJugadores().size();
        String datos = cantJugadoresConectados + "/" + Juego.getCantidadJugadores();
        vistaEspera.mostrarFaltan(datos);
    }

    public void verificarInicio() {
        try {
            sistema.vericarInicioJuego();
        } catch (JuegoException ex) {
            Logger.getLogger(ControladorJuego.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void empezarJuego() {
        vistaEspera.salir();
        new VentanaJuego(jugador).setVisible(true);
    }
    
    public void quitarJugador() {
        HistoricoJugador j = new HistoricoJugador(jugador);
        try {
            sistema.expulsarJugador(j);
        } catch (JuegoException ex) {
            Logger.getLogger(ControladorEspera.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
