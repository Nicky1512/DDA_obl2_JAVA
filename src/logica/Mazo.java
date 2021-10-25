package logica;

import java.util.ArrayList;
import java.util.Collections;

public class Mazo {
    
    private ArrayList<Carta> cartas;

    public Mazo(ArrayList<Carta> cartas) {
        this.cartas = cartas;
        this.barajar();
    }
    
    public void barajar(){
        Collections.shuffle(cartas);
    }
    
    public Carta robarCarta(){
        Carta ret = cartas.get(0);
        cartas.remove(0);
        return ret;
    }
}
