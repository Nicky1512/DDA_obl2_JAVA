package modelo;

import modelo.Figuras.Figura;
import java.util.ArrayList;
import modelo.excepciones.JuegoException;
import observador.Observable;

public class Participacion extends Observable {

    private double apuesta;
    private Jugador jugador;
    private Figura figura;
    private ArrayList<Carta> cartas;
    private double montoGanado;
    
    public enum Eventos {
        salir, apostar, pasar, observarCartas, saldoModificado
    };

    public double getMontoGanado() {
        return montoGanado;
    }

    public void setMontoGanado(double montoGanado) {
        this.montoGanado = montoGanado;
    }

    public Participacion() {}

    public Participacion(Jugador jugador) {
        this.jugador = jugador;
        this.cartas = new ArrayList();
    }

    public Figura getFigura() {
        return figura;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public double getApuesta() {
        return apuesta;
    }

    public ArrayList<Carta> getCartas() {
        return cartas;
    }

    public void setCartas(ArrayList<Carta> cartas) {
        this.cartas = cartas;
    }

    
    public void realizarApuesta(double monto) throws JuegoException {
        this.apuesta = monto;
        this.jugador.descontarSaldo(monto);
        avisar(Eventos.saldoModificado);
    }

    public void figurasEnMano() {
        for (Figura fig : SistemaJuegos.getInstancia().getFiguras()) {
            Figura miFigura = fig.determinarFigura((Carta[]) this.cartas.toArray());
            if (miFigura != null) {
                this.figura = miFigura;
                break;
            }
        }
    }
}
