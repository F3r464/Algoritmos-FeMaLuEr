package actividad2;
import actividad1.ExceptionIsEmpty;
public class TestDeque{
    public static void main(String[] args) throws ExceptionIsEmpty{
        DequeLink<Integer> d=new DequeLink<>();
        d.addFirst(10);
        d.addLast(20);
        d.addFirst(5);
        d.addLast(30);
        System.out.println("deque: "+d);
        System.out.println("first: "+d.getFirst());
        System.out.println("last: "+d.getLast());
        System.out.println("removeFirst: "+d.removeFirst());
        System.out.println("removeLast: "+d.removeLast());
        System.out.println("deque: "+d);
        d.removeFirst();
        d.removeFirst();
        System.out.println("exception: "+d.isEmpty());
    }
}