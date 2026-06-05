package Lab08;

import avltree.AVLTree;
import bstree.BSTree;
import exceptions.ItemDuplicated;
import exceptions.ItemNotFound;
 

public class TestAVL {
	
	static void titulo(String t) {
        System.out.println("\n╔══════════════════════════════════════════════════════╗");
        System.out.println("║  " + t);
        System.out.println("╚══════════════════════════════════════════════════════╝");
    }
	
	
    static void seccion(String s) {
        System.out.println("\n┌── " + s + " ──────────────────────────────────────");
    }
	
	  public static void main(String[] args) throws ItemDuplicated, ItemNotFound{
		  
	        prueba_RSL(); //ACTIVIDAD 3.5 CASOS DE PRUEBAS
	        prueba_RSR(); //ACTIVIDAD 3.5 CASOS DE PRUEBAS
	        prueba_RDL(); //ACTIVIDAD 3.5 CASOS DE PRUEBAS
	        prueba_RDR(); //ACTIVIDAD 3.5 CASOS DE PRUEBAS
	        prueba_Eliminacion();	//ACTIVIDAD 3.5 CASOS DE PRUEBAS
	       ejercicio5_BFSRecursivo(); //EJERCICIO 5 BFS RECURSIVO
	       ejercicio6_PreOrden(); //EJERCICIO 6 PRE ORDEN
	       ejercicio7_InsertarEliminarConRotaciones(); //EJERCICIO 7 ROTACIONES
	       
	  }        
	        
// ACTIVIDAD 3.5 CASOS DE PRUEBAS
//-------------------------------------------------------------------------------------------------------------------
	       static void prueba_RSL() throws ItemDuplicated {
	    	   	titulo("PRUEBA RSL – Caso Derecha-Derecha"); 
	    	   	System.out.println("Secuencia: 10 → 20 → 30");
	            System.out.println("Todos van a la derecha → se desequilibra → RSL en 10");
	            AVLTree<Integer> avl1 = new AVLTree<>();
	            avl1.insert(10);
	            avl1.insert(20);
	            avl1.insert(30);
	            avl1.printTree();
	            avl1.inOrder();
	    }
	       
	       static void prueba_RSR() throws ItemDuplicated{
	    	   titulo("PRUEBA RSR – Caso Izquierda-Izquierda");
	    	   System.out.println("Secuencia: 30 → 20 → 10");
	           System.out.println("Todos van a la izquierda → se desequilibra → RSR en 30");
	           AVLTree<Integer> avl2 = new AVLTree<>();
	           avl2.insert(30);
	           avl2.insert(20);
	           avl2.insert(10);
	           avl2.printTree();
	           avl2.inOrder();
	       }
	       
	       static void prueba_RDL() throws ItemDuplicated{
	    	   titulo("PRUEBA RDL – Caso Derecha-Izquierda");
	           System.out.println("Secuencia: 10 → 30 → 20");
	           System.out.println("10 → inserta 30 a su derecha → inserta 20 entre 10 y 30");
	           System.out.println("Caso DI → RSR en 30, luego RSL en 10");
	           AVLTree<Integer> avl3 = new AVLTree<>();
	           avl3.insert(10);
	           avl3.insert(30);
	           avl3.insert(20);
	           avl3.printTree();
	           avl3.inOrder();
	       }
	       
	       static void prueba_RDR() throws ItemDuplicated{
	    	   titulo("PRUEBA RDR – Caso Izquierda-Derecha");
	           System.out.println("Secuencia: 30 → 10 → 20");
	           System.out.println("30 → inserta 10 a su izquierda → inserta 20 entre 10 y 30");
	           System.out.println("Caso ID → RSL en 10, luego RSR en 30");
	           AVLTree<Integer> avl4 = new AVLTree<>();
	           avl4.insert(30);
	           avl4.insert(10);
	           avl4.insert(20);
	           avl4.printTree();
	           avl4.inOrder();
	       }
	       
