public class Ejer_07 {
    public static <T> ListLinked<T> invertirLista(ListLinked<T> lista) {
        ListLinked<T> nuevaLista = new ListLinked<>();
        
        if (lista != null && !lista.isEmptyList()) {
            Node<T> actual = lista.getFirst();
            
            while (actual != null) {
                nuevaLista.insertFirst(actual.value);
                actual = actual.next;
            }
        }
        
        return nuevaLista;
    }
}