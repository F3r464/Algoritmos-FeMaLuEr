package actividades;

public class Actividad3 {

    static class HashO<T> {

        private LinkedList<Register<T>>[] table; // Matriz base compuesta por listas enlazadas independientes
        private int size;

        @SuppressWarnings("unchecked")
        public HashO(int size) {
            this.size = nextPrime(size); // Ajusta la dimensión al número primo superior para optimizar la dispersión
            table = new LinkedList[this.size];
            for (int i = 0; i < this.size; i++) {
                table[i] = new LinkedList<>(); // Inicializa explícitamente cada celda evitando excepciones nulas
            }
            System.out.println("HashO creado con tamaño primo: " + this.size);
        }

        private int hash(int key) {
            return key % size; // Método de la división aritmética para mapear llaves numéricas
        }

        public void insert(Register<T> reg) {
            int index = hash(reg.getKey());
            Register<T> existing = table[index].find(r -> r.getKey() == reg.getKey()); // Filtra duplicados locales
            if (existing != null) {
                System.out.println("Clave duplicada " + reg.getKey() + " ignorada.");
                return; // Cláusula de protección para garantizar identificadores únicos
            }
            table[index].add(reg); // Agrega al final de la cadena de colisión correspondiente
            System.out.println("Insertado " + reg + " en índice " + index + " (lista: " + table[index] + ")");
        }

        public Register<T> search(int key) {
            int index = hash(key);
            Register<T> found = table[index].find(r -> r.getKey() == key); // Acota el escaneo únicamente a esa lista
            if (found != null) {
                System.out.println("Encontrado: " + found + " en índice " + index);
            } else {
                System.out.println("Clave " + key + " no encontrada.");
            }
            return found;
        }

        public void delete(int key) {
            int index = hash(key);
            boolean removed = table[index].removeIf(r -> r.getKey() == key); // Remueve físicamente variando punteros
            if (removed) {
                System.out.println("Clave " + key + " eliminada del índice " + index);
            } else {
                System.out.println("Clave " + key + " no encontrada para eliminar.");
            }
        }

        public void printTable() {
            System.out.println("\n--- Estado de la tabla HashO (tamaño=" + size + ") ---");
            for (int i = 0; i < size; i++) {
                System.out.printf("  [%2d] %s%n", i, table[i].isEmpty() ? "[]" : table[i]);
            }
            System.out.println("----------------------------------------------\n");
        }

        private int nextPrime(int n) {
            if (n <= 2) return 2;
            int candidate = (n % 2 == 0) ? n + 1 : n; // Forza el inicio de búsqueda desde un dígito impar
            while (!isPrime(candidate)) candidate += 2; // Evalúa omitiendo múltiplos de dos para celeridad
            return candidate;
        }

        private boolean isPrime(int n) {
            if (n < 2) return false;
            if (n == 2) return true;
            if (n % 2 == 0) return false;
            for (int i = 3; i * i <= n; i += 2) // Límite matemático optimizado hasta la raíz cuadrada de n
                if (n % i == 0) return false;
            return true;
        }
    }

    public static void main(String[] args) {
        // Pruebas omitidas para concisión
    }
}