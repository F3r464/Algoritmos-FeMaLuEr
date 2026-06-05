package actividad2;
import actividad1.ExceptionIsEmpty;
public class DequeLink<E> implements Deque<E>{
    private Nodo<E> first;
    private Nodo<E> last;

    public DequeLink(){
        first=null;
        last=null;
    }

    public void addFirst(E x){
        Nodo<E> nuevo=new Nodo<>(x);
        if(isEmpty()){
            first=last=nuevo;
        }else{
            nuevo.next=first;
            first=nuevo;
        }
    }

    public void addLast(E x){
        Nodo<E> nuevo=new Nodo<>(x);
        if(isEmpty()){
            first=last=nuevo;
        }else{
            last.next=nuevo;
            last=nuevo;
        }
    }

    public E removeFirst() throws ExceptionIsEmpty{
        if(isEmpty()){
            throw new ExceptionIsEmpty("vacio");
        }
        E dat=first.dato;
        first=first.next;
        if(first==null){
            last=null;
        }
        return dat;
    }

    public E removeLast() throws ExceptionIsEmpty{
        if(isEmpty()){
            throw new ExceptionIsEmpty("Deque vacio");
        }
        if(first==last){
            E val=first.dato;
            first=null;
            last=null;
            return val;
        }
        Nodo<E> actual=first;
        while(actual.next!=last){
            actual=actual.next;
        }
        E dat=last.dato;
        last=actual;
        last.next=null;
        return dat;
    }

    public E getFirst() throws ExceptionIsEmpty{
        if(isEmpty()){
            throw new ExceptionIsEmpty("vacio");
        }
        return first.dato;
    }

    public E getLast() throws ExceptionIsEmpty{
        if(isEmpty()){
            throw new ExceptionIsEmpty("vacio");
        }
        return last.dato;
    }

    public boolean isEmpty(){
        return first==null;
    }

    public String toString(){
        String s="";
        Nodo<E> actual=first;
        while(actual!=null){
            s+=actual.dato+" ";
            actual=actual.next;
        }
        return s;
    }
}