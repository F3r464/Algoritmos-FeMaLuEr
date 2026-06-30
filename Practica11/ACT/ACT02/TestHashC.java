package ACT02;
/**
 * Clase de prueba para la tabla hash cerrada (HashC) con sondeo lineal.
 * Inserta los valores del enunciado, muestra la tabla, elimina la clave 30
 * y busca la clave 23 antes y después de la eliminación.
 */
public class TestHash {
    public static void main(String[] args) {
        HashC<String> tabla = new HashC<>(11);

        // ── Inserción ─────────────────────────────────────────────────────────
        System.out.println("=== INSERTANDO ELEMENTOS ===");
        tabla.insert(34, "Alejandro");
        tabla.insert(3,  "Beatriz");
        tabla.insert(7,  "Carlos");
        tabla.insert(30, "Diana");
        tabla.insert(11, "Eduardo");
        tabla.insert(8,  "Fernanda");
        tabla.insert(7,  "Claudio");   // clave duplicada → actualiza
        tabla.insert(23, "Gabriela");
        tabla.insert(41, "Hugo");
        tabla.insert(16, "Irene");
        tabla.insert(34, "Alonso");    // clave duplicada → actualiza

        // ── Estado inicial ────────────────────────────────────────────────────
        System.out.println("\n=== TABLA HASH ANTES DE ELIMINAR ===");
        System.out.print(tabla.toString());

        // ── Búsqueda antes de eliminar ────────────────────────────────────────
        System.out.println("--- Buscando clave 23 ---");
        String encontrado = tabla.search(23);
        if (encontrado != null)
            System.out.println("  Clave 23 → " + encontrado);
        else
            System.out.println("  Clave 23 no encontrada.");

        // ── Eliminación lógica de clave 30 ───────────────────────────────────
        System.out.println("\n--- Eliminando clave 30 ---");
        tabla.delete(30);

        // ── Estado después de eliminar ────────────────────────────────────────
        System.out.println("\n=== TABLA HASH DESPUÉS DE ELIMINAR CLAVE 30 ===");
        System.out.print(tabla.toString());

        // ── Verificación: buscar la clave eliminada ───────────────────────────
        System.out.println("--- Verificando: buscando clave 30 (eliminada) ---");
        String eliminado = tabla.search(30);
        if (eliminado != null)
            System.out.println(" ERROR: clave 30 aún aparece → " + eliminado);
        else
            System.out.println(" Correcto: clave 30 ya no está en la tabla.");
    }
}