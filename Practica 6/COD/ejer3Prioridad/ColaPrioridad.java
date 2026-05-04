package ejer3Prioridad;
import actividad2.DequeLink;
import actividad1.ExceptionIsEmpty;

public class ColaPrioridad<E>{

    private DequeLink<E>[] colas;
    private int niveles;

    @SuppressWarnings("unchecked")
    public ColaPrioridad(int niveles){
        this.niveles=niveles;
        colas=new DequeLink[niveles];

        for(int i=0;i<niveles;i++){
            colas[i]=new DequeLink<>();
        }
    }

    // enqueue
    public void enqueue(E x,int prioridad){
        colas[prioridad].addLast(x);
    }

    // dequeue
    public E dequeue() throws ExceptionIsEmpty{

        for(int i=niveles-1;i>=0;i--){
            if(!colas[i].isEmpty()){
                return colas[i].removeFirst();
            }
        }

        throw new ExceptionIsEmpty("Cola vacia");
    }

    public boolean isEmpty(){
        for(int i=0;i<niveles;i++){
            if(!colas[i].isEmpty()) return false;
        }
        return true;
    }
}