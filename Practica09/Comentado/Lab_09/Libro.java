package Lab_09;
 

 //Representa un libro con atributos básicos para su gestión en una biblioteca digital.

public class Libro implements Comparable<Libro> {
 
    // ─── Atributos ────────────────────────────────────────────────────────────
    private final String isbn;
    private final String titulo;
    private final String autor;
    private final int    anio;
 
    // ─── Constructor ──────────────────────────────────────────────────────────
 
    /**
     * Crea un libro con todos sus atributos.
     */
    public Libro(String isbn, String titulo, String autor, int anio) {
        this.isbn   = isbn;
        this.titulo = titulo;
        this.autor  = autor;
        this.anio   = anio;
    }
 
    // ─── Getters ──────────────────────────────────────────────────────────────
 
    public String getIsbn()   { return isbn;   }
    public String getTitulo() { return titulo; }
    public String getAutor()  { return autor;  }
    public int    getAnio()   { return anio;   }
 
    // ─── Comparable ───────────────────────────────────────────────────────────
 
    /**
     * Compara dos libros por su ISBN en orden lexicográfico.
     * Esto determina el orden en que se almacenan dentro del Árbol B.
     */
    @Override
    public int compareTo(Libro otro) {
        return this.isbn.compareTo(otro.isbn);
    }
 
    // ─── Representación textual ───────────────────────────────────────────────
 
    @Override
    public String toString() {
        return String.format("[ISBN: %s | Título: %s | Autor: %s | Año: %d]",
                isbn, titulo, autor, anio);
    }
 
    /**
     * Dos libros son iguales si tienen el mismo ISBN.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Libro)) return false;
        return this.isbn.equals(((Libro) obj).isbn);
    }
 
    @Override
    public int hashCode() {
        return isbn.hashCode();
    }
}