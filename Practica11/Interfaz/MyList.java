package Practica11.Interfaz;
public class MyList<T> {

    private Object[] data;
    private int size;
    private int capacity;

    public MyList() {
        capacity = 10;
        data = new Object[capacity];
        size = 0;
    }

    public void add(T value) {

        if (size == capacity) {
            resize();
        }

        data[size] = value;
        size++;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return (T) data[index];
    }

    public int size() {
        return size;
    }

    public void clear() {
        size = 0;
    }

    private void resize() {

        capacity = capacity * 2;

        Object[] newData = new Object[capacity];

        for (int i = 0; i < data.length; i++) {
            newData[i] = data[i];
        }

        data = newData;
    }
}