package Extra;
public interface Cola<T> {
    void encolar(T dato);
    T desencolar();
    T getInicio();
    boolean isEmpty();
}