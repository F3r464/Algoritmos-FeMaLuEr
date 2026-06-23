package ejercicios;

public class Ejercicio1 {

    static final int SIZE  = 11;
    static final int EMPTY = -1;

    static int[] table = new int[SIZE];

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("  EJERCICIO 1 - Hash sin colisiones");
        System.out.println("  Función: h(x) = x % 11, Tamaño = 11");
        System.out.println("========================================\n");

        for (int i = 0; i < SIZE; i++) table[i] = EMPTY; // Setea estado inicial homogéneo de celdas desocupadas

        System.out.println("--- Cálculo manual de direcciones hash ---");
        int[] values = {3, 14, 25, 36, 47, 58};
        for (int v : values) {
            int idx = hash(v);
            System.out.printf("  h(%2d) = %2d %% 11 = %2d%n", v, v, idx);
        }

        System.out.println("\n--- Insertando valores ---");
        for (int v : values) {
            insert(v);
        }

        printTable();
    }

    static int hash(int key) {
        return key % SIZE; // Computa la dirección aritmética mediante el residuo de la división entera
    }

    static void insert(int value) {
        int index = hash(value); // Determina la ranura teórica destino
        if (table[index] == EMPTY) { // Verifica si la ranura se encuentra libre
            table[index] = value; // Aloja físicamente el dato al no existir conflicto posicional
            System.out.printf("  Insertado %2d en índice %2d%n", value, index);
        } else {
            System.out.printf("  COLISIÓN al insertar %2d en índice %2d (ya tiene %2d)%n", value, index, table[index]); // Alerta de solapamiento de direcciones
        }
    }

    static void printTable() {
        System.out.println("\n--- Tabla hash final ---");
        for (int i = 0; i < SIZE; i++) {
            String contenido = (table[i] == EMPTY) ? "VACÍO" : String.valueOf(table[i]);
            System.out.printf("  [%2d] -> %s%n", i, contenido);
        }
        System.out.println();
    }
}