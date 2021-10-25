
package logica;

import java.util.ArrayList;

public class Participacion {
    private double apuesta;
    private Jugador jugador;
    private ArrayList<Carta> cartas;
    private Figura figura;

    public Participacion(double apuesta, Jugador jugador, ArrayList<Carta> cartas, Figura figura) {
        this.apuesta = apuesta;
        this.jugador = jugador;
        this.cartas = cartas;
        this.figura = figura;
    }
    
    public Participacion(){
        
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public Figura getFigura() {
        return figura;
    }

    public void setFigura(Figura figura) {
        this.figura = figura;
    }


    public double getApuesta() {
        return apuesta;
    }

    public void setApuesta(double apuesta) {
        this.apuesta = apuesta;
    }


    public ArrayList<Carta> getCartas() {
        return cartas;
    }

    public void setCartas(ArrayList<Carta> cartas) {
        this.cartas = cartas;
    }

    
    
}
