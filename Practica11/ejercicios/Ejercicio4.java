package ejercicios;

public class Ejercicio4 {

    static final int SIZE    = 7;
    static final int EMPTY   = 0;
    static final int OCCUPIED = 1;
    static final int DELETED  = 2; // Estado lógico intermedio (lápida o tombstone)

    static class Entry {
        int key;
        int status;
        //contructor 
        Entry() { key = -1; status = EMPTY; }

        String statusStr() {
            return switch (status) {
                case EMPTY    -> "EMPTY";
                case OCCUPIED -> "OCCUPIED(" + key + ")";
                case DELETED  -> "DELETED";
                default       -> "?";
            };
        }
    }

    static Entry[] table = new Entry[SIZE];     //arreglo de tabla hash fijo

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("  EJERCICIO 4 - Eliminación Lógica y Reinserción");
        System.out.println("  Tamaño=7, h(x)=x%7, Sondeo Lineal");
        System.out.println("========================================\n");

        for (int i = 0; i < SIZE; i++) table[i] = new Entry();

        System.out.println("--- Insertando: 5, 12, 19, 26 ---");
        insert(5); insert(12); insert(19); insert(26);
        printTable("Tabla inicial");

        System.out.println("--- Paso 1: Eliminar clave 12 (lógicamente) ---");
        delete(12);
        printTable("Tabla después de eliminar 12");

        System.out.println("--- Paso 2: Buscar clave 19 ---");
        System.out.println("  La celda DELETED en el camino NO detiene el sondeo.");
        System.out.println("  Solo una celda EMPTY detiene la búsqueda.");
        search(19);
        System.out.println();

        System.out.println("--- Paso 3: Reinsertar clave 33 ---");
        System.out.println("  h(33) = 33 % 7 = " + (33 % SIZE));
        System.out.println("  Si hay una celda DELETED antes de EMPTY, se reutiliza.");
        insert(33);
        printTable("Tabla después de reinsertar 33");
    }
    //función hash para obtener el índice de la tabla
    static int hash(int key) 
    { return key % SIZE; }

    static void insert(int key) {
        int h = hash(key);
        int start = h;
        int firstDeleted = -1; // Almacena el rastro de la primera ranura inactiva que se cruza en el recorrido

        do {
            if (table[h].status == EMPTY) {
                int insertAt = (firstDeleted != -1) ? firstDeleted : h; // Prefiere reciclar la celda DELETED guardada si existe
                table[insertAt].key = key; // Asigna el valor comercial del entero
                table[insertAt].status = OCCUPIED; // Setea flag para indicar celda ocupada activa
                System.out.printf("  Insertado %2d en [%d]%s%n", key, insertAt, firstDeleted != -1 ? " (celda DELETED reutilizada)" : "");
                return; // Corta la ejecución al consolidar el ingreso del dato
            } else if (table[h].status == DELETED && firstDeleted == -1) {
                firstDeleted = h; // Memoriza la primera ranura libre pero prosigue para descartar duplicados adelante
            }
            h = (h + 1) % SIZE; // Cuando ocurre una colisión: sondeo lienal si hay espacio
        } while (h != start);

        if (firstDeleted != -1) { // Caso alternativo: Tabla saturada de ocupados pero con huecos lógicos lícitos
            table[firstDeleted].key = key;
            table[firstDeleted].status = OCCUPIED;
            System.out.printf("  Insertado %2d en [%d] (celda DELETED reutilizada)%n", key, firstDeleted);
        } else {
            System.out.println("  Tabla llena. No se pudo insertar " + key);
        }
    }

    static void search(int key) {
        int h = hash(key);
        int start = h;
        int pasos = 0;

        do {
            pasos++;
            System.out.printf("  Revisando [%d] -> %s%n", h, table[h].statusStr());
            if (table[h].status == EMPTY) {
                System.out.println("  Clave " + key + " NO encontrada. (EMPTY detiene la búsqueda)");
                return; // Garantía absoluta: si está vacía original, el elemento jamás ingresó a la estructura
            }
            if (table[h].status == OCCUPIED && table[h].key == key) {
                System.out.println("  Clave " + key + " ENCONTRADA en [" + h + "] (pasos=" + pasos + ")");
                return; // Concluye con éxito al validar el match de llaves
            }
            h = (h + 1) % SIZE; // Continúa el sondeo pasando de largo por celdas ocupadas ajenas o estados DELETED
        } while (h != start);

        System.out.println("  Clave " + key + " no encontrada.");
    }

    static void delete(int key) {
        int h = hash(key);
        int start = h;

        do {
            if (table[h].status == EMPTY) {
                System.out.println("  Clave " + key + " no encontrada para eliminar.");
                return; // Cancela el borrado ante una celda vacía base
            }
            if (table[h].status == OCCUPIED && table[h].key == key) {
                table[h].key = -1; // Resetea valor comercial
                table[h].status = DELETED; // Despliega la lápida protectora para no romper sondeos de colisionados subsecuentes
                System.out.println("  Clave " + key + " marcada como DELETED en [" + h + "]");
                return;
            }
            h = (h + 1) % SIZE;
        } while (h != start);

        System.out.println("  Clave " + key + " no encontrada.");
    }

    static void printTable(String label) {
        System.out.println("\n--- " + label + " ---");
        for (int i = 0; i < SIZE; i++) { System.out.printf("  [%d] %s%n", i, table[i].statusStr()); }
        System.out.println();
    }
}