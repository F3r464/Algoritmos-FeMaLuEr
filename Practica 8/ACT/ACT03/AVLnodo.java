package ACT.ACT03;

import EJER.EJER02.*;

public class AVLnodo<E> extends Node<E>{
    public int height; 

    public AVLnodo(E dato){
        super(dato);
        this.height = 1;
    }
}
