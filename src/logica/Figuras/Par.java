package logica.Figuras;

import logica.Carta;

public class Par extends Figura{

    private Carta cartaPar;

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
        while(!hayPar){
            //TODO: Buscar carta con su par.
        }
        return ret;
    }

}
