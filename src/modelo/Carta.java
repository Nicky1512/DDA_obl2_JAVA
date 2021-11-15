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

    public String getImgPath() {
        return imgPath;
    }

    public void setUrlImg(String imgPath) {
        this.imgPath = imgPath;
    }

    @Override
    public int compareTo(Carta o) {
        return o.orden - this.orden;
    }
}
