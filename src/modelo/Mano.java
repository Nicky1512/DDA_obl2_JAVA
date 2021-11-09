package modelo;

import java.util.ArrayList;
import modelo.Figuras.Figura;

public class Mano {

    private double pozoInicial;
    private Mazo mazo;
    private ArrayList<Participacion> participantes;
    private double totalApostado;

    public Mano(double pozoInicial, Mazo mazo, ArrayList<Jugador> jugadores) {
        this.pozoInicial = pozoInicial;
        this.mazo = mazo;
        this.participantes = new ArrayList<Participacion>();
        iniciarMano(jugadores);
    }

    private void iniciarMano(ArrayList<Jugador> jugadores) {
        for (Jugador j : jugadores) {
            ArrayList<Carta> cartas = this.mazo.repartirCartas();
            Participacion newPart = new Participacion(j, cartas);
            this.participantes.add(newPart);
        }
    }

    public double getTotalApostado() {
        return totalApostado;
    }

    public double getPozoInicial() {
        return pozoInicial;
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

    public Jugador determinarGanador() {
        for (Figura fig : ControlJuegos.getInstancia().getFiguras()) {
            ArrayList<Participacion> participaciones = new ArrayList<Participacion>();
            for (Participacion p : participantes) {
                if (fig.getClass().getName() == p.getFigura().getClass().getName()) {
                    participaciones.add(p);
                }
            }
            if (participaciones.size() > 1) {
                Participacion ganador = fig.desempatarFiguras((Participacion[]) participaciones.toArray());
                return ganador.getJugador();
            } else if (participaciones.size() == 1) {
                return participaciones.get(0).getJugador();
            }
        }
        return null;
    }
}
