package Practica11.Interfaz;

public class HashTable {

    private Register[] table;

    public HashTable(int size) {
        table = new Register[size];
    }

    // Inserción con direccionamiento lineal
    public void insertVisual(Register r) {

        int index = r.getId() % table.length;

        while (table[index] != null) {
            index = (index + 1) % table.length;
        }

        table[index] = r;
    }

    // Buscar un jugador por ID
    public Register search(int id) {

        int index = id % table.length;
        int inicio = index;

        while (table[index] != null) {

            if (table[index].getId() == id) {
                return table[index];
            }

            index = (index + 1) % table.length;

            if (index == inicio) {
                break;
            }
        }

        return null;
    }

    // Eliminar un jugador
    public boolean delete(int id) {

        int index = id % table.length;
        int inicio = index;

        while (table[index] != null) {

            if (table[index].getId() == id) {

                table[index] = null;

                rehash();

                return true;
            }

            index = (index + 1) % table.length;

            if (index == inicio) {
                break;
            }
        }

        return false;
    }

    // Reacomoda la tabla después de eliminar
    private void rehash() {

        Register[] vieja = table;

        table = new Register[vieja.length];

        for (Register r : vieja) {

            if (r != null) {

                insertVisual(r);

            }

        }

    }

    // Devuelve la tabla para mostrarla en la interfaz
    public Register[] getTable() {
        return table;
    }

}