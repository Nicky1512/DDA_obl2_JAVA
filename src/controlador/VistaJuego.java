package controlador;

import java.util.ArrayList;
import modelo.Carta;
import modelo.Participacion;

public interface VistaJuego {

    public void mostrarJuego();

    public void mostrarNombreJugador(String nombre);

    public void terminarJuego();

    public void terminarParticipacion();
    
    public void mostrarJugadoresActivos(ArrayList<Participacion> p);

    public void empezarJuego();

    public void observarCartas(ArrayList<Carta> cartas);

    public void apostar(String m);

    public void pasar();

    public void error(String m);
    
    public void mostrarSaldoJugador(String d);
    
    public void mostrarApuestaActual(String monto);
    
    public void mostrarNombreJugadorApostador(String nombre);
    
    public void mostrarMontoPozoActual(String monto);
    
    public void pagarApuestaFijada();
    
    public void modificarEstado(String estado);
    
    public void mostarGanador(String s);
   
    public void mostrarFigura(String figura);
}
