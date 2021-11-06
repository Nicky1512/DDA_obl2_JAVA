package controlador;

import modelo.Participacion;
import modelo.Sistema;
import observador.Observable;
import observador.Observador;

public class ControladorJuego implements Observador{

    private VistaJuego vista;
    private Sistema sistema = Sistema.getInstancia();
    private Participacion participacion;


    @Override
    public void actualizar(Object evento, Observable origen) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}