	       static void prueba_Eliminacion() throws ItemDuplicated {
	           titulo("PRUEBA ELIMINACIONES");
	           AVLTree<Integer> avl5 = new AVLTree<>();
	           avl5.insert(50);
	           avl5.insert(30);
	           avl5.insert(70);
	           avl5.insert(20);
	           avl5.insert(40);
	           avl5.insert(60);
	           avl5.insert(80); 
	    
	           System.out.println("\nÁrbol inicial:");
	           avl5.printTree();
	    
	           seccion("Caso 1 – Eliminar nodo HOJA (20)");
	           try { avl5.delete(20); } catch (ItemNotFound e) { System.out.println(e.getMessage()); }
	           avl5.printTree();
	    
	           seccion("Caso 2 – Eliminar nodo con UN HIJO (80 → solo hijo 60 quedará)");
	           try { avl5.delete(80); } catch (ItemNotFound e) { System.out.println(e.getMessage()); }
	           avl5.printTree();
	    
	           seccion("Caso 3 – Eliminar nodo con DOS HIJOS (50, usa sucesor inorden = 60)");
	           try { avl5.delete(50); } catch (ItemNotFound e) { System.out.println(e.getMessage()); }
	           avl5.printTree();
	    
	           seccion("Eliminación con rotación – eliminar 40 (puede desequilibrar)");
	           try { avl5.delete(40); } catch (ItemNotFound e) { System.out.println(e.getMessage()); }
	           avl5.printTree();
	           avl5.inOrder();
	       }
//------------------------------------------------------------------------------------------------------------------      	 
	       
//EJERCICIO 5 RECORRIDO POR AMPLITUD
//------------------------------------------------------------------------------------------------------------------   
	       
	    // ══════════════════════════════════════════════════════════
	       //  EJERCICIO 5 – RECORRIDO POR AMPLITUD RECURSIVO (BFS)
	       //
	       //  El método levelOrderAVL() vive en AVLTree.
	       //  Lógica: por cada nivel i (0..altura-1) llama al auxiliar
	       //  imprimirNivel(node, i) que baja recursivamente i pasos
	       //  e imprime el nodo cuando i llega a 0.
	       //  NO usa cola, todo es recursión pura.
	       //
	       //  Árbol construido con: 50,30,70,20,40,60,80,10,25,65
	       //  Resultado esperado  : 50 30 70 20 40 60 80 10 25 65
	       // ══════════════════════════════════════════════════════════
	    
	       static void ejercicio5_BFSRecursivo() throws ItemDuplicated {
	           titulo("EJERCICIO 5 – BFS Recursivo por niveles");
	    
	           AVLTree<Integer> avl6 = new AVLTree<>();
	           avl6.insert(50);
	           avl6.insert(30);
	           avl6.insert(70);
	           avl6.insert(20);
	           avl6.insert(40);
	           avl6.insert(60);
	           avl6.insert(80);
	           avl6.insert(10);
	           avl6.insert(25);
	           avl6.insert(65);
	           
	           System.out.println("\nÁrbol AVL construido:");
	           avl6.printTree();
	    
	           System.out.println("\nRecorrido por niveles (BFS recursivo desde AVLTree):");
	           avl6.levelOrderAVL();   // método definido en AVLTree (Ej. 5)
	    
	           System.out.println("Altura del árbol: " + avl6.height());
	           avl6.inOrder();         // comparación: inorden siempre sale ordenado
	       }
//------------------------------------------------------------------------------------------------------------------   	    

//EJERCICIO 6 RECORRIDO PreOrden
//------------------------------------------------------------------------------------------------------------------ 	
	       
	       //(raíz → izq → der)
	       //  preOrderAVL() está definido en AVLTree.
	       //  Se prueba con 3 árboles de secuencias distintas para
	       //  verificar que el resultado sea correcto en cada caso.

	    
	       static void ejercicio6_PreOrden() throws ItemDuplicated {
	           titulo("EJERCICIO 6 – Recorrido PreOrden en AVL");
	    
	           // ── Árbol 1: secuencia del enunciado Ej.5 ───────────────
	           seccion("Árbol 1: 50,30,70,20,40,60,80,10,25,65");
	           AVLTree<Integer> avl7 = new AVLTree<>();
	           avl7.insert(50);
	           avl7.insert(30);
	           avl7.insert(70);
	           avl7.insert(20);
	           avl7.insert(40);
	           avl7.insert(60);
	           avl7.insert(80);
	           avl7.insert(10);
	           avl7.insert(25);
	           avl7.insert(65);
	           avl7.printTree();
	           avl7.preOrderAVL();    // método definido en AVLTree (Ej. 6)
	           avl7.inOrder();        // referencia para comparar
	    
	           // ── Árbol 2: inserción que provoca rotaciones ────────────
	           seccion("Árbol 2: 10,20,30,40,50 (caso degenerado en BST, balanceado en AVL)");
	           AVLTree<Integer> avl8 = new AVLTree<>();
	           avl8.insert(10);
	           avl8.insert(20);
	           avl8.insert(30);
	           avl8.insert(40);
	           avl8.insert(50);
	           
	           avl8.printTree();
	           avl8.preOrderAVL();
	           avl8.inOrder();
	    
	           // ── Árbol 3: secuencia con rotaciones dobles ────────────
	           seccion("Árbol 3: 30,10,20,50,40,60");
	           AVLTree<Integer> avl9 = new AVLTree<>();
	           avl9.insert(30);
	           avl9.insert(10);
	           avl9.insert(20);
	           avl9.insert(50);
	           avl9.insert(40);
	           avl9.insert(60);
	           
	           avl9.printTree();
	           avl9.preOrderAVL();
	           avl9.inOrder();
	       }
//EJERCICIO 7 INSERSION Y ELIMINACION CON ROTACIONES
//------------------------------------------------------------------------------------------------------------------ 	
	   //  Se parte de un árbol vacío, se insertan claves que
	       //  provoquen los 4 tipos de desbalance, y luego se eliminan
	       //  claves que también fuercen nuevas rotaciones.
	       //  Todo en el main, sin modificar AVLTree.
	       // ══════════════════════════════════════════════════════════
	    
