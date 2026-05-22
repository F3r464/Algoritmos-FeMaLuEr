package ACT.ACT03;

import ACT.ACT04.*;
public class AVLtree<E extends Comparable<E>>{
    public class AVLnodo{
        public E dato;
        public int bf;
        public AVLnodo left;
        public AVLnodo right;

        public AVLnodo(E dato){
            this.dato=dato;
            this.bf=0;
            left=null;
            right=null;
        }
        public String toString(){
            return dato+"(bf:"+bf+")";
        }
    }
    protected AVLnodo root;
    protected boolean altura;

    public AVLtree(){
        root=null;
    }
    public boolean isEmpty(){
        return root==null;
    }

    public void insert(E x)throws ItemDuplicated{
        altura=false;
        root=insertRec(root,x);
    }
    private AVLnodo insertRec(AVLnodo node,E x)throws ItemDuplicated{
        if(node==null){
            altura=true;
            return new AVLnodo(x);
        }
        int cmp=x.compareTo(node.dato);
        //izquierda
        if(cmp<0){
            node.left=insertRec(node.left,x);
            if(altura){
                switch(node.bf){
                    case 1:
                        node.bf=0;
                        altura=false;
                        break;
                    case 0:
                        node.bf=-1;
                        break;
                    case -1:
                        node=balanceToLeft(node);
                        altura=false;
                        break;
                }
            }
        }
        //derecha
        else if(cmp>0){
            node.right=insertRec(node.right,x);
            if(altura){
                switch(node.bf){
                    case -1:
                        node.bf=0;
                        altura=false;
                        break;
                    case 0:
                        node.bf=1;
                        break;
                    case 1:
                        node=balanceToRight(node);
                        altura=false;
                        break;
                }
            }
        }
        else{
            throw new ItemDuplicated("duplicado");
        }
        return node;
    }

    protected AVLnodo balanceToLeft(AVLnodo node){
        AVLnodo izq=node.left;
        //LL
        if(izq.bf==-1){
            node.bf=0;
            izq.bf=0;
            node=rotRight(node);
        }

        //LR
        else{
            AVLnodo der=izq.right;
            switch(der.bf){
                case -1:
                    node.bf=1;
                    izq.bf=0;
                    break;
                case 0:
                    node.bf=0;
                    izq.bf=0;
                    break;
                case 1:
                    node.bf=0;
                    izq.bf=-1;
                    break;
            }
            der.bf=0;
            node.left=rotLeft(izq);
            node=rotRight(node);
        }

        return node;
    }
    protected AVLnodo balanceToRight(AVLnodo node){
        AVLnodo der=node.right;
        //RR
        if(der.bf==1){
            node.bf=0;
            der.bf=0;
            node=rotLeft(node);
        }
        //RL
        else{
            AVLnodo izq=der.left;
            switch(izq.bf){
                case 1:
                    node.bf=-1;
                    der.bf=0;
                    break;
                case 0:
                    node.bf=0;
                    der.bf=0;
                    break;
                case -1:
                    node.bf=0;
                    der.bf=1;
                    break;
            }
            izq.bf=0;
            node.right=rotRight(der);
            node=rotLeft(node);
        }
        return node;
    }
    protected AVLnodo rotRight(AVLnodo y){
        AVLnodo x=y.left;
        AVLnodo t2=x.right;
        x.right=y;
        y.left=t2;
        return x;
    }
    protected AVLnodo rotLeft(AVLnodo x){
        AVLnodo y=x.right;
        AVLnodo t2=y.left;
        y.left=x;
        x.right=t2;
        return y;
    }
    public void preOrder(){
        System.out.print("AVL: ");
        preOrderRec(root);
        System.out.println();
    }
    private void preOrderRec(AVLnodo actual){
        if(actual!=null){
            System.out.print(actual+" ");
            preOrderRec(actual.left);
            preOrderRec(actual.right);
        }
    }
    public void inOrder(){
        System.out.print("InOrder: ");
        inOrderRec(root);
        System.out.println();
    }
    private void inOrderRec(
    AVLnodo actual){
        if(actual!=null){
            inOrderRec(actual.left);
            System.out.print(actual+" ");
            inOrderRec(actual.right);
        }
    }
}