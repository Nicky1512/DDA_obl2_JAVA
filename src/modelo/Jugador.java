package modelo;

public class Jugador extends Usuario {

    private double saldo;

    public Jugador(double saldo, String nombreUsuario, String contraseña, String nombreCompleto) {
        super(nombreUsuario, contraseña, nombreCompleto);
        this.saldo = saldo;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
