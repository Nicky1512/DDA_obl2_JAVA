package modelo;

import java.util.ArrayList;
import modelo.Figuras.Figura;
import modelo.excepciones.JuegoException;
import observador.Observable;

public class Participacion extends Observable {

    private Jugador jugador;
    private boolean activo;
    private double saldoInicial;
    private double totalGanado;
    private double totalApostado;

    private double apuesta;
    private Figura figura;
    private ArrayList<Carta> cartas;

    public enum Eventos {
        salir, apostar, pasar, saldoModificado
    };

    public Participacion() {
    }

    public Participacion(Jugador jugador) {
        this.jugador = jugador;
        this.cartas = new ArrayList();
        this.saldoInicial = jugador.getSaldo();
        this.activo = true;
        this.totalApostado = 0;
        this.totalGanado = 0;
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

    public void figurasEnMano() {
        for (Figura fig : SistemaJuegos.getInstancia().getFiguras()) {
            Figura miFigura = fig.determinarFigura((Carta[]) this.cartas.toArray());
            if (miFigura != null) {
                this.figura = miFigura;
                break;
            }
        }
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public double getSaldoInicial() {
        return saldoInicial;
    }

    public String getNombreJugador() {
        return this.jugador.getNombreCompleto();
    }

    public void setSaldoInicial(double saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public double getTotalGanado() {
        return totalGanado;
    }

    public void setTotalGanado(double totalGanado) {
        this.totalGanado = totalGanado;
    }

    public double getTotalApostado() {
        return totalApostado;
    }

    public void setTotalApostado(double totalApostado) {
        this.totalApostado = totalApostado;
    }

    @Override
    public String toString() {
        return "Nombre: " + jugador.getNombreCompleto()
                + " | Total Apostado" + totalApostado
                + " | Saldo Incial: " + saldoInicial
                + " | Total Ganado: " + totalGanado;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Participacion)) {
            return false;
        }

        Participacion j = (Participacion) o;
        return this.jugador.nombreCompleto.equals(j.getJugador().getNombreCompleto());
    }

    public void agregarSaldo(double d) {
        this.jugador.agregarSaldo(d);
        this.totalGanado += d;
        avisar(Eventos.saldoModificado);
    }

    public void descontarSaldo(double apuestaBase) throws JuegoException {
        this.jugador.descontarSaldo(apuestaBase);
        this.totalApostado += apuestaBase;
        this.avisar(Participacion.Eventos.saldoModificado);
    }

    public void realizarApuesta(double monto) throws JuegoException {
        this.apuesta = monto;
        this.jugador.descontarSaldo(monto);
        this.totalApostado += monto;
        avisar(Eventos.saldoModificado);
    }
}
