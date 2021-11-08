package modelo;

import java.util.ArrayList;
import modelo.Figuras.Figura;

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

    public Jugador determinarGanador() {
        for (Figura fig : ControlJuegos.getInstancia().getFiguras()) {
            ArrayList<Participacion> participaciones = new ArrayList<Participacion>();
            for (Participacion p : participantes) {
                if (fig.getClass().getName() == p.getFigura().getClass().getName()) {
                    participaciones.add(p);
                }
            }
            if (participaciones.size() > 1) {
                //Get figuras
                //fig.desempatarFiguras(figuras);
                //Se que figura gano, pero no se de quien es
                return null;
            } else if (participaciones.size() == 1) {
                return participaciones.get(0).getJugador();
            }
        }
        return null;
    }
}
