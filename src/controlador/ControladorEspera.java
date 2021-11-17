package controlador;

import iu.VentanaJuego;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private Juego juego;
    

    public ControladorEspera(VistaEspera vista, Jugador jugador) {
        this.vistaEspera = vista;
        this.jugador = jugador;
        this.juego = sistema.getJuegoAIniciar();
        juego.agregar(this);
        ingresarJugadorJuego();
        mostrarFaltan();
    }

    public void ingresarJugadorJuego() {
        try {
            juego.agregarJugador(jugador);
        } catch (JuegoException ex) {           
            vistaEspera.error(ex.getMessage()); 
            vistaEspera.salir();
        }
    }

    @Override
    public void actualizar(Object evento, Observable origen) { 
        if (evento.equals(Juego.Eventos.nuevoJugador)) {
            mostrarFaltan();
            verificarInicio();
        }
        if (evento.equals(Juego.Eventos.nuevoJuego)) {
            empezarJuego();
        }
        if (evento.equals(Juego.Eventos.quitarJugador)) {
            mostrarFaltan();
        }
    }

    public void mostrarFaltan() {
        Juego j = sistema.getJuegoAIniciar();
        int cantJugadoresConectados = j.getParticipaciones().size();
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
        new VentanaJuego(jugador, juego).setVisible(true);
    }
    
    public void quitarJugador() {
        try {
            juego.retirarJugador(jugador);
        } catch (JuegoException ex) {
            vistaEspera.error(ex.getMessage());
        }
    }
}
