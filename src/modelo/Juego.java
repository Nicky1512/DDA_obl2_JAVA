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

    private Boolean enCurso;
    private Date fechaInicio;

    private ArrayList<Mano> manos = new ArrayList<>();
    private ArrayList<HistoricoJugador> jugadores = new ArrayList<>();

    public enum Eventos {
        nuevoJugador, nuevaMano
    };

    public Juego() {
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public ArrayList<Mano> getManos() {
        return manos;
    }

    public ArrayList<HistoricoJugador> getJugadores() {
        return jugadores;
    }
    
    public ArrayList<HistoricoJugador> getJugadoresActivos(){
        ArrayList<HistoricoJugador> aux = new ArrayList();
        for(HistoricoJugador j:jugadores){
            if(j.isActivo()){
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

    public void agregarJugador(Jugador jugador) throws JuegoException {
        if (Juego.cantidadJugadores == jugadores.size()) {
            throw new JuegoException("El juego no puede aceptar mas jugadores");
        }
        if (jugador.getSaldo() > (Juego.cantidadJugadores * Juego.apuestaBase)) {
            if (!jugadores.contains(jugador)) {
                HistoricoJugador j = new HistoricoJugador(jugador);
                jugadores.add(j);
                Sistema.getInstancia().avisar(Sistema.Eventos.nuevoJugador);
            } else {
                throw new JuegoException("El jugador ya fue ingresado al juego");
            }
        } else {
            throw new JuegoException("El saldo actual es menor a " + Juego.cantidadJugadores + " apuestas base");
        }
    }

    public void verificarInicioJuego() throws JuegoException {
        if (Juego.cantidadJugadores == jugadores.size()) {
            Sistema.getInstancia().empezarJuego();
        }
    }

    public void retirarJugador(HistoricoJugador jugador) throws JuegoException {
        if (jugadores.contains(jugador)) {
            jugadores.remove(jugador);
        } else {
            throw new JuegoException("El jugador no fue ingresado previamente.");
        }
    }

    private void removerJugadores() throws JuegoException {
        int cont = 0;
        for (HistoricoJugador j : jugadores) {
            HistoricoJugador copia = j;
            if (j.isActivo()) {
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
    
    public ArrayList<Jugador> getListaJugadores(){
        ArrayList<Jugador> aux = new ArrayList();
        for(HistoricoJugador j:jugadores){
            aux.add(j.getJugador());
        }
        return aux;
    }

    public void iniciarMano(double pozoAcumulado) throws JuegoException {
        descontarSaldoTodos();
        Mazo mazo = SistemaJuegos.getInstancia().getMazo();
        mazo.barajar();
        Mano nuevaMano = new Mano((Juego.apuestaBase * jugadores.size()) + pozoAcumulado, mazo, getListaJugadores());
        this.manos.add(nuevaMano);
        avisar(Eventos.nuevaMano);
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
        ganador.getJugador().agregarSaldo(actual.getPozoInicial() + actual.getTotalApostado());
        this.iniciarManoSig(0);
    }

    public void terminaManoSinApuestas() throws JuegoException {
        Mano actual = this.getManoActual();
        this.iniciarManoSig(actual.getPozoInicial());
    }

    public void descontarSaldoTodos() throws JuegoException {
        for (HistoricoJugador j : jugadores) {
            j.getJugador().descontarSaldo(Juego.apuestaBase);
        }
    }

    public void empezarJuego() throws JuegoException {     
        this.fechaInicio = new Date();
        this.enCurso = true;
        iniciarMano(0);
        Sistema.getInstancia().avisar(Sistema.Eventos.nuevoJuego);
    }

    public void finalizarJuego() {
        this.enCurso = false;
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

    public int cantidadJugadoresFaltan() {
//        return cantidadJugadores - jugadores.size();
        return jugadores.size();
    }

    public ArrayList<String> getDatosJugadores() {

//        String nombre = ""; //Solo tenemos los jugadores actuales, no estan TODOS los que participaron
//        double saldoInicial = 0; //No lo tenemos guardado en ningun lado
//        double totalApostado = 0; //Se puede calcular, incluimos la apuesta base ?
//        double totalGanado = 0;  //No lo tenemos guardado en ningun lado
//
//        return "Nombre: " + nombre //Retorna datos de todos los jugadores, no solo de uno
//                + " | Total Apostado" + totalApostado
//                + " | Saldo Incial: " + saldoInicial
//                + " | Total Ganado: " + totalGanado;
        ArrayList<String> lista = new ArrayList();
        for(HistoricoJugador j:jugadores){
            lista.add(j.toString());
        }
        return lista;

    }
}
