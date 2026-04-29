public class Tarea {
    private String nombre;
    private int prioridad;
    private String estado;
     
    public Tarea(String nombre, int prioridad, String estado) {
        this.nombre = nombre;
        this.prioridad = prioridad;
        this.estado = estado;
    }
     
    public String getNombre() { 
        return nombre; }
    public int getPrioridad() { 
        return prioridad; }
    public String getEstado() { 
        return estado; }
    public void setEstado(String estado) { 
        this.estado = estado; }
     
    @Override
    public String toString() {
        return "[" + nombre + "  Prioridad: " + prioridad + "  Estado: " + estado + "]";
    }
     
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Tarea)) return false;
        Tarea otra = (Tarea) obj;
        return this.nombre.equals(otra.nombre);
    }
} 