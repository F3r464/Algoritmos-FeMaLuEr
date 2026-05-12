package EJER.EJER03;
import actividad1.QueueArray;
import ACT.ACT06.*;
public class LinkedBSTit<E extends Comparable<E>> extends LinkedBST<E> {
    public int areaBST(){
        if(root==null){
            return 0;
        }
        QueueArray<Node> cola=new QueueArray<>(100);
        int hojas=0;
        int altura=0;
        try{
            cola.enqueue(root);
            while(!cola.isEmpty()){
                int nivel=cola.size();
                for(int i=0;i<nivel;i++){
                    Node actual=cola.dequeue();
                    if(actual.left==null && actual.right==null){
                        hojas++;
                    }
                    if(actual.left!=null){
                        cola.enqueue(actual.left);
                    }
                    if(actual.right!=null){
                        cola.enqueue(actual.right);
                    }
                }
                altura++;
            }
        }catch(Exception e){}
        return hojas*altura;
    }
    public void drawBST(){
        drawRec(root,0);
    }
    private void drawRec(Node actual,int nivel){
        if(actual!=null){
            drawRec(actual.right,nivel+1);
            for(int i=0;i<nivel;i++){
                System.out.print("   ");
            }
            System.out.println(actual.dato);
            drawRec(actual.left,nivel+1);
        }
    }
}