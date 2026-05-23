package ACT.ACT034;

import ACT.ACT03.*;
public class AVLtree34<E extends Comparable<E>>{

    protected AVLnodo<E> rotRight(AVLnodo<E> y){
        AVLnodo<E> x=y.left;
        AVLnodo<E> t2=x.right;
        x.right=y;
        y.left=t2;
        return x;
    }
    protected AVLnodo<E> rotLeft(AVLnodo<E> x){
        AVLnodo<E> y=x.right;
        AVLnodo<E> t2=y.left;
        y.left=x;
        x.right=t2;
        return y;
    }
}