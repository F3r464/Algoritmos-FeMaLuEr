package EJER01;
import exceptions.TreeException;

public class Main3{

    public static void main(String[] args) {

        BTree<Integer> tree = new BTree<>(4);
        int[] claves = {50, 20, 70, 10, 30, 60, 80, 25, 27, 26, 65, 75, 85, 5};

        for (int k : claves) {
            try { tree.insert(k); }
            catch (TreeException e) { System.out.println(e.getMessage()); }
        }

        System.out.println(tree);
        System.out.println("=== Pruebas search() ===\n");

        // Hoja extremo inicial
        System.out.print("Buscar 5: ");
        System.out.println(tree.search(5));

        // Hoja extremo final
        System.out.print("Buscar 85: ");
        System.out.println(tree.search(85));

        // En la raíz
        System.out.print("Buscar 50: ");
        System.out.println(tree.search(50));

        // No encontrado
        System.out.print("Buscar 99: ");
        System.out.println(tree.search(99));
    }
}