
public class Node<T>{
    //Atributos del nodo
    T value; //almacena el dato del nodo
    Node<T> next; //referencia al siguiente nodo en la lista
    //Nuestro constructor
    public Node(T value){
        this.value = value;
        this.next = null;
    }
}