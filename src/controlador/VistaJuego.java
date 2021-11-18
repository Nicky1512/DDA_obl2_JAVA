package controlador;

import java.util.ArrayList;
import modelo.Carta;
import modelo.Participacion;

public interface VistaJuego {

    public void apostar(String m);

    public void pagarApuestaFijada();

    public void terminarJuego();

    public void terminarParticipacion();

    public void pasar();

    public void modificarEstado(String estado);

    public void observarCartas(ArrayList<Carta> cartas);

    public void mostrarJugadoresActivos(ArrayList<Participacion> p);

    public void mostrarSaldoJugador(String d);

    public void mostrarApuestaActual(String monto);

    public void mostrarNombreJugador(String nombre);

    public void mostrarNombreJugadorApostador(String nombre);

    public void mostrarMontoPozoActual(String monto);

    public void mostarGanador(String s, Carta[] c);

    public void mostrarFigura(String figura);

    public void error(String m);
    
    public void salir(String m);
}
