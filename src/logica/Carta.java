package logica;

import java.util.Comparator;

public class Carta implements Comparator<Carta> {

    public Carta(String valor, int orden, Palo palo) {
        this.valor = valor;
        this.orden = orden;
        this.palo = palo;
    }

    private String valor;
    private int orden;
    private Palo palo;

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Palo getPalo() {
        return palo;
    }

    public void setPalo(Palo palo) {
        this.palo = palo;
    }

    @Override
    public int compare(Carta o1, Carta o2) {
        return o1.orden - o2.orden;
    }

}
