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

        if ("modelo.Participacion".equals(origen.getClass().getName())) {

            switch ((modelo.Participacion.Eventos) evento) {
                case salir:
                    this.mostrarJugadoresActivos();
                    break;
                case saldoModificado:
                    this.setearSaldoJugador();
                    break;
            case juegoTerminado: 
                this.juegoTerminado();
                break;
            }
        } else {

            if (Juego.Eventos.terminarParticipacion.equals(evento)) {
                this.mostrarJugadoresActivos();
            }
            if (Juego.Eventos.apuestaFijada.equals(evento)) {
                this.mostrarApuestaActual();
                this.mostrarNombreJugadorApostador();
            }
            if (Juego.Eventos.actualizacionPozo.equals(evento)) {
                this.mostrarApuestaActual();
                this.mostrarPozoActual();
            }
//            if (Juego.Eventos.terminarJuego.equals(evento)) {
//               this.juegoTerminado();
//            }
        }
    }

    public void juegoTerminado(){
        vistaJuego.terminarJuego();
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
            if (isNumeric(monto)) {
                double m = Double.parseDouble(monto);
                if (m > 0) {
                    sistema.recibirApuesta(m, juego, participacion);
                } else {
                    vistaJuego.error("Monto apostado tiene que ser mayor a 0");
                }
            } else {
                vistaJuego.error("Ingrese un numero");
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

    public void mostrarApuestaActual() {
        vistaJuego.mostrarApuestaActual(String.valueOf(juego.getApuetaActual()));
    }

    public void mostrarNombreJugadorApostador() {
        vistaJuego.mostrarNombreJugadorApostador(juego.getNombreJugadorApostador());
    }

    public void mostrarPozoActual() {
        vistaJuego.mostrarMontoPozoActual(String.valueOf(juego.getMontoPozoActual()));
    }

    public void IniciarVentana() {
        this.setearSaldoJugador();
        this.mostrarCartas();
        this.mostrarJugadoresActivos();
        this.mostrarApuestaActual();
        this.mostrarPozoActual();
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
