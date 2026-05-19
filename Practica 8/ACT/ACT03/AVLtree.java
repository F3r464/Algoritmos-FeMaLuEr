package ACT.ACT03;

import ACT.ACT06.*;
import ACT.ACT04.*;

public class AVLtree<E extends Comparable<E>> extends LinkedBST<E>{

    protected class AVLNode extends Node{

        int altura;

        AVLNode(E dato){
            super(dato);
            altura=1;
        }
    }

    public AVLtree(){
        super();
    }

    protected int altura(Node n){
        if(n==null){
            return 0;
        }
        return ((AVLNode)n).altura;
    }

    protected int balance(Node n){
        if(n==null){
            return 0;
        }
        return altura(n.left)-altura(n.right);
    }

    protected Node rotRight(Node y){
        Node x=y.left;
        Node t2=x.right;
        x.right=y;
        y.left=t2;
        ((AVLNode)y).altura=Math.max(altura(y.left),altura(y.right))+1;
        ((AVLNode)x).altura=Math.max(altura(x.left),altura(x.right))+1;

        return x;
    }

    protected Node rotLeft(Node x){

        Node y=x.right;
        Node t2=y.left;

        y.left=x;
        x.right=t2;

        ((AVLNode)x).altura= Math.max(altura(x.left),altura(x.right))+1;

        ((AVLNode)y).altura=Math.max(altura(y.left),altura(y.right))+1;

        return y;
    }

    public void insert(E dato) throws ItemDuplicated{
        root=insertAVL(root,dato);
    }
    protected Node insertAVL(Node actual,E dato) throws ItemDuplicated{
        if(actual==null){
            return new AVLNode(dato);
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

        ((AVLNode)actual).altura= 1+Math.max(altura(actual.left),altura(actual.right));

        int bal=balance(actual);

        // LL
        if(bal>1 && dato.compareTo(actual.left.dato)<0){
            return rotRight(actual);
        }

        // RR
        if(bal<-1 && dato.compareTo(actual.right.dato)>0){
            return rotLeft(actual);
        }

        // LR
        if(bal>1 && dato.compareTo(actual.left.dato)>0){
            actual.left=rotLeft(actual.left);
            return rotRight(actual);
        }

        // RL
        if(bal<-1 && dato.compareTo(actual.right.dato)<0){
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

    protected void preOrder(Node actual){

        if(actual!=null){
            System.out.print(actual.dato+" ");
            preOrder(actual.left);
            preOrder(actual.right);
        }
    }
}