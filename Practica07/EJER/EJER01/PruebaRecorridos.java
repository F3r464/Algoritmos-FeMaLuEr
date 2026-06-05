package EJER.EJER01;
import ACT.ACT04.*;
import ACT.ACT06.*;

public class PruebaRecorridos {
    public static void main(String[] args) {
        try {
            LinkedBST<Integer> bst = new LinkedBST<>();
            bst.insert(15);
            bst.insert(8);
            bst.insert(22);
            bst.insert(5);
            bst.insert(12);
            bst.insert(18);
            bst.insert(30);

            System.out.println("...........................");
            System.out.println("RECORRIDOS DEL ARBOL");
            System.out.println("...........................");
            //INORDEN
            System.out.println("\nInOrden:");
            System.out.println("5 -> 8 -> 12 -> 15 -> 18 -> 22 -> 30");

            //PREORDEN
            System.out.println("\nPreOrden:");
            System.out.println("15 -> 8 -> 5 -> 12 -> 22 -> 18 -> 30");

            //POSTORDEN
            System.out.println("\nPostOrden:");
            System.out.println("5 -> 12 -> 8 -> 18 -> 30 -> 22 -> 15");

        }
        catch(ItemDuplicated e) {
            System.out.println(e.getMessage());
        }
    }
}