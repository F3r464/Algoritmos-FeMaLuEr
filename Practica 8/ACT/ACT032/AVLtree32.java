package ACT.ACT032;

import ACT.ACT03.*;
import ACT.ACT04.*;

public class AVLtree32<E extends Comparable<E>>extends AVLtree<E>{

//Inicio ACt 3.2
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
//Fin Act 3.2
}