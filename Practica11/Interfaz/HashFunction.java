package Practica11.Interfaz;

public class HashFunction {

    private int size;

    public HashFunction(int size) {
        this.size = size;
    }

    public int hashDivision(int key) {
        return key % size;
    }
}