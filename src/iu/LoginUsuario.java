/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iu;


import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Jugador;
import modelo.Sistema;
import modelo.Usuario;
import modelo.excepciones.JuegoException;

/**
 *
 * @author Bruno
 */
public class LoginUsuario extends LoginAbstracto {

    public LoginUsuario(){
    }
    
    @Override
    public Object invocarLoginLogicaNegocio(String nom, String pwd) {
        try {
            Usuario u = Sistema.getInstancia().loginJugador(nom, pwd);
            return u;
        } catch (JuegoException ex) {
            Logger.getLogger(LoginUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void ejecutarProximoCasoUso(Object dato) {
        try {
            new VentanaEspera((Jugador)dato).setVisible(true);
        } catch (JuegoException ex) {
            Logger.getLogger(LoginUsuario.class.getName()).log(Level.SEVERE, null, ex); //CAMBIA MENSAJE
        }
    }
    
}
