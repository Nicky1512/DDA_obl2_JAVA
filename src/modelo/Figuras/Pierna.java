package modelo.Figuras;

import java.util.Arrays;
import modelo.Carta;
import modelo.Participacion;

public class Pierna extends Figura {

    private Carta cartaClave;

    public Pierna(Carta cartaClave, String nombre) {
        super(nombre);
        this.cartaClave = cartaClave;
    }

    public Pierna(String nombre) {
       super(nombre);
    }

    public Carta getCartaClave() {
        return cartaClave;
    }

    public void setCartaClave(Carta cartaClave) {
        this.cartaClave = cartaClave;
    }

    @Override
    public Figura determinarFigura(Carta[] cartas) {
        Pierna ret = null;
        Boolean hayTres = false;
        Carta cartaClave = null;
        try{
            Arrays.sort(cartas);
        }catch(Exception ex){
            throw ex;
        }
        for (int i = 0; i < cartas.length - 2; i++) {
            if (cartas[i].getValor().equals(cartas[i + 1].getValor()) && cartas[i].getValor().equals(cartas[i + 2].getValor())) {
                hayTres = true;
                cartaClave = cartas[i];
                break;
            }
        }
        if (hayTres) {
            ret = new Pierna(cartaClave, "Pierna");
        }
        return ret;
    }

    @Override
    public Participacion desempatarFiguras(Participacion[] participaciones) {
        Participacion max = participaciones[0];
        for (Participacion p : participaciones) {
            if (((Pierna) max.getFigura()).getCartaClave().getOrden() < ((Pierna) p.getFigura()).getCartaClave().getOrden()) {
                max = p;
            }
        }
        return max;
    }

}
