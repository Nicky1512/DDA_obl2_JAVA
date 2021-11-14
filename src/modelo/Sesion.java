package modelo;

import java.util.Date;

public class Sesion {

    private Date fechaSesion;
    private Juego juegoEnLinea;

    public Sesion() {
        this.fechaSesion = new Date();
    }

    public Date getFechaSesion() {
        return fechaSesion;
    }

    public Juego getJuegoEnLinea() {
        return juegoEnLinea;
    }
}


