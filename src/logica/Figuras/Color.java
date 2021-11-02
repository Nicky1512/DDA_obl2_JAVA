package logica.Figuras;

import logica.Carta;
import logica.Palo;

public class Color extends Figura {

    public Color(String nombre) {
        super(nombre);
    }

    @Override
    public Figura determinarFigura(Carta[] cartas) {
        Color ret = null;
        Boolean hayDistinto = false;
        int pos = 1;
        Palo palo = cartas[0].getPalo();
        while (!hayDistinto && pos <= cartas.length) {
            if (palo == cartas[pos].getPalo()) {
                pos++;
            } else {
                hayDistinto = true;
            }
        }
        if (!hayDistinto) {
            ret = new Color("Color");
        }
        return ret;
    }
}
