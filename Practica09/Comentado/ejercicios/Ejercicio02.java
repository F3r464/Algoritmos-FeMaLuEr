package ejercicios;

import btree.BNode;
import btree.BTree;
import exceptions.TreeException;

public class Ejercicio02 {
    public static void ejecutar() throws TreeException {
        // ─── Separadores de Guía de Práctica ──────────────────────────────────
        System.out.println("========================================================================");
        System.out.println("  EJERCICIO 02 – Búsqueda por Rango Específico mediante searchRange()");
        System.out.println("========================================================================");

        BNode.resetIdCounter();
        BTree<Integer> bt = new BTree<>(4);
        int[] claves = {10, 15, 20, 25, 30, 35, 40, 45};
        for (int c : claves) bt.insert(c);
 
        System.out.println("Estructura Base: " + java.util.Arrays.toString(claves));
        System.out.println(bt);
        System.out.println("-".repeat(72));
 
        // a) Rango normal
        System.out.println("[a] Rango normal → searchRange(20, 40) (Esperado: 20 25 30 35 40)");
        bt.searchRange(20, 40);
 
        // b) Rango inválido (min > max)
        System.out.println("[b] Rango inválido → searchRange(40, 20) (Manejo controlado de error)");
        bt.searchRange(40, 20);
 
        // c) Rango inexistente en el árbol
        System.out.println("[c] Rango inexistente → searchRange(50, 60) (Sin elementos que coincidan)");
        bt.searchRange(50, 60);
 
        // d) Rango exacto (un solo elemento)
        System.out.println("[d] Rango exacto → searchRange(15, 15) (Límites idénticos)");
        bt.searchRange(15, 15);
        System.out.println();
    }
}