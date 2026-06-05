public class Main{
    public static void main(String[] args){
        Node<Integer> lista=null;
        lista=AgregaFinal.insertarAlFinal(lista,10);
        lista=AgregaFinal.insertarAlFinal(lista,20);
        lista=AgregaFinal.insertarAlFinal(lista,30);

        /* 
        imprimir(lista,5);
        lista=AgregaFinal.AlFinalOtro(lista,40);
        imprimir(lista,5);
    }
    static void imprimir(Node<Integer> head,int limite){
        Node<Integer> actual=head;
        int c=0;

        while(actual!=null && c<limite){
            System.out.print(actual.value+" ");
            actual=actual.next;
            c++;
        }
        System.out.println();
        */
    }
}