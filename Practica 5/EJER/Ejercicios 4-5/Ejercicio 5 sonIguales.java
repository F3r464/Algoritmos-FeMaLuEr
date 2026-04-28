

public class sonIguales {

    // Método genérico que compara dos listas enlazadas
    // Retorna true si tienen los mismos elementos en el mismo orden
    public static <T> boolean sonIguales(ListaEnlazada<T> lista1, ListaEnlazada<T> lista2) {

        // Si los tamaños son diferentes, las listas no pueden ser iguales
        if (lista1.getTamanio() != lista2.getTamanio()) {
            return false;
        }

        // Obtenemos la cabeza de cada lista para recorrerlas
        NodoLista<T> actual1 = lista1.getCabeza();
        NodoLista<T> actual2 = lista2.getCabeza();

        // Recorremos ambas listas al mismo tiempo
        while (actual1 != null && actual2 != null) {

            // Comparamos el dato de cada nodo en la misma posición
            // Si alguno es diferente, las listas no son iguales
            if (!actual1.dato.equals(actual2.dato)) {
                return false;
            }

            // Avanzamos al siguiente nodo en ambas listas
            actual1 = actual1.siguiente;
            actual2 = actual2.siguiente;
        }

        // Si llegamos aquí, todos los elementos fueron iguales
        return true;
    }
}