package logica.Figuras;

import logica.Carta;

public class Pierna extends Figura{

    private Carta cartaClave;

    public Carta getCartaClave() {
        return cartaClave;
    }

    public void setCartaClave(Carta cartaClave) {
        this.cartaClave = cartaClave;
    }
    
    @Override
    public Figura determinarFigura(Carta[] cartas) {
        Par ret = null;
        Boolean hayTres = false;
        while(!hayTres){
            //TODO: Buscar tres cartas del mismo numero
        }
        return ret;
    }
    
}
