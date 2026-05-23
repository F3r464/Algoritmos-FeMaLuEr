package ACT.ACT031;

import ACT.ACT03.*;
public class AVLtree31<E extends Comparable<E>>{
    protected AVLnodo<E> root;
    public AVLtree31(){
        root=null;
    }
    public boolean isEmpty(){
        return root==null;
    }

}