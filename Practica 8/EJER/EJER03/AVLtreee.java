package EJER.EJER03;

import ACT.ACT03.AVLtree; 
import ACT.ACT04.*;
import ACT.ACT06.*;

public class AVLtreee<E extends Comparable<E>> extends AVLtree<E>{

    public void delete(E dato) throws ExceptionIsEmpty{
        if (isEmpty()) {
            throw new ExceptionIsEmpty();
        }
        root = deleteAVL(root, dato);
    }

    private Node deleteAVL(Node actual, E dato){
        if(actual == null){
            return null;
        }

        int cmp = dato.compareTo(actual.dato);

        if (cmp < 0){
            actual.left = deleteAVL(actual.left, dato);
        }else if (cmp > 0){
            actual.right = deleteAVL(actual.right, dato);
        } else {
            if (actual.left == null){
                return actual.right;
            }
            if (actual.right == null){
                return actual.left;
            }
            E sucesor = findMin(actual.right);
            actual.dato = sucesor;
            actual.right = deleteAVL(actual.right, sucesor);
        }
        ((AVLNode) actual).height = 1 + Math.max(height(actual.left), height(actual.right));

        int bal = balance(actual);

        // Caso LL
        if (bal > 1 && balance(actual.left) >= 0){
            return rotRight(actual);
        }

        // Caso LR
        if (bal > 1 && balance(actual.left) < 0){
            actual.left = rotLeft(actual.left);
            return rotRight(actual);
        }

        // Caso RR
        if (bal < -1 && balance(actual.right) <= 0){
            return rotLeft(actual);
        }

        // Caso RL
        if (bal < -1 && balance(actual.right) > 0){
            actual.right = rotRight(actual.right);
            return rotLeft(actual);
        }

        return actual;
    }

    private E findMin(Node node){
        if (node.left == null){
            return node.dato;
        } else {
            return findMin(node.left);
        }
    }
}
