package modelo.Figuras;

import modelo.Carta;
import modelo.Participacion;

public abstract class Figura {
    
    private String nombre;

    public Figura(){}
    
    public Figura(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
    
    public abstract Figura determinarFigura(Carta[] cartas);
    
    public abstract Participacion desempatarFiguras(Participacion[] participaciones);
}
