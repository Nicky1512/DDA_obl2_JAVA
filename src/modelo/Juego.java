package modelo;

import java.util.ArrayList;
import java.util.Date;
import observador.Observable;

public class Juego extends Observable {

    private static int cantidadJugadores;
    private static double apuestaBase;
    private Date fechaInicio;
    private ArrayList<Mano> manos;
    private ArrayList<Jugador> jugadores;

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

    private void removerJugadores() {
        //Hacer copia para que no de error de ejecucion ?
        for (Jugador j : jugadores) {

            if (j.getSaldo() == 0) {
                jugadores.remove(j);
            }
        }
    }

    public void iniciarMano(double pozoAcumulado) {
        removerJugadores();     //Remueve jugadores con saldo 0
        if (this.jugadores.size() == 1) {
            finalizarJuego();
        }
        descontarSaldoTodos();
        Mazo mazo = ControlJuegos.getInstancia().getMazo();
        mazo.barajar();
        Mano nuevaMano = new Mano(Juego.apuestaBase * jugadores.size() + pozoAcumulado, mazo, this.jugadores);
        this.manos.add(nuevaMano);
    }

    private void descontarSaldoTodos() {
        for (Jugador j : jugadores) {
            Boolean desconto = j.descontarSaldo(Juego.apuestaBase);
            if (!desconto) {
                retirarJugador(j);
            }
        }
    }

    public void terminarMano() {

    }

    public void finalizarJuego() {

    }

    public void empezarJuego() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
