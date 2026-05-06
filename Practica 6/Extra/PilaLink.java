package Extra;
public class PilaLink<T> implements Pila<T>{

    private Nodo<T> cima;

    public PilaLink() {
        cima = null;
    }
    @Override
    public void push(T dato){
        Nodo<T> nuevo = new Nodo<>(dato);
        nuevo.setNext(cima);
        cima = nuevo;
    }
    public T pop() {
        if (isEmpty()){
            return null;
        }
        T dato = cima.getDato();
        cima = cima.getNext();
        return dato;
    }
    public T peek() {
        if (isEmpty()){
            return null;
        }
        return cima.getDato();
    }
    public boolean isEmpty(){
        return cima == null;
    }
}