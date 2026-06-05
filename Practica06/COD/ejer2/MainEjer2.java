package ejer2;
import actividad1.ExceptionIsEmpty;
import actividad1.QueueArray;
public class MainEjer2{
    public static void main(String[] args){
        QueueArray<Integer> cola=new QueueArray<>(5);
        //Encola los clientes: 101, 102, 103, 104, 10
        cola.enqueue(101);
        cola.enqueue(102);
        cola.enqueue(103);
        cola.enqueue(104);
        cola.enqueue(105);
        try{
            cola.enqueue(106);
            //Intenta encolar un cliente más (106) → debe mostrar que la cola está llena.
        }catch(Exception e){
            System.out.println("Cola llena");
        }

        try{ //Desencola 2 clientes.
            System.out.println("Atendiendo cliente: "+cola.dequeue());
            System.out.println("Atendiendo cliente: "+cola.dequeue());
            //Muestra el cliente que está al frente.
            System.out.println("Cliente en frente: "+cola.front());
            //Encola 2 clientes más: 106, 107 (aquí se verifica el comportamiento circular).
            cola.enqueue(106);
            cola.enqueue(107);
            while(true){
                //Desencola todos los elementos hasta que la cola quede vacía.
                System.out.println("Atendiendo cliente: "+cola.dequeue());
            }

        }catch(ExceptionIsEmpty e){
            //Intenta desencolar uno más → debe mostrar que la cola está vacía.
            System.out.println("Cola vacia");
        }
    }
}