	       static void ejercicio7_InsertarEliminarConRotaciones() throws ItemDuplicated {
	           titulo("EJERCICIO 7 – Inserción y eliminación con rotaciones");
	    
	           AVLTree<Integer> avl10 = new AVLTree<>();
	    
	           // ── FASE 1: Inserciones que provocan los 4 tipos ────────
	           System.out.println("\n--- FASE 1: Inserciones ---");
	    
	           // Insertar base sin desbalance
	           seccion("Insertando 40, 20, 60 (sin rotación aún)");
	           avl10.insert(40);
	           avl10.insert(20);
	           avl10.insert(60);
	           avl10.printTree();
	           avl10.inOrder();
	    
	           // Caso II → RSR en 40
	           seccion("Insertando 10, 5 → Caso II → RSR");
	           avl10.insert(10);
	           avl10.insert(5);
	           avl10.printTree();
	           avl10.inOrder();
	    
	           // Caso DD → RSL en 60
	           seccion("Insertando 80, 90 → Caso DD → RSL");
	           avl10.insert(80);
	           avl10.insert(90);
	           avl10.printTree();
	           avl10.inOrder();
	    
	           // Caso ID → Rotación Doble (RSL en 5, luego RSR)
	           seccion("Insertando 3, 7 → Caso ID → Rotación Doble");
	           avl10.insert(3);
	           avl10.insert(7);
	           avl10.printTree();
	           avl10.inOrder();
	    
	           // Caso DI → Rotación Doble (RSR en 90, luego RSL)
	           seccion("Insertando 85, 70 → Caso DI → Rotación Doble");
	           avl10.insert(85);
	           avl10.insert(70);
	           avl10.printTree();
	           avl10.inOrder();
	    
	           System.out.println("\nÁrbol completo tras todas las inserciones:");
	           avl10.printTree();
	           avl10.levelOrderAVL();
	           avl10.preOrderAVL();
	           avl10.inOrder();
	           System.out.println("Altura: " + avl10.height());
	    
	           // ── FASE 2: Eliminaciones que pueden generar rotaciones ──
	           System.out.println("\n--- FASE 2: Eliminaciones ---");
	    
	           seccion("Eliminar 5 (nodo hoja)");
	           try { avl10.delete(5); } catch (ItemNotFound e) { System.out.println(e.getMessage()); }
	           avl10.printTree();
	           avl10.inOrder();
	    
	           seccion("Eliminar 10 (puede causar rotación)");
	           try { avl10.delete(10); } catch (ItemNotFound e) { System.out.println(e.getMessage()); }
	           avl10.printTree();
	           avl10.inOrder();
	    
	           seccion("Eliminar 40 (dos hijos → usa sucesor inorden)");
	           try { avl10.delete(40); } catch (ItemNotFound e) { System.out.println(e.getMessage()); }
	           avl10.printTree();
	           avl10.inOrder();
	    
	           seccion("Eliminar 60 (puede causar rotación)");
	           try { avl10.delete(60); } catch (ItemNotFound e) { System.out.println(e.getMessage()); }
	           avl10.printTree();
	           avl10.inOrder();
	    
	           seccion("Eliminar 90 (puede causar rotación encadenada)");
	           try { avl10.delete(90); } catch (ItemNotFound e) { System.out.println(e.getMessage()); }
	           avl10.printTree();
	           avl10.inOrder();
	    
	           System.out.println("\nÁrbol final tras todas las eliminaciones:");
	           avl10.printTree();
	           avl10.levelOrderAVL();
	           avl10.inOrder();
	           System.out.println("Altura final: " + avl10.height());
	       }
	   
	   
}
 