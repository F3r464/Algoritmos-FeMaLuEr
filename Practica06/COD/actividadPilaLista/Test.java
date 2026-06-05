package actividadPilaLista;
import actividad1.ExceptionIsEmpty;

public class Test{
    public static void main(String[]args)throws ExceptionIsEmpty{
        StackLink<Integer>pila = new StackLink<>();
        pila.push(10);
        pila.push(20);
        pila.push(30);
        System.out.println(pila);
        System.out.println(pila.pop());
        System.out.println(pila.top());
        System.out.println(pila);
    }
}