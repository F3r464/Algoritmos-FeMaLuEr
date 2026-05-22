package ACT.ACT03;

public class AVLnodo<E>{

    public E dato;
    public int bf;

    public AVLnodo<E> left;
    public AVLnodo<E> right;

    public AVLnodo(E dato){

        this.dato=dato;
        this.bf=0;

        left=null;
        right=null;
    }

    @Override
    public String toString(){

        return dato+"(bf:"+bf+")";
    }
}