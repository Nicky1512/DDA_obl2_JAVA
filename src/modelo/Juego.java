package modelo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import modelo.excepciones.JuegoException;
import observador.Observable;

public class Juego extends Observable {

    private static int cantidadJugadores;
    public static double apuestaBase;

    private Boolean enCurso;
    private Date fechaInicio;

    private ArrayList<Mano> manos = new ArrayList<>();
    private ArrayList<Participacion> participaciones = new ArrayList<>();

    public enum Eventos {
        nuevoJugador, nuevaMano, nuevoJuego, quitarJugador, terminarParticipacion, apuestaFijada, actualizacionPozo, terminarJuego, mostrarGanador
    };

    public Juego() {
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public ArrayList<Mano> getManos() {
        return manos;
    }

    public Participacion getGanador() {
        return this.getManoActual().getGanador();
    }

    public ArrayList<Participacion> getParticipaciones() {
        return participaciones;
    }

    public Participacion getParticipacionDeJugador(Jugador jugador) {
        for (Participacion p : participaciones) {
            if (p.getJugador().equals(jugador)) {
                return p;
            }
        }
        return null;
    }

    public ArrayList<Participacion> getJugadoresActivos() {
        ArrayList<Participacion> aux = new ArrayList();
        for (Participacion j : participaciones) {
            if (j.isActivo()) {
                aux.add(j);
            }
        }
        return aux;
    }

    public Boolean estaEnCurso() {
        return enCurso;
    }

    public static int getCantidadJugadores() {
        return cantidadJugadores;
    }

    public static void setCantidadJugadores(int cantidadJugadores) {
        if (cantidadJugadores > 1 && cantidadJugadores < 6) {
            Juego.cantidadJugadores = cantidadJugadores;
        }
    }

    public static double getApuestaBase() {
        return apuestaBase;
    }

    public static void setApuestaBase(double apuestaBase) {
        if (apuestaBase > 0) {
            Juego.apuestaBase = apuestaBase;
        }
    }

    public void verificarIngresoJugador(Jugador jugador) throws JuegoException {
        if (Juego.cantidadJugadores == participaciones.size()) {
            throw new JuegoException("El juego no puede aceptar mas jugadores");
        }
        if (jugador.getSaldo() > (Juego.cantidadJugadores * Juego.apuestaBase)) {
            Participacion j = new Participacion(jugador);
            if (participaciones.contains(j)) {
                throw new JuegoException("El jugador ya fue ingresado al juego");
            }
        } else {
            throw new JuegoException("El saldo actual es menor a " + Juego.cantidadJugadores + " apuestas base");
        }
    }

    public void agregarJugador(Jugador jugador) throws JuegoException {
        verificarIngresoJugador(jugador);
        Participacion j = new Participacion(jugador);
        participaciones.add(j);
        avisar(Juego.Eventos.nuevoJugador);
    }

    public void verificarInicioJuego() throws JuegoException {
        if (Juego.cantidadJugadores == participaciones.size()) {
            Sistema.getInstancia().empezarJuego();
        }
    }

    public void retirarJugador(Jugador jugador) throws JuegoException {
        Boolean aux = false;
        for (Participacion p : participaciones) {
            if (p.getJugador().equals(jugador)) {
                participaciones.remove(p);
                aux = true;
                avisar(Juego.Eventos.quitarJugador);
                break;
            }
        }

        if (!aux) {
            throw new JuegoException("El jugador no fue ingresado previamente.");
        }

    }

    public void finalizarParticipacion(Participacion participacion) throws JuegoException {
        ArrayList<Participacion> jugadoresActivos = getJugadoresActivos();
        Boolean aux = false;
        if (jugadoresActivos.size() > 0) {
            for (Participacion p : jugadoresActivos) {
                if (p.equals(participacion)) {
                    p.setActivo(false);
                    aux = true;
                    avisar(Juego.Eventos.terminarParticipacion);
                    verificarFinalJuego();
                }
            }
            if (!aux) {
                throw new JuegoException("El jugador no fue ingresado previamente.");
            }
        }
    }

    private void removerJugadores() throws JuegoException {
        int cont = 0;
        ArrayList<Participacion> jugadoresActivos = getJugadoresActivos();
        for (Participacion j : jugadoresActivos) {
            Participacion copia = j;
            if (copia.getJugador().getSaldo() == 0) {
                finalizarParticipacion(copia);
            }
            Boolean puedo = copia.getJugador().puedoApostar(Juego.apuestaBase);
            if (!puedo) {
                finalizarParticipacion(copia);
            } else {
                cont++;
            }
        }
        verificarFinalJuego();
    }

    public void verificarFinalJuego() {
        ArrayList<Participacion> jugadoresActivos = getJugadoresActivos();
        if (jugadoresActivos.size() == 1) {
            finalizarJuego(jugadoresActivos.get(0));
        }
    }

    public ArrayList<Participacion> getListaJugadores() {
        return participaciones;
    }

    public void iniciarMano(double pozoAcumulado) throws JuegoException {
        Mazo mazo = SistemaJuegos.getInstancia().getMazo();
        mazo.barajar();
        Mano nuevaMano = new Mano((Juego.apuestaBase * participaciones.size()) + pozoAcumulado, mazo, getJugadoresActivos());
        this.manos.add(nuevaMano);
    }

    public Mano getManoActual() {
        if (manos.size() > 0) {
            Mano ultima = manos.get(manos.size() - 1);
            return ultima;
        } else {
            return null;
        }
    }

    public void iniciarManoSig(double pozoAcumulado) throws JuegoException {
        removerJugadores();
        if (this.estaEnCurso()) {
            iniciarMano(pozoAcumulado);
            avisar(Eventos.nuevaMano);
        }
    }

    public void terminarMano() throws JuegoException {
        Mano actual = this.getManoActual();
        actual.determinarGanador();
        avisar(Juego.Eventos.mostrarGanador);
        this.iniciarManoSig(0);
    }

    public void terminaManoSinApuestas() throws JuegoException {
        Mano actual = this.getManoActual();
        this.iniciarManoSig(actual.getPozoInicial());
    }

    public void empezarJuego() throws JuegoException {
        this.fechaInicio = new Date();
        this.enCurso = true;
        iniciarMano(0);
        avisar(Juego.Eventos.nuevoJuego);
    }

    public void finalizarJuego(Participacion participacion) {
        this.enCurso = false;
        participacion.agregarSaldo(this.getMontoPozoActual());
        participacion.TerminoJuego();
    }

    public void recibirApuesta(double monto, Participacion participacion) throws JuegoException {
        this.getManoActual().recibirApuesta(monto, participacion);
        avisar(Juego.Eventos.apuestaFijada);
        avisar(Juego.Eventos.actualizacionPozo);
    }

    public void pagarApuestaFijada(Participacion p) throws JuegoException {
        this.getManoActual().pagarApuesta(p);
        avisar(Juego.Eventos.actualizacionPozo);
        verificarFinalMano();
    }

    public void verificarFinalMano() throws JuegoException {
        if (this.getManoActual().verificarEstadoParticipantes()) {
            if (this.getManoActual().getParticipantesApostadores().size() > 0) {
                terminarMano();
            } else {
                terminaManoSinApuestas();
            }
        }
    }

    public double getTotalApostadoJuego() {
        double total = 0;
        for (Mano m : manos) {
            total += m.getTotalApostado();
        }
        return total;
    }

    public double getApuetaActual() {
        return this.getManoActual().getApuestaFijada();
    }

    public String getNombreJugadorApostador() {
        return this.getManoActual().getNombreApostador();
    }

    public double getMontoPozoActual() {
        return this.getManoActual().getPozoInicial();
    }

    public String getDatosJuego() {
        String patron = "dd/MM/yyyy HH:mm";
        DateFormat df = new SimpleDateFormat(patron);
        double totalApostado = this.getTotalApostadoJuego();
        return "Fecha inicio: " + df.format(this.fechaInicio) + "Cant jugadores: " + this.getJugadoresActivos().size() + "Total apostado: " + totalApostado + "Cant manos jugadas: " + this.manos.size();
    }
}
