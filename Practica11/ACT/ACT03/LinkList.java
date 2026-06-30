package ACT03;

public class LinkList<T extends Register<?>> {

    private Node<T> head;

    public LinkList() {
        head = null;
    }

    // Insertar al inicio
    public void insert(T data) {
        Node<T> nuevo = new Node<>(data);
        nuevo.next = head;
        head = nuevo;
    }

    // Buscar por clave
    public T search(int key) {
        Node<T> aux = head;

        while (aux != null) {
            if (aux.data.getKey() == key) {
                return aux.data;
            }
            aux = aux.next;
        }

        return null;
    }

    // Eliminar por clave
    public boolean delete(int key) {

        if (head == null)
            return false;

        if (head.data.getKey() == key) {
            head = head.next;
            return true;
        }

        Node<T> ant = head;
        Node<T> act = head.next;

        while (act != null) {
            if (act.data.getKey() == key) {
                ant.next = act.next;
                return true;
            }
            ant = act;
            act = act.next;
        }

        return false;
    }

    public void show() {
        Node<T> aux = head;

        while (aux != null) {
            System.out.print(aux.data + " -> ");
            aux = aux.next;
        }

        System.out.println("null");
    }
}