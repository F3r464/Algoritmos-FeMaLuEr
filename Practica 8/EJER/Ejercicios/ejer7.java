package Ejercicios;

//  EJERCICIO 7 – Árbol AVL: Inserción y Eliminación con Rotaciones

public class ejer7 {

    //  CLASE NODO AVL
   
    static class NodoAVL {
        int dato;
        int altura;
        NodoAVL izq, der;

        // Constructor: todo nodo nuevo es hoja, altura = 1
        NodoAVL(int dato) {
            this.dato   = dato;
            this.altura = 1;
        }

        // Muestra dato y factor de equilibrio del nodo
        @Override
        public String toString() {
            int bf = altura(der) - altura(izq);
            return dato + "(bf=" + bf + ")";
        }

        // Devuelve la altura de un nodo, 0 si es null
        static int altura(NodoAVL n) {
            return n == null ? 0 : n.altura;
        }

        // Devuelve el factor de equilibrio de un nodo
        static int balance(NodoAVL n) {
            return n == null ? 0 : altura(n.der) - altura(n.izq);
        }
    }

    //  CLASE ÁRBOL AVL
    static class AVLTree {

        NodoAVL raiz;
        //  MÉTODO: actualizarAltura
        //  Recalcula la altura del nodo según sus hijos
        private void actualizarAltura(NodoAVL n) {
            n.altura = 1 + Math.max(NodoAVL.altura(n.izq), NodoAVL.altura(n.der));
        }

        //  MÉTODO: rotarIzquierda (RSL)
        //  Caso Derecha-Derecha: sube el hijo derecho como nueva raíz
        private NodoAVL rotarIzquierda(NodoAVL x) {
            NodoAVL y  = x.der;
            NodoAVL t2 = y.izq;

            // Realizar la rotación
            y.izq = x;
            x.der = t2;

            // Actualizar alturas después de rotar
            actualizarAltura(x);
            actualizarAltura(y);

            System.out.println("    [RSL] Rotacion simple IZQUIERDA en nodo "
                    + x.dato + " -> nueva raiz: " + y.dato);
            return y;
        }

        //  MÉTODO: rotarDerecha (RSR)
        //  Caso Izquierda-Izquierda: sube el hijo izquierdo como nueva raíz
        private NodoAVL rotarDerecha(NodoAVL x) {
            NodoAVL y  = x.izq;
            NodoAVL t2 = y.der;

            // Realizar la rotación
            y.der = x;
            x.izq = t2;

            // Actualizar alturas después de rotar
            actualizarAltura(x);
            actualizarAltura(y);

            System.out.println("    [RSR] Rotacion simple DERECHA en nodo "
                    + x.dato + " -> nueva raiz: " + y.dato);
            return y;
        }

        //  MÉTODO: balancear
        //  Detecta el tipo de desbalance y aplica la rotación correcta.
        //  Usa switch para los valores exactos del factor de equilibrio
        private NodoAVL balancear(NodoAVL nodo) {

            // Recalcular altura antes de revisar el balance
            actualizarAltura(nodo);

            int bf = NodoAVL.balance(nodo);

            // switch sobre el factor de balance del nodo actual
            switch (bf) {

                // Desbalance hacia la DERECHA (bf = +2)
                case 2:
                    // switch sobre el bf del hijo derecho para decidir rotación
                    switch (NodoAVL.balance(nodo.der)) {
                        case 1:
                        case 0:
                            // Caso Derecha-Derecha: rotación simple izquierda
                            return rotarIzquierda(nodo);
                        case -1:
                            // Caso Derecha-Izquierda: rotación doble (RSR en hijo + RSL en nodo)
                            System.out.println("    [RDI] Caso Derecha-Izquierda en nodo " + nodo.dato);
                            nodo.der = rotarDerecha(nodo.der);
                            return rotarIzquierda(nodo);
                    }
                    break;

                // Desbalance hacia la IZQUIERDA (bf = -2)
                case -2:
                    // switch sobre el bf del hijo izquierdo para decidir rotación
                    switch (NodoAVL.balance(nodo.izq)) {
                        case -1:
                        case 0:
                            // Caso Izquierda-Izquierda: rotación simple derecha
                            return rotarDerecha(nodo);
                        case 1:
                            // Caso Izquierda-Derecha: rotación doble (RSL en hijo + RSR en nodo)
                            System.out.println("    [RID] Caso Izquierda-Derecha en nodo " + nodo.dato);
                            nodo.izq = rotarIzquierda(nodo.izq);
                            return rotarDerecha(nodo);
                    }
                    break;

                // bf = -1, 0 o 1: árbol balanceado, no se hace nada
                default:
                    break;
            }

            return nodo;
        }

