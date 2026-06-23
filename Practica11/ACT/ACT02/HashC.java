package ACT02;

/**
 * Tabla hash cerrada con sondeo lineal para resolver colisiones.
 */
public class HashC<E>{

    private static final int EMPTY    = 0; // Celda vacía.
    private static final int OCCUPIED = 1; // Celda ocupada.
    private static final int DELETED  = 2; // Celda eliminada.

    private class Element{
        Register<E> reg; // Registro almacenado.
        int status;      // Estado actual de la celda.

        Element(){
            this.reg = null;      // Inicialmente no hay registro.
            this.status = EMPTY;  // La celda inicia vacía.
        }
    }

    private Element[] table; // Arreglo principal de la tabla hash.
    protected int m;         // Tamaño de la tabla.

    @SuppressWarnings("unchecked")
    public HashC(int size){
        this.m = size;                         // Guarda el tamaño de la tabla.
        this.table = new HashC.Element[m];     // Crea el arreglo de celdas.

        for (int i = 0; i < m; i++){
            table[i] = new Element();          // Inicializa cada posición.
        }
    }

    private int hash(int key){
        return key % m; // Calcula la dirección hash.
    }

    private int linearProbing(int index){
        return (index + 1) % m; // Avanza circularmente al siguiente índice.
    }

    public void insert(int key, E value){
        int home = hash(key); // Posición original de la clave.
        int index = home;     // Posición actual de búsqueda.

        do {
            if (table[index].status == OCCUPIED &&
                table[index].reg.getKey() == key){

                table[index].reg = new Register<>(key, value); // Actualiza el valor.
                System.out.println("  [UPDATE] Clave " + key +
                        " ya existía → valor actualizado.");
                return;
            }

            if (table[index].status == EMPTY)
                break; // La clave no existe.

            index = linearProbing(index); // Continúa el sondeo.
        } while (index != home);

        index = home;
        int start = index; // Guarda el punto inicial del recorrido.

        do {
            if (table[index].status == EMPTY ||
                table[index].status == DELETED){

                table[index].reg = new Register<>(key, value); // Inserta el registro.
                table[index].status = OCCUPIED; // Marca la celda como ocupada.

                System.out.println("  [INSERT] Clave " + key +
                        " → índice " + index +
                        (index != home
                                ? " (colisión, sondeo desde " + home + ")"
                                : ""));
                return;
            }

            index = linearProbing(index); // Busca otra posición disponible.
        } while (index != start);

        System.out.println("  [ERROR] Tabla llena. No se pudo insertar clave "
                + key + ".");
    }

    public E search(int key) {
        int home = hash(key); // Posición inicial de búsqueda.
        int index = home;

        do {
            if (table[index].status == EMPTY)
                break; // La clave no existe.

            if (table[index].status == OCCUPIED &&
                table[index].reg.getKey() == key) {

                return table[index].reg.getValue(); // Retorna el valor encontrado.
            }

            index = linearProbing(index); // Continúa buscando.
        } while (index != home);

        return null; // No se encontró la clave.
    }

    public void delete(int key) {
        int home = hash(key); // Posición original de la clave.
        int index = home;

        do {
            if (table[index].status == EMPTY)
                break; // La clave no existe.

            if (table[index].status == OCCUPIED &&
                table[index].reg.getKey() == key) {

                table[index].status = DELETED; // Marca la celda como eliminada.
                table[index].reg = null;       // Elimina la referencia al registro.

                System.out.println("  [DELETE] Clave " + key +
                        " eliminada (celda " + index +
                        " marcada como DELETED).");
                return;
            }

            index = linearProbing(index); // Continúa el recorrido.
        } while (index != home);

        System.out.println("  [DELETE] Clave " + key + " no encontrada.");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(); // Construye la salida.

        sb.append(String.format("%-8s %-8s %-10s %s%n",
                "Índice", "D.Hash", "Estado", "Registro"));

        sb.append("─".repeat(42)).append("\n");

        for (int i = 0; i < m; i++) {
            Element e = table[i]; // Obtiene la celda actual.

            String estado;
            String reg;
            String dHash;

            if (e.status == OCCUPIED) {
                estado = "OCCUPIED";
                dHash = String.valueOf(hash(e.reg.getKey())); // Dirección hash original.
                reg = e.reg.toString(); // Registro almacenado.
            } else if (e.status == DELETED) {
                estado = "DELETED";
                dHash = "-";
                reg = "-";
            } else {
                estado = "EMPTY";
                dHash = "-";
                reg = "-";
            }

            sb.append(String.format("%-8d %-8s %-10s %s%n",
                    i, dHash, estado, reg)); // Agrega una fila a la tabla.
        }

        return sb.toString(); // Retorna la representación completa.
    }
}