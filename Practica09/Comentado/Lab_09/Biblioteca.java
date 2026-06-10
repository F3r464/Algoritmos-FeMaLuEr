package Lab_09;
 
import btree.BTree;
import exceptions.TreeException;
import java.io.*;
import java.util.Scanner;
 
public class Biblioteca {
 
    // ─── Atributo principal ───────────────────────────────────────────────────
    private BTree<Libro> arbol;
 
    // ─── Constructor ──────────────────────────────────────────────────────────
 

     //Crea una biblioteca con un Árbol B del orden especificado.

    public Biblioteca(int orden) {
        this.arbol = new BTree<>(orden);
    }
 
    // ─── Operaciones CRUD ─────────────────────────────────────────────────────
 
    public void agregarLibro(Libro libro) {
        try {
            arbol.insert(libro);
            System.out.println("Libro agregado: " + libro.getIsbn());
        } catch (TreeException e) {
            System.out.println("Error al agregar libro: " + e.getMessage());
        }
    }
 
    public boolean buscarLibro(String isbn) {
        Libro buscado = new Libro(isbn, "", "", 0);
        System.out.println("\n── Buscando ISBN: " + isbn + " ──");
        boolean resultado = arbol.search(buscado);
        if (!resultado) System.out.println("Libro con ISBN " + isbn + " no encontrado.");
        return resultado;
    }
 
    public void eliminarLibro(String isbn) {
        try {
            Libro libro = new Libro(isbn, "", "", 0);
            arbol.remove(libro);
            System.out.println("Libro eliminado: ISBN " + isbn);
        } catch (TreeException e) {
            System.out.println("Error al eliminar: " + e.getMessage());
        }
    }
 
    // ─── Visualización ────────────────────────────────────────────────────────
 
    /**
     * Muestra la estructura tabular del Árbol B en consola.
     */
    public void mostrarArbol() {
        System.out.println("\n── Estructura del Árbol B ──");
        System.out.println(arbol.toString());
    }
 
    /**
     * Muestra todos los libros ordenados por ISBN (recorrido in-order).
     * Usa searchRange con los extremos del universo de ISBNs.
     */
    public void mostrarLibrosOrdenados() {
        System.out.println("\n── Libros ordenados por ISBN ──");
        // Un ISBN válido va de "0000000000000" a "9999999999999"
        Libro minLibro = new Libro("0000000000000", "", "", 0);
        Libro maxLibro = new Libro("9999999999999", "", "", 0);
        arbol.searchRange(minLibro, maxLibro);
    }
 
    public void mostrarAltura() {
        System.out.println("Altura del Árbol B: " + arbol.height());
    }
 
    public void mostrarTotalLibros() {
        System.out.println("Total de libros: " + arbol.size());
    }
 
    // ─── Carga desde archivo ──────────────────────────────────────────────────
 
    /**
     * Construye el Árbol B leyendo los libros desde un archivo de texto.
     */
    public void cargarDesdeArchivo(String rutaArchivo) {
        try (Scanner sc = new Scanner(new File(rutaArchivo))) {
            if (!sc.hasNextLine()) {
                System.out.println("El archivo está vacío.");
                return;
            }
 
            // Primera línea: orden del árbol
            int ordenArchivo = Integer.parseInt(sc.nextLine().trim());
            this.arbol = new BTree<>(ordenArchivo);
            System.out.println("Cargando árbol de orden " + ordenArchivo + " desde: " + rutaArchivo);
 
            int linea = 1;
            while (sc.hasNextLine()) {
                linea++;
                String line = sc.nextLine().trim();
                if (line.isEmpty()) continue;
 
                String[] partes = line.split(",", 4);
                if (partes.length < 4) {
                    System.out.println("Línea " + linea + " con formato inválido, se omite: " + line);
                    continue;
                }
 
                try {
                    String isbn  = partes[0].trim();
                    String titulo = partes[1].trim();
                    String autor  = partes[2].trim();
                    int    anio   = Integer.parseInt(partes[3].trim());
                    arbol.insert(new Libro(isbn, titulo, autor, anio));
                } catch (NumberFormatException e) {
                    System.out.println("Año inválido en línea " + linea + ", se omite.");
                } catch (TreeException e) {
                    System.out.println("Error en línea " + linea + ": " + e.getMessage());
                }
            }
            System.out.println("Carga completada. " + arbol.size() + " libro(s) cargado(s).");
 
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + rutaArchivo);
        }
    }
}