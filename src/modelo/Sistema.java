package modelo;

import java.util.ArrayList;
import modelo.Figuras.Figura;
import modelo.excepciones.JuegoException;
import observador.Observable;

public class Sistema extends Observable {

    private SistemaUsuarios cUsuarios = SistemaUsuarios.getInstancia();
    private SistemaJuegos cJuegos = SistemaJuegos.getInstancia();

    public enum Eventos {nuevoJugador, nuevoJuego, nuevaMano, quitarJugador};

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

    public ArrayList<Juego> getJuegosEnCurso() {
        return cJuegos.getJuegosEnCurso();
    }

    public void agregarAdmin(Administrador a) {
        cUsuarios.agregarAdmin(a);
    }

    public void empezarJuego() throws JuegoException {
        cJuegos.empezarJuego();
    }

    public void vericarInicioJuego() throws JuegoException{
        cJuegos.verificarInicioJuego();
    }
    
    public void ingresarJugador(HistoricoJugador jugador) throws JuegoException {
        cJuegos.agregarJugador(jugador);
    }

    public void recibirApuesta(double monto, Juego juego, Jugador jugador) throws JuegoException {
        cJuegos.recibirApuesta(monto, juego, jugador);
    }

    public ArrayList<Jugador> getJugadores() {
        return cUsuarios.getJugadores();
    }

    public void expulsarJugador(HistoricoJugador j) throws JuegoException{
        cJuegos.quitarJugador(j);
    }
    
    public Juego getJuegoAIniciar(){
        return cJuegos.getJuegoAIniciar();
    }

    public void terminarParticipacion(Jugador jugador, Juego juego) throws JuegoException {
        cJuegos.terminarParticipacion(jugador, juego);
    }
}
