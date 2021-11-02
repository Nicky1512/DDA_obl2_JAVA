package logica.Figuras;

import java.util.Arrays;
import logica.Carta;

public class Pierna extends Figura {

    private Carta cartaClave;

    public Carta getCartaClave() {
        return cartaClave;
    }

    public void setCartaClave(Carta cartaClave) {
        this.cartaClave = cartaClave;
    }

    public Pierna(Carta cartaClave, String nombre) {
        super(nombre);
        this.cartaClave = cartaClave;
    }

    @Override
    public Figura determinarFigura(Carta[] cartas) {
        Color ret = null;
        Boolean hayTres = false;
        Arrays.sort(cartas);
        while (!hayTres) {
            //TODO: Buscar tres cartas del mismo numero
        }
        return ret;
    }

}
