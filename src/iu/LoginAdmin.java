/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iu;

import logica.Administrador;
import logica.Sistema;

/**
 *
 * @author Bruno
 */
public class LoginAdmin extends LoginAbstracto{

    public LoginAdmin(){
        
    }
    @Override
    public Object invocarLoginLogicaNegocio(String nom, String pwd) {
        Administrador a = Sistema.getInstancia().loginAdmin(nom, pwd);
        return a;
    }

    @Override
    public void ejecutarProximoCasoUso(Object dato) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
