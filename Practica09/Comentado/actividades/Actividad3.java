package actividades;

import btree.BNode;
import btree.BTree;
import exceptions.TreeException;

public class Actividad3 {
    public static void ejecutar() throws TreeException {
        // Separador decorativo de la Actividad
        System.out.println("========================================================================");
        System.out.println("  ACTIVIDAD 3.3 – Inicialización e Inserción Básica del Árbol B");
        System.out.println("========================================================================");
        
        // Inicializar un Árbol B genérico de prueba de orden 3
        BNode.resetIdCounter();
        BTree<String> stringTree = new BTree<>(3);
        
        // Inserción ordenada de caracteres alfanuméricos
        stringTree.insert("A");
        stringTree.insert("M");
        stringTree.insert("Z");
        
        System.out.println("Estructura en memoria del Árbol B construido (Strings):");
        System.out.println(stringTree);
    }
}