package ACT.ACT031;

import ACT.ACT03.AVLtree;

public class AVLtree31<E extends Comparable<E>> extends AVLtree<E> {

    class NodeAVL extends Node {
        protected int bf; // Factor de equilibrio

        public NodeAVL(E data) {
            super(data);
            this.bf = 0; // Inicia equilibrado (nodo hoja)
        }

        @Override
        public String toString() {
            return dato.toString() + " (bf: " + bf + ")";
        }
    }

    private boolean altura; // Indicador de cambio de altura
}
