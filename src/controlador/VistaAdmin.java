package controlador;

import java.util.ArrayList;
import modelo.Juego;

public interface VistaAdmin {
    
    public void mostrarPartidas(ArrayList<Juego> juegos);
    //Sesiones de los juegos que estan empezados?
    
    public void mostrarJugadores();
    
    public void detallesPartida(String detalles);
    
    public void detallesJugadores(String detalles);
    
    public void salir();
    
}
