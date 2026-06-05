package actividad1;

public class TestQueue{
    public static void main(String[] args) throws ExceptionIsEmpty{
        QueueArray<Integer>colaInt = new QueueArray<>(5);
        colaInt.enqueue(10);
        colaInt.enqueue(20);
        colaInt.enqueue(30);
        System.out.println(colaInt);
        System.out.println(colaInt.dequeue());
        System.out.println(colaInt.front());
        System.out.println(colaInt);

        QueueArray<String> colaStr = new QueueArray<>(5);
        colaStr.enqueue("Ana");
        colaStr.enqueue("Pedro");
        colaStr.enqueue("Juan");
        System.out.println(colaStr);
        System.out.println(colaStr.dequeue());
        System.out.println(colaStr.front());
        System.out.println(colaStr);
    }
}