package actividadEjercicio4; 

import actividad1.ExceptionIsEmpty; 

public class TestHybrid { 
    public static void main(String[] args) { 
        try {
            PriorityQueueHybrid<String, Integer> hybridQueue = new PriorityQueueHybrid<>(3);

            System.out.println("--- Encolando elementos de prueba ---"); 
            hybridQueue.enqueue("A", 2, 5); // Registra en Nivel 2 con valor secundario 5
            hybridQueue.enqueue("B", 2, 1); // Registra en Nivel 2 con valor secundario 1
            hybridQueue.enqueue("C", 1, 3); // Registra en Nivel 1 con valor secundario 3
            hybridQueue.enqueue("D", 2, 3); // Registra en Nivel 2 con valor secundario 3

            System.out.println("\nResultado interno de la estructura (Ordenado por ambos criterios):");
            System.out.print(hybridQueue.toString()); 

            System.out.println("--- Desencolando elementos (Salida esperada: B, D, A, C) ---");
            while (!hybridQueue.isEmpty()) { 
                System.out.print(hybridQueue.dequeue() + " "); 
            }
            System.out.println(); 

        } catch (ExceptionIsEmpty e) {
            System.out.println("Error: " + e.getMessage()); 
        }
    }
}