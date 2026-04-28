
public class ListaEnlazada<T>{
    private NodoLista<T> primero;
    public ListaEnlazada(){
        primero = null;
    }
    public void insertFirst(T x){ //Inserta al inicio.
        NodoLista<T> nuevoNodo = new NodoLista<>(x);
        nuevoNodo.siguiente= primero;
        primero = nuevoNodo;
    }
    public void insertLast(T x){ //Inserta al final.
        NodoLista<T> nuevoNodo = new NodoLista<>(x);
        if (primero == null){
            primero = nuevoNodo;
            return;
        }
        NodoLista<T> actual = primero;
        while (actual.siguiente != null)
            actual = actual.siguiente;
        actual.siguiente = nuevoNodo;
    }
    public boolean removeNodoLista(T x){ //Elimina el nodo con el valor indicado.
        if (primero == null) return false;
        if (primero.value.equals(x)){
            primero = primero.siguiente;
            return true;
        }
        NodoLista<T> actual = primero;
        while (actual.siguiente != null){
            if (actual.siguiente.value.equals(x)){
                actual.siguiente = actual.siguiente.siguiente;
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }
    public boolean search(T x){ //Retorna true si el elemento existe.
        NodoLista<T> actual = primero;
        while (actual != null){
            if (actual.value.equals(x)) return true;
            actual = actual.siguiente;
        }
        return false;
    }
    public int length(){ //Retorna la cantidad de elementos.
        int contador = 0;
        NodoLista<T> actual = primero;
        while (actual != null){
            contador++;
            actual = actual.siguiente;
        }
        return contador;
    }
    public boolean isEmptyList(){ //Retorna true si la lista está vacía.
        return primero == null;
    }
    public void print(){ //Imprime todos los elementos.
        NodoLista<T> actual = primero;
        while (actual != null){
            System.out.println(actual.value);
            actual = actual.siguiente;
        }
    }
    public void reverse(){ //Invierte el orden de los nodos.
        NodoLista<T> anterior = null;
        NodoLista<T> actual = primero;
        while (actual != null){
            NodoLista<T> siguiente = actual.siguiente;
            actual.siguiente = anterior;
            anterior = actual;
            actual = siguiente;
        }
        primero = anterior;
    }
    public NodoLista<T> getFirst(){ //Para acceso directo al primer nodo.
        return primero;
    }
}