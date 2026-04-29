public class GestorDeTareas { 

 

    // Lista enlazada donde se guardan todas las tareas 

    private ListaEnlazada<Tarea> tareas; 

 

    // Constructor: crea una lista vacía de tareas 

    public GestorDeTareas() { 

        this.tareas = new ListaEnlazada<>(); 

    } 

 

    //  Agregar tarea al final de la lista 

    public void agregarTarea(Tarea t) { 

 

        // Usa el método agregar de la lista enlazada 

        tareas.agregar(t); 

 

        // Muestra mensaje de confirmación 

        System.out.println(" Tarea agregada: " + t); 

    } 

 

    //  Eliminar tarea por nombre 

    public boolean eliminarTarea(String nombre) { 

 

        // Se crea una tarea "temporal" solo con el nombre 

        // (esto funciona porque equals compara por nombre) 

        Tarea buscada = new Tarea(nombre, 0, ""); 

 

        // Se intenta eliminar de la lista 

        boolean resultado = tareas.eliminar(buscada); 

 

        // Mensaje dependiendo si se eliminó o no 

        if (resultado) { 

            System.out.println("  Tarea eliminada: \"" + nombre + "\""); 

        } else { 

            System.out.println(" No se encontró la tarea: \"" + nombre + "\""); 

        } 

 

        return resultado; 

    } 

 

    //  Imprimir todas las tareas 

    public void imprimirTareas() { 

 

        // Muestra cantidad total de tareas 

        System.out.println(" Lista de tareas (" + tareas.getTamanio() + " en total):"); 

 

        // Llama al método imprimir de la lista 

        tareas.imprimir(); 

    } 

 

    //  Verificar si existe una tarea por nombre 

    public boolean existeTarea(String nombre) { 

 

        // Se crea una tarea temporal para comparar 

        Tarea buscada = new Tarea(nombre, 0, ""); 

 

        // Se verifica si está en la lista 

        boolean existe = tareas.contiene(buscada); 

 

        // Muestra resultado 

        System.out.println(" ¿Existe \"" + nombre + "\"?  " + (existe ? "Sí" : "No")); 

 

        return existe; 

    } 

 