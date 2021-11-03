
package modelo;

import java.util.ArrayList;

public class SistemaUsuario {
    
    private ArrayList<Jugador> jugadores = new ArrayList();
    private ArrayList<Administrador> admins = new ArrayList();
    private ArrayList<Sesion> conectados = new ArrayList();
    
    public Usuario login (String usuario, String password, ArrayList listaUsuarios){
        Usuario user;
        for(Object u:listaUsuarios){
            user = (Usuario)u;
            if(user.getNombreUsuario().equals(usuario) && user.getContraseña().equals(password))
                return user;
        }
        return null;
    }
    
    public Jugador loginUsuario(String usuario, String password){
        Jugador loginRequest = (Jugador)login(usuario, password, jugadores );
        Sesion s = null;
        if(loginRequest != null){
            s = new Sesion(loginRequest);
            conectados.add(s);
            return loginRequest;
        }
        return null;
    }
    
    public Administrador loginAdmin(String usuario, String password){
        Administrador loginRequest = (Administrador)login(usuario, password, admins );
        if(loginRequest != null){
            return loginRequest;
        }
        return null;
    }
    
    public void agregarJugador(Jugador j){
        jugadores.add(j);
    }
    public void agregarAdmin(Administrador a){
        admins.add(a);
    }
//    public ArrayList<Usuario> getUsuarios(){
//        return usuarios;
//    }
    
    
}