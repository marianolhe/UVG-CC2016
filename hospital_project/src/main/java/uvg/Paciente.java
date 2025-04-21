package uvg;

// --- Clase Paciente.java ---
public class Paciente implements Comparable<Paciente> {
    private String nombre;
    private String sintoma;
    private String prioridad; // A - E

    public Paciente(String nombre, String sintoma, String prioridad) {
        this.nombre = nombre;
        this.sintoma = sintoma;
        this.prioridad = prioridad;
    }

    public String getNombre() { return nombre; }
    public String getSintoma() { return sintoma; }
    public String getPrioridad() { return prioridad; }

    @Override
    public int compareTo(Paciente otro) {
        return this.prioridad.compareTo(otro.prioridad); // A < B < C < D < E
    }

    @Override
    public String toString() {
        return nombre + " - " + sintoma + " - Prioridad: " + prioridad;
    }
}