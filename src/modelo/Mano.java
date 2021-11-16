package modelo;

import java.util.ArrayList;
import modelo.Figuras.Figura;
import modelo.excepciones.JuegoException;

public class Mano {

    private double pozoInicial;
    private Mazo mazo;
    private double totalApostado;
    private double apuestaFijada;
    private String nombreApostador;

    private ArrayList<Participacion> participantes;

    public Mano(double pozoInicial, Mazo mazo, ArrayList<Participacion> jugadores) throws JuegoException {
        this.pozoInicial = pozoInicial;
        this.mazo = mazo;
        this.participantes = new ArrayList<>();
        this.apuestaFijada = 0;
        this.totalApostado = jugadores.size() * Juego.apuestaBase;
        this.nombreApostador = "";
        iniciarMano(jugadores);
        descontarSaldoTodos();
    }

    public double getTotalApostado() {
        return totalApostado;
    }

    public double getPozoInicial() {
        return pozoInicial;
    }

    public Mazo getMazo() {
        return mazo;
    }

    public void setMazo(Mazo mazo) {
        this.mazo = mazo;
    }

    public ArrayList<Participacion> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(ArrayList<Participacion> participantes) {
        this.participantes = participantes;
    }

    public double getApuestaFijada() {
        return apuestaFijada;
    }

    public void setApuestaFijada(double apuestaFijada) {
        this.apuestaFijada = apuestaFijada;
    }

    public String getNombreApostador() {
        return nombreApostador;
    }

    private void iniciarMano(ArrayList<Participacion> jugadores) {
        for (Participacion p : jugadores) {
            ArrayList<Carta> cartas = this.mazo.repartirCartas();
            p.setCartas(cartas);
            p.setPasar(false);
            p.setApuestaActual(0);
            this.participantes.add(p);
        }
    }

    public void recibirApuesta(double monto, Participacion participacion) throws JuegoException {
        participacion.puedoApostar(monto);
        if (this.apuestaFijada == 0) {
            this.apuestaFijada = monto;
            resetearPasar();
            this.nombreApostador = participacion.getNombreJugador();
            this.completarApuesta(monto, participacion);
        } else {
            throw new JuegoException("Ya hay una apuesta fijada");
        }
    }
    
    public void resetearPasar(){
        for(Participacion p:participantes){
            p.setPasar(false);
        }
    }
    
    public void pagarApuesta(Participacion p) throws JuegoException{
        p.puedoApostar(this.apuestaFijada);
        if (this.apuestaFijada != 0) {
            this.completarApuesta(this.apuestaFijada, p);
        } else {
            throw new JuegoException("Ya hay una apuesta fijada");
        } 
    }

    public void completarApuesta(double monto, Participacion p) throws JuegoException {
        this.totalApostado += monto;
        p.realizarApuesta(monto);
    }

    public Participacion determinarGanador() {
        Participacion ganador = null;
        ArrayList<Participacion> participaciones = new ArrayList<>();
        for (Figura fig : SistemaJuegos.getInstancia().getFiguras()) {
            for (Participacion p : participantes) {
                if(p.getApuesta() > 0 ){
                    p.figurasEnMano();
                    if (fig.getClass().getName() == null ? p.getFigura().getClass().getName() == null : fig.getClass().getName().equals(p.getFigura().getClass().getName())) {
                        participaciones.add(p);
                    }
                }
            }
            if (participaciones.size() > 1) {
                Participacion[] array = new Participacion[participaciones.size()];
                array = participaciones.toArray(array);
                ganador = fig.desempatarFiguras(array);
                break;
            } else if (participaciones.size() == 1) {
                ganador = participaciones.get(0);
                break;
            }
        }
        ganador.agregarSaldo(this.getPozoInicial() + this.getTotalApostado());
        return ganador;
    }

    public Participacion getParticipacionDeJugador(Jugador j) {
        for (Participacion p : participantes) {
            if (p.getJugador().equals(j)) {
                return p;
            }
        }
        return null;
    }

    public void descontarSaldoTodos() throws JuegoException {
        for (Participacion p : participantes) {
            p.descontarSaldo(Juego.getApuestaBase());
        }
    }
    
    public boolean verificarEstadoParticipantes(){
        for(Participacion p: participantes){
            if(!p.debeJugar())
                return false;
        }
        return true;
    }
}
