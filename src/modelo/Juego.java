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
    private ArrayList<Participacion> jugadores = new ArrayList<>();

    public enum Eventos {
        nuevoJugador, nuevaMano, nuevoJuego, quitarJugador
    };

    public Juego() {
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public ArrayList<Mano> getManos() {
        return manos;
    }

    public ArrayList<Participacion> getJugadores() {
        return jugadores;
    }

    public ArrayList<Participacion> getJugadoresActivos() {
        ArrayList<Participacion> aux = new ArrayList();
        for (Participacion j : jugadores) {
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
        if (Juego.cantidadJugadores == jugadores.size()) {
            throw new JuegoException("El juego no puede aceptar mas jugadores");
        }
        if (jugador.getSaldo() > (Juego.cantidadJugadores * Juego.apuestaBase)) {
            Participacion j = new Participacion(jugador);
            if (jugadores.contains(j)) {              
                throw new JuegoException("El jugador ya fue ingresado al juego");
            }
        } else {
            throw new JuegoException("El saldo actual es menor a " + Juego.cantidadJugadores + " apuestas base");
        }
    }

    public void agregarJugador(Jugador jugador) throws JuegoException {
        verificarIngresoJugador(jugador);
        Participacion j = new Participacion(jugador);
        jugadores.add(j);
        avisar(Juego.Eventos.nuevoJugador);
    }

    public void verificarInicioJuego() throws JuegoException {
        if (Juego.cantidadJugadores == jugadores.size()) {
            Sistema.getInstancia().empezarJuego();
        }
    }

    public void retirarJugador(Participacion jugador) throws JuegoException {
        Boolean aux = false;
        for (Participacion j : jugadores) {
            if (j.equals(jugador)) {
                jugadores.remove(j);
                aux = true;
                avisar(Juego.Eventos.quitarJugador);
                break;
            }
        }

        if (!aux) {
            throw new JuegoException("El jugador no fue ingresado previamente.");
        }

    }

    public void finalizarParticipacion(Jugador jugador) throws JuegoException {
        ArrayList<Participacion> jugadoresActivos = getJugadoresActivos();
        Boolean aux = false;
        for (Participacion j : jugadoresActivos) {
            if (j.getJugador().equals(jugador)) {
                j.setActivo(false);
                aux = true;
                avisar(Juego.Eventos.quitarJugador);
            }
        }

        if (!aux) {
            throw new JuegoException("El jugador no fue ingresado previamente.");
        }
    }

    private void removerJugadores() throws JuegoException {
        int cont = 0;
        ArrayList<Participacion> jugadoresActivos = getJugadoresActivos();
        for (Participacion j : jugadoresActivos) {
            Participacion copia = j;
            if (copia.getJugador().getSaldo() == 0) {
                retirarJugador(copia);
            }
            Boolean puedo = copia.getJugador().puedoApostar(Juego.apuestaBase);
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

    public ArrayList<Participacion> getListaJugadores() {
        return jugadores;
    }

    public void iniciarMano(double pozoAcumulado) throws JuegoException {
        Mazo mazo = SistemaJuegos.getInstancia().getMazo();
        mazo.barajar();
        Mano nuevaMano = new Mano((Juego.apuestaBase * jugadores.size()) + pozoAcumulado, mazo, getJugadoresActivos());
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
        if (this.jugadores.size() <= 1) {
            finalizarJuego();
        } else {
            iniciarMano(pozoAcumulado);
            avisar(Eventos.nuevaMano);
        }
    }

    public void terminarMano() throws JuegoException {
        Mano actual = this.getManoActual();
        Participacion ganador = actual.determinarGanador();
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

    public void finalizarJuego() {
        this.enCurso = false;
    }

    public void recibirApuesta(double monto, Jugador jugador) throws JuegoException {
        this.getManoActual().recibirApuesta(monto, jugador);
    }

    public double getTotalApostadoJuego() {
        double total = 0;
        for (Mano m : manos) {
            total += m.getTotalApostado();
        }
        return total;
    }

    public String getDatosJuego() {
        String patron = "dd/MM/yyyy HH:mm";
        DateFormat df = new SimpleDateFormat(patron);
        double totalApostado = this.getTotalApostadoJuego();
        return "Fecha inicio: " + df.format(this.fechaInicio) + "Cant jugadores: " + this.getJugadoresActivos().size() + "Total apostado: " + totalApostado + "Cant manos jugadas: " + this.manos.size();
    }

    public ArrayList<Participacion> getDetallesJugadores() {
        for (Participacion j : jugadores) {
            double totalApostado = 0;
            double totalGanado = 0;
            for(Mano m: manos){
                Participacion p = m.getParticipacionDeJugador(j.getJugador());
                if(p != null){
                    totalApostado += p.getApuesta();
                    totalGanado += p.getMontoGanado();
                }
                
            }
            j.setTotalApostado(totalApostado);
            j.setTotalGanado(totalGanado);
        }
        return jugadores;
    }
}
