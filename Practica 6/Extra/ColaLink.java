package Extra;

public class ColaLink<T> implements Cola<T> {

    private Nodo<T> frente;
    private Nodo<T> fin;

    public ColaLink() {
        frente = null;
        fin = null;
    }

    @Override
    public void encolar(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);

        if (estaVacia()) {
            frente = nuevo;
            fin = nuevo;
        } else {
            fin.setSiguiente(nuevo);
            fin = nuevo;
        }
    }

    @Override
    public T desencolar() {
        if (estaVacia()) {
            return null;
        }

        T dato = frente.getDato();
        frente = frente.getSiguiente();

        if (frente == null) {
            fin = null;
        }

        return dato;
    }

    @Override
    public T frente() {
        if (estaVacia()) {
            return null;
        }
        return frente.getDato();
    }

    @Override
    public boolean estaVacia() {
        return frente == null;
    }
}