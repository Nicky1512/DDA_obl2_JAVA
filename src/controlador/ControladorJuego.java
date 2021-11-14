package controlador;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Carta;
import modelo.Juego;
import modelo.Jugador;
import modelo.Participacion;
import modelo.Sistema;
import modelo.SistemaJuegos;
import modelo.excepciones.JuegoException;
import observador.Observable;
import observador.Observador;

public class ControladorJuego implements Observador {

    private VistaJuego vistaJuego;
    private VistaEspera vistaEspera;
    private Sistema sistema = Sistema.getInstancia();
    private Participacion participacion;  

    public ControladorJuego(VistaJuego vista, Jugador jugador) {
        this.vistaJuego = vista;
//        this.participacion = new Participacion();
        sistema.agregar(this);
        vista.mostrarNombreJugador(jugador.getNombreCompleto());

    }

    public ControladorJuego(VistaEspera vista, Jugador jugador) throws JuegoException {
        this.vistaEspera = vista;
        sistema.agregar(this);
    }

    public VistaJuego getVista() {
        return vistaJuego;
    }

    public void setVista(VistaJuego vista) {
        this.vistaJuego = vista;
    }

    public Sistema getSistema() {
        return sistema;
    }

    public void setSistema(Sistema sistema) {
        this.sistema = sistema;
    }

    public Participacion getParticipacion() {
        return participacion;
    }

    public void setParticipacion(Participacion participacion) {
        this.participacion = participacion;
    }

    public void salir() {
        participacion.quitar(this);
    }

    public void apostar(double a) throws JuegoException {
        participacion.realizarApuesta(a);
    }

    public void pasar() throws JuegoException {
        participacion.realizarApuesta(0);
    }

    public ArrayList<Carta> ObservarCartas() {
        return participacion.getCartas();
    }

//    public void expulsarJugador(Participacion p, Mano m){
//        sistema.expulsarJugador(p, m);
//    }
    @Override
    public void actualizar(Object evento, Observable origen) {
//        switch((modelo.Participacion.Eventos)evento){
//            
//            case salir: vistaJuego.terminarJuego();
//                break;
//            case apostar: vistaJuego.apostar(); //VERIFICAR SI NO ES NECESARIO RECIBIR PARAMETRO
//                break;
//            case pasar: vistaJuego.pasar();
//                break;
//            case observarCartas: vistaJuego.observarCartas();
//                break;
//        }
        if (evento.equals(Juego.Eventos.nuevoJugador)) { //TODO, evento llega cambioListaJugadoresEnLinea en vez de nuevoJugador
            mostrarFaltan();

        }
        if (evento.equals(Sistema.Eventos.cambioListaJugadoresEnLinea)) {
            mostrarFaltan();
        }
    }

//    public void mostrarVentanaEspera() {
//        vistaEspera.mostrarFaltan();
//    }

    public void empezarJuego() throws JuegoException {
        sistema.empezarJuego();
    }


    private void cargarJugador(Jugador jugador) throws JuegoException {
        SistemaJuegos.getInstancia().agregarJugador(jugador);
    }

    public void mostrarFaltan() {
        Juego j = sistema.getJuegoAIniciar();
        int cantJugadoresConectados = j.getJugadores().size();
        String datos = cantJugadoresConectados + "/" + Juego.getCantidadJugadores();
        vistaEspera.mostrarFaltan(datos);
    }

}
