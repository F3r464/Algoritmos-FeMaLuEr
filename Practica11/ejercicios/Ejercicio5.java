package ejercicios;

public class Ejercicio5 {

    static final double LOAD_FACTOR_THRESHOLD = 0.75; // Porcentaje crítico límite permitido (75%)

    static int[] table;
    static int   tableSize;
    static int   count = 0;

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("  EJERCICIO 5 - Factor de Carga y Rehashing");
        System.out.println("  Tamaño inicial=7, umbral α=0.75");
        System.out.println("========================================\n");

        tableSize = 7;
        table = initTable(tableSize);

        int[] values = {2, 9, 16, 23, 4, 11};
        for (int v : values) { insert(v); }

        System.out.println("\n--- Tabla FINAL (post-rehashing si aplica) ---");
        printTable();
    }

    static int[] initTable(int size) {
        int[] t = new int[size];
        for (int i = 0; i < size; i++) t[i] = -1;
        return t;
    }

    static int hash(int key, int size) { return key % size; }

    static void insert(int value) {
        double alpha = (double)(count + 1) / tableSize; // Evalúa prospectivamente el factor de carga resultante
        System.out.printf("Insertando %2d | n=%d, M=%d, α=(n+1)/M=%.2f", value, count, tableSize, alpha);

        if (alpha > LOAD_FACTOR_THRESHOLD) { // Verifica si se desborda el límite de saturación estipulado
            System.out.println("  → α > 0.75 ¡REHASHING!");
            rehash(); // Gatilla el reordenamiento estructural y migración de datos
        } else {
            System.out.println();
        }

        int h = hash(value, tableSize);
        for (int i = 0; i < tableSize; i++) {
            int idx = (h + i) % tableSize;
            if (table[idx] == -1) {
                table[idx] = value;
                count++; // Incrementa el censo global de celdas activas
                double currentAlpha = (double) count / tableSize;
                System.out.printf("  -> colocado en [%d]. α actual = %d/%d = %.2f%n", idx, count, tableSize, currentAlpha);
                return;
            }
        }
    }

    static void rehash() {
        int newSize = nextPrime(tableSize * 2); // Duplica el tamaño base teórico y busca el número primo superior idóneo
        System.out.println("\n  === REHASHING: tamaño " + tableSize + " → " + newSize + " ===");
        printTable();

        int[] oldTable = table; // Almacena temporalmente la matriz antigua saturada
        int   oldSize  = tableSize;

        tableSize = newSize; // Asigna nueva dimensión física global
        table     = initTable(newSize); // Sobreescribe la tabla con un arreglo limpio expandido
        count     = 0; // Reinicia el contador para que sea recalculado en la inserción de la migración

        for (int i = 0; i < oldSize; i++) {
            if (oldTable[i] != -1) { // Recupera únicamente elementos válidos ignorando ranuras vacías antiguas
                int h = hash(oldTable[i], newSize); // RE-calcula la posición hash basándose estrictamente en el nuevo tamaño M
                for (int j = 0; j < newSize; j++) {
                    int idx = (h + j) % newSize; // Sondea linealmente en la nueva matriz vacía
                    if (table[idx] == -1) {
                        table[idx] = oldTable[i]; // Traslada el elemento a su nueva coordenada óptima
                        count++;
                        System.out.printf("  Re-insertado %2d en [%d] (h=%d%s)%n", oldTable[i], idx, h, j > 0 ? "+sondeo" : "");
                        break; // Rompe el sondeo interno para proceder con el próximo número viejo
                    }
                }
            }
        }
        System.out.println("  === Rehashing completado. Nueva tabla: ===");
        printTable();
    }

    static void printTable() {
        System.out.printf("  Tabla (tamaño=%d, elementos=%d, α=%.2f):%n", tableSize, count, (double) count / tableSize);
        for (int i = 0; i < tableSize; i++) {
            String v = (table[i] == -1) ? "VACÍO" : String.valueOf(table[i]);
            System.out.printf("    [%2d] -> %s%n", i, v);
        }
        System.out.println();
    }

    static int nextPrime(int n) {
        if (n <= 2) return 2;
        int candidate = (n % 2 == 0) ? n + 1 : n;
        while (!isPrime(candidate)) candidate += 2;
        return candidate;
    }

    static boolean isPrime(int n) {
        if (n < 2) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;
        for (int i = 3; i * i <= n; i += 2) if (n % i == 0) return false;
        return true;
    }
}