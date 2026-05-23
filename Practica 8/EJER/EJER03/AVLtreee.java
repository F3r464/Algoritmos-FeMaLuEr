package EJER.EJER03;

import ACT.ACT03.AVLtree; 
import ACT.ACT04.*;
public class AVLtreee<E extends Comparable<E>> extends AVLtree<E>{
    private boolean altura;
    public void delete(E dato) throws ExceptionIsEmpty{
        if(isEmpty()){
            throw new ExceptionIsEmpty();
        }
        altura = false;
        root = deleteAVL(root, dato);
    }
    private AVLnodo deleteAVL(AVLnodo actual, E dato){
        if(actual == null){
            return null;
        }
        int cmp=dato.compareTo(actual.dato);

        if(cmp<0){
            actual.left=deleteAVL(actual.left, dato);
            if(altura){
                actual=recargarBalanceDerecha(actual);
            }
        }else if(cmp>0){
            actual.right=deleteAVL(actual.right, dato);
            if(altura){
                actual = recargarBalanceIzquierda(actual);
            }
        }else{
            if(actual.left == null){
                altura = true;
                return actual.right;
            }
            if(actual.right == null){ 
                altura = true;
                return actual.left;
            }
            E sucesor =findMin(actual.right);
            actual.dato=sucesor;
            actual.right =deleteAVL(actual.right, sucesor);
            if(altura){
                actual=recargarBalanceIzquierda(actual);
            }
        }
        return actual;
    }
    private E findMin(AVLnodo node){
        if(node.left==null){
            return node.dato;
        }else{
            return findMin(node.left);
        }
    }
    private AVLnodo recargarBalanceDerecha(AVLnodo node){
        switch (node.bf){
            case -1:
                node.bf = 0;
                altura = true;
                break;
            case 0: 
                node.bf = 1;
                altura = false;
                break;
            case 1: 
                node = balanceToRightDelete(node);
                break;
        }
        return node;
    }
    private AVLnodo recargarBalanceIzquierda(AVLnodo node){
        switch (node.bf) {
            case 1: 
                node.bf = 0;
                altura = true;
                break;
            case 0: 
                node.bf = -1;
                altura = false;
                break;
            case -1:
                node = balanceToLeftDelete(node);
                break;
        }
        return node;
    }
    private AVLnodo balanceToRightDelete(AVLnodo node){
        AVLnodo der = node.right;
        if(der.bf >= 0){
            if (der.bf==0){
                node.bf=1;
                der.bf=-1;
                altura=false;
                return rotLeft(node);
            }
            node.bf=0;
            der.bf=0;
            altura= true;
            return rotLeft(node);
        }else{
            AVLnodo izq=der.left;
            switch (izq.bf){
                case 1:  
                    node.bf=-1;
                    der.bf=0; 
                    break;
                case 0: 
                    node.bf=0;  
                    der.bf =0;
                    break;
                case -1:   
                    node.bf=0;   
                    der.bf=1;
                    break;
            }
            izq.bf = 0;
            node.right = rotRight(der);
            altura = true;
            return rotLeft(node);
        }
    }
    private AVLnodo balanceToLeftDelete(AVLnodo node){
        AVLnodo izq = node.left;
        if (izq.bf <= 0){
            if (izq.bf == 0){
                node.bf = -1;
                izq.bf = 1;
                altura = false;
                return rotRight(node);
            }
            node.bf=0;
            izq.bf=0;
            altura=true;
            return rotRight(node);
        } else {
            AVLnodo der = izq.right;
            switch (der.bf) {
                case -1: 
                    node.bf=1;  
                    izq.bf=0; 
                    break;
                case 0:  
                    node.bf=0; 
                    izq.bf =0; 
                    break;
                case 1:  
                    node.bf=0;  
                    izq.bf=-1; 
                    break;
            }
            der.bf = 0;
            node.left = rotLeft(izq);
            altura = true;
            return rotRight(node);
        }
    }
}
