public class ContarNodos{
    public static <T> int contarNodo(NodoLista<T> head){

        int c=0;
        NodoLista<T> actual=head;

        while(actual!=null){
            c++;
            actual=actual.siguiente;
        }

        return c;
    }
}