package modelo;

import java.util.ArrayList;
import java.util.Collections;

public class Mazo {

    private ArrayList<Carta> cartas;

    public Mazo(ArrayList<Carta> cartas) {
        this.cartas = cartas;
        this.barajar();
    }

    public void barajar() {
        Collections.shuffle(cartas);
    }

    public Carta robarCarta() {
        if (cartas.size() > 0) {
            Carta ret = cartas.get(0);
            cartas.remove(0);
            return ret;
        }
        return null;
    }

    public ArrayList<Carta> repartirCartas() {
        ArrayList<Carta> cartas = null;
        int contador = 0;
        do {
            cartas.add(this.robarCarta());
            contador++;
        } while (contador < 5);
        return cartas;
    }
}
