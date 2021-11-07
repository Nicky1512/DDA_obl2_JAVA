package controlador;

import java.util.ArrayList;
import modelo.Carta;
import modelo.Participacion;
import modelo.Sistema;
import observador.Observable;
import observador.Observador;

public class ControladorJuego implements Observador{

    private VistaJuego vista;
    private Sistema sistema = Sistema.getInstancia();
    private Participacion participacion;

    public VistaJuego getVista() {
        return vista;
    }

    public void setVista(VistaJuego vista) {
        this.vista = vista;
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
    
    public void salir(){
        participacion.quitar(this);
    }
    
    public void apostar(double a){
        participacion.setApuesta(a);
    }
    
    public void pasar(){
        //TOO
        //Discutir METODO
        participacion.setApuesta(0);
    }
    
    public ArrayList<Carta> ObservarCartas(){
        return participacion.getCartas();
    }

    public ControladorJuego(VistaJuego vista, Participacion participacion) {
        this.vista = vista;
        this.participacion = participacion;
    }
    public ControladorJuego() {
    }
    


    @Override
    public void actualizar(Object evento, Observable origen) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}