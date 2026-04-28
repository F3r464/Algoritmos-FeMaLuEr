

public class contarNodos {

    // Método genérico que recibe la cabeza de una lista enlazada
    // y retorna el número total de nodos que contiene
    public static <T> int contarNodos(NodoLista<T> head) {

        // Contador que irá sumando cada nodo encontrado
        int contador = 0;

        // Nodo auxiliar que empieza desde la cabeza de la lista
        NodoLista<T> actual = head;

        // Recorremos la lista mientras el nodo actual no sea null
        while (actual != null) {

            // Encontramos un nodo, sumamos 1 al contador
            contador++;

            // Avanzamos al siguiente nodo usando "siguiente"
            actual = actual.siguiente;
        }

        // Retornamos el total de nodos encontrados
        return contador;
    }
}