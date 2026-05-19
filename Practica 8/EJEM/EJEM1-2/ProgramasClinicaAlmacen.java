package Lab08;

import avltree.AVLTree;
import exceptions.ItemDuplicated;
import exceptions.ItemNotFound;

public class ProgramasClinicaAlmacen {

    public static void main(String[] args) {
        // =================================================================
        // EJEMPLO 1: Registro de códigos de productos en almacén
        // =================================================================
        System.out.println("   EJEMPLO 1: SISTEMA DE ALMACÉN (PRODUCTOS)      ");
        
        AVLTree<Integer> almacen = new AVLTree<>();
        
        try {
            System.out.println("-> Insertando códigos de productos nuevos...");
            almacen.insert(1040);
            almacen.insert(1020);
            almacen.insert(1050);
            almacen.insert(1060); // Esto causará un desbalance y aplicará rotación RSL
            
            System.out.println("\nInventario actual del almacén:");
            almacen.printTree();
            
            // Buscar un producto
            System.out.println("\n-> Buscando producto 1050...");
            int encontrado = almacen.search(1050);
            System.out.println("Código " + encontrado + " encontrado y disponible en stock.");
            
            // Eliminar un producto que ya no está disponible
            System.out.println("\n-> Eliminando producto 1020 (sin stock)...");
            almacen.delete(1020);
            
            System.out.println("\nInventario actualizado:");
            almacen.printTree();
            
        } catch (ItemDuplicated e) {
            System.out.println("Error: " + e.getMessage());
        } catch (ItemNotFound e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n\n");

        // EJEMPLO 2: Cola de atención por turnos en una clínica
        System.out.println("   EJEMPLO 2: CONTROL DE TURNOS EN CLÍNICA        ");

        
        AVLTree<Integer> turnosClinica = new AVLTree<>();
        
        try {
            System.out.println("-> Registrando llegada de pacientes (Turnos: 10, 20, 30)...");
            turnosClinica.insert(10);
            turnosClinica.insert(20);
            turnosClinica.insert(30); 
            
            System.out.println("\nEstructura de la cola de turnos en el sistema AVL:");
            turnosClinica.printTree();
            

            System.out.println("\n-> Verificando si el turno 20 está en espera...");
            int turnoBuscado = turnosClinica.search(20);
            System.out.println("Turno " + turnoBuscado + " verificado. Pase al consultorio.");
            

            System.out.println("\n-> Atendiendo y eliminando el turno 10...");
            turnosClinica.delete(10); // Caso BST: Eliminación de nodo hoja
            
            System.out.println("\nTurnos restantes por atender:");
            turnosClinica.printTree();
            
        } catch (ItemDuplicated e) {
            System.out.println("Error: " + e.getMessage());
        } catch (ItemNotFound e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}