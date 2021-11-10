package modelo;

import java.util.ArrayList;
import java.util.Date;
import observador.Observable;

public class Juego extends Observable {

    private static int cantidadJugadores;
    private static double apuestaBase;
    private Date fechaInicio;
//    la fecha puede estar solo en sesion
    private ArrayList<Mano> manos;
    private ArrayList<Jugador> jugadores;

    
    
    
    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public ArrayList<Mano> getManos() {
        return manos;
    }

    public void setManos(ArrayList<Mano> manos) {
        this.manos = manos;
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
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

    public Juego() {
    }

    public void retirarJugador(Jugador jugador) {
        //TODO: Implementar
    }

    public String agregarJugador(Jugador jugador) {
        //TODO: Implementar
        return "";
    }

    public Mano getManoActual() {
        if (manos.size() > 0) {
            Mano ultima = manos.get(manos.size() - 1);
            return ultima;
        } else {
            return null;
        }
    }

    private void removerJugadores() {
        //Hacer copia del jugador para que no de error de ejecucion ?
        for (Jugador j : jugadores) {

            if (j.getSaldo() == 0) {
                jugadores.remove(j);
            }
        }
    }

    public void iniciarManoSig(double pozoAcumulado) {
        removerJugadores();
        if (this.jugadores.size() <= 1) {
            finalizarJuego();
        }
        iniciarMano(pozoAcumulado);
    }

    public void iniciarMano(double pozoAcumulado) {
        descontarSaldoTodos();
        Mazo mazo = ControlJuegos.getInstancia().getMazo();
        mazo.barajar();
        Mano nuevaMano = new Mano(Juego.apuestaBase * jugadores.size() + pozoAcumulado, mazo, this.jugadores);
        this.manos.add(nuevaMano);
    }

    private void descontarSaldoTodos() {
        int cont = 0;
        for (Jugador j : jugadores) {
            Boolean desconto = j.descontarSaldo(Juego.apuestaBase);
            if (!desconto) {
                retirarJugador(j);
            }
            cont++;
        }
        if (cont == 1) {
            this.jugadores.get(0).agregarSaldo(apuestaBase); //Le devuelve el saldo al unico jugador que se desconto
            finalizarJuego();
        }
    }

    public void terminarMano() { //Deberia retornar una participacion
        Mano actual = this.getManoActual();
        Jugador ganador = actual.determinarGanador(); //Deberiamos retornar una participacion en lugar de un jugador
        ganador.agregarSaldo(actual.getPozoInicial() + actual.getTotalApostado());
        this.iniciarManoSig(0);
    }

    public void terminaManoSinApuestas() {
        Mano actual = this.getManoActual();
        this.iniciarManoSig(actual.getPozoInicial());
    }

    public void finalizarJuego() {
        //TODO: Implementar
    }

    public void empezarJuego() {
        this.fechaInicio = new Date();
        iniciarMano(0);
    }
}
