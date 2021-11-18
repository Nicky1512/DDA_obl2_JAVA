package modelo;

import java.util.ArrayList;
import modelo.Figuras.Figura;
import modelo.excepciones.JuegoException;
import observador.Observable;

public class Participacion extends Observable {

    private boolean activo, pasar;
    private double saldoInicial, totalGanado, totalApostado, apuestaActual;

    private Jugador jugador;
    private Figura figura;
    private ArrayList<Carta> cartas;

    public enum Eventos {
        salir, apostar, pasar, saldoModificado, juegoTerminado, modificarEstado
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
        this.pasar = false;
    }

    public void nuevaMano(ArrayList<Carta> cartas) {
        this.cartas = cartas;
        pasar = false;
        apuestaActual = 0;
        figurasEnMano();
    }

    public Figura getFigura() {
        return figura;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setApuestaActual(double apuestaActual) {
        this.apuestaActual = apuestaActual;
    }

    public double getApuesta() {
        return apuestaActual;
    }

    public ArrayList<Carta> getCartas() {
        return cartas;
    }

    public void setCartas(ArrayList<Carta> cartas) {
        this.cartas = cartas;
    }

    public boolean isPasar() {
        return pasar;
    }

    public void setPasar(boolean pasar) {
        this.pasar = pasar;
    }

    public void pasar() {
        this.pasar = true;
    }

    public boolean yaJugo() {
        return pasar || apuestaActual > 0 || !activo;
    }

    public void figurasEnMano() {
        for (Figura fig : SistemaJuegos.getInstancia().getFiguras()) {
            Carta[] array = new Carta[this.cartas.size()];
            array = this.cartas.toArray(array);
            Figura miFigura = fig.determinarFigura(array);
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

    public String getDatosJugadorActivo() {
        String estado = "";
        if (this.apuestaActual > 0) {
            estado = " (Apuesta: " + String.valueOf(this.apuestaActual) + ")";
        } else if (this.pasar) {
            estado = " (Paso en esta mano)";
        } else {
            estado = " (Sin participacion en esta mano)";
        }
        return this.jugador.getNombreCompleto() + estado;
    }

    public void agregarSaldo(double d) {
        this.jugador.agregarSaldo(d);
        this.totalGanado += d;
        avisar(Eventos.saldoModificado);
        Sistema.getInstancia().avisar(Sistema.Eventos.eventoAdmin);
    }

    public void descontarSaldo(double apuestaBase) throws JuegoException {
        this.jugador.descontarSaldo(apuestaBase);
        this.totalApostado += apuestaBase;
        this.avisar(Participacion.Eventos.saldoModificado);
        Sistema.getInstancia().avisar(Sistema.Eventos.eventoAdmin);
    }

    public void realizarApuesta(double monto) throws JuegoException {
        this.apuestaActual = monto;
        this.jugador.descontarSaldo(monto);
        this.totalApostado += monto;
        avisar(Eventos.saldoModificado);
        Sistema.getInstancia().avisar(Sistema.Eventos.eventoAdmin);
    }

    public void terminarParticipacion(Boolean expulsado) {
        this.activo = false;
        if (expulsado) {
            avisar(Participacion.Eventos.salir);

        }
        Sistema.getInstancia().avisar(Sistema.Eventos.eventoAdmin);
    }

    public void puedoApostar(double monto) throws JuegoException {
        if (apuestaActual == 0) {
            if (!jugador.puedoApostar(monto)) {
                throw new JuegoException("El jugador no dispone del saldo suficiente para realizar la apuesta");
            }
        } else {
            throw new JuegoException("El jugador ya realizo una apuesta en esta mano");
        }
    }

    public void TerminoJuego() {
        this.avisar(Participacion.Eventos.juegoTerminado);
        Sistema.getInstancia().avisar(Sistema.Eventos.eventoAdmin);
    }

    public String datosGanador() {
        Carta[] c = new Carta[this.getCartas().size()];
        this.cartas.toArray(c);
        String cartasGanadoras = "";
        for (int i = 0; i < c.length; i++) {
            cartasGanadoras += c[i].getValor() + c[i].getPalo().getNombre() + " | ";
        }

        return "El ganador es: " + this.getNombreJugador() + " con: \n" + this.figura.getNombre()
                + " \n  " + cartasGanadoras;
    }

    public Carta[] cartasGanador() {
        Carta[] cartasGanadoras = new Carta[this.getCartas().size()];
        this.cartas.toArray(cartasGanadoras);
        return cartasGanadoras;
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

    @Override
    public String toString() {
        return "Nombre: " + jugador.getNombreCompleto()
                + " | Total Apostado" + totalApostado
                + " | Saldo Incial: " + saldoInicial
                + " | Total Ganado: " + totalGanado;
    }
}
