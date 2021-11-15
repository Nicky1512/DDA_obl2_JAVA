package controlador;

import java.util.ArrayList;
import modelo.Juego;

public interface VistaAdmin {
    
    public void mostrarJuegos(ArrayList<Juego> juegos);
        
    public void detallesPartida(String detalles);
    
    public void detallesJugadores(String detalles);
    
    public void mostrarNombreAdmin(String nombre);
    
    public void salir();
    
    public void error(String m);   
}
