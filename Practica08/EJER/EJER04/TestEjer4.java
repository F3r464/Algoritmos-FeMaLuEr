package EJER.EJER04;

import ACT.ACT04.ItemDuplicated;
public class TestEjer4{
    public static void main(String[] args){
        AmplitAVL<Integer> arbol=new AmplitAVL<>();

        System.out.println("insert ");
        try {
            int[] valores ={20, 10, 30, 5, 15, 40};
            for (int val :valores){
                arbol.insert(val);
            }
            arbol.inOrder(); 

        } catch (ItemDuplicated e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("\nrecorrido");
        System.out.println("Altura calculada del árbol: " + arbol.height());
        arbol.amplitudRec();
    }
}
