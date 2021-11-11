package controlador;

import java.util.ArrayList;
import modelo.Juego;
import modelo.Sesion;

public interface VistaAdmin {
    
    public void mostrarSesiones(ArrayList<Sesion> juegos);
    //Sesiones de los juegos que estan empezados?
    
    public void mostrarJugadores();
    
    public void detallesPartida(String detalles);
    
    public void detallesJugadores(String detalles);
    
    public void mostrarNombreAdmin(String nombre);
    
    public void salir();
    
    public void error(String m);
    
}
