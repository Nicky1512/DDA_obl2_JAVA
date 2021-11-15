package controlador;

import java.util.ArrayList;
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
        sistema.agregar(this);
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
//        switch((modelo.Participacion.Eventos)evento){
//            
//            case salir: vistaJuego.terminarJuego();
//                break;
//            case apostar: vistaJuego.apostar(); //VERIFICAR SI NO ES NECESARIO RECIBIR PARAMETRO
//                break;
//            case pasar: vistaJuego.pasar();
//                break;
//            case observarCartas: vistaJuego.observarCartas();
//                break;
//        }
    

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
}
