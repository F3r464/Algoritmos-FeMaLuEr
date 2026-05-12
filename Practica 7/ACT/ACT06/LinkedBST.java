package ACT.ACT06;
import ACT.ACT04.*;
import ACT.ACT05.*;
public class LinkedBST<E extends Comparable<E>> implements BinarySearchTree<E>{
    
    public class Node{
        public E dato;
        public Node left;
        public Node right;

        public Node(E dato){
            this(dato, null, null);
        }

        public Node(E dato, Node left, Node right){
            this.dato = dato;
            this.left = left;
            this.right = right;
        }
    }
    protected Node root;
    public LinkedBST(){
        this.root = null;
    }
    public void insert(E dato) throws ItemDuplicated{
        this.root = insertRec(this.root, dato);
    }
    private Node insertRec(Node actual, E dato) throws ItemDuplicated{
        if (actual == null){
            return new Node(dato);
        }
        int cmp = dato.compareTo(actual.dato);
        if(cmp < 0){
            actual.left = insertRec(actual.left, dato);
        } else if(cmp > 0){
            actual.right = insertRec(actual.right, dato);
        } else{
            throw new ItemDuplicated("duplicado : " + dato);
        }
        return actual;
    }
    public E search(E dato) throws ItemNoFound{
        Node result = searchRec(this.root, dato);
        if (result == null){
            throw new ItemNoFound("no hay : " + dato);
        }
        return result.dato;
    }

    private Node searchRec(Node actual, E dato){
        if(actual == null ||dato.equals(actual.dato)){
            return actual;
        }
        if (dato.compareTo(actual.dato) < 0) {
            return searchRec(actual.left, dato);
        } else {
            return searchRec(actual.right, dato);
        }
    }

    public void delete(E dato) throws ExceptionIsEmpty{
        if (isEmpty()){
            throw new ExceptionIsEmpty();
        }
        this.root=deleteRec(this.root, dato);
    }
    private Node deleteRec(Node actual, E dato){
        if (actual == null){
            return null;
        }
        int cmp = dato.compareTo(actual.dato);
        if (cmp <0){
            actual.left=deleteRec(actual.left, dato);
        }
        else if (cmp >0){
            actual.right =deleteRec(actual.right, dato);
        }
        else {
            if (actual.left ==null){
                return actual.right;
            }
            if (actual.right==null){
                return actual.left;
            }
            actual.dato =findMin(actual.right);
            actual.right =deleteRec(actual.right, actual.dato);
        }
        return actual;
    }
    private E findMin(Node node) {
        if (node.left == null) {
            return node.dato;
        } else {
            return findMin(node.left);
        }
    }


    public boolean isEmpty(){
        return this.root == null;
    }

    @Override
    public String toString(){
        return "Arbol: [" + toStringRec(root) + "]";
    }

    private String toStringRec(Node actual) {
        if (actual == null) return "";
        return toStringRec(actual.left) + " " + actual.dato + " " + toStringRec(actual.right);
    }
}
