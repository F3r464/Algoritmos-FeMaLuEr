package actividad3; 

import actividad1.ExceptionIsEmpty; 

public class Test {
    public static void main(String[] args) { 
        try {
            PriorityQueue<String, Integer> pq = new PriorityQueueLinkSort<>(); 

            pq.enqueue("Paciente con Gripe A", 1); // Prioridad baja
            pq.enqueue("Emergencia de Tránsito B", 5); // Prioridad muy alta (debe ir al frente)
            pq.enqueue("Control Regular C", 2); // Prioridad media-baja
            pq.enqueue("Fractura Expuesta D", 4); // Prioridad alta
            pq.enqueue("Paciente con Gripe E", 1); // Misma prioridad que A (debe quedar detrás de A)

            System.out.println("\nEstructura actual de la cola (Ordenada automáticamente de Mayor a Menor):"); 
            System.out.println(pq.toString()); // Muestra la lista enlazada en orden de prioridad

            System.out.println("\n--- Ejecutando pruebas de lectura ---"); 
            
            System.out.println("Elemento con mayor prioridad: " + pq.front()); 

            System.out.println("Elemento con menor prioridad: " + pq.back()); 

            
            System.out.println("\n--- Desencolando elementos en orden estricto de prioridad ---"); 
            System.out.println("dequeue() atendido: " + pq.dequeue()); // Saca y muestra el de prioridad 5
            System.out.println("dequeue() atendido: " + pq.dequeue()); // Saca y muestra el de prioridad 4
            
            System.out.println("\nEstado de la cola después de dos extracciones:"); 
            System.out.println(pq.toString()); 

        } catch (ExceptionIsEmpty e) { 
            System.out.println("Se produjo un error controlado: " + e.getMessage()); 
        }
    }
}
