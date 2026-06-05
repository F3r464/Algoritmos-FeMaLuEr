package EJER.EJER02;
//Comparación entre BST y AVL.
public class Comparaciones{
    public static void main(String[] args){
        ArbolBST bst = new ArbolBST();
        ArbolAVL avl = new ArbolAVL();
        System.out.println("----COMPARACIÓN BST vs AVL----");
        //CASO 1.
        System.out.println("\n----CASO 1----");
        int[] datos1 = {10, 20, 30, 40, 50};
        for (int x : datos1){
            bst.insertar(x);
            avl.insertar(x);
        }
        System.out.println("\nRECORRIDO BST:");
        bst.inorden();
        System.out.println("Altura BST: " + bst.altura());
        bst.mostrarArbol();
        System.out.println("\nRECORRIDO AVL:");
        avl.inorden();
        System.out.println("Altura AVL: " + avl.altura());
        avl.mostrarArbol();
        //CASO 2.
        System.out.println("\n----CASO 2----");
        ArbolBST bst2 = new ArbolBST();
        ArbolAVL avl2 = new ArbolAVL();
        int[] datos2 = {50, 40, 30, 20, 10};
        for (int x : datos2){
            bst2.insertar(x);
            avl2.insertar(x);
        }
        System.out.println("\nRECORRIDO BST:");
        bst2.inorden();
        System.out.println("Altura BST: " + bst2.altura());
        bst2.mostrarArbol();
        System.out.println("\nRECORRIDO AVL:");
        avl2.inorden();
        System.out.println("Altura AVL: " + avl2.altura());
        avl2.mostrarArbol();
        //BÚSQUEDA.
        System.out.println("\n----BÚSQUEDA----");
        int buscar = 30;
        System.out.println("\nBuscando " + buscar + " en BST: "
                + (bst.buscar(buscar)
                ? "ENCONTRADO"
                : "NO existe"));
        System.out.println("Buscando " + buscar + " en AVL: "
                + (avl.buscar(buscar)
                ? "ENCONTRADO"
                : "NO existe"));
        System.out.println("\nNOTAS:");
        System.out.println("El BST puede desbalancearse dependiendo");
        System.out.println("del orden de inserción.");
        System.out.println("\nEl AVL mantiene una altura equilibrada");
        System.out.println("mediante rotaciones automáticas.");
    }
}