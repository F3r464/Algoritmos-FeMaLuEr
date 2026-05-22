package ACT.ACT032;

import ACT.ACT03.*;
import ACT.ACT031.*;
import ACT.ACT04.*;
import ACT.ACT033.*;

public class AVLtree32<E extends Comparable<E>>extends AVLtree31<E>{

    protected boolean altura;

    public void insert(E x)
    throws ItemDuplicated{

        altura=false;

        root=insertRec(root,x);
    }

    private AVLnodo<E> insertRec(
    AVLnodo<E> node,E x)
    throws ItemDuplicated{

        if(node==null){

            altura=true;

            return new AVLnodo<>(x);
        }

        int cmp=x.compareTo(node.dato);

        if(cmp<0){

            node.left=
            insertRec(node.left,x);

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
                        node=
                        balanceToLeft(node);

                        altura=false;
                        break;
                }
            }
        }

        else if(cmp>0){

            node.right=
            insertRec(node.right,x);

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
                        node=
                        balanceToRight(node);

                        altura=false;
                        break;
                }
            }
        }

        else{
            throw new ItemDuplicated(
            "duplicado");
        }

        return node;
    }
}