        //  MÉTODO: insertar (público)
        public void insertar(int dato) {
            raiz = insertar(raiz, dato);
        }

        //  MÉTODO: insertar (privado, recursivo)
        private NodoAVL insertar(NodoAVL nodo, int dato) {

            // Caso base: posición encontrada, crear nodo hoja
            if (nodo == null) return new NodoAVL(dato);

            // Comparar el dato con el nodo actual para decidir dirección
            if (dato < nodo.dato) {
                // Bajar por la izquierda
                nodo.izq = insertar(nodo.izq, dato);
            } else if (dato > nodo.dato) {
                // Bajar por la derecha
                nodo.der = insertar(nodo.der, dato);
            } else {
                // Duplicado: no se permite
                System.out.println("    [!] Dato " + dato + " ya existe, se ignora.");
                return nodo;
            }

            // Al volver hacia la raíz, balancear cada nodo afectado
            return balancear(nodo);
        }

        //  MÉTODO: eliminar (público)
        public void eliminar(int dato) {
            raiz = eliminar(raiz, dato);
        }

        //  MÉTODO: eliminar (privado, recursivo)
        //  Elimina siguiendo los 3 casos BST y balancea al volver
        private NodoAVL eliminar(NodoAVL nodo, int dato) {

            // Si el nodo es null, el dato no existe
            if (nodo == null) {
                System.out.println("    [!] Dato " + dato + " no encontrado.");
                return null;
            }

            // Navegar el árbol para encontrar el nodo a eliminar
            if (dato < nodo.dato) {
                // Buscar a la izquierda
                nodo.izq = eliminar(nodo.izq, dato);
            } else if (dato > nodo.dato) {
                // Buscar a la derecha
                nodo.der = eliminar(nodo.der, dato);
            } else {

                // Nodo encontrado: aplicar el caso BST correspondiente
                // usando switch sobre la cantidad de hijos

                // Determinar cuántos hijos tiene el nodo
                boolean tieneIzq = nodo.izq != null;
                boolean tieneDer = nodo.der != null;

                int caso = tieneIzq && tieneDer ? 3 : (tieneIzq || tieneDer ? 2 : 1);

                switch (caso) {

                    case 1:
                        // Caso 1 – Nodo hoja: eliminar directamente
                        System.out.println("   CASO 1 Nodo hoja " + dato + " eliminado.");
                        return null;

                    case 2:
                        // Caso 2 – Un solo hijo: reemplazar por ese hijo
                        if (!tieneIzq) {
                            System.out.println("   CASO 2 Nodo " + dato
                                    + " reemplazado por hijo derecho " + nodo.der.dato);
                            return nodo.der;
                        } else {
                            System.out.println("  CASO 2 Nodo " + dato
                                    + " reemplazado por hijo izquierdo " + nodo.izq.dato);
                            return nodo.izq;
                        }

                    case 3:
                        // Caso 3 – Dos hijos: reemplazar por el sucesor inorden
                        // (el mínimo del subárbol derecho)
                        NodoAVL sucesor = minimoNodo(nodo.der);
                        System.out.println("    CASO 3 Nodo " + dato
                                + " reemplazado por sucesor inorden " + sucesor.dato);
                        nodo.dato = sucesor.dato;
                        nodo.der  = eliminar(nodo.der, sucesor.dato);
                        break;
                }
            }

            // Rebalancear en el camino de vuelta hacia la raíz
            return balancear(nodo);
        }

