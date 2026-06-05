package EJER.EJER01;

//Inserción, eliminación, búsqueda, rotaciones y balanceo.
public class ArbolAVL {
    private Nodo raiz;
    //Si el nodo no existe devuelve 0, si existe devuelve su altura.
    private int obtenerAltura(Nodo nodo){
        return (nodo == null) ? 0 : nodo.altura;
    }
    //Recalcula la altura según cuál hijo es más alto.
    private void actualizarAltura(Nodo nodo){
        nodo.altura = 1 +
                Math.max(obtenerAltura(nodo.izquierdo),
                         obtenerAltura(nodo.derecho));
    }
    //Calculamos el FE.
    private int factorEquilibrio(Nodo nodo){
        return (nodo == null) ? 0 : obtenerAltura(nodo.derecho) - obtenerAltura(nodo.izquierdo);
    }
    //ROTACIÓN SIMPLE DERECHA (RSR)
    private Nodo rotarDerecha(Nodo nodo){
        Nodo nuevaRaiz = nodo.izquierdo;
        Nodo temporal  = nuevaRaiz.derecho;
        nuevaRaiz.derecho = nodo;
        nodo.izquierdo = temporal;
        actualizarAltura(nodo);
        actualizarAltura(nuevaRaiz);
        System.out.println("    → RSR aplicada en nodo " + nodo.ticket);
        return nuevaRaiz;
    }
    //ROTACIÓN SIMPLE IZQUIERDA (RSL)
    private Nodo rotarIzquierda(Nodo nodo){
        Nodo nuevaRaiz = nodo.derecho;
        Nodo temporal  = nuevaRaiz.izquierdo;
        nuevaRaiz.izquierdo = nodo;
        nodo.derecho = temporal;
        actualizarAltura(nodo);
        actualizarAltura(nuevaRaiz);
        System.out.println("    → RSL aplicada en nodo " + nodo.ticket);
        return nuevaRaiz;
    }
    //BALANCEO AUTOMÁTICO AVL.
    private Nodo balancear(Nodo nodo){
        actualizarAltura(nodo);
        int fe = factorEquilibrio(nodo);
        //Desbalance hacia derecha.
        if (fe == 2){
            //Caso DD.
            if (factorEquilibrio(nodo.derecho) >= 0){
                System.out.println("    Caso DD en nodo " + nodo.ticket);

                return rotarIzquierda(nodo);

            } else {
                //Caso DI.
                System.out.println("    Caso DI en nodo " + nodo.ticket);

                nodo.derecho = rotarDerecha(nodo.derecho);
                return rotarIzquierda(nodo);
            }
        }
        //Desbalance hacia izquierda.
        if (fe == -2){
            //Caso II.
            if (factorEquilibrio(nodo.izquierdo) <= 0){
                System.out.println("    Caso II en nodo " + nodo.ticket);
                return rotarDerecha(nodo);
            } else {
                //Caso ID.
                System.out.println("    Caso ID en nodo " + nodo.ticket);
                nodo.izquierdo = rotarIzquierda(nodo.izquierdo);
                return rotarDerecha(nodo);
            }
        }
        return nodo;
    }
    //INSERCIÓN.
    public void insertar(int ticket){
        raiz = insertar(raiz, ticket);
    }
    private Nodo insertar(Nodo nodo, int ticket){
        //Insertamos como BST normal.
        if (nodo == null){
            return new Nodo(ticket);
        }
        if (ticket < nodo.ticket){
            nodo.izquierdo = insertar(nodo.izquierdo, ticket);
        } else if (ticket > nodo.ticket){
            nodo.derecho = insertar(nodo.derecho, ticket);
        } else {
            System.out.println("    El ticket ya existe.");
            return nodo;
        }
        //Balanceamos.
        return balancear(nodo);
    }
    //BÚSQUEDA.
    public boolean buscar(int ticket){
        return buscar(raiz, ticket);
    }
    private boolean buscar(Nodo nodo, int ticket){
        if (nodo == null) return false;
        if (ticket == nodo.ticket) return true;
        if (ticket < nodo.ticket){
            return buscar(nodo.izquierdo, ticket);
        } else {
            return buscar(nodo.derecho, ticket);
        }
    }
    //ELIMINACIÓN.
    public void eliminar(int ticket){
        raiz = eliminar(raiz, ticket);
    }
    private Nodo eliminar(Nodo nodo, int ticket){
        if (nodo == null){
            System.out.println("    El ticket no existe.");
            return null;
        }
        if (ticket < nodo.ticket){
            nodo.izquierdo = eliminar(nodo.izquierdo, ticket);
        } else if (ticket > nodo.ticket){
            nodo.derecho = eliminar(nodo.derecho, ticket);
        } else {
            //Caso 1: hoja.
            if (nodo.izquierdo == null && nodo.derecho == null){
                System.out.println("    Caso 1: nodo hoja eliminado.");
                return null;
            }
            //Caso 2: un hijo.
            if (nodo.izquierdo == null){
                System.out.println("    Caso 2: reemplazado por hijo derecho.");

                return nodo.derecho;
            }
            if (nodo.derecho == null){
                System.out.println("    Caso 2: reemplazado por hijo izquierdo.");
                return nodo.izquierdo;
            }
            //Caso 3: dos hijos.
            Nodo sucesor = encontrarMinimo(nodo.derecho);
            System.out.println("    Caso 3: sucesor inorden = "
                    + sucesor.ticket);
            nodo.ticket = sucesor.ticket;
            nodo.derecho = eliminar(nodo.derecho, sucesor.ticket);
        }
        return balancear(nodo);
    }
    //Buscamos el menor del subárbol derecho.
    private Nodo encontrarMinimo(Nodo nodo){
        while (nodo.izquierdo != null){
            nodo = nodo.izquierdo;
        }
        return nodo;
    }
    //RECORRIDO INORDEN.
    public void inorden(){
        System.out.print("  Inorden: ");
        inorden(raiz);
        System.out.println();
    }
    private void inorden(Nodo nodo){
        if (nodo == null) return;
        inorden(nodo.izquierdo);
        System.out.print(nodo.ticket + " ");
        inorden(nodo.derecho);
    }
    //MOSTRAR ÁRBOL VISUALMENTE.
    public void mostrarArbol(){
        System.out.println("\n  Árbol actual:\n");
        mostrarArbol(raiz, 0);
    }
    //Impresión visual tipo árbol real.
    private void mostrarArbol(Nodo nodo, int espacio){
        if (nodo == null) return;
        espacio += 8;
        //Mostramos primero derecha.
        mostrarArbol(nodo.derecho, espacio);
        //Espacios.
        System.out.println();
        for (int i = 8; i < espacio; i++){
            System.out.print(" ");
        }
        //Nodo actual.
        System.out.println(nodo.ticket
                + "(fe=" + factorEquilibrio(nodo) + ")");
        //Mostramos izquierda.
        mostrarArbol(nodo.izquierdo, espacio);
    }
}