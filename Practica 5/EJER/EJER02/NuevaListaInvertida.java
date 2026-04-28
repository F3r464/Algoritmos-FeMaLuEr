public class NuevaListaInvertida {
    public static <T> ListLinked<T> invertirLista(ListLinked<T> lista){
        ListLinked<T> nuevals=new ListLinked<>();
        Node<T> actual=lista.getFirst();

        while(actual!=null){
            nuevals.insertFirst(actual.value);
            actual=actual.next;
        }
        return nuevals;
    }
}
