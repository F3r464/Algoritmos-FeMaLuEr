

public class Main2 {
    public static void main(String[] args) {
        
        // Creamos nodos para probar
        NodoLista<Integer> nodo1 = new NodoLista<>(10);
        NodoLista<Integer> nodo2 = new NodoLista<>(20);
        NodoLista<Integer> nodo3 = new NodoLista<>(30);
        
        // Enlazamos los nodos: 10 - 20 - 30 - null
        nodo1.siguiente = nodo2;
        nodo2.siguiente = nodo3;
        
        // Llamamos al método contarNodos
        int total = contarNodos.contarNodos(nodo1);
        
        // Mostramos el resultado
        System.out.println("Total de nodos: " + total); // Imprimir 3
    }
}