package controlador;

import modelo.Sistema;
import observador.Observable;
import observador.Observador;

public class ControladorAdmin implements Observador {
    
    private VistaAdmin vista;
    private Sistema sistema = Sistema.getInstancia();

    public VistaAdmin getVista() {
        return vista;
    }

    public Sistema getSistema() {
        return sistema;
    }

    public void setVista(VistaAdmin vista) {
        this.vista = vista;
    }

    public void setSistema(Sistema sistema) {
        this.sistema = sistema;
    }
   

    @Override
    public void actualizar(Object evento, Observable origen) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void mostrarPartidas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void salir() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
