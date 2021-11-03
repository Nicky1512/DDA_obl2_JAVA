/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.Figuras;

import java.util.Arrays;
import modelo.Carta;

/**
 *
 * @author Bruno
 */
public class SinFigura extends Figura {
    

    private Carta CartaMasAlta;
    
    public SinFigura(){
        
    }
    public SinFigura(Carta c, String nombre){
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
        ret = new SinFigura(c, "Sin figura");
        return ret;
        
    }
    
}
