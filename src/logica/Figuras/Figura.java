package logica.Figuras;

import logica.Carta;

public abstract class Figura {
    
    private String nombre;
    
    public abstract Figura determinarFigura(Carta[] cartas);
}
