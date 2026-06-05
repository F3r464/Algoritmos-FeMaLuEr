package Extra;

public class AVLTree {

    public NodoAVL raiz;

    private boolean height;

    // =========================================
    // ROTACION SIMPLE IZQUIERDA
    // =========================================
    private NodoAVL rotateSL(
            NodoAVL nodo) {

        NodoAVL hijo =
                nodo.derecha;

        nodo.derecha =
                hijo.izquierda;

        hijo.izquierda =
                nodo;

        return hijo;
    }

    // =========================================
    // ROTACION SIMPLE DERECHA
    // =========================================
    private NodoAVL rotateSR(
            NodoAVL nodo) {

        NodoAVL hijo =
                nodo.izquierda;

        nodo.izquierda =
                hijo.derecha;

        hijo.derecha =
                nodo;

        return hijo;
    }

    // =========================================
    // BALANCE IZQUIERDA
    // =========================================
    private NodoAVL balanceToLeft(
            NodoAVL nodo) {

        NodoAVL hijo =
                nodo.derecha;

        switch (hijo.bf) {

            // DD
            case 1:

                nodo.bf = 0;
                hijo.bf = 0;

                nodo =
                        rotateSL(nodo);

                break;

            // DI
            case -1:

                NodoAVL nieto =
                        hijo.izquierda;

                switch (nieto.bf) {

                    case -1:

                        nodo.bf = 0;
                        hijo.bf = 1;
                        break;

                    case 0:

                        nodo.bf = 0;
                        hijo.bf = 0;
                        break;

                    case 1:

                        nodo.bf = -1;
                        hijo.bf = 0;
                        break;
                }

                nieto.bf = 0;

                nodo.derecha =
                        rotateSR(hijo);

                nodo =
                        rotateSL(nodo);

                break;
        }

        return nodo;
    }

    // =========================================
    // BALANCE DERECHA
    // =========================================
    private NodoAVL balanceToRight(
            NodoAVL nodo) {

        NodoAVL hijo =
                nodo.izquierda;

        switch (hijo.bf) {

            // II
            case -1:

                nodo.bf = 0;
                hijo.bf = 0;

                nodo =
                        rotateSR(nodo);

                break;

            // ID
            case 1:

                NodoAVL nieto =
                        hijo.derecha;

                switch (nieto.bf) {

                    case 1:

                        nodo.bf = 0;
                        hijo.bf = -1;
                        break;

                    case 0:

                        nodo.bf = 0;
                        hijo.bf = 0;
                        break;

                    case -1:

                        nodo.bf = 1;
                        hijo.bf = 0;
                        break;
                }

                nieto.bf = 0;

                nodo.izquierda =
                        rotateSL(hijo);

                nodo =
                        rotateSR(nodo);

                break;
        }

        return nodo;
    }

    // =========================================
    // INSERTAR AVL
    // =========================================
    private NodoAVL insertAVL(
            int codigo,
            String nombre,
            NodoAVL nodo) {

        if (nodo == null) {

            height = true;

            return new NodoAVL(
                    codigo,
                    nombre);
        }

        // REPETIDO
        if (codigo == nodo.codigo) {

            height = false;

            return nodo;
        }

        // DERECHA
        if (codigo > nodo.codigo) {

            nodo.derecha =
                    insertAVL(
                            codigo,
                            nombre,
                            nodo.derecha);

            if (height) {

                switch (nodo.bf) {

                    case -1:

                        nodo.bf = 0;
                        height = false;
                        break;

                    case 0:

                        nodo.bf = 1;
                        break;

                    case 1:

                        nodo =
                                balanceToLeft(
                                        nodo);

                        height = false;
                        break;
                }
            }
        }

        // IZQUIERDA
        else {

            nodo.izquierda =
                    insertAVL(
                            codigo,
                            nombre,
                            nodo.izquierda);

            if (height) {

                switch (nodo.bf) {

                    case 1:

                        nodo.bf = 0;
                        height = false;
                        break;

                    case 0:

                        nodo.bf = -1;
                        break;

                    case -1:

                        nodo =
                                balanceToRight(
                                        nodo);

                        height = false;
                        break;
                }
            }
        }

        return nodo;
    }

