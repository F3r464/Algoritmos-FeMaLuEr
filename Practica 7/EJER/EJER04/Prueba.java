package EJER.EJER04;

import ACT.ACT04.ItemDuplicated;

public class Prueba{
    public static void main(String[] args) throws ItemDuplicated{
        Parenthesize<Integer> arbol=new Parenthesize<>();

        arbol.insert(50);
        arbol.insert(30);
        arbol.insert(70);
        arbol.insert(20);
        arbol.insert(40);
        arbol.insert(60);
        arbol.insert(80);

        arbol.parenthesize();
    }
}