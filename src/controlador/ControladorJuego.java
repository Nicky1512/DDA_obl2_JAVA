package controlador;

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
    private Participacion participacion;
    private Juego juego;

    public ControladorJuego(VistaJuego vista, Jugador jugador, Juego juego) {
        this.vistaJuego = vista;
        this.juego = juego;
        this.participacion = juego.getParticipacionDeJugador(jugador);
        juego.agregar(this);
        participacion.agregar(this);
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
        switch ((modelo.Participacion.Eventos) evento) {

            case salir:
                this.terminarParticipacion();
                break;
            case saldoModificado:
                this.setearSaldoJugador();
                break;
//            case pasar: vistaJuego.pasar();
//                break;
        }
    }

    public void terminarParticipacion() {
        try {
            sistema.terminarParticipacion(participacion, juego);
        } catch (JuegoException ex) {
            vistaJuego.error(ex.getMessage());
        }
    }

    public void mostrarCartas() {
        vistaJuego.observarCartas(participacion.getCartas());
    }

    public void apostar(String monto) {
        try {
            double m = Double.parseDouble(monto);
            if (m > 0) {
                sistema.recibirApuesta(m, juego, participacion);
            } else {
                vistaJuego.error("Monto apostado tiene que ser mayor a 0");
            }
        } catch (JuegoException ex) {
            vistaJuego.error(ex.getMessage());
        }
    }

    public void setearSaldoJugador() {
        vistaJuego.mostrarSaldoJugador(String.valueOf(participacion.getJugador().getSaldo()));
    }

    public void mostrarJugadoresActivos() {
        vistaJuego.mostrarJugadoresActivos(this.juego.getJugadoresActivos());
    }

    public void IniciarVentana() {
        this.setearSaldoJugador();
        this.mostrarCartas();
        this.mostrarJugadoresActivos();
    }
}
