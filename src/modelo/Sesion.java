package modelo;

import java.util.Date;

public class Sesion {
    private Date fechaSesion;
    private Jugador jugadorEnLinea;

    public Sesion(Jugador jugadorEnLinea) {
        this.fechaSesion = new Date();
        this.jugadorEnLinea = jugadorEnLinea;
    }

    public void setFechaSesion(Date fechaSesion) {
        this.fechaSesion = fechaSesion;
    }

    public void setJugadorEnLinea(Jugador jugadorEnLinea) {
        this.jugadorEnLinea = jugadorEnLinea;
    }

    public Date getFechaSesion() {
        return fechaSesion;
    }

    public Jugador getJugadorEnLinea() {
        return jugadorEnLinea;
    }
}