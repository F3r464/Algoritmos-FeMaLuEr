package ExtraP6;
public interface Cola<T>{
    void encolar(T dato);
    T desencolar();
    T getInicio();
    boolean isEmpty();
}