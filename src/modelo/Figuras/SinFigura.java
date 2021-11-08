package modelo.Figuras;

import java.util.Arrays;
import modelo.Carta;

public class SinFigura extends Figura {

    private Carta CartaMasAlta;

    public SinFigura(String nombre) {
        super(nombre);
    }

    public SinFigura(Carta c, String nombre) {
        super(nombre);
        this.CartaMasAlta = c;
    }

    public Carta getCartaMasAlra() {
        return CartaMasAlta;
    }

    public void setCartaMasAlra(Carta CartaMasAlra) {
        this.CartaMasAlta = CartaMasAlra;
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
    public Figura desempatarFiguras(Figura[] figuras) {
        throw new UnsupportedOperationException("Not supported yet."); //Todo: Crear metodo para desempatar sinfigura
    }

}
