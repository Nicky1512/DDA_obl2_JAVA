package modelo;

import java.util.ArrayList;
import modelo.Figuras.Figura;
import modelo.excepciones.JuegoException;

public class SistemaJuegos {

    private Juego juegoAIniciar;
    private Mazo mazo;

    private ArrayList<Juego> juegos;
    private ArrayList<Figura> figuras;
    private ArrayList<Sesion> conectados;

    private static SistemaJuegos instancia;

    private SistemaJuegos() {
        juegos = new ArrayList();
        figuras = new ArrayList();
    }

    public ArrayList<Figura> getFiguras() {
        return figuras;
    }

    public Mazo getMazo() {
        return mazo;
    }

    public static SistemaJuegos getInstancia() {
        if (instancia == null) {
            instancia = new SistemaJuegos();
        }
        return instancia;
    }

    public void setMazo(Mazo mazo) {
        this.mazo = mazo;
    }

    public void setJuegoAIniciar(Juego juegoAIniciar) {
        this.juegoAIniciar = juegoAIniciar;
    }

    public ArrayList<Sesion> getConexiones() {
        return conectados;
    }

    public ArrayList<Juego> getJuegosEnCurso() {
        ArrayList<Juego> ret = new ArrayList<>();
        for (Juego j : juegos) {
            if (j.estaEnCurso()) {
                ret.add(j);
            }
        }
        return ret;
    }

    public Juego getJuegoAIniciar() {
        return juegoAIniciar;
    }
    
    public void empezarJuego() throws JuegoException {
        juegoAIniciar.empezarJuego();
        Juego nuevo = new Juego();
        this.juegos.add(nuevo);
        this.juegoAIniciar = nuevo;
    }

    public void agregarFigura(Figura figura) {
        this.figuras.add(figura);
    }

    public void agregarJugador(Jugador jugador) throws JuegoException {
        this.juegoAIniciar.agregarJugador(jugador);
    }

    public void recibirApuesta(double monto, Juego juego, Participacion participacion) throws JuegoException {
        juego.recibirApuesta(monto, participacion);
    }

    public void quitarJugador(Participacion jugador) throws JuegoException {
        juegoAIniciar.retirarJugador(jugador);
    }

    public void verificarInicioJuego() throws JuegoException {
        juegoAIniciar.verificarInicioJuego();
    }
    
    public void terminarParticipacion(Participacion participacion, Juego juego) throws JuegoException{
        juego.finalizarParticipacion(participacion);        
    }

    public void verificarIngresoJugador(Jugador j) throws JuegoException{
        juegoAIniciar.verificarIngresoJugador(j);
    }
}
