package controlador;

public interface VistaJuego {
    
    public void mostrarJuego();
    public void mostrarNombreJugador(String nombre);
    
    public void terminarJuego();
    public void explusarJugador();
    public void empezarJuego();
    public void observarCartas();
    public void apostar();
    public void pasar();
    public void error(String m);
    
}