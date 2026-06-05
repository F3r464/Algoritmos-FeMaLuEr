package EJER.EJER01;

//Aquí coordinamos insertar, buscar y eliminar tickets.
public class GestorTicketsAVL{
    public static void main(String[] args){
        ArbolAVL arbol = new ArbolAVL();
        System.out.println("----GESTOR DE TICKETS----");

        //INSERCIÓN.
        System.out.println("\n----INSERCIÓN DE TICKETS----");
        int[] tickets = {30, 10, 20, 40, 50, 25};

        for (int t : tickets){
            System.out.println("\nInsertando ticket " + t + " ");
            arbol.insertar(t);
            arbol.mostrarArbol();
            arbol.inorden();
        }
        //BÚSQUEDA.
        System.out.println("\n----BÚSQUEDA DE TICKETS----");
        int[] buscar = {20, 60};

        for (int t : buscar){
            System.out.print("\nBuscando ticket " + t + ": ");
            boolean encontrado = arbol.buscar(t);
            System.out.println(
                    encontrado
                    ? "ENCONTRADO."
                    : "NO existe."
            );
        }
        //ELIMINACIÓN.
        System.out.println("\n----ELIMINACIÓN DE TICKETS----");
        int[] eliminar = {10, 40, 30};

        for (int t : eliminar){
            System.out.println("\nEliminando ticket " + t + " ");
            arbol.eliminar(t);
            arbol.mostrarArbol();
            arbol.inorden();
        }
        //FINAL.
        System.out.println("\n----ÁRBOL FINAL----");
        arbol.mostrarArbol();
        arbol.inorden();
        System.out.println("\nSi hay AVL.");
    }
}