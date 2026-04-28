
public class ListLinked<T>{
    private Node<T> primero;
    public ListLinked(){
        primero = null;
    }
    public void insertFirst(T x){ //Inserta al inicio.
        Node<T> nuevoNodo = new Node<>(x);
        nuevoNodo.next= primero;
        primero = nuevoNodo;
    }
    public void insertLast(T x){ //Inserta al final.
        Node<T> nuevoNodo = new Node<>(x);
        if (primero == null){
            primero = nuevoNodo;
            return;
        }
        Node<T> actual = primero;
        while (actual.next != null)
            actual = actual.next;
        actual.next = nuevoNodo;
    }
    public boolean removeNode(T x){ //Elimina el nodo con el valor indicado.
        if (primero == null) return false;
        if (primero.value.equals(x)){
            primero = primero.next;
            return true;
        }
        Node<T> actual = primero;
        while (actual.next != null){
            if (actual.next.value.equals(x)){
                actual.next = actual.next.next;
                return true;
            }
            actual = actual.next;
        }
        return false;
    }
    public boolean search(T x){ //Retorna true si el elemento existe.
        Node<T> actual = primero;
        while (actual != null){
            if (actual.value.equals(x)) return true;
            actual = actual.next;
        }
        return false;
    }
    public int length(){ //Retorna la cantidad de elementos.
        int contador = 0;
        Node<T> actual = primero;
        while (actual != null){
            contador++;
            actual = actual.next;
        }
        return contador;
    }
    public boolean isEmptyList(){ //Retorna true si la lista está vacía.
        return primero == null;
    }
    public void print(){ //Imprime todos los elementos.
        Node<T> actual = primero;
        while (actual != null){
            System.out.println(actual.value);
            actual = actual.next;
        }
    }
    public void reverse(){ //Invierte el orden de los nodos.
        Node<T> anterior = null;
        Node<T> actual = primero;
        while (actual != null){
            Node<T> siguiente = actual.next;
            actual.next = anterior;
            anterior = actual;
            actual = siguiente;
        }
        primero = anterior;
    }
    public Node<T> getFirst(){ //Para acceso directo al primer nodo.
        return primero;
    }
}