package ejer3Prioridad;
import actividad1.ExceptionIsEmpty;
public class TestEjer3{
    public static void main(String[] args) throws ExceptionIsEmpty{
        // Creamos una cola de prioridad con 3 niveles: 0 (baja), 1 (media), 2 (alta)
        ColaPrioridad<String> cp=new ColaPrioridad<>(3);
        // Encolamos los elementos con sus respectivas prioridades
        cp.enqueue("A",0);  //BAJA
        cp.enqueue("B",2);  //ALTA
        cp.enqueue("C",1);  //MEDIA
        cp.enqueue("D",2);  //ALTA

        while(!cp.isEmpty()){   // mientras la cola NO esté vacía
            System.out.println(cp.dequeue());
        }
    }
}
