package modelo;

public class HistoricoJugador {
    
    private Jugador jugador;
    private boolean activo;
    private double saldoInicial;
    private double totalGanado;
    private double totalApostado;

    public Jugador getJugador() {
        return jugador;
    }

    public HistoricoJugador() {
    }

    public HistoricoJugador(Jugador jugador) {
        this.jugador = jugador;
        this.saldoInicial = jugador.getSaldo();
        this.activo = true;
        this.totalApostado = 0;
        this.totalGanado = 0;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public double getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(double saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public double getTotalGanado() {
        return totalGanado;
    }

    public void setTotalGanado(double totalGanado) {
        this.totalGanado = totalGanado;
    }

    public double getTotalApostado() {
        return totalApostado;
    }

    public void setTotalApostado(double totalApostado) {
        this.totalApostado = totalApostado;
    }
    
    @Override
    public String toString(){
        return "Nombre: " + jugador.getNombreCompleto()
                + " | Total Apostado" + totalApostado
                + " | Saldo Incial: " + saldoInicial
                + " | Total Ganado: " + totalGanado;

    }
    
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof HistoricoJugador)) {
            return false;
        }

        HistoricoJugador j = (HistoricoJugador) o;
        return this.jugador.nombreCompleto.equals(j.getJugador().getNombreCompleto());
    }
    
    
}
