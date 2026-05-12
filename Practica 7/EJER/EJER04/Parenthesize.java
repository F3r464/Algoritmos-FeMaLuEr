package EJER.EJER04;
import ACT.ACT06.*;
public class Parenthesize<E extends Comparable<E>> extends LinkedBST<E>{

public void parenthesize(){
    parenthesize(root,0);
}
    private void parenthesize(Node actual,int nivel){
        if(actual==null){
            return;
        }
        for(int i=0;i<nivel;i++){
            System.out.print("   ");
        }

        System.out.println(actual.dato);

        if(actual.left!=null || actual.right!=null){

            for(int i=0;i<nivel;i++){
                System.out.print("   ");
            }
            System.out.println("(");

            parenthesize(actual.left,nivel+1);
            parenthesize(actual.right,nivel+1);

            for(int i=0;i<nivel;i++){
                System.out.print("   ");
            }
            System.out.println(")");
        }
    }
}