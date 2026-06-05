package ACT.ACT07;
import ACT.ACT06.*;

public class InOrder<E extends Comparable<E>> extends LinkedBST<E>{
    public void inOrder(){
        System.out.print("In Order: ");
        inOrder(root);
    }
    private void inOrder(Node actual){
        if(actual!=null){
            inOrder(actual.left);
            System.out.print(actual.dato+" ");
            inOrder(actual.right);
        }
    }
}