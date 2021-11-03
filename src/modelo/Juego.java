package modelo;

import java.util.ArrayList;
import observador.Observable;

public class Juego extends Observable{

    private Mazo mazo;
    private ArrayList<Participacion> participantes;
    private Pozo pozo;

    public Juego(Mazo mazo, ArrayList<Participacion> participantes, Pozo pozo) {
        this.mazo = mazo;
        this.participantes = participantes;
        this.pozo = pozo;
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
            
            if(p.getJugador().getSaldo() == 0){
                participantes.remove(p);
            }
        }
    }
}
