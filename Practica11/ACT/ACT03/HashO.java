package ACT03;
public class HashO<T> {

    private LinkList<Register<T>>[] table;
    private int size;

    @SuppressWarnings("unchecked")
    public HashO(int size) {
        this.size = size;
        table = new LinkList[size];

        for (int i = 0; i < size; i++) {
            table[i] = new LinkList<>();
        }
    }

    private int hash(int key) {
        return key % size;
    }

    // Insertar
    public void insert(int key, T data) {
        int index = hash(key);
        table[index].insert(new Register<>(key, data));
    }

    // Buscar
    public Register<T> search(int key) {
        int index = hash(key);
        return table[index].search(key);
    }

    // Eliminar
    public boolean delete(int key) {
        int index = hash(key);
        return table[index].delete(key);
    }

    // Mostrar tabla
    public void showTable() {

        for (int i = 0; i < size; i++) {
            System.out.print("[" + i + "] ");
            table[i].show();
        }
    }
}