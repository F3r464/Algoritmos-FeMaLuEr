public class Main {
    public static void main(String[] args) {

        separator("PASO 1 · Crear GestorDeTareas y agregar 5 tareas");
        GestorDeTareas gestor = new GestorDeTareas();
        gestor.agregarTarea(new Tarea("Diseñar BD",        2, "pendiente"));
        gestor.agregarTarea(new Tarea("Deploy produccion", 1, "pendiente"));
        gestor.agregarTarea(new Tarea("Documentar API",    3, "completada"));
        gestor.agregarTarea(new Tarea("Code review",       2, "pendiente"));
        gestor.agregarTarea(new Tarea("Corregir bug #42",  1, "completada"));

        separator("PASO 2 · Eliminar una tarea existente");
        gestor.eliminarTarea("Code review");
        gestor.eliminarTarea("Tarea Inexistente"); // caso de error controlado

        separator("PASO 3 · Imprimir todas las tareas actuales");
        gestor.imprimirTareas();

        separator("PASO 4 · Verificar si ciertas tareas existen");
        gestor.existeTarea("Diseñar BD");
        gestor.existeTarea("Code review");    // ya fue eliminada

        separator("PASO 5 · Tarea más prioritaria");
        Tarea top = gestor.obtenerTareaMasPrioritaria();
        System.out.println("  Más prioritaria: " + top);

        separator("PASO 6 · Invertir la lista e imprimirla");
        gestor.invertirTareas();
        gestor.imprimirTareas();

        separator("PASO 7 · Transferir completadas a segunda lista");
        ListaEnlazada<Tarea> completadas = gestor.transferirCompletadas();

        System.out.println("\n  >> Tareas pendientes (lista principal):");
        gestor.imprimirTareas();

        System.out.println("\n  >> Tareas completadas (segunda lista):");
        completadas.imprimir();
    }

    // Utilidad para separadores visuales
    private static void separator(String titulo) {
        System.out.println("\n" + "=".repeat(55));
        System.out.println("  " + titulo);
        System.out.println("=".repeat(55));
    }
}