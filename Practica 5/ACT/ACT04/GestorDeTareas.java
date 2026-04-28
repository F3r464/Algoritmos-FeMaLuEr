public class GestorDeTareas<T extends Comparable<T>>{

    private ListLinked<T> lista=new ListLinked<>();

    // agrega al final de la lista
    void agregarTarea(T t){
        lista.insertLast(t);
    }

    // elimina la tarea si existe
    boolean eliminarTarea(T t){
        return lista.removeNode(t);
    }

    // busca una tarea
    boolean contieneTarea(T t){
        return lista.search(t);
    }

    // imprime todas las tareas
    void imprimirTareas(){
        lista.print();
    }

    // cuenta el total de tareas
    int contarTareas(){
        return lista.length();
    }

    // si se trabaja con objetos Tarea, devuelve la de mayor prioridad (menor valor numérico)
    T obtenerTareaMasPrioritaria(){
        if(lista.isEmptyList()){
            return null;
        }

        Node<T> actual=lista.getFirst();
        T mejor=actual.value;

        while(actual!=null){
            if(actual.value.compareTo(mejor)<0){
                mejor=actual.value;
            }
            actual=actual.next;
        }
        return mejor;
    }

    //  invierte la lista enlazada
    void invertirTareas(){
        lista.reverse();
    }
}