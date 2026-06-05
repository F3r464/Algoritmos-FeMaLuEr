public class AgregaFinal {
    public static <T> Node<T> insertarAlFinal(Node<T> head,T valor){
        Node<T> nuevo=new Node<>(valor);
        if(head==null){
            return nuevo; //actualizando head
        }

        Node<T> actual=head;
        while(actual.next!=null){
            actual=actual.next; //recorriendo hasta el final
        }

        actual.next=nuevo; //insertando al final

        return head; //devuelve la cabeza
    }


    public static <T> Node<T> AlFinalOtro(Node<T> head,T valor){

        if(head==null){
            return new Node<>(valor); //crea nuevo si no hay 
        }

        head.value=valor; //actualiza el head con el nuevo valor

        Node<T> actual=head;
        while(actual.next!=null){
            actual=actual.next;
        }

        actual.next=head; // lo mando al final

        return head;//retorna la cabeza
    }
}
