package modelo;

import java.util.ArrayList;
import modelo.Figuras.Figura;
import modelo.excepciones.JuegoException;
import observador.Observable;

public class Sistema extends Observable {

    private SistemaUsuarios cUsuarios = SistemaUsuarios.getInstancia();
    private SistemaJuegos cJuegos = SistemaJuegos.getInstancia();

    public enum Eventos {cambioListaJugadoresEnLinea, nuevoJuego, nuevaMano};

    private static Sistema instancia = new Sistema();

    private Sistema() {}
    
    public static Sistema getInstancia() {
        return instancia;
    }

    public Usuario loginJugador(String usuario, String pass) throws JuegoException {
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

    public void empezarJuego() throws JuegoException {
        cJuegos.empezarJuego();
    }

    public ArrayList<Sesion> getConexiones() {
        return cJuegos.getConexiones();
    }

    public void ingresarJugador(Jugador jugador) throws JuegoException {
        cJuegos.agregarJugador(jugador);
    }

    public void recibirApuesta(double monto, Juego juego, Participacion participacion) throws JuegoException {
        cJuegos.recibirApuesta(monto, juego, participacion);
    }

    public ArrayList<Jugador> getJugadores() {
        return cUsuarios.getJugadores();
    }

//    public void expulsarJugador(Participacion p, Mano m){
//        cJuegos.g
//    }
    
    public Juego getJuegoAIniciar(){
        return cJuegos.getJuegoAIniciar();
    }
    
    
}
