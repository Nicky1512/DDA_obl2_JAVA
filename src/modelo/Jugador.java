package modelo;

import modelo.excepciones.JuegoException;

public class Jugador extends Usuario {

    private double saldo;

    public Jugador(double saldo, String nombreUsuario, String contraseña, String nombreCompleto) {
        super(nombreUsuario, contraseña, nombreCompleto);
        this.saldo = saldo;
    }

    public double getSaldo() {
        return saldo;
    }

    public void descontarSaldo(double desc) throws JuegoException {
        if (desc <= this.saldo) {
            this.saldo -= desc;
        } else {
            throw new JuegoException("No se pudo descontar el saldo");
        }
    }

    public void agregarSaldo(double monto) {
        this.saldo += saldo;
    }

    public Boolean puedoApostar(double monto) {
        return monto < this.saldo;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Jugador)) {
            return false;
        }

        Jugador j = (Jugador) o;
        return nombreUsuario.equals(j.nombreUsuario);
    }
    
}
