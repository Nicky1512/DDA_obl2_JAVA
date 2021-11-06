package modelo;

import java.util.ArrayList;

public class Mano {

    private int pozoInicial;
    private Mazo mazo;
    private ArrayList<Participacion> participantes;
    private double totalApostado;

    public Mano(int pozoInicial, Mazo mazo) {
        this.pozoInicial = pozoInicial;
        this.mazo = mazo;
    }

    public int getPozoInicial() {
        return pozoInicial;
    }

    public void setPozoInicial(int pozoInicial) {
        this.pozoInicial = pozoInicial;
    }

    public Mazo getMazo() {
        return mazo;
    }

    public void setMazo(Mazo mazo) {
        this.mazo = mazo;
    }

    public ArrayList<Participacion> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(ArrayList<Participacion> participantes) {
        this.participantes = participantes;
    }
}
