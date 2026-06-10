package ejercicios;

import btree.BNode;
import btree.BTree;
import exceptions.TreeException;

public class Ejercicio01 {
    public static void ejecutar() throws TreeException {
        // ─── Separadores de Guía de Práctica ──────────────────────────────────
        System.out.println("========================================================================");
        System.out.println("  EJERCICIO 01 – Búsqueda Controlada mediante search()");
        System.out.println("========================================================================");

        BNode.resetIdCounter();
        BTree<Integer> bt = new BTree<>(4);
        
        // Árbol de la figura 10.14 de la guía de práctica
        int[] claves = {31, 12, 19, 3, 10, 13, 16, 22, 25, 28, 41, 57, 63, 33, 35, 38, 49, 52, 55, 60, 62, 67, 70, 72};
        for (int c : claves) bt.insert(c);
 
        System.out.println("Árbol de prueba construido:");
        System.out.println(bt);
        System.out.println("Altura actual: " + bt.height() + "  |  Total llaves registradas: " + bt.size());
        System.out.println("-".repeat(72));
 
        // a) Clave en hoja (extremo inicial)
        System.out.println("[a] Buscar clave 3 (Ubicada en una hoja - Extremo inicial):");
        System.out.println("    ¿Encontrado?: " + bt.search(3));
 
        // b) Clave en hoja (extremo final)
        System.out.println("[b] Buscar clave 72 (Ubicada en una hoja - Extremo final):");
        System.out.println("    ¿Encontrado?: " + bt.search(72));
 
        // c) Clave en la raíz
        System.out.println("[c] Buscar clave 31 (Ubicada exactamente en la Raíz):");
        System.out.println("    ¿Encontrado?: " + bt.search(31));
 
        // d) Clave que no existe
        System.out.println("[d] Buscar clave 99 (Inexistente en toda la estructura):");
        System.out.println("    ¿Encontrado?: " + bt.search(99));
        System.out.println();
    }
}