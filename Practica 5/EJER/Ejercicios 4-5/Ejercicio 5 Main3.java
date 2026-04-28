

public class Main3 {
    public static void main(String[] args) {

        // Creamos la primera lista: 10 -> 20 -> 30
        ListaEnlazada<Integer> lista1 = new ListaEnlazada<>();
        lista1.agregar(10);
        lista1.agregar(20);
        lista1.agregar(30);

        // Creamos la segunda lista igual: 10 -> 20 -> 30
        ListaEnlazada<Integer> lista2 = new ListaEnlazada<>();
        lista2.agregar(10);
        lista2.agregar(20);
        lista2.agregar(30);

        // Creamos una tercera lista diferente: 10 -> 20 -> 99
        ListaEnlazada<Integer> lista3 = new ListaEnlazada<>();
        lista3.agregar(10);
        lista3.agregar(20);
        lista3.agregar(99);

        // Comparamos lista1 y lista2 (deben ser iguales)
        System.out.println("lista1 y lista2 son iguales: " + sonIguales.sonIguales(lista1, lista2)); // true

        // Comparamos lista1 y lista3 (deben ser diferentes)
        System.out.println("lista1 y lista3 son iguales: " + sonIguales.sonIguales(lista1, lista3)); // false
    }
}