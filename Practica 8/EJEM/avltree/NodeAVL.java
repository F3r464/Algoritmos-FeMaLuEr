package avltree;
 
import bstree.Node;
 

public class NodeAVL<E extends Comparable<E>> extends Node<E> {
 
    protected int bf;   // Factor de equilibrio (balance factor)
 
    
    public NodeAVL(E data) {
        super(data);    // Llama al constructor de Node<E>
        this.bf = 0;
    }
    
    public NodeAVL<E> getLeft() {
        return (NodeAVL<E>) this.left;
    }

    public NodeAVL<E> getRight() {
        return (NodeAVL<E>) this.right;
    }
 
    @Override
    public String toString() {
        return data + "(bf=" + bf + ")";
    }
}