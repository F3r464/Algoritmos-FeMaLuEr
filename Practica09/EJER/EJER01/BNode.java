package EJER01;
import java.util.ArrayList;

public class BNode<E extends Comparable<E>>{
    private static int nextId = 1; //Para identificar cada nodo
    protected int idNode;
    protected ArrayList<E> keys; //Claves guardadas en el nodo
    protected ArrayList<BNode<E>> childs; //Hijos del nodo
    protected int count; //Cantidad de claves usadas
    public BNode(int n){
        this.idNode = nextId++;
        this.count = 0;

        this.keys = new ArrayList<>(n); //Reservamos espacio para claves e hijos
        this.childs = new ArrayList<>(n + 1);

        for (int i = 0; i < n - 1; i++) keys.add(null); //Inicialmente todo está vacío
        for (int i = 0; i < n; i++) childs.add(null);
    }

    public boolean nodeFull(int maxKeys){//¿Ya no entra otra clave?
        return count == maxKeys;
    }

    public boolean nodeEmpty(){//¿El nodo sigue vacío?
        return count == 0;
    }

    public boolean searchNode(E cl, int[] pos){
        int i = 0;
        while (i < count && cl.compareTo(keys.get(i)) > 0) i++; //Buscamos dónde está (o debería estar) la clave
        if (i < count && cl.compareTo(keys.get(i)) == 0){
            pos[0] = i; //Clave encontrada
            return true;
        }
        pos[0] = i; //Posición de inserción
        return false;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Node[").append(idNode).append("] → ("); //Mostrar el nodo y sus claves
        for (int i = 0; i < count; i++) {
            if (i > 0) sb.append(", ");
            sb.append(keys.get(i));
        }
        sb.append(")");
        return sb.toString();
    }
}