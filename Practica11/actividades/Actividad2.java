package actividades;

public class Actividad2 {

    private static class Element<T> {
        Register<T> register;
        int status; // 0=EMPTY, 1=OCCUPIED, 2=DELETED (Flags lógicas de control)

        Element() {
            this.register = null;
            this.status = 0;
        }
    }

    static class HashC<T> {

        private static final int EMPTY    = 0;
        private static final int OCCUPIED = 1;
        private static final int DELETED  = 2;
        private Element<T>[] table;
        private int size;

        @SuppressWarnings("unchecked")
        public HashC(int size) {
            this.size = nextPrime(size);
            table = new Element[this.size];
            for (int i = 0; i < this.size; i++) {
                table[i] = new Element<>();
            }
            System.out.println("HashC creado con tamaño primo: " + this.size);
        }

        private int hash(int key) {
            return key % size;
        }

        public void insert(Register<T> reg) {
            int index = hash(reg.getKey());
            int start = index; // Registra la posición original para interceptar bucles infinitos
            int firstDeleted = -1; // Almacena la ubicación del primer espacio huérfano apto para reciclaje

            do {
                if (table[index].status == EMPTY) {
                    int insertAt = (firstDeleted != -1) ? firstDeleted : index; // Reutiliza posición inactiva si existiese
                    table[insertAt].register = reg;
                    table[insertAt].status = OCCUPIED;
                    System.out.println("Insertado " + reg + " en índice " + insertAt);
                    return;
                } else if (table[index].status == DELETED && firstDeleted == -1) {
                    firstDeleted = index; // Conserva el índice disponible pero prosigue buscando duplicados
                } else if (table[index].status == OCCUPIED && table[index].register.getKey() == reg.getKey()) {
                    System.out.println("Clave duplicada " + reg.getKey() + " ignorada.");
                    return;
                }
                index = (index + 1) % size; // Sondeo Lineal: Incremento cíclico unitario mediante operador modular
            } while (index != start); // Condición de parada si se recorrió el arreglo circular por completo

            if (firstDeleted != -1) { // Caso complementario: Tabla llena pero con huecos lógicos liberados
                table[firstDeleted].register = reg;
                table[firstDeleted].status = OCCUPIED;
                System.out.println("Insertado " + reg + " en celda DELETED " + firstDeleted);
            } else {
                System.out.println("Tabla llena. No se pudo insertar " + reg);
            }
        }

        public Register<T> search(int key) {
            int index = hash(key);
            int start = index;

            do {
                if (table[index].status == EMPTY) {
                    System.out.println("Clave " + key + " no encontrada.");
                    return null; // El estado EMPTY garantiza con seguridad la ausencia del elemento
                } else if (table[index].status == OCCUPIED && table[index].register.getKey() == key) {
                    System.out.println("Encontrado: " + table[index].register + " en índice " + index);
                    return table[index].register;
                }
                index = (index + 1) % size; // Continúa explorando saltando casillas ocupadas con llaves ajenas o DELETED
            } while (index != start);

            System.out.println("Clave " + key + " no encontrada (tabla recorrida).");
            return null;
        }

        public void delete(int key) {
            int index = hash(key);
            int start = index;

            do {
                if (table[index].status == EMPTY) {
                    System.out.println("Clave " + key + " no encontrada para eliminar.");
                    return;
                } else if (table[index].status == OCCUPIED && table[index].register.getKey() == key) {
                    table[index].register = null; // Remueve la instancia comercial del objeto
                    table[index].status = DELETED; // Despliega una lápida (Tombstone) para no fracturar las búsquedas futuras
                    System.out.println("Clave " + key + " eliminada lógicamente (DELETED) en índice " + index);
                    return;
                }
                index = (index + 1) % size;
            } while (index != start);

            System.out.println("Clave " + key + " no encontrada para eliminar.");
        }

        public void printTable() {
            System.out.println("\n--- Estado de la tabla HashC (tamaño=" + size + ") ---");
            for (int i = 0; i < size; i++) {
                String estado = switch (table[i].status) {
                    case EMPTY    -> "EMPTY";
                    case OCCUPIED -> "OCCUPIED -> " + table[i].register;
                    case DELETED  -> "DELETED";
                    default       -> "?";
                };
                System.out.printf("  [%2d] %s%n", i, estado);
            }
            System.out.println("----------------------------------------------\n");
        }

        private int nextPrime(int n) {
            if (n <= 2) return 2;
            int candidate = (n % 2 == 0) ? n + 1 : n;
            while (!isPrime(candidate)) candidate += 2;
            return candidate;
        }

        private boolean isPrime(int n) {
            if (n < 2) return false;
            if (n == 2) return true;
            if (n % 2 == 0) return false;
            for (int i = 3; i * i <= n; i += 2) {
                if (n % i == 0) return false;
            }
            return true;
        }
    }

    public static void main(String[] args) {
        // Pruebas omitidas para concisión
    }
}