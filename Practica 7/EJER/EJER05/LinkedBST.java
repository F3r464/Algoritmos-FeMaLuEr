package EJER.EJER05;



class ItemDuplicated extends Exception {
 public ItemDuplicated(String msg) { super(msg); }
}
class ItemNotFound extends Exception {
 public ItemNotFound(String msg) { super(msg); }
}
class ExceptionIsEmpty extends Exception {
 public ExceptionIsEmpty(String msg) { super(msg); }
}

//--- CLASE PRINCIPAL ---
public class LinkedBST<E extends Comparable<E>> {
 
 class Node {
     public E data;
     public Node left;
     public Node right;

     public Node(E data) {
         this(data, null, null);
     }

     public Node(E data, Node left, Node right) {
         this.data = data;
         this.left = left;
         this.right = right;
     }
 }

 private Node root;

 public LinkedBST() {
     this.root = null;
 }

 public boolean isEmpty() {
     return this.root == null;
 }

 // --- a. INSERTAR ---
 public void insert(E data) throws ItemDuplicated {
     root = insertRec(root, data);
 }

 private Node insertRec(Node n, E d) throws ItemDuplicated {
     if (n == null) return new Node(d);
     int cmp = d.compareTo(n.data);
     if (cmp > 0) n.right = insertRec(n.right, d);
     else if (cmp < 0) n.left = insertRec(n.left, d);
     else throw new ItemDuplicated("El dato ya existe");
     return n;
 }

 // --- BUSCAR ---
 public E search(E data) throws ItemNotFound {
     if (root == null) throw new ItemNotFound("Dato no encontrado");
     if (searchRec(root, data)) return data;
     else throw new ItemNotFound("Dato no encontrado");
 }

 private boolean searchRec(Node n, E d) {
     if (n == null) return false;
     int cmp = d.compareTo(n.data);
     if (cmp > 0) return searchRec(n.right, d);
     else if (cmp < 0) return searchRec(n.left, d);
     else return true;
 }

 // --- ELIMINAR ---
 public void delete(E data) throws ItemNotFound, ExceptionIsEmpty {
     if (isEmpty()) throw new ExceptionIsEmpty("El árbol está vacío");
     root = deleteRec(root, data);
 }

 private Node deleteRec(Node n, E d) throws ItemNotFound {
     if (n == null) throw new ItemNotFound("El dato no existe");
     int cmp = d.compareTo(n.data);
     if (cmp < 0) n.left = deleteRec(n.left, d);
     else if (cmp > 0) n.right = deleteRec(n.right, d);
     else {
         if (n.left == null) return n.right;
         else if (n.right == null) return n.left;
         n.data = minElement(n.right);
         n.right = deleteRec(n.right, n.data);
     }
     return n;
 }

 private E minElement(Node n) {
     E minv = n.data;
     while (n.left != null) {
         minv = n.left.data;
         n = n.left;
     }
     return minv;
 }

 // --- b. MÉTODO searchRange(min, max) ---
 public String searchRange(E min, E max) {
     return searchRange(root, min, max);
 }

 private String searchRange(Node n, E min, E max) {
     if (n == null) return "";
     String res = "";
     if (n.data.compareTo(min) > 0) res += searchRange(n.left, min, max);
     if (n.data.compareTo(min) >= 0 && n.data.compareTo(max) <= 0) res += n.data + " ";
     if (n.data.compareTo(max) < 0) res += searchRange(n.right, min, max);
     return res;
 }

 // --- c. MÉTODO countLeaves() ---
 public int countLeaves() {
     return countLeaves(root);
 }

 private int countLeaves(Node n) {
     if (n == null) return 0;
     if (n.left == null && n.right == null) return 1;
     return countLeaves(n.left) + countLeaves(n.right);
 }

 // --- d. MÉTODO printDescending() ---
 public void printDescending() {
     printDescending(root);
 }

 private void printDescending(Node n) {
     if (n != null) {
         printDescending(n.right);
         System.out.print(n.data + " ");
         printDescending(n.left);
     }
 }

}