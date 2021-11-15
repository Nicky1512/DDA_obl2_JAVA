package modelo;

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

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    @Override
    public int compareTo(Carta o) {
        return o.orden - this.orden;    
    }

}
