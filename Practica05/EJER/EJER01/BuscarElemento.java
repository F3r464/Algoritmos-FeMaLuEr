
public class BuscarElemento{
    public static<T> boolean buscarElemento(ListLinked<T>lista, T valor){
        Node<T> actual = lista.getFirst();
        while (actual!=null){
            if (actual.value.equals(valor)) return true;
            actual = actual.next;
        }
        return false;
    }  
}
