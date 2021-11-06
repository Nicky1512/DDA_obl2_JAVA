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

    public void retirarJugador(Participacion jugador) {
        //TODO: Implementar
    }

    public String agregarJugador(Participacion jugador) {
        //TODO: Implementar
        return "";
    }

    //Remueve jugadores con saldo 0
    private void removerJugadores() {
        //Hacer copia para que no de error de ejecucion ?
        for (Jugador j : jugadores) {

            if (j.getSaldo() == 0) {
                jugadores.remove(j);
            }
        }
    }
}
