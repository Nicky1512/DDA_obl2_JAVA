package modelo;

import java.util.ArrayList;
import modelo.Figuras.Figura;
import observador.Observable;

public class Sistema extends Observable {

    private ControlUsuarios cUsuarios = ControlUsuarios.getInstancia();
    private ControlJuegos cJuegos = ControlJuegos.getInstancia();

    private static Sistema instancia = new Sistema();

    public static Sistema getInstancia() {
        return instancia;
    }

    private Sistema() {
    }

    public Usuario loginUsuario(String usuario, String pass) {
        return cUsuarios.loginUsuario(usuario, pass);
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

}
