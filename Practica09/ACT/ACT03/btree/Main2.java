package btree;
import exceptions.TreeException;

public class Main2{
    public static void main(String[] args) {
        // Orden 4: máximo 3 claves y 4 hijos por nodo
        BTree<Integer> tree = new BTree<>(4);
        // Secuencia que construye el árbol
        int[] claves = {31, 12, 19, 41, 57, 63, 3, 10, 13, 16, 22, 25, 28, 33, 38, 40, 49, 52, 55, 60, 62, 67, 70, 72};

        System.out.println("Insertando claves...");
        for (int k : claves) {
            try {
                tree.insert(k);
            } catch (TreeException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("\n=== Árbol B resultante ===");
        System.out.println(tree);
    }
}