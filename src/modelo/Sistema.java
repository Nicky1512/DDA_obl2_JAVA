package modelo;

import java.util.ArrayList;
import modelo.Figuras.Figura;
import observador.Observable;

public class Sistema extends Observable {

    private ControlUsuarios cUsuarios = ControlUsuarios.getInstancia();
    private ControlJuegos cJuegos = ControlJuegos.getInstancia();
    
    public enum Eventos{cambioListaJugadoresEnLinea,nuevoJuego};

    private static Sistema instancia = new Sistema();

    public static Sistema getInstancia() {
        return instancia;
    }

    private Sistema() {
    }

    public Usuario loginJugador(String usuario, String pass) {
        return cUsuarios.loginJugador(usuario, pass);
    }

    public Administrador loginAdmin(String usuario, String password) {
        return cUsuarios.loginAdmin(usuario, password);
    }

    public void agregarFigura(Figura figura) {
        cJuegos.agregarFigura(figura);
    }

    public void agregarJugador(Jugador j) {
        cUsuarios.agregarJugador(j);
    }

    public void agregarAdmin(Administrador a) {
        cUsuarios.agregarAdmin(a);
    }

    public void empezarJuego() {
        cJuegos.empezarJuego();
    }

}
