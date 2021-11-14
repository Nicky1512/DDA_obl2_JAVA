package controlador;

import modelo.Juego;
import modelo.Jugador;
import modelo.Sistema;
import modelo.SistemaJuegos;
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
//        if (evento.equals(Juego.Eventos.nuevoJugador)) { //TODO, evento llega nuevoJugador en vez de nuevoJugador
//            mostrarFaltan();
//        }

    }

    private void cargarJugador(Jugador jugador) throws JuegoException {
        SistemaJuegos.getInstancia().agregarJugador(jugador);
    }


}
