
package modelo;

import java.util.ArrayList;

public class Pozo {
    private ArrayList<Participacion> participantes;
    private double monto;
    

    public Pozo(ArrayList<Participacion> participantes, double monto) {
        this.participantes = participantes;
        this.monto = monto;
    }

    public ArrayList<Participacion> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(ArrayList<Participacion> participantes) {
        this.participantes = participantes;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
    
}
