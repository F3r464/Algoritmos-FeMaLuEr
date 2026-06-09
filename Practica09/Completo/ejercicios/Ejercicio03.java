package ejercicios;

import btree.BNode;
import btree.BTree;
import exceptions.TreeException;

public class Ejercicio03 {
    public static void ejecutar() throws TreeException {
        // ─── Separadores de Guía de Práctica ──────────────────────────────────
        System.out.println("========================================================================");
        System.out.println("  EJERCICIO 03 – Eliminación con Redistribución y Fusión Colectiva");
        System.out.println("========================================================================");

        BNode.resetIdCounter();
        BTree<Integer> bt = new BTree<>(5);
 
        // Árbol base del marco teórico (figuras 10.12 / 10.8)
        int[] claves = {10, 20, 5, 9, 12, 18, 25, 65, 92, 99};
        for (int c : claves) bt.insert(c);
 
        System.out.println("Estructura Inicial del Árbol de Prueba (Orden 5):");
        System.out.println(bt);
        System.out.println("-".repeat(72));
 
        // Ejemplo 4 del marco teórico: borrar directo
        System.out.println("▶ Paso 1: Eliminar 65 (Nodo hoja con llaves excedentes → Remoción directa):");
        bt.remove(65);
        System.out.println(bt);
 
        // Ejemplo 6 del marco teórico: eliminar con redistribución
        System.out.println("▶ Paso 2: Eliminar 12 (Genera Underflow → Redistribución desde Hermano Derecho):");
        bt.remove(12);
        System.out.println(bt);
 
        // Ejemplo 7: eliminar con fusión estructural
        System.out.println("▶ Paso 3: Eliminar 5 (Genera Underflow Crítico → Fusión Completa de Hijos):");
        bt.remove(5);
        System.out.println(bt);
 
        // Ejemplo 5: eliminar nodo interno mediante reemplazo con sucesor
        System.out.println("▶ Paso 4: Eliminar 20 (Nodo Interno → Intercambio con Sucesor In-Order):");
        bt.remove(20);
        System.out.println(bt);
        System.out.println();
    }
}