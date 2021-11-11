package modelo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import modelo.excepciones.JuegoException;
import observador.Observable;

public class Juego extends Observable {

    private static int cantidadJugadores;
    private static double apuestaBase;
    private Date fechaInicio;
    private ArrayList<Mano> manos;
    private ArrayList<Jugador> jugadores;
    private Boolean enCurso;

    public Juego() {
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public ArrayList<Mano> getManos() {
        return manos;
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public Boolean estaEnCurso(){
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

    public void retirarJugador(Jugador jugador) throws JuegoException {
        if (jugadores.contains(jugador)) {
            jugadores.remove(jugador);
        } else {
            throw new JuegoException("El jugador no fue ingresado previamente.");
        }
    }

    public void agregarJugador(Jugador jugador) throws JuegoException {
        if (Juego.cantidadJugadores <= jugadores.size()) {
            throw new JuegoException("El juego no puede aceptar mas jugadores");
        }
        if (jugador.getSaldo() < Juego.cantidadJugadores * Juego.apuestaBase) {
            if (!jugadores.contains(jugador)) {
                jugadores.add(jugador);
            } else {
                throw new JuegoException("El jugador ya fue ingresado al juego");
            }
        } else {
            throw new JuegoException("El saldo actual es menor a " + Juego.cantidadJugadores + "apuestas base");
        }
    }

    public Mano getManoActual() {
        if (manos.size() > 0) {
            Mano ultima = manos.get(manos.size() - 1);
            return ultima;
        } else {
            return null;
        }
    }

    private void removerJugadores() throws JuegoException {
        int cont = 0;
        for (Jugador j : jugadores) {
            Jugador copia = j;
            if (copia.getSaldo() == 0) {
                retirarJugador(copia);
            }
            Boolean puedo = copia.puedoApostar(Juego.apuestaBase);
            if (!puedo) {
                retirarJugador(copia);
            } else {
                cont++;
            }
        }
        if (cont == 1) {
            finalizarJuego();
        }
    }

    public void iniciarManoSig(double pozoAcumulado) throws JuegoException {
        removerJugadores();
        if (this.jugadores.size() <= 1) {
            finalizarJuego();
        } else {
            iniciarMano(pozoAcumulado);
        }
    }

    public void iniciarMano(double pozoAcumulado) throws JuegoException {
        descontarSaldoTodos();
        Mazo mazo = ControlJuegos.getInstancia().getMazo();
        mazo.barajar();
        Mano nuevaMano = new Mano(Juego.apuestaBase * jugadores.size() + pozoAcumulado, mazo, this.jugadores);
        this.manos.add(nuevaMano);
    }

    public void descontarSaldoTodos() throws JuegoException {
        for (Jugador j : jugadores) {
            j.descontarSaldo(Juego.apuestaBase);
        }
    }

    public void terminarMano() throws JuegoException {
        Mano actual = this.getManoActual();
        Participacion ganador = actual.determinarGanador();
        ganador.getJugador().agregarSaldo(actual.getPozoInicial() + actual.getTotalApostado());
        this.iniciarManoSig(0);
    }

    public void terminaManoSinApuestas() throws JuegoException {
        Mano actual = this.getManoActual();
        this.iniciarManoSig(actual.getPozoInicial());
    }

    public void finalizarJuego() {
        this.enCurso = false;
    }

    public void empezarJuego() throws JuegoException {
        this.fechaInicio = new Date();
        this.enCurso = true;
        iniciarMano(0);
    }

    public void recibirApuesta(double monto, Participacion participacion) throws JuegoException {
        this.getManoActual().recibirApuesta(monto, participacion);
    }

    public double getTotalApostado() {
        double total = 0;
        for (Mano m : manos) {
            total += m.getTotalApostado();
        }
        return total;
    }

    public String getDatosJuego() {
        String patron = "dd/MM/yyyy HH:mm";
        DateFormat df = new SimpleDateFormat(patron);
        double totalApostado = this.getTotalApostado();
        return "Fecha inicio: " + df.format(this.fechaInicio) + "Cant jugadores: " + this.jugadores.size() + "Total apostado: " + totalApostado + "Cant manos jugadas: " + this.manos.size();
    }
}
