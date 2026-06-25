package Practica11.Interfaz;

public class LinearProbing {
    public int resolve(int hash, int i, int size) {
        return (hash + i) % size;
    }
}