        //  MÉTODO: minimoNodo
        //  Devuelve el nodo con el valor mínimo de un subárbol
        //  (el más a la izquierda), usado para hallar el sucesor inorden
        
        private NodoAVL minimoNodo(NodoAVL nodo) {
            // Bajar siempre a la izquierda hasta el final
            while (nodo.izq != null) nodo = nodo.izq;
            return nodo;
        }

        //  MÉTODO: inorden
        //  Recorre el árbol izq → raíz → der (orden ascendente
        public void inorden() {
            System.out.print("Inorden: ");
            inorden(raiz);
            System.out.println();
        }

        private void inorden(NodoAVL n) {
            if (n != null) {
                inorden(n.izq);
                System.out.print(n.dato + " ");
                inorden(n.der);
            }
        }

        //  MÉTODO: preorden
        //  Recorre el árbol raíz → izq → der
        public void preorden() {
            System.out.print("Preorden: ");
            preorden(raiz);
            System.out.println();
        }

        private void preorden(NodoAVL n) {
            if (n != null) {
                System.out.print(n.dato + " ");
                preorden(n.izq);
                preorden(n.der);
            }
        }

        //  MÉTODO: mostrar
        //  El subárbol derecho aparece arriba, el izquierdo abajo
        public void mostrar() {
            mostrar(raiz, "", true);
        }

        private void mostrar(NodoAVL n, String prefijo, boolean esRaiz) {
            if (n != null) {
                System.out.println(prefijo + (esRaiz ? "L-- " : "|-- ") + n);
                if (n.izq != null || n.der != null) {
                    mostrar(n.der, prefijo + "    ", false);
                    mostrar(n.izq, prefijo + "    ", false);
                }
            }
        }

        // Devuelve la altura total del árbol
        public int altura() {
            return NodoAVL.altura(raiz);
        }
    }

    //  MAIN 
    public static void main(String[] args) {

        separador("arbol -AVL: Insercion y Eliminacion con Rotaciones");

        AVLTree avl = new AVLTree();

       
        //  BLOQUE 1: INSERCIONES
        //  Secuencia que provoca los 4 tipos de rotación
        
        separador("BLOQUE 1 - INSERCIONES");

        int[] claves = {50, 30, 70, 20, 40, 60, 80, 10, 25, 35, 45, 65, 75, 90, 5, 15, 28, 55};

        // for: recorre todas las claves a insertar
        for (int k : claves) {
            System.out.println("\n>> Insertar: " + k);
            avl.insertar(k);
            avl.mostrar();
            avl.inorden();
            System.out.println("   Altura del arbol: " + avl.altura());
        }

        //  BLOQUE 2: ELIMINACIONES
        //  Cubre los 3 casos BST y provoca rotaciones
        
        separador("BLOQUE 2 - ELIMINACIONES");

        int[] aEliminar = {10, 80, 50, 30, 65, 20};

        // for: recorre todas las claves a eliminar
        for (int k : aEliminar) {
            System.out.println("\n>> Eliminar: " + k);
            avl.eliminar(k);
            avl.mostrar();
            avl.inorden();
            System.out.println("   Altura del arbol: " + avl.altura());
        }

        //  BLOQUE 3: PARTE FINAL
        separador("ESTADO FINAL DEL ARBOL");
        avl.mostrar();
        avl.inorden();
        avl.preorden();
        System.out.println("Altura final: " + avl.altura());
        System.out.println("Raiz: " + (avl.raiz != null ? avl.raiz.dato : "vacio"));
    }

    // Imprime un separador visual con título
    static void separador(String titulo) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("  " + titulo);
        System.out.println("=".repeat(60));
    }
}
