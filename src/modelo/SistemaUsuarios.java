package modelo;

import java.util.ArrayList;
import modelo.excepciones.JuegoException;

public class SistemaUsuarios {

    private ArrayList<Jugador> jugadores;
    private ArrayList<Administrador> admins;
    private ArrayList<Jugador> conectados;
//    private ArrayList<Sesion> conectados;

    private static SistemaUsuarios instancia;

    private SistemaUsuarios() {
        jugadores = new ArrayList();
        admins = new ArrayList();
//        conectados = new ArrayList();
    }

    public static SistemaUsuarios getInstancia() {
        if (instancia == null) {
            instancia = new SistemaUsuarios();
        }
        return instancia;
    }

    ArrayList<Jugador> getJugadores() {
        return conectados;
    }

    public Usuario login(String usuario, String password, ArrayList listaUsuarios) {
        Usuario user;
        for (Object u : listaUsuarios) {
            user = (Usuario) u;
            if (user.getNombreUsuario().equals(usuario) && user.getContraseña().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public Jugador loginJugador(String usuario, String password) throws JuegoException {
        Jugador loginRequest = (Jugador) login(usuario, password, jugadores);
        if (loginRequest != null) {
            return loginRequest;
        }
        return null;
    }

    public Administrador loginAdmin(String usuario, String password) {
        Administrador loginRequest = (Administrador) login(usuario, password, admins);
        if (loginRequest != null) {
            return loginRequest;
        }
        return null;
    }

    public void agregarJugador(Jugador j) {
        jugadores.add(j);
    }

    public void agregarAdmin(Administrador a) {
        admins.add(a);
    }

//    public ArrayList<Sesion> getConexiones(){
//        return conectados;
//    }
}
