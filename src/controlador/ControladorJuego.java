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
        IniciarVentana();
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
                    this.salir();
                    break;
                case saldoModificado:
                    this.setearSaldoJugador();
                    break;
                case juegoTerminado:
                    this.juegoTerminado();
                    break;
                case modificarEstado:
                    this.modificarEstado();
                    break;

            }
        } else {

            switch ((modelo.Juego.Eventos) evento) {
                case terminarParticipacion:
                    this.mostrarJugadoresActivos();
                    break;
                case accionRealizada:
                    this.mostrarJugadoresActivos();
                    break;
                case apuestaFijada:
                    this.mostrarApuestaActual();
                    this.mostrarNombreJugadorApostador();
                    break;
                case actualizacionPozo:
                    this.mostrarApuestaActual();
                    this.mostrarPozoActual();
                    break;
                case nuevaMano:
                    this.IniciarVentana();
                    break;
                case mostrarGanador:
                    this.mostrarGanador();
                    break;
            }
        }
    }

    public void juegoTerminado() {
        vistaJuego.terminarJuego();
    }

    public void terminarParticipacion() {
        try {
            juego.finalizarParticipacion(participacion, false);
            juego.quitar(this);
            participacion.quitar(this);
        } catch (JuegoException ex) {
            vistaJuego.error(ex.getMessage());
        }
    }

    public void mostrarCartas() {
        vistaJuego.observarCartas(participacion.getCartas());
        vistaJuego.mostrarFigura(participacion.getFigura().getNombre());
    }

    public void apostar(String monto) {
        try {
            if (isNumeric(monto)) {
                double m = Double.parseDouble(monto);
                if (m > 0) {
                    juego.recibirApuesta(m, participacion);
                    modificarEstado();
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
        String nombre = juego.getNombreJugadorApostador();
        if (nombre.length() == 0 || nombre == null) {
            nombre = "Aun no hay apuestas";
        }
        vistaJuego.mostrarNombreJugadorApostador(nombre);
    }

    public void mostrarPozoActual() {
        vistaJuego.mostrarMontoPozoActual(String.valueOf(juego.getMontoPozoActual()));
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void pagarApuestaFijada() {
        try {
            juego.pagarApuestaFijada(participacion);
            modificarEstado();
        } catch (JuegoException ex) {
            vistaJuego.error(ex.getMessage());
        }
    }

    public void pasar() {
        participacion.pasar();
        this.mostrarJugadoresActivos();
        modificarEstado();
        try {
            juego.verificarFinalMano();
        } catch (JuegoException ex) {
            vistaJuego.error(ex.getMessage());
        }
    }

    private void modificarEstado() {
        if (participacion.yaJugo()) {
            vistaJuego.modificarEstado("En espera!");
        } else {
            vistaJuego.modificarEstado("Es tu turno!");
        }
    }

    private void mostrarGanador() {
        Participacion p = juego.getGanador();
        vistaJuego.mostarGanador(p.datosGanador(), p.cartasGanador());
    }

    public void IniciarVentana() {
        this.setearSaldoJugador();
        this.mostrarCartas();
        this.mostrarJugadoresActivos();
        this.mostrarApuestaActual();
        this.mostrarPozoActual();
        this.modificarEstado();
        this.mostrarNombreJugadorApostador();
    }

    public void salir() {
        juego.quitar(this);
        participacion.quitar(this);
        vistaJuego.salir("Fuiste expulsado debido a que tu saldo actual es insuficiente para realizar una apuesta");
    }
}
