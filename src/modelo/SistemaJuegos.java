package modelo;

import java.util.ArrayList;
import modelo.Figuras.Figura;
import modelo.excepciones.JuegoException;

public class SistemaJuegos {

    private Juego juegoAIniciar;
    private Mazo mazo;

    private ArrayList<Juego> juegos;
    private ArrayList<Figura> figuras;

    private static SistemaJuegos instancia;

    private SistemaJuegos() {
        juegos = new ArrayList();
        figuras = new ArrayList();
    }

    public static SistemaJuegos getInstancia() {
        if (instancia == null) {
            instancia = new SistemaJuegos();
        }
        return instancia;
    }

    public ArrayList<Figura> getFiguras() {
        return figuras;
    }

    public Mazo getMazo() {
        Mazo ret = new Mazo();
        mazo.getCartas().forEach(c -> {
            ret.agregarCarta(c);
        });
        return ret;
    }

    public void setMazo(Mazo mazo) {
        this.mazo = mazo;
    }

    public void setJuegoAIniciar(Juego juegoAIniciar) {
        this.juegoAIniciar = juegoAIniciar;
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

    public void agregarJuego(Juego j) {
        juegos.add(j);
    }

    public void empezarJuego() throws JuegoException {
        juegoAIniciar.empezarJuego();
        Juego nuevo = new Juego();
        this.juegoAIniciar = nuevo;
        this.juegos.add(nuevo);

    }

    public void agregarFigura(Figura figura) {
        this.figuras.add(figura);
    }

    public void verificarInicioJuego() throws JuegoException {
        juegoAIniciar.verificarInicioJuego();
    }

    public void verificarIngresoJugador(Jugador j) throws JuegoException {
        juegoAIniciar.verificarIngresoJugador(j);
    }
}
