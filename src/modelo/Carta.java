package modelo;

import java.util.Comparator;

public class Carta implements Comparable<Carta> {

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

//    @Override
//    public int compare(Carta o1, Carta o2) {
//        return o2.orden - o1.orden;
//    }

    @Override
    public int compareTo(Carta o) {
        return o.orden - this.orden;    
    }

}
