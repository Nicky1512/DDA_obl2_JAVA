package controlador;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Carta;
import modelo.HistoricoJugador;
import modelo.Juego;
import modelo.Jugador;
import modelo.Participacion;
import modelo.Sistema;
import modelo.excepciones.JuegoException;
import observador.Observable;
import observador.Observador;

public class ControladorJuego implements Observador {

    private VistaJuego vistaJuego;
    private Sistema sistema = Sistema.getInstancia();
    private Jugador jugador;
    private Juego juego;

    public ControladorJuego(VistaJuego vista, Jugador jugador) {
        this.vistaJuego = vista;
        this.jugador = jugador;
        this.juego = sistema.getJuegoAIniciar();
        juego.agregar(this);
        vista.mostrarNombreJugador(jugador.getNombreCompleto());
    }

    public VistaJuego getVista() {
        return vistaJuego;
    }

    public void setVista(VistaJuego vista) {
        this.vistaJuego = vista;
    }

    public Sistema getSistema() {
        return sistema;
    }

    public void setSistema(Sistema sistema) {
        this.sistema = sistema;
    }

    @Override
    public void actualizar(Object evento, Observable origen) {
        switch((modelo.Participacion.Eventos)evento){
            
            case salir: this.terminarParticipacion();
                break;
            case saldoModificado: this.setearSaldoJugador();
                break;
//            case pasar: vistaJuego.pasar();
//                break;
//            case observarCartas: vistaJuego.observarCartas();
//                break;
        }
    

    }

    private void cargarJugador(Jugador jugador) throws JuegoException {
        sistema.ingresarJugadorJuego(jugador);
    }

    public void terminarParticipacion(){
        try {
            sistema.terminarParticipacion(jugador, juego);
        } catch (JuegoException ex) {
            vistaJuego.error(ex.getMessage());
        }
    }
    
    public void mostrarCartas(){
        ArrayList<Participacion> copia = juego.getManoActual().getParticipantes();
        for (Participacion p : copia) {
            if(p.getJugador().equals(jugador)){
                Carta[] cartas = (Carta[]) p.getCartas().toArray();
                vistaJuego.observarCartas(cartas);
            }
        }
    }
    
    public void apostar(String monto){
        try {
            double m = Double.parseDouble(monto);
            if(m > 0)
                sistema.recibirApuesta(m, juego, jugador);
            else
                vistaJuego.error("Monto apostado tiene que ser mayor a 0");
        } catch (JuegoException ex) {
            vistaJuego.error(ex.getMessage());
        }
    }

    public void setearSaldoJugador() {
        vistaJuego.mostrarSaldoJugador(String.valueOf(jugador.getSaldo()));
    }
}
