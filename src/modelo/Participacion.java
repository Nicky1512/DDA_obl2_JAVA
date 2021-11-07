
package modelo;

import modelo.Figuras.Figura;
import java.util.ArrayList;
import observador.Observable;

public class Participacion  {
    private double apuesta;
    private Jugador jugador;
    private ArrayList<Carta> cartas;
    private Figura figura;
    public enum Eventos {salir, apostar, pasar, observarCartas} ;

    public Participacion(double apuesta, Jugador jugador, ArrayList<Carta> cartas) {
        this.apuesta = apuesta;
        this.jugador = jugador;
        this.cartas = cartas;
    }
    
    public Participacion(){
            
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
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
