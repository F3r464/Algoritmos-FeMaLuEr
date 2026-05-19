package ACT.ACT033;

public class AVLtree33 {
    private NodeAVL balanceToLeft(NodeAVL node) {
        NodeAVL izquierdo = (NodeAVL) node.left;
        if (izquierdo.bf == -1) { // Caso LL
            node.bf = 0;
            izquierdo.bf = 0;
            node = (NodeAVL) rotRight(node);
        } else { // Caso LR
            NodeAVL subDerecho = (NodeAVL) izquierdo.right;
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
            node = (NodeAVL) rotRight(node);
        }
        return node;
    }

    private NodeAVL balanceToRight(NodeAVL node) {
        NodeAVL derecho = (NodeAVL) node.right;
        if (derecho.bf == 1) { // Caso RR
            node.bf = 0;
            derecho.bf = 0;
            node = (NodeAVL) rotLeft(node);
        } else { // Caso RL
            NodeAVL subIzquierdo = (NodeAVL) derecho.left;
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
            node = (NodeAVL) rotLeft(node);
        }
        return node;
    }

    // --- ANULACIÓN DE ROTACIONES HEREDADAS PARA TRABAJAR CON NODEAVL ---

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
