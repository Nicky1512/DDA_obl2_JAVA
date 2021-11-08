package modelo;

import java.util.ArrayList;
import modelo.Figuras.Figura;

public class ControlJuegos {

    private ArrayList<Juego> juegos;
    private ArrayList<Figura> figuras;
    private Juego juegoAIniciar;
    private Mazo mazo;

    private static ControlJuegos instancia;

    private ControlJuegos() {
        juegos = new ArrayList();
        figuras = new ArrayList();
    }

    public ArrayList<Figura> getFiguras() {
        return figuras;
    }

    public void setFiguras(ArrayList<Figura> figuras) {
        this.figuras = figuras;
    }

    public static ControlJuegos getInstancia() {
        if (instancia == null) {
            instancia = new ControlJuegos();
        }
        return instancia;
    }

    public void setMazo(Mazo mazo) {
        this.mazo = mazo;
    }

    public void setJuegoAIniciar(Juego juegoAIniciar) {
        this.juegoAIniciar = juegoAIniciar;
    }

    public void agregarJuego(Juego unJuego) {

    }

    public void agregarFigura(Figura figura) {
        this.figuras.add(figura);
    }

    public void barajarMazo(){
        mazo.barajar();
    }

    public Carta[] repartirCartas(){
        Carta[] cartas = null;
        int contador = 0;
        do{
            cartas[contador] = mazo.robarCarta();
            contador++;
        }while(contador < 5);
        return cartas;
    }
}