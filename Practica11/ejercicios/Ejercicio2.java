package ejercicios;

public class Ejercicio2 {

    static final int SIZE  = 7;
    static final int EMPTY = -1;

    public static void main(String[] args) {
        int[] values = {10, 17, 24, 31, 4};

        System.out.println("========================================");
        System.out.println("  EJERCICIO 2 - Sondeo Lineal vs Cuadrático");
        System.out.println("  Tamaño=7, h(x)=x%7");
        System.out.println("  Valores: 10, 17, 24, 31, 4");
        System.out.println("========================================\n");

        System.out.println("============ SONDEO LINEAL ============");
        int[] tableLinear = new int[SIZE];
        for (int i = 0; i < SIZE; i++) tableLinear[i] = EMPTY;

        for (int v : values) {
            insertLinear(tableLinear, v);
            printTable(tableLinear, "Después de insertar " + v);
        }

        System.out.println("\n========== SONDEO CUADRÁTICO ==========");
        int[] tableQuad = new int[SIZE];
        for (int i = 0; i < SIZE; i++) tableQuad[i] = EMPTY;

        for (int v : values) {
            insertQuadratic(tableQuad, v);
            printTable(tableQuad, "Después de insertar " + v);
        }
    }

    static int hash(int key) {
        return key % SIZE;
    }

    static void insertLinear(int[] table, int value) {
        int h = hash(value); // Obtiene dirección base del elemento
        System.out.printf("Insertando %2d | h(%2d)=%d | ", value, value, h);

        for (int i = 0; i < SIZE; i++) { // Bucle de exploración acotada por el tamaño de la estructura
            int index = (h + i) % SIZE; // Desplazamiento secuencial e incremental de uno en uno circularmente
            if (table[index] == EMPTY) { // Evalúa disponibilidad física del casillero explorado
                table[index] = value; // Consolida el registro en la primera celda libre hallada
                System.out.printf("colocado en [%d] (saltos=%d)%n", index, i);
                return; // Rompe la función al ubicar con éxito el elemento
            } else {
                System.out.printf("colisión en [%d] -> ", index); // Registra el conflicto con cluster primario
            }
        }
        System.out.println("Tabla llena.");
    }

    static void insertQuadratic(int[] table, int value) {
        int h = hash(value); // Obtiene dirección base teórica
        System.out.printf("Insertando %2d | h(%2d)=%d | ", value, value, h);

        for (int i = 0; i < SIZE; i++) { // Itera controlando el número de saltos realizados
            int index = (h + i * i) % SIZE; // Incremento cuadrático de la distancia respecto al índice base original
            if (table[index] == EMPTY) { // Verifica si el casillero calculado está libre de datos
                table[index] = value; // Aloja el dato mitigando el agrupamiento primario
                System.out.printf("colocado en [%d] (i=%d, i²=%d, saltos=%d)%n", index, i, i * i, i);
                return; // Termina la inserción al ubicar el dato
            } else {
                System.out.printf("colisión en [%d] (i=%d) -> ", index, i); // Indica colisión intermedia detectada
            }
        }
        System.out.println("No se encontró posición disponible.");
    }

    static void printTable(int[] table, String label) {
        System.out.print("  [" + label + "] -> ");
        for (int i = 0; i < SIZE; i++) {
            String v = (table[i] == EMPTY) ? " _" : String.format("%2d", table[i]);
            System.out.print("[" + i + "]=" + v + " ");
        }
        System.out.println();
    }
}