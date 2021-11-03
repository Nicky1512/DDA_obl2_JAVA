package modelo;

public class Palo {
    private String nombre;
    private int orden;

    public Palo(String nombre, int orden) {
        this.nombre = nombre;
        this.orden = orden;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }
    
}
