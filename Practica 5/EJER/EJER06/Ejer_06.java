public class Ejer_06 {
    public static <T> ListLinked<T> concatenarListas(ListLinked<T> lista1, ListLinked<T> lista2) {
        ListLinked<T> nuevaLista = new ListLinked<>();
        
        if (lista1 != null && !lista1.isEmptyList()) {
            copiarElementos(lista1, nuevaLista);
        }
        
        if (lista2 != null && !lista2.isEmptyList()) {
            copiarElementos(lista2, nuevaLista);
        }
        
        return nuevaLista;
    }

    private static <T> void copiarElementos(ListLinked<T> origen, ListLinked<T> destino) {
        Node<T> actual = origen.getFirst();
        while (actual != null) {
            destino.insertLast(actual.value);
            actual = actual.next;
        }
    }
}
