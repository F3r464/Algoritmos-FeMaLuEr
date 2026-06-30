package ACT03;

public class MainACT03 {

    public static void main(String[] args) {

        HashO<String> hash = new HashO<>(5);
        hash.insert(1, "Juan");
        hash.insert(6, "Pedro");
        hash.insert(11, "María");
        hash.insert(16, "Luis");

        hash.insert(3, "Ana");
        hash.insert(8, "Carlos");

        System.out.println("Contenido de la tabla:");
        hash.showTable();

        System.out.println("\nBuscar clave 11:");
        System.out.println(hash.search(11));

        System.out.println("\nEliminar clave 6");
        hash.delete(6);

        System.out.println("\nTabla después de eliminar:");
        hash.showTable();
    }
}