package bstree;
 

public class Node<E extends Comparable<E>> {
 
    public E data;          // Dato almacenado
    protected Node<E> left;    // Hijo izquierdo
    protected Node<E> right;   // Hijo derecho
 
    public Node(E data) {
        this.data  = data;
        this.left  = null;
        this.right = null;
    }
 
    public E getData() { return data; }
 
    public void setLeft(Node<E> left) { 
        this.left = left; 
    }

    public void setRight(Node<E> right) { 
        this.right = right; 
    }
    
    @Override
    public String toString() {
        return String.valueOf(data);
    }
}