package controlador;

import java.util.ArrayList;
import modelo.Juego;

public interface VistaAdmin {
    public void mostrarPartidas(ArrayList<Juego> juegos);
    public void salir();
}
