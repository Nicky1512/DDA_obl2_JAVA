package modelo;

import java.util.ArrayList;
import modelo.Figuras.Figura;
import modelo.excepciones.JuegoException;

public class ControlJuegos {

    private ArrayList<Juego> juegos;
    private ArrayList<Figura> figuras;
    private Juego juegoAIniciar;
    private Mazo mazo;

    private static ControlJuegos instancia;

    private ControlJuegos() {
        juegos = new ArrayList();
        figuras = new ArrayList();
    }

    public ArrayList<Juego> getJuegos() {
        return juegos;
    }

    public ArrayList<Figura> getFiguras() {
        return figuras;
    }

    public Mazo getMazo() {
        return mazo;
    }

    public static ControlJuegos getInstancia() {
        if (instancia == null) {
            instancia = new ControlJuegos();
        }
        return instancia;
    }

    public void setMazo(Mazo mazo) {
        this.mazo = mazo;
    }

    public void empezarJuego() throws JuegoException {
        juegoAIniciar.empezarJuego();
        Juego nuevo = new Juego();
        this.juegos.add(nuevo);
        this.juegoAIniciar = nuevo;
    }

    public void setJuegoAIniciar(Juego juegoAIniciar) {
        this.juegoAIniciar = juegoAIniciar;
    }

    public void agregarFigura(Figura figura) {
        this.figuras.add(figura);
    }
    
    public void agregarJugador(Jugador jugador) throws JuegoException{
        this.juegoAIniciar.agregarJugador(jugador);
    }
    
    public void recibirApuesta(double monto, Juego juego, Participacion participacion) throws JuegoException{
        juego.recibirApuesta(monto, participacion);
    }
}
