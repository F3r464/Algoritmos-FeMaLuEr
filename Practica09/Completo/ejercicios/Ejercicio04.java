package ejercicios;

import Lab_09.Biblioteca;
import Lab_09.Libro;
import btree.BNode;

public class Ejercicio04 {
    public static void ejecutar() {
        // ─── Separadores de Guía de Práctica ──────────────────────────────────
        System.out.println("========================================================================");
        System.out.println("  EJERCICIO 04 – Aplicación Real: Biblioteca Digital Modular");
        System.out.println("========================================================================");
 
        BNode.resetIdCounter();
 
        // ── 4.a) Carga manual ─────────────────────────────────────────────────
        System.out.println("\n[A] Carga manual e inserción de registros literarios (Árbol Orden 4):");
        Biblioteca bib = new Biblioteca(4);
        bib.agregarLibro(new Libro("9780132350884", "Clean Code",           "Robert Martin", 2008));
        bib.agregarLibro(new Libro("9780134494166", "Clean Architecture",   "Robert Martin", 2017));
        bib.agregarLibro(new Libro("9780201633610", "Design Patterns",      "GoF",           1994));
        bib.agregarLibro(new Libro("9780596009205", "Head First Java",      "Kathy Sierra",  2005));
        bib.agregarLibro(new Libro("9780135957059", "The Pragmatic Programmer", "D. Thomas", 2019));
        bib.agregarLibro(new Libro("9780201485677", "Refactoring",          "Martin Fowler", 1999));
 
        // Intento explícito de inserción de clave duplicada
        System.out.println("\n-> Insertando Libro con un ISBN Duplicado para verificar control de errores:");
        bib.agregarLibro(new Libro("9780132350884", "Duplicado Invalido",    "Test",          2000));
 
        bib.mostrarArbol();
        bib.mostrarLibrosOrdenados();
        bib.mostrarAltura();
        bib.mostrarTotalLibros();
 
        // ── 4.b) Búsqueda por ISBN ────────────────────────────────────────────
        System.out.println("\n[B] Simulación de Consultas Indexadas (Búsquedas por ISBN):");
        bib.buscarLibro("9780201633610");  // Caso exitoso
        bib.buscarLibro("9999999999999");  // Caso inexistente
 
        // ── 4.c) Eliminación ──────────────────────────────────────────────────
        System.out.println("\n[C] Operaciones de Baja (Eliminación de Ejemplares del Catálogo):");
        bib.eliminarLibro("9780201633610");
        bib.mostrarArbol();
        bib.mostrarTotalLibros();
 
        // ── 4.d) Carga desde archivo ──────────────────────────────────────────
        System.out.println("\n[D] Carga Automatizada mediante Lectura de Archivo 'biblioteca.txt':");
        BNode.resetIdCounter();
        Biblioteca bibArchivo = new Biblioteca(4);
        
        // Ejecución de la rutina parser para leer registros separados por comas
        bibArchivo.cargarDesdeArchivo("src/Lab_09/biblioteca.txt");
        bibArchivo.mostrarArbol();
        bibArchivo.mostrarAltura();
        bibArchivo.mostrarTotalLibros();
    }
}