package modelo.Figuras;

import modelo.Carta;
import modelo.Palo;
import modelo.Participacion;

public class Color extends Figura {

    private Palo palo;

    public Color(String nombre, Palo palo) {
        super(nombre);
        this.palo = palo;
    }

    public Color() {
    }

    public Palo getPalo() {
        return palo;
    }

    public void setPalo(Palo palo) {
        this.palo = palo;
    }

    @Override
    public Figura determinarFigura(Carta[] cartas) {
        Boolean hayDistinto = false;
        Color ret = null;
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
            ret = new Color("Color", palo);
        }
        return ret;
    }

    @Override
    public Participacion desempatarFiguras(Participacion[] participaciones) {
        Participacion max = participaciones[0];
        for (Participacion p : participaciones) {
            if (((Color) max.getFigura()).getPalo().getOrden() < ((Color) p.getFigura()).getPalo().getOrden()) {
                max = p;
            }
        }
        return max;
    }
}
