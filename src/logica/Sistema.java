package logica;

import java.util.ArrayList;
import observador.Observable;


public class Sistema extends Observable{
    
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
