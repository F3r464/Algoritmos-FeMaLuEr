package ejercicios;

public class Ejercicio3 {

    static class Node {
        int key;
        String name;
        Node next;

        Node(int key, String name) {
            this.key = key;
            this.name = name;
            this.next = null;
        }

        @Override
        public String toString() { return "(" + key + "," + name + ")"; }
    }

    static class SimpleLinkedList {
        Node head;
        int size;

        SimpleLinkedList() { head = null; size = 0; }

        void add(int key, String name) {
            Node newNode = new Node(key, name);
            if (head == null) {
                head = newNode; // Define el nodo inicial si la estructura está vacía
            } else {
                Node cur = head;
                while (cur.next != null) cur = cur.next; // Itera secuencialmente hasta el extremo terminal
                cur.next = newNode; // Engancha el nuevo nodo reasignando el puntero del último eslabón
            }
            size++;
        }

        Node search(int key) {
            Node cur = head;
            int pos = 0;
            while (cur != null) { // Explora secuencialmente la lista enlazada de colisiones
                if (cur.key == key) { // Evalúa la igualdad exacta del identificador numérico
                    System.out.println("  Encontrado: " + cur + " (posición " + pos + " en la lista)");
                    return cur; // Extrae el nodo coincidente suspendiendo el bucle
                }
                cur = cur.next; // Avanza el cursor de memoria al eslabón sucesor
                pos++;
            }
            System.out.println("  Clave " + key + " no encontrada.");
            return null;
        }

        boolean remove(int key) {
            if (head == null) return false;
            if (head.key == key) { head = head.next; size--; return true; } // Desplaza la raíz para aislar la cabeza vieja
            Node cur = head;
            while (cur.next != null) {
                if (cur.next.key == key) { // Evalúa el valor del elemento siguiente de forma adelantada
                    cur.next = cur.next.next; // Salto físico: vincula el nodo actual con el "nieto"
                    size--; // Ajusta el contador de elementos de la sublista
                    return true;
                }
                cur = cur.next;
            }
            return false;
        }

        @Override
        public String toString() {
            if (head == null) return "[]";
            StringBuilder sb = new StringBuilder();
            Node cur = head;
            while (cur != null) {
                sb.append(cur);
                if (cur.next != null) sb.append(" -> ");
                cur = cur.next;
            }
            return sb.toString();
        }
    }

    static final int SIZE = 7;
    static SimpleLinkedList[] table = new SimpleLinkedList[SIZE];

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("  EJERCICIO 3 - Hash Abierto con Colisiones");
        System.out.println("  Tamaño=7, h(k)=k%7");
        System.out.println("========================================\n");

        for (int i = 0; i < SIZE; i++) table[i] = new SimpleLinkedList(); // Inicializa cada cubeta para albergar colisiones

        System.out.println("--- Cálculo de índices y detección de colisiones ---");
        int[][] data = {{10, 0}, {17, 0}, {24, 0}, {31, 0}, {5, 0}, {12, 0}};
        String[] names = {"Juan", "Ana", "Luis", "Rosa", "Pedro", "Carla"};

        for (int i = 0; i < data.length; i++) {
            int key = data[i][0];
            int idx = key % SIZE; // Calcula dirección del mapeo modular
            System.out.printf("  h(%2d) = %2d %% 7 = %d  [%s]%s%n",
                    key, key, idx, names[i],
                    table[idx].head != null ? "  *** COLISIÓN con " + table[idx] : ""); // Valida colisión preexistente
            table[idx].add(key, names[i]); // Concatena el elemento al final de la cadena de esa ranura
        }

        printTable("\n--- Estado final de la tabla ---");

        System.out.println("--- Búsqueda de clave 24 ---");
        int idx24 = 24 % SIZE;
        System.out.println("  h(24) = 24 % 7 = " + idx24 + "  -> buscar en tabla[" + idx24 + "]");
        table[idx24].search(24); // Direcciona la búsqueda al índice de la sublista específica

        System.out.println("\n--- Eliminar clave 17 ---");
        int idx17 = 17 % SIZE;
        System.out.println("  h(17) = 17 % 7 = " + idx17);
        boolean removed = table[idx17].remove(17); // Ejecuta remoción real alterando enlaces físicos del subnodo
        if (removed) {
            System.out.println("  Clave 17 eliminada. Nodos restantes en tabla[" + idx17 + "]: " + table[idx17].size + " -> " + table[idx17]);
        }

        printTable("\n--- Tabla DESPUÉS de eliminar clave 17 ---");
    }

    static void printTable(String label) {
        System.out.println(label);
        for (int i = 0; i < SIZE; i++) { System.out.printf("  [%d] %s%n", i, table[i]); }
        System.out.println();
    }
}