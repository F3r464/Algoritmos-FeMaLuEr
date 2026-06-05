import java.util.ArrayList;
import java.util.Collections;

// 1. CLASE PRINCIPAL (Debe llamarse igual que el archivo: Ejer_08.java)
public class ejer_08 {
    public static void main(String[] args) {
        ColaReproduccion<Cancion> spotify = new ColaReproduccion<>();
        
        // Agregando canciones según la guía [cite: 292]
        spotify.agregarCancion(new Cancion("Bohemian Rhapsody", "Queen", 354));
        spotify.agregarCancion(new Cancion("Blinding Lights", "The Weeknd", 200));
        spotify.agregarCancion(new Cancion("Shape of You", "Ed Sheeran", 234));
        spotify.agregarCancion(new Cancion("One", "Metallica", 447));
        spotify.agregarCancion(new Cancion("Starboy", "The Weeknd", 230));
        spotify.agregarCancion(new Cancion("Yellow", "Coldplay", 266));

        System.out.println("Cola de Reproducción Inicial");
        spotify.mostrarCola();

        System.out.println("\n Avanzando 3 canciones");
        spotify.reproducirSiguiente();
        spotify.reproducirSiguiente();
        System.out.println("Reproduciendo ahora: " + spotify.reproducirSiguiente());

        System.out.println("\nRetrocediendo 1 canción");
        System.out.println("Regresamos a: " + spotify.reproducirAnterior());

        System.out.println("\nMezclando (Shuffle)");
        spotify.mezclar();
        spotify.mostrarCola();
    }
}

// 2. CLASE CANCIÓN
class Cancion {
    String titulo, artista;
    int duracionSeg;

    public Cancion(String titulo, String artista, int duracionSeg) {
        this.titulo = titulo;
        this.artista = artista;
        this.duracionSeg = duracionSeg;
    }

    @Override
    public String toString() {
        return titulo + " - " + artista + " (" + duracionSeg + "s)";
    }
}

// 3. NODO DOBLE (Con punteros next y prev) [cite: 281]
class NodeDoble<T> {
    T value;
    NodeDoble<T> next, prev;
    public NodeDoble(T value) { this.value = value; }
}

// 4. COLA DE REPRODUCCIÓN (Lógica de lista doblemente enlazada) [cite: 283]
class ColaReproduccion<T> {
    private NodeDoble<T> primero, ultimo, actual;

    // Agrega al final de la cola [cite: 284]
    public void agregarCancion(T cancion) {
        NodeDoble<T> nuevo = new NodeDoble<>(cancion);
        if (primero == null) {
            primero = ultimo = actual = nuevo;
        } else {
            ultimo.next = nuevo;
            nuevo.prev = ultimo;
            ultimo = nuevo;
        }
    }

    // Avanza al siguiente nodo [cite: 285]
    public T reproducirSiguiente() {
        if (actual != null && actual.next != null) {
            actual = actual.next;
            return actual.value;
        }
        return null;
    }

    // Retrocede al nodo anterior usando prev [cite: 286]
    public T reproducirAnterior() {
        if (actual != null && actual.prev != null) {
            actual = actual.prev;
            return actual.value;
        }
        return null;
    }

    // Muestra todas las canciones e indica la actual [cite: 289]
    public void mostrarCola() {
        NodeDoble<T> temp = primero;
        int i = 1;
        while (temp != null) {
            String marca = (temp == actual) ? "► " : "  ";
            System.out.println(marca + i + ". " + temp.value);
            temp = temp.next;
            i++;
        }
    }

    // Reorganiza aleatoriamente usando una lista temporal [cite: 287]
    public void mezclar() {
        if (primero == null) return;
        ArrayList<T> tempArr = new ArrayList<>();
        NodeDoble<T> tempNode = primero;
        while (tempNode != null) {
            tempArr.add(tempNode.value);
            tempNode = tempNode.next;
        }
        Collections.shuffle(tempArr);
        
        primero = ultimo = null;
        for (T elemento : tempArr) agregarCancion(elemento);
        actual = primero;
    }
}