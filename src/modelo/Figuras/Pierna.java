package modelo.Figuras;

import java.util.Arrays;
import modelo.Carta;

public class Pierna extends Figura {

    private Carta cartaClave;

    public Pierna(Carta cartaClave, String nombre) {
        super(nombre);
        this.cartaClave = cartaClave;
    }

    public Pierna() {
    }

    public Carta getCartaClave() {
        return cartaClave;
    }

    public void setCartaClave(Carta cartaClave) {
        this.cartaClave = cartaClave;
    }

    @Override
    public Boolean determinarFigura(Carta[] cartas) {
        Boolean hayTres = false;
        Carta cartaClave = null;
        Arrays.sort(cartas);
        for (int i = 0; i < cartas.length - 2; i++) {
            if (cartas[i].getValor().equals(cartas[i + 1].getValor()) && cartas[i].getValor().equals(cartas[i + 2].getValor())) {
                hayTres = true;
                cartaClave = cartas[i];
                break;
            }
        }
        if (hayTres) {
            this.setNombre("Pierna");
            this.setCartaClave(cartaClave);
        }
        return hayTres;
    }

}
