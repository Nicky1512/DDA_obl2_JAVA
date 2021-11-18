package modelo.Figuras;

import java.util.Arrays;
import modelo.Carta;
import modelo.Participacion;

public class SinFigura extends Figura {

    private Carta CartaMasAlta;

    public SinFigura(String nombre) {
        super(nombre);
    }

    public SinFigura(Carta c, String nombre) {
        super(nombre);
        this.CartaMasAlta = c;
    }

    public Carta getCartaMasAlta() {
        return CartaMasAlta;
    }

    @Override
    public Figura determinarFigura(Carta[] cartas) {
        SinFigura ret = null;
        Arrays.sort(cartas);
        Carta c = cartas[0];
        ret = new SinFigura(c, "SinFigura");
        return ret;

    }

    @Override
    public Participacion desempatarFiguras(Participacion[] participaciones) {
        Participacion max = participaciones[0];
        for (Participacion p : participaciones) {
            if (((SinFigura) max.getFigura()).getCartaMasAlta().getOrden() < ((SinFigura) p.getFigura()).getCartaMasAlta().getOrden()) {
                max = p;
            }
        }
        return max;
    }

}
