package logica;

public class Carta {

    public Carta(String valor, Palo palo) {
        this.valor = valor;
        this.palo = palo;
    }
    
    private String valor;
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
    
}
