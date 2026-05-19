package ACT.ACT031;

import ACT.ACT06.*;
import ACT.ACT04.*;

public class AVLtree31<E extends Comparable<E>>extends LinkedBST<E>{
    protected class NodeAVL extends Node{

        protected int bf;

        public NodeAVL(E dato){
            super(dato);
            bf=0;
        }
    }

    public AVLtree31(){
        super();
    }

    private int height(Node actual){

        if(actual==null){
            return 0;
        }

        int izq=height(actual.left);
        int der=height(actual.right);

        return Math.max(izq,der)+1;
    }

    private int balance(Node actual){

        if(actual==null){
            return 0;
        }

        return height(actual.left)-height(actual.right);
    }

    private Node rotRight(Node y){

        Node x=y.left;
        Node t2=x.right;

        x.right=y;
        y.left=t2;

        return x;
    }

    private Node rotLeft(Node x){

        Node y=x.right;
        Node t2=y.left;

        y.left=x;
        x.right=t2;

        return y;
    }

    public void insert(E dato)throws ItemDuplicated{
        root=insertAVL(root,dato);
    }

    private Node insertAVL(Node actual,E dato)
    throws ItemDuplicated{

        if(actual==null){
            return new NodeAVL(dato);
        }

        int cmp=dato.compareTo(actual.dato);

        if(cmp<0){
            actual.left=insertAVL(actual.left,dato);
        }
        else if(cmp>0){
            actual.right=insertAVL(actual.right,dato);
        }
        else{
            throw new ItemDuplicated("duplicado");
        }

        ((NodeAVL)actual).bf=balance(actual);

        //LL
        if(((NodeAVL)actual).bf==2 &&
        dato.compareTo(actual.left.dato)<0){

            return rotRight(actual);
        }

        //RR
        if(((NodeAVL)actual).bf==-2 &&
        dato.compareTo(actual.right.dato)>0){

            return rotLeft(actual);
        }

        //LR
        if(((NodeAVL)actual).bf==2 &&
        dato.compareTo(actual.left.dato)>0){

            actual.left=rotLeft(actual.left);

            return rotRight(actual);
        }

        //RL
        if(((NodeAVL)actual).bf==-2 &&
        dato.compareTo(actual.right.dato)<0){

            actual.right=rotRight(actual.right);

            return rotLeft(actual);
        }

        return actual;
    }

    public void preOrder(){
        System.out.print("AVL: ");
        preOrder(root);
        System.out.println();
    }

    private void preOrder(Node actual){

        if(actual!=null){

            System.out.print(
            actual.dato+
            "("+
            ((NodeAVL)actual).bf+
            ") "
            );

            preOrder(actual.left);
            preOrder(actual.right);
        }
    }
}