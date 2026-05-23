package ACT.ACT033;

import ACT.ACT03.AVLtree;
import ACT.ACT032.*;

public class AVLtree33<E extends Comparable<E>>
extends AVLtree32<E>{

    protected AVLnodo<E>
    balanceToLeft(AVLnodo<E> node){

        AVLnodo<E> izq=node.left;

        //LL
        if(izq.bf==-1){

            node.bf=0;
            izq.bf=0;

            node=rotRight(node);
        }

        //LR
        else{

            AVLnodo<E> der=izq.right;

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

    protected AVLnodo<E>
    balanceToRight(AVLnodo<E> node){

        AVLnodo<E> der=node.right;

        //RR
        if(der.bf==1){

            node.bf=0;
            der.bf=0;

            node=rotLeft(node);
        }

        //RL
        else{

            AVLnodo<E> izq=der.left;

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

    public void preOrder(){

        System.out.print("AVL: ");

        preOrderRec(root);

        System.out.println();
    }

    private void preOrderRec(
    AVLnodo<E> actual){

        if(actual!=null){

            System.out.print(actual+" ");

            preOrderRec(actual.left);
            preOrderRec(actual.right);
        }
    }
}