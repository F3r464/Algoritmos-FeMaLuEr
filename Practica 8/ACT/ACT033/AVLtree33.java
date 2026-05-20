package ACT.ACT033;

import ACT.ACT03.AVLnodo;

public class AVLtree33 {
    private AVLnodo balanceToLeft(AVLnodo node){
        AVLnodo izquierdo = (AVLnodo) node.left;
        if (izquierdo.bf == -1){ // Caso LL
            node.bf = 0;
            izquierdo.bf = 0;
            node = (AVLnodo) rotRight(node);
        } else { // Caso LR
            AVLnodo subDerecho = (AVLnodo) izquierdo.right;
            switch (subDerecho.bf) {
                case -1:
                    node.bf = 1;
                    izquierdo.bf = 0;
                    break;
                case 0:
                    node.bf = 0;
                    izquierdo.bf = 0;
                    break;
                case 1:
                    node.bf = 0;
                    izquierdo.bf = -1;
                    break;
            }
            subDerecho.bf = 0;
            node.left = rotLeft(izquierdo);
            node = (AVLnodo) rotRight(node);
        }
        return node;
    }

    private AVLnodo balanceToRight(AVLnodo node) {
        AVLnodo derecho = (AVLnodo) node.right;
        if (derecho.bf == 1) { // Caso RR
            node.bf = 0;
            derecho.bf = 0;
            node = (AVLnodo) rotLeft(node);
        } else { // Caso RL
            AVLnodo subIzquierdo = (AVLnodo) derecho.left;
            switch (subIzquierdo.bf) {
                case 1:
                    node.bf = -1;
                    derecho.bf = 0;
                    break;
                case 0:
                    node.bf = 0;
                    derecho.bf = 0;
                    break;
                case -1:
                    node.bf = 0;
                    derecho.bf = 1;
                    break;
            }
            subIzquierdo.bf = 0;
            node.right = rotRight(derecho);
            node = (AVLnodo) rotLeft(node);
        }
        return node;
    }

    // --- ANULACIÓN DE ROTACIONES HEREDADAS PARA TRABAJAR CON AVLnodo ---

    @Override
    protected Node rotRight(Node y) {
        Node x = y.left;
        y.left = x.right;
        x.right = y;
        return x;
    }

    @Override
    protected Node rotLeft(Node x) {
        Node y = x.right;
        x.right = y.left;
        y.left = x;
        return y;
    }
}
