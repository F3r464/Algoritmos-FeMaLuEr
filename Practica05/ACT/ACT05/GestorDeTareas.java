public class GestorDeTareas {
    private ListaEnlazada<Tarea> tareas;    // Lista enlazada donde se guardan todas las tareas
    
    //CONSTRUCTOR    
    public GestorDeTareas() {
        this.tareas = new ListaEnlazada<>();
    }

    // Agregar tarea al final de la lista
    public void agregarTarea(Tarea t) {
        tareas.agregar(t);          // Usa el método agregar de la lista enlazada
        System.out.println("Tarea agregada: " + t);
    }

    // Eliminar tarea por nombre
    public boolean eliminarTarea(String nombre) {
        
        // Se crea una tarea "temporal" solo con el nombre
        // (esto funciona porque equals compara por nombre)
        Tarea buscada = new Tarea(nombre, 0, " ");
        boolean resultado = tareas.eliminar(buscada);
        if (resultado) {
            System.out.println("Tarea eliminada: \"" + nombre + "\"");
        } else {
            System.out.println(" No se encontró la tarea: \"" + nombre + "\"");
        }
        return resultado;
    }

    // Imprimir todas las tareas
    public void imprimirTareas() {
        System.out.println("Lista de tareas (" + tareas.getTamanio() + " en total):");
        tareas.imprimir();
    }

    // Verificar si existe una tarea por nombre
    public boolean existeTarea(String nombre) {
        Tarea buscada = new Tarea(nombre, 0, "");
        boolean existe = tareas.contiene(buscada);
        System.out.println("¿Existe \"" + nombre + "\"? → " + (existe ? "Sí" : "No"));
        return existe;
    }

    // Obtener la tarea con menor número de prioridad (1 = más urgente)
    public Tarea obtenerTareaMasPrioritaria() {
        if (tareas.estaVacia()) return null;

        NodoLista<Tarea> actual = tareas.getCabeza();
        Tarea masPrioritaria = actual.dato;
        actual = actual.siguiente;

        while (actual != null) {
            if (actual.dato.getPrioridad() < masPrioritaria.getPrioridad()) {
                masPrioritaria = actual.dato;
            }
            actual = actual.siguiente;
        }
        return masPrioritaria;
    }

    // Invertir el orden de la lista
    public void invertirTareas() {
        tareas.invertir();
        System.out.println("Lista invertida.");
    }

    // Transferir tareas completadas a una nueva lista
    public ListaEnlazada<Tarea> transferirCompletadas() {
        ListaEnlazada<Tarea> completadas = new ListaEnlazada<>();
        ListaEnlazada<Tarea> pendientes  = new ListaEnlazada<>();

        NodoLista<Tarea> actual = tareas.getCabeza();
        while (actual != null) {
            if (actual.dato.getEstado().equalsIgnoreCase("completada")) {
                completadas.agregar(actual.dato);
            } else {
                pendientes.agregar(actual.dato);
            }
            actual = actual.siguiente;
        }

        this.tareas = pendientes; // la lista principal conserva solo las pendientes
        System.out.println("Tareas completadas transferidas (" + completadas.getTamanio() + ").");
        return completadas;
    }
}