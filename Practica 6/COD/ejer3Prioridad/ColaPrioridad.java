package ejer3Prioridad;
import actividad2.DequeLink;
import actividad1.ExceptionIsEmpty;

public class ColaPrioridad<E>{
    // Arreglo de colas (DequeLink), una por cada nivel de prioridad
    // colas[0] = prioridad baja, colas[2] = prioridad alta (con 3 niveles)

    private DequeLink<E>[] colas;
    private int niveles;

// @SuppressWarnings("unchecked"): silencia la advertencia del compilador

    @SuppressWarnings("unchecked")
    public ColaPrioridad(int niveles){
        // Guardamos el número de niveles en el atributo de la clase

        this.niveles=niveles;

        colas=new DequeLink[niveles];
        // Usamos DequeLink[] sin <E> porque Java no admite new DequeLink<E>[n]
        // Inicializamos cada posición del arreglo con una DequeLink vacía
        for(int i=0;i<niveles;i++){
            colas[i]=new DequeLink<>();
        }
    }

    // enqueue FIFO
        // Agrega el elemento x en la cola del nivel de prioridad indicado

    public void enqueue(E x,int prioridad){
        colas[prioridad].addLast(x);
         // addLast agrega al FINAL de la cola interna del nivel "prioridad"
    }

    // dequeue
     // Saca y devuelve el elemento de MAYOR prioridad disponible
    // Lanza ExceptionIsEmpty si todas las colas están vacías
    public E dequeue() throws ExceptionIsEmpty{
         // Recorremos desde el nivel MÁS ALTO (niveles-1) hasta el 0
        for(int i=niveles-1;i>=0;i--){
            if(!colas[i].isEmpty()){
                // Sí tiene → removeFirst saca el primer elemento (FIFO)
                // y lo devuelve como resultado del dequeue
                return colas[i].removeFirst();
            }
        }

        throw new ExceptionIsEmpty("Cola vacia");
    }
    //isEmpty
      // Devuelve true SOLO si todos los niveles están vacíos
    public boolean isEmpty(){
        for(int i=0;i<niveles;i++){ // Revisamos cada nivel de prioridad, del 0 al más alto
            if(!colas[i].isEmpty()) return false;
        }
        return true;
    }
}
