package main;

import java.util.ArrayList;
import modelo.Administrador;
import modelo.Carta;
import modelo.SistemaJuegos;
import modelo.Figuras.Color;
import modelo.Figuras.Par;
import modelo.Figuras.Pierna;
import modelo.Figuras.SinFigura;
import modelo.Juego;
import modelo.Jugador;
import modelo.Mazo;
import modelo.Palo;
import modelo.Sistema;

public class DatosPrueba {

    public static void cargar() {

        Juego.setApuestaBase(10); //Mayor a 0
        Juego.setCantidadJugadores(4); //Mayor a 1 y menor que 6

        Administrador Lucas = new Administrador("Lucas", "123", "Lucas Andueza");
        Administrador Bruno = new Administrador("Bruno", "234", "Bruno Barcelo");
        Administrador Nicole = new Administrador("Nicole", "852", "Nicole Fabian");

        Sistema.getInstancia().agregarAdmin(Lucas);
        Sistema.getInstancia().agregarAdmin(Nicole);
        Sistema.getInstancia().agregarAdmin(Lucas);

        Jugador j1 = new Jugador(500000, "Luis", "123", "Luis Ramirez");
        Jugador j2 = new Jugador(2500, "Ana", "123", "Ana Pou");
        Jugador j3 = new Jugador(1000, "Jessica", "otraPass", "Jessica Suarez");
        Jugador j4 = new Jugador(200, "Pepe", "pass125823", "Pepe Chaplin");
        Jugador j5 = new Jugador(8000, "Rosa", "Ppass123", "Rosa Flores");
        Jugador j6 = new Jugador(900, "Juan", "pass3", "Juan Juanes");
        Jugador j7 = new Jugador(0, "Silvina", "258pass", "Silvina Fuentes");
        Jugador j8 = new Jugador(350, "Luigi", "pass258", "Luigi Lista");
        Jugador j9 = new Jugador(10000, "Martina", "wweers4", "Martina James");

        Sistema.getInstancia().agregarJugador(j1);
        Sistema.getInstancia().agregarJugador(j2);
        Sistema.getInstancia().agregarJugador(j3);
        Sistema.getInstancia().agregarJugador(j4);
        Sistema.getInstancia().agregarJugador(j5);
        Sistema.getInstancia().agregarJugador(j6);
        Sistema.getInstancia().agregarJugador(j7);
        Sistema.getInstancia().agregarJugador(j8);
        Sistema.getInstancia().agregarJugador(j9);

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
        for (Palo p : palos) {
            for (int i = 2; i < 11; i++) {
                Carta nueva = new Carta(String.valueOf(i), (i - 1), p);
                cartas.add(nueva);
            }
            Carta a = new Carta("A", 13, p);
            Carta k = new Carta("K", 12, p);
            Carta q = new Carta("Q", 11, p);
            Carta j = new Carta("J", 10, p);

            cartas.add(k);
            cartas.add(a);
            cartas.add(j);
            cartas.add(q);
        }

        Mazo mazo = new Mazo(cartas);
        SistemaJuegos.getInstancia().setMazo(mazo);
        Juego juego = new Juego();
        SistemaJuegos.getInstancia().setJuegoAIniciar(juego);
        //TODO setear mazo en juego

        Color color = new Color("Color");
        Pierna pierna = new Pierna("Pierna");
        Par par = new Par("Par");
        SinFigura sinFigura = new SinFigura("SinFigura");

        Sistema.getInstancia().agregarFigura(color);
        Sistema.getInstancia().agregarFigura(pierna);
        Sistema.getInstancia().agregarFigura(par);
        Sistema.getInstancia().agregarFigura(sinFigura);

    }
}
