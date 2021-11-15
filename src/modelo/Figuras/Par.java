package modelo.Figuras;

import java.util.Arrays;
import modelo.Carta;
import modelo.Participacion;

public class Par extends Figura {

    private Carta cartaPar;
    private Carta cartaMasAlta;

    public Par(Carta cartaPar, Carta cartaMasAlta, String nombre) {
        super(nombre);
        this.cartaPar = cartaPar;
        this.cartaMasAlta = cartaMasAlta;
    }

    public Par(String nombre) {
        super(nombre);
    }

    public Carta getCartaPar() {
        return cartaPar;
    }

    public Carta getCartaMasAlta() {
        return cartaMasAlta;
    }

    public void setCartaMasAlta(Carta cartaMasAlta) {
        this.cartaMasAlta = cartaMasAlta;
    }

    public void setCartaPar(Carta cartaPar) {
        this.cartaPar = cartaPar;
    }

    @Override
    public Figura determinarFigura(Carta[] cartas) {
        Par ret = null;
        Boolean hayPar = false;
        Carta cartaPar = null;
        Carta cartaMayor = null;
        Arrays.sort(cartas); //QQ j 2 3  K QQ 2 3   
        //TODO: Completar algoritmo para encontrar pares
        for (int i = 0; i < cartas.length; i++) {
            for (int j = 1; j < cartas.length - 1; j++) {
                if (cartas[i].getValor().equals(cartas[j].getValor())) {
                    hayPar = true;
                    cartaPar = cartas[i];
                    if (i == 0) {
                        cartaMayor = cartas[j + 1];
                    } else {
                        cartaMayor = cartas[0];
                    }
                    break;
                }
            }
        }
        if (hayPar) {
            ret = new Par(cartaPar, cartaMayor, "Par");
        }
        return ret;
    }

    @Override
    public Participacion desempatarFiguras(Participacion[] participaciones) {
        Participacion max = participaciones[0];
        for (Participacion p : participaciones) {
            if (((Par) max.getFigura()).getCartaPar().getOrden() < ((Par) p.getFigura()).getCartaPar().getOrden()) {
                max = p;
            } else if (((Par) max.getFigura()).getCartaPar().getOrden() == ((Par) p.getFigura()).getCartaPar().getOrden()) {
                if (((Par) max.getFigura()).getCartaMasAlta().getOrden() < ((Par) p.getFigura()).getCartaMasAlta().getOrden()) {
                    max = p;
                }
            }
        }
        return max;
    }
}
