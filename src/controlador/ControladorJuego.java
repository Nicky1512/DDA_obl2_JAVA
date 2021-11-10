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

    
    public ControladorJuego(VistaJuego vista, Participacion participacion) {
        this.vista = vista;
        this.participacion = participacion;
        sistema.agregar(this);
        vista.mostrarNombreJugador(participacion.getJugador().getNombreCompleto());
        
    }


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
        participacion.realizarApuesta(a);
    }
    
    public void pasar(){
        participacion.realizarApuesta(0);
    }
    
    public ArrayList<Carta> ObservarCartas(){
        return participacion.getCartas();
    }

    
    
    @Override
    public void actualizar(Object evento, Observable origen) {
        switch((modelo.Participacion.Eventos)evento){
            case salir: vista.terminarJuego();
                break;
            case apostar: vista.apostar(0); //VERIFICAR
                break;
            case pasar: vista.pasar();
                break;
            case observarCartas: vista.observarCartas();
                break;
        }
    }

    public void empezarJuego() {
        sistema.empezarJuego();
    }
}