package logica.Figuras;

import java.util.Arrays;
import logica.Carta;

public class Par extends Figura {

    private Carta cartaPar;
    private Carta cartaMasAlta;

    public Par(Carta cartaPar, Carta cartaMasAlta, String nombre) {
        super(nombre);
        this.cartaPar = cartaPar;
        this.cartaMasAlta = cartaMasAlta;
    }

    public Carta getCartaPar() {
        return cartaPar;
    }

    public void setCartaPar(Carta cartaPar) {
        this.cartaPar = cartaPar;
    }

    @Override
    public Figura determinarFigura(Carta[] cartas) {
        Par ret = null;
        Boolean hayPar = false;
        Arrays.sort(cartas);
        //TODO: Completar algoritmo para encontrar pares
        return ret;
    }
}