    // =========================================
    // REGISTRAR
    // =========================================
    public void registrarLibro(
            int codigo,
            String nombre) {

        height = false;

        raiz =
                insertAVL(
                        codigo,
                        nombre,
                        raiz);
    }

    // =========================================
    // BUSCAR
    // =========================================
    public NodoAVL buscar(
            NodoAVL nodo,
            int codigo) {

        if (nodo == null)
            return null;

        if (codigo == nodo.codigo)
            return nodo;

        if (codigo < nodo.codigo) {

            return buscar(
                    nodo.izquierda,
                    codigo);
        }

        return buscar(
                nodo.derecha,
                codigo);
    }

    // =========================================
    // MINIMO
    // =========================================
    private NodoAVL minimo(
            NodoAVL nodo) {

        while (nodo.izquierda != null) {

            nodo =
                    nodo.izquierda;
        }

        return nodo;
    }

    // =========================================
    // REBALANCE DELETE LEFT
    // =========================================
    private NodoAVL rebalanceLeft(
            NodoAVL nodo) {

        switch (nodo.bf) {

            case -1:

                nodo.bf = 0;
                break;

            case 0:

                nodo.bf = 1;
                height = false;
                break;

            case 1:

                nodo =
                        balanceToLeft(nodo);

                break;
        }

        return nodo;
    }

    // =========================================
    // REBALANCE DELETE RIGHT
    // =========================================
    private NodoAVL rebalanceRight(
            NodoAVL nodo) {

        switch (nodo.bf) {

            case 1:

                nodo.bf = 0;
                break;

            case 0:

                nodo.bf = -1;
                height = false;
                break;

            case -1:

                nodo =
                        balanceToRight(nodo);

                break;
        }

        return nodo;
    }

    // =========================================
    // DELETE AVL
    // =========================================
    private NodoAVL deleteAVL(
            int codigo,
            NodoAVL nodo) {

        if (nodo == null) {

            height = false;

            return null;
        }

        // IZQUIERDA
        if (codigo < nodo.codigo) {

            nodo.izquierda =
                    deleteAVL(
                            codigo,
                            nodo.izquierda);

            if (height) {

                nodo =
                        rebalanceLeft(nodo);
            }
        }

        // DERECHA
        else if (codigo > nodo.codigo) {

            nodo.derecha =
                    deleteAVL(
                            codigo,
                            nodo.derecha);

            if (height) {

                nodo =
                        rebalanceRight(nodo);
            }
        }

        // ENCONTRADO
        else {

            NodoAVL eliminado =
                    nodo;

            // HOJA
            if (eliminado.izquierda == null
                    && eliminado.derecha == null) {

                nodo = null;

                height = true;
            }

            // SOLO DERECHO
            else if (eliminado.izquierda == null) {

                nodo =
                        eliminado.derecha;

                height = true;
            }

            // SOLO IZQUIERDO
            else if (eliminado.derecha == null) {

                nodo =
                        eliminado.izquierda;

                height = true;
            }

            // DOS HIJOS
            else {

                NodoAVL sucesor =
                        minimo(
                                eliminado.derecha);

                nodo.codigo =
                        sucesor.codigo;

                nodo.nombre =
                        sucesor.nombre;

                nodo.derecha =
                        deleteAVL(
                                sucesor.codigo,
                                nodo.derecha);

                if (height) {

                    nodo =
                            rebalanceRight(nodo);
                }
            }
        }

        return nodo;
    }

    // =========================================
    // RETIRAR
    // =========================================
    public void retirarLibro(
            int codigo) {

        height = false;

        raiz =
                deleteAVL(
                        codigo,
                        raiz);
    }

    // =========================================
    // INORDEN
    // =========================================
    public void inOrden(
            NodoAVL nodo,
            StringBuilder sb) {

        if (nodo != null) {

            inOrden(
                    nodo.izquierda,
                    sb);

            sb.append(
                    nodo.codigo
                            + " - "
                            + nodo.nombre
                            + "\n");

            sb.append(
                    "BF: "
                            + nodo.bf
                            + "\n\n");

            inOrden(
                    nodo.derecha,
                    sb);
        }
    }
}