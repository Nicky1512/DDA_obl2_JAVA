package controlador;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import modelo.Administrador;
import modelo.Sesion;
import modelo.Sistema;
import observador.Observable;
import observador.Observador;

public class ControladorAdmin implements Observador {

    private VistaAdmin vista;
    private Sistema sistema = Sistema.getInstancia();
    private Administrador admin;

    public ControladorAdmin(VistaAdmin vista, Administrador a) {
        this.vista = vista;
        sistema.agregar(this);
        admin = a;
        vista.mostrarNombreAdmin(a.getNombreCompleto());
        mostrarJuegos();
    }
    
    
    @Override
    public void actualizar(Object evento, Observable origen) {
        switch ((Sistema.Eventos) evento) {
            case nuevoJuego:
                mostrarJuegos();
                break;
            case cambioListaJugadoresEnLinea:
                mostrarJuegos();//TODO 
                break;
        }
    }

    public void salir() {
        //TODO Admin extends Observable??
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void mostrarJuegos() {
        vista.mostrarPartidas(sistema.getJuegos());
    }

    public void detalles(Sesion s) {
//        sesion tiene toda la info del juego en linea
        if (s != null) {
            String datos = s.toString();
            vista.detallesPartida(datos);
        }else vista.detallesPartida("");
    }
//    En el listado de juegos se muestra: 
//    - Fecha/hora de inicio
//    - Cantidad Jugadores
//    - Total apostado
//    - cantidad de manos jugadas

    public void detallesJugadores(Sesion s){
        if (s != null) {
            String datos = ""; //TODO
            vista.detallesJugadores(datos);
        }else vista.detallesJugadores("");
    }
//    En el listado de jugadores de un juego se muestra: 
//    - Nombre Completo
//    - Total Apostado
//    - Saldo al iniciar la partida
//    - Total ganado(que puede ser negativo)
}
