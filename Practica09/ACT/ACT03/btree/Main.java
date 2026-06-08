package btree;
import exceptions.TreeException;

public class Main{
    public static void main(String[] args) {
        BTree<Integer> tree = new BTree<>(4);

        int[] claves = {50, 20, 70, 10, 30, 60, 80, 25, 27, 26, 65, 75, 85, 5};
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