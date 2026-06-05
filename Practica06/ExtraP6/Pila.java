package ExtraP6;
public interface Pila<T>{
    void push(T dato);
    T pop();
    T peek();
    boolean isEmpty();
}