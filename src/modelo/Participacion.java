package modelo;

import modelo.Figuras.Figura;
import java.util.ArrayList;
import observador.Observable;

public class Participacion extends Observable {

    private double apuesta;
    private Jugador jugador;
    private ArrayList<Carta> cartas;
    private Figura figura;

    public enum Eventos {
        salir, apostar, pasar, observarCartas
    };

    public Participacion(Jugador jugador, ArrayList<Carta> cartas) {
        this.jugador = jugador;
        this.cartas = cartas;
    }

    public Figura getFigura() {
        return figura;
    }

    public Participacion() {

    }

    public Jugador getJugador() {
        return jugador;
    }

    public double getApuesta() {
        return apuesta;
    }

    public ArrayList<Carta> getCartas() {
        return cartas;
    }

    public Boolean realizarApuesta(double monto) {
        if (this.jugador.puedoApostar(monto)) {
            this.apuesta = monto;
            return true;
        }
        return false;
    }

    public void figurasEnMano() {
        for (Figura fig : ControlJuegos.getInstancia().getFiguras()) {
            Figura miFigura = fig.determinarFigura((Carta[]) this.cartas.toArray());
            if (miFigura != null) {
                this.figura = miFigura;
                break;
            }
        }
    }
}
