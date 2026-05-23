package EJER.EJER03;

import ACT.ACT04.ExceptionIsEmpty;
import ACT.ACT04.ItemDuplicated;

public class TestEjer3 {
    public static void main(String[] args) {
        AVLtreee<Integer> arbol = new AVLtreee<>();

        System.out.println("insert");
        try {
            // Insertamos elementos de tal forma que se genere un árbol balanceado
            int[] valores = {50, 25, 75, 15, 35, 60, 90, 10, 20, 30, 40};
            for (int val : valores) {
                arbol.insert(val);
            }
            
            arbol.inOrder(); // Debería mostrar los números ordenados de menor a mayor
            arbol.preOrder(); // Muestra la raíz y sus factores de balance (bf)

        } catch (ItemDuplicated e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\ndelete");
        try {
            // Caso A: Eliminar una hoja que no altera el balance general
            System.out.println("elimina 10");
            arbol.delete(10);
            arbol.preOrder();

            // Caso B: Eliminar un nodo con un solo hijo
            System.out.println("\nelimina 15");
            arbol.delete(15);
            arbol.preOrder();

            // Caso C: Eliminar un nodo con dos hijos (Forzará reemplazo por sucesor inorden)
            System.out.println("\nelimina 25");
            arbol.delete(25);
            arbol.preOrder();

            // Caso D: Forzar una rotación por eliminación
            // Al eliminar el 50, se desencadenará una reestructuración de balances
            System.out.println("\nelimina 50");
            arbol.delete(50);
            arbol.inOrder();
            arbol.preOrder();

        } catch (ExceptionIsEmpty e) {
            System.out.println("Error");
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
