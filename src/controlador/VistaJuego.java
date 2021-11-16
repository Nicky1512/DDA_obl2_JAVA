package controlador;

import java.lang.reflect.Array;
import modelo.Carta;

public interface VistaJuego {

    public void mostrarJuego();

    public void mostrarNombreJugador(String nombre);

    public void terminarJuego();

    public void terminarParticipacion();

    public void empezarJuego();

    public void observarCartas(Carta[] cartas);

    public void apostar(String m);

    public void pasar();

    public void error(String m);
    
    public void mostrarSaldoJugador(String d);
    

}
