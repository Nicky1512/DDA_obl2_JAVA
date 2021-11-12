package modelo;

import java.util.ArrayList;
import modelo.Figuras.Figura;
import modelo.excepciones.JuegoException;

public class Mano {

    private double pozoInicial;
    private Mazo mazo;
    private ArrayList<Participacion> participantes;
    private double totalApostado;
    private double apuestaFijada;

    public Mano(double pozoInicial, Mazo mazo, ArrayList<Jugador> jugadores) {
        this.pozoInicial = pozoInicial;
        this.mazo = mazo;
        this.participantes = new ArrayList<Participacion>();
        this.apuestaFijada = 0;
        iniciarMano(jugadores);
    }

    private void iniciarMano(ArrayList<Jugador> jugadores) {
        for (Jugador j : jugadores) {
            ArrayList<Carta> cartas = this.mazo.repartirCartas();
            Participacion newPart = new Participacion(j);
            newPart.setCartas(cartas);
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

    public void recibirApuesta(double monto, Participacion participacion) throws JuegoException {
        Boolean puedoApostar = participacion.getJugador().puedoApostar(monto);
        if (puedoApostar) {
            if (this.apuestaFijada == 0) {
                this.apuestaFijada = monto;
                this.completarApuesta(monto, participacion);
            }
            if (this.apuestaFijada == monto) {
                this.completarApuesta(monto, participacion);
            } else {
                throw new JuegoException("La apuesta ingresada es distinta a la apuesta fijada.");
            }
        } else {
            throw new JuegoException("El jugador no dispone del saldo para realizar la apuesta");
        }
    }

    public void completarApuesta(double monto, Participacion participacion) throws JuegoException{
        this.totalApostado += monto;
        participacion.realizarApuesta(monto);
    }
    
    public Participacion determinarGanador() {
        for (Figura fig : SistemaJuegos.getInstancia().getFiguras()) {
            ArrayList<Participacion> participaciones = new ArrayList<Participacion>();
            for (Participacion p : participantes) {
                if (fig.getClass().getName() == p.getFigura().getClass().getName()) {
                    participaciones.add(p);
                }
            }
            if (participaciones.size() > 1) {
                Participacion ganador = fig.desempatarFiguras((Participacion[]) participaciones.toArray());
                return ganador;
            } else if (participaciones.size() == 1) {
                return participaciones.get(0);
            }
        }
        return null;
    }
}
