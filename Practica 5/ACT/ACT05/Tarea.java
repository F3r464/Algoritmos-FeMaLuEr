public class Tarea implements Comparable<Tarea> {
    private String nombre;
    private int prioridad;
    private String estado;

    public Tarea(String nombre, int prioridad, String estado) {
        this.nombre = nombre;
        this.prioridad = prioridad;
        this.estado = estado;
    }

    public String getNombre() { return nombre; }
    public int getPrioridad() { return prioridad; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    @Override
    public int compareTo(Tarea otra) {
        return this.nombre.compareTo(otra.nombre);  // compara nombres alfabéticamente
    }

    @Override
    public String toString() {
        return "[" + nombre + " | Prioridad: " + prioridad + " | Estado: " + estado + "]";
    }
}
