/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.ArrayList;


public class Sistema {
    
    private SistemaUsuario sistemaUsuario = new SistemaUsuario();
    private static Sistema instancia = new Sistema();
    
    
    public static Sistema getInstancia() {
        return instancia;
    }
    
    public Usuario loginUsuario(String usuario, String pass){
        return sistemaUsuario.loginUsuario(usuario, pass);
    }
    
    public Administrador loginAdmin(String usuario, String password){
        return sistemaUsuario.loginAdmin(usuario, password);
    }
    
    public void agregarJugador(Jugador j){
        sistemaUsuario.agregarJugador(j);
    }
    
    public void agregarAdmin(Administrador a){
        sistemaUsuario.agregarAdmin(a);
    }

    
}
