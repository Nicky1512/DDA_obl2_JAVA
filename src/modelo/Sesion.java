package modelo;

import java.text.SimpleDateFormat;
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

    @Override
    public String toString() {
        
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss dd/MM/yyyy");
        String fecha = sdf.format(fechaSesion);
        int cantidadJugadores = juegoEnLinea.getJugadores().size();
        int cantidadManos = juegoEnLinea.getManos().size();
        double totalApostado = juegoEnLinea.getManoActual().getTotalApostado();

        return "Fecha/Hora: " + fecha
                + " | Cantidad Jugadores: " + cantidadJugadores
                + " | Cantidad Manos: " + cantidadManos
                + " | Total Apostado: " + totalApostado;
        
    }
    
    public String jugadoresToString(){
        
        String nombre = "";
        double saldoInicial = 0;
        double totalApostado = 0;
        double totalGanado = 0;
        
        for (Jugador j : juegoEnLinea.getJugadores()) {
            nombre = j.getNombreCompleto();
            saldoInicial = j.getSaldo(); //Guardar el primer saldo
            totalApostado = saldoInicial - j.getSaldo(); //TODO
            totalGanado = 0; //TODO
        }
        return "Nombre Completo: " + nombre +
                " | Total Apostado" + totalApostado +
                " | Saldo Incial: " + saldoInicial +
                " | Total Gnanado: " + totalGanado;
        
    }
    
}

//    En el listado de jugadores de un juego se muestra: 
//    - Nombre Completo
//    - Total Apostado
//    - Saldo al iniciar la partida
//    - Total ganado(que puede ser negativo)
