package modelo;

public class Carta implements Comparable<Carta> {

    private String valor;
    private Palo palo;
    private int orden;
    private String imgPath;

    public Carta(String valor, int orden, Palo palo, String imgPath) {
        this.valor = valor;
        this.orden = orden;
        this.palo = palo;
        this.imgPath = imgPath;
    }

    public String getValor() {
        return valor;
    }

    public Palo getPalo() {
        return palo;
    }

    public int getOrden() {
        return orden;
    }

    public String getImgPath() {
        return imgPath;
    }

    @Override
    public int compareTo(Carta o) {
        return o.orden - this.orden;
    }
}
