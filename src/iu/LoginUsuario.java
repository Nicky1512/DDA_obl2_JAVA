package iu;

import javax.swing.JOptionPane;
import modelo.Jugador;
import modelo.Sistema;
import modelo.Usuario;
import modelo.excepciones.JuegoException;


public class LoginUsuario extends LoginAbstracto {

    public LoginUsuario(){
    }
    
    @Override
    public Object invocarLoginLogicaNegocio(String nom, String pwd) {
        try {
            Usuario u = Sistema.getInstancia().loginJugador(nom, pwd);
            return u;
        } catch (JuegoException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
        return null;
    }

    @Override
    public void ejecutarProximoCasoUso(Object dato) {
        try {
            Sistema.getInstancia().verificarIngresoJugador((Jugador)dato);
            new VentanaEspera((Jugador)dato).setVisible(true);
        } catch (JuegoException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
    
}
