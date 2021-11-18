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

    public int getOrden() {
        return orden;
    }

}
