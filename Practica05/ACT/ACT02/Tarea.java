
public class Tarea implements Comparable<Tarea>{
    private String titulo;
    private int prioridad; //1 = alta, 2 = media, 3 = baja
    private String estado; //"pendiente", "completada"
    //Aqui nuestro constructor
    public Tarea(String titulo, int prioridad, String estado){
        this.titulo =titulo;
        this.prioridad = prioridad;
        this.estado= estado;
    }
    public String getTitulo() {return titulo;}
    public int getPrioridad() {return prioridad;}
    public String getEstado() {return estado;}
    public void setTitulo(String titulo) {this.titulo = titulo;}
    public void setPrioridad(int prioridad) {this.prioridad = prioridad;}
    public void setEstado(String estado) {this.estado = estado;}
    @Override
    public String toString(){
        return "[" + titulo + " | Prioridad: " + prioridad + " | Estado: " + estado + "]";
    }
    @Override
    public int compareTo(Tarea otra){
        return Integer.compare(this.prioridad, otra.prioridad);
    }
}