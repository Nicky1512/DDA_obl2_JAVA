/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iu;

import java.awt.Frame;
import java.util.ArrayList;
import modelo.Sistema;
import modelo.Usuario;

/**
 *
 * @author Bruno
 */
public class LoginUsuario extends LoginAbstracto {

    public LoginUsuario(){
    }
    
    @Override
    public Object invocarLoginLogicaNegocio(String nom, String pwd) {
        Usuario u = Sistema.getInstancia().loginUsuario(nom, pwd);
        return u;
    }

    @Override
    public void ejecutarProximoCasoUso(Object dato) {
    }
    
}
