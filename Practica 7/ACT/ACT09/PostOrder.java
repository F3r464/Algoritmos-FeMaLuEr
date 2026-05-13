package ACT.ACT09;
import ACT.ACT06.*;

public class PostOrder<E extends Comparable<E>> extends LinkedBST<E>{
    public void postOrder(){
        System.out.print("Post Order: ");
        postOrder(this.root);
    }
    private void postOrder(Node actual){
        if(actual != null){
            postOrder(actual.left);
            postOrder(actual.right);
            System.out.print(actual.dato + " ");
        }
    }
}
