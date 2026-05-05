package Extra;

public class PilaLink<T> implements Pila<T> {

    private Nodo<T> cima;

    public PilaLink() {
        cima = null;
    }

    @Override
    public void push(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        nuevo.setSiguiente(cima);
        cima = nuevo;
    }

    @Override
    public T pop() {
        if (estaVacia()) {
            return null;
        }
        T dato = cima.getDato();
        cima = cima.getSiguiente();
        return dato;
    }

    @Override
    public T peek() {
        if (estaVacia()) {
            return null;
        }
        return cima.getDato();
    }

    @Override
    public boolean estaVacia() {
        return cima == null;
    }
}