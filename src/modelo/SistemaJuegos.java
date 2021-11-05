package modelo;

import java.util.ArrayList;
import modelo.Figuras.Figura;

public class SistemaJuegos {
    
    private ArrayList<Juego> juegos = new ArrayList();
    private ArrayList<Figura> figuras = new ArrayList();
    private Mazo mazo;
    private Juego juegoAIniciar = null;
    
    public void agregarJuego(Juego unJuego){
        
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
