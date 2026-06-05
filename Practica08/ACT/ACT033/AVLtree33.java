package ACT.ACT033;

import ACT.ACT03.AVLtree;

public class AVLtree33<E extends Comparable<E>> extends AVLtree<E>{
//Inicio ACT 3.3
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
//Fin Act 3.3
}