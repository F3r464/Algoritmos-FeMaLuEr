package Extra;
public class QueueLink<T> implements Cola<T> {
    private Nodo<T> inicio;
    private Nodo<T> fin;
    public QueueLink() {
        inicio = null;
        fin = null;
    }
    public void encolar(T dato){
        Nodo<T> nuevo = new Nodo<>(dato);
        if (isEmpty()){
            inicio = nuevo;
            fin = nuevo;
        }else{
            fin.setNext(nuevo);
            fin = nuevo;
        }
    }
    public T desencolar(){
        if (isEmpty()){
            return null;
        }
        T dato = inicio.getDato();
        inicio = inicio.getNext();
        if (inicio == null) {
            fin = null;
        }
        return dato;
    }
    public T getInicio() {
        if (isEmpty()){
            return null;
        }
        return inicio.getDato();
    }
    public boolean isEmpty(){
        return inicio==null;
    }
}