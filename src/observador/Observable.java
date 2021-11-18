package observador;

import java.util.ArrayList;

public class Observable {

    private ArrayList<Observador> observadores = new ArrayList();

    public void agregar(Observador o) {
        if (!observadores.contains(o)) {
            observadores.add(o);
        }
    }

    public void quitar(Observador o) {
        observadores.remove(o);
    }

    public void avisar(Object evento) {

        ArrayList<Observador> copia = new ArrayList(observadores);

        for (Observador o : copia) {
            try {
                o.actualizar(evento, this);
            } catch (Exception e) {
                System.out.println("Error al avisar:" + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
