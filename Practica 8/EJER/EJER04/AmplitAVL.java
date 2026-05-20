package EJER.EJER04;

import ACT.ACT03.AVLtree;

public class AmplitAVL<E extends Comparable<E>> extends AVLtree<E>{
public int height(){

    return heightRec(root);
}

private int heightRec(Node actual){

    if(actual==null){
        return 0;
    }

    int izq=heightRec(actual.left);
    int der=heightRec(actual.right);

    return Math.max(izq,der)+1;
}

public void amplitudRec(){

    int h=height();

    System.out.print("Amplitud: ");

    for(int i=1;i<=h;i++){
        printLevel(root,i);
    }

    System.out.println();
}

//imprime un nivel
private void printLevel(Node actual,int nivel){

    if(actual==null){
        return;
    }

    if(nivel==1){
        System.out.print(actual.dato+" ");
    }
    else{
        printLevel(actual.left,nivel-1);
        printLevel(actual.right,nivel-1);
    }
}
}
