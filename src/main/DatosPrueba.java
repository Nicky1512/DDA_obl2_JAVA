package main;

import java.util.ArrayList;
import logica.Administrador;
import logica.Carta;
import logica.Jugador;
import logica.Mazo;
import logica.Palo;
import logica.Sistema;

public class DatosPrueba {

    public static void cargar() {

        //TODO: Agregar precarga de datos (Jugadores, Admins, apuestaBase y cantParticipantes)
        
         
        Administrador Lucas = new Administrador("Lucas", "123", "Lucas Andueza");
        Administrador Bruno = new Administrador("Bruno", "234", "Bruno Barcelo");
        Administrador Nicole = new Administrador("Nicole", "852", "Nicole Fabian");
        
        Jugador j1 = new Jugador(5000, "Luis" , "pass123", "Luis Ramirez");
        Jugador j2 = new Jugador(2500, "Ana" , "7898789Pass", "Ana Pou");
        Jugador j3 = new Jugador(1000, "Jessica" , "otraPass", "Jessica Suarez");
        Jugador j4 = new Jugador(200, "Pepe" , "pass125823", "Pepe Chaplin");
        Jugador j5 = new Jugador(8000, "Rosa" , "Ppass123", "Rosa Flores");
        Jugador j6 = new Jugador(900, "Juan" , "pass3", "Juan Juanes");
        Jugador j7 = new Jugador(0, "Silvina" , "258pass", "Silvina Fuentes");
        Jugador j8 = new Jugador(350, "Luigi" , "pass258", "Luigi Lista");
        Jugador j9 = new Jugador(10000, "Martina" , "wweers4", "Martina James");
        
        
        ArrayList<Palo> palos = new ArrayList();
        Palo p1 = new Palo("Corazon", 4);
        Palo p2 = new Palo("Diamante", 3);
        Palo p3 = new Palo("Trebol", 2);
        Palo p4 = new Palo("Pique", 1);
        
        palos.add(p4);
        palos.add(p3);
        palos.add(p2);
        palos.add(p1);
        
        ArrayList<Carta> cartas = new ArrayList();
        for(Palo p:palos){
            for (int i = 2; i < 11; i++) {
                Carta nueva = new Carta(String.valueOf(i),p);
                cartas.add(nueva);
            }
            Carta k = new Carta("K", p);
            Carta q = new Carta("Q", p);
            Carta j = new Carta("J", p);
            Carta a = new Carta("A", p);
            cartas.add(k);
            cartas.add(a);
            cartas.add(j);
            cartas.add(q);
        }
        
        Mazo mazo = new Mazo(cartas);
        
        Sistema.getInstancia().agregarAdmin(Lucas);
        Sistema.getInstancia().agregarAdmin(Nicole);
        Sistema.getInstancia().agregarAdmin(Lucas);
        
        Sistema.getInstancia().agregarJugador(j1);
        Sistema.getInstancia().agregarJugador(j2);
        Sistema.getInstancia().agregarJugador(j3);
        Sistema.getInstancia().agregarJugador(j4);
        Sistema.getInstancia().agregarJugador(j5);
        Sistema.getInstancia().agregarJugador(j6);
        Sistema.getInstancia().agregarJugador(j7);
        Sistema.getInstancia().agregarJugador(j8);
        Sistema.getInstancia().agregarJugador(j9);
        
    }
}
