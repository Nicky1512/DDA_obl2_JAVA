package controlador;

import modelo.Mazo;

public interface VistaJuego {
    public void mostrarJuego();
    
    public void mostrarNombreJugador(String nombre);
    
    public void mostrarCartas();
    public void terminarJuego();
    public void explusarJugador();
    public void empezarJuego();
    public void observarCartas();
    public void apostar(double a);
    public void pasar();
    
}