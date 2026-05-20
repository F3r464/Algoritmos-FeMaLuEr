package ACT.ACT032

import ACT.ACT03.AVLtree;
import ACT.ACT031.*;
import ACT.ACT04.*;
public class AVLtree32<E extends Comparable<E>> extends AVLtree<E> {

    public void insert(E x) throws ItemDuplicated {
        this.height = false;
        this.root = insert(x, (NodeAVL) this.root);
    }

    protected Node insert(E x, NodeAVL node) throws ItemDuplicated {
        NodeAVL fat = node;

        if (node == null) {
            this.height = true;
            fat = new NodeAVL(x);
        } else {
            // Compara el dato del nodo actual con el elemento x a insertar
            int resC = node.dato.compareTo(x);

            if (resC == 0) {
                throw new ItemDuplicated(x + " ya se encuentra en el arbol...");
            }

            if (resC < 0) { // x es mayor -> va a la derecha
                fat.right = insert(x, (NodeAVL) node.right);
                if (this.height) {
                    switch (fat.bf) {
                        case -1:
                            fat.bf = 0;
                            this.height = false;
                            break;
                        case 0:
                            fat.bf = 1; // f = 1; en la imagen
                            this.height = true;
                            break;
                        case 1: // bf = 2
                            fat = balanceToRight(fat);
                            this.height = false;
                            break;
                    }
                }
            } else { // COMPLETADO: x es menor -> va a la izquierda
                fat.left = insert(x, (NodeAVL) node.left);
                if (this.height) {
                    switch (fat.bf) {
                        case 1:
                            fat.bf = 0;
                            this.height = false;
                            break;
                        case 0:
                            fat.bf = -1;
                            this.height = true;
                            break;
                        case -1: // bf = -2
                            fat = balanceToLeft(fat);
                            this.height = false;
                            break;
                    }
                }
            }
        }
        return fat;
    }  
}
