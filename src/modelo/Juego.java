package modelo;

import java.util.ArrayList;
import java.util.Date;
import observador.Observable;

public class Juego extends Observable {

    private Date fechaInicio;
    private static int cantidadJugadores;
    private static double apuestaBase;
    private ArrayList<Participacion> participantes;
    
//    Pozo existe?, Mazo?
    private Mazo mazo;
    private Pozo pozo;

    public Juego(Mazo mazo, Pozo pozo) {
        this.mazo = mazo;
        this.pozo = pozo;
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
    
    public ArrayList<Participacion> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(ArrayList<Participacion> participantes) {
        this.participantes = participantes;
    }

    public Pozo getPozo() {
        return pozo;
    }

    public void setPozo(Pozo pozo) {
        this.pozo = pozo;
    }

    public Mazo getMazo() {
        return mazo;
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
        for (Participacion p : participantes) {

            if (p.getJugador().getSaldo() == 0) {
                participantes.remove(p);
            }
        }
    }
}
