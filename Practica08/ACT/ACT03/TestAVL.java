package ACT.ACT03;


public class TestAVL{
    public static void main(String[] args)
    throws Exception{
        AVLtree<Integer> avl=new AVLtree<>();
        avl.insert(30);
        avl.insert(20);
        avl.insert(10);
        avl.insert(40);
        avl.insert(50);
        avl.preOrder();
    }
}