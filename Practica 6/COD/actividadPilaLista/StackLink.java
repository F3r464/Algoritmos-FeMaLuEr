package actividadPilaLista;
import actividad1.ExceptionIsEmpty;

class StackLink<E> implements Stack<E>{
    private Node<E> top;
    public StackLink(){
        this.top = null;
    }

    public void push(E x){
        Node<E>n = new Node<>(x);
        n.setNext(top);
        top = n;
    }

    public E pop() throws ExceptionIsEmpty{
        if (isEmpty()) throw new ExceptionIsEmpty("");
        E aux = top.getData();
        top = top.getNext();
        return aux;
    }

    public E top() throws ExceptionIsEmpty{
        if (isEmpty()) throw new ExceptionIsEmpty("");
        return top.getData();
    }

    public boolean isEmpty(){
        return top == null;
    }

    public String toString(){
        String s = "";
        Node<E>aux = top;
        while (aux != null){
            s += aux.getData()+ " ";
            aux = aux.getNext();
        }
        return s;
    }
}