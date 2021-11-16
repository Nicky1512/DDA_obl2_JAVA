package controlador;

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
//            case nuevoJugador:
//                mostrarJuegos();//TODO 
//                break;
        }
    }

    public void salir() {
        //TODO Admin extends Observable??
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void mostrarJuegos() {
        vista.mostrarJuegos(sistema.getJuegosEnCurso());
    }

    public void detalles() {

    }


    public void detallesJugadores(Sesion s){
        if (s != null) {
            String datos = ""; //TODO
            vista.detallesJugadores(datos);
        }else vista.detallesJugadores("");
    }

}
