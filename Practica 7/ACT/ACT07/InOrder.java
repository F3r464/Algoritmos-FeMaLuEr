package ACT.ACT07;
import ACT.ACT06.*;
public class InOrder{
    private Node root;
    public InOrder(){
        this.root = null;
    }
    public void inOrder(){
        System.out.print(" In Order: ");
        inOrder(this.root);
    }
    private void inOrder(Node actual){
        if (actual != null) {
            inOrder(actual.left);
            System.out.print(actual.dato + " ");
            inOrder(actual.right);
        }
    }
}
