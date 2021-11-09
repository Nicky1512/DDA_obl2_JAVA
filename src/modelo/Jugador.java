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

    public Boolean descontarSaldo(double desc) {
        if (desc <= this.saldo) {
            this.saldo -= desc;
            return true;
        }
        return false;
    }

    public void agregarSaldo(double monto) {
        this.saldo += saldo;
    }

    public Boolean puedoApostar(double monto) {
        return monto < this.saldo;
    }
}
