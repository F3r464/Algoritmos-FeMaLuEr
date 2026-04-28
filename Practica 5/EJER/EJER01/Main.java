
public class Main {
    public static void main(String[] args) {
        ListLinked<Integer> lista = new ListLinked<>();
        lista.insertLast(10);
        lista.insertLast(20);
        lista.insertLast(30);

        System.out.println(BuscarElemento.buscarElemento(lista, 20)); // true
        System.out.println(BuscarElemento.buscarElemento(lista, 99)); // false
    }
}