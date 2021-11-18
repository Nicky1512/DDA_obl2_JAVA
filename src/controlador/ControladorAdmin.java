package controlador;

import modelo.Administrador;
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
            case eventoAdmin:
                mostrarJuegos();
                break;
        }
    }

    public void salir() {
        sistema.quitar(this);
    }

    public void mostrarJuegos() {
        vista.mostrarJuegos(sistema.getJuegosEnCurso());
    }
    
}
