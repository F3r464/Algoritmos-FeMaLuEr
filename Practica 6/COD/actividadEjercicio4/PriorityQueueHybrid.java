package actividadEjercicio4; 

import actividad1.ExceptionIsEmpty; 
import actividad3.PriorityQueueLinkSort; 

public class PriorityQueueHybrid<E, N extends Comparable<N>> { 
    
    private PriorityQueueLinkSort<E, N>[] subQueues; 
    private int levels; 

    @SuppressWarnings("unchecked")
    public PriorityQueueHybrid(int levels) { 
        this.levels = levels; 
        this.subQueues = (PriorityQueueLinkSort<E, N>[]) new PriorityQueueLinkSort[levels]; 
        for (int i = 0; i < levels; i++) { 
            subQueues[i] = new PriorityQueueLinkSort<E, N>(); 
        }
    }

    public void enqueue(E x, int priorityLevel, N secondaryValue) { 
        if (priorityLevel < 0 || priorityLevel >= levels) { 
            throw new IllegalArgumentException("Nivel de prioridad inválido"); 
        }
        subQueues[priorityLevel].enqueue(x, secondaryValue); 
    }

    public E dequeue() throws ExceptionIsEmpty { 
        for (int i = levels - 1; i >= 0; i--) { 
            if (!subQueues[i].isEmpty()) { 
                return subQueues[i].dequeue(); 
            }
        }
        throw new ExceptionIsEmpty("La cola híbrida se encuentra vacía");
    }

    public boolean isEmpty() { 
        for (int i = 0; i < levels; i++) { 
            if (!subQueues[i].isEmpty()) { 
                return false; 
            }
        }
        return true; 
    }

    @Override
    public String toString() { 
        String s = ""; 
        for (int i = levels - 1; i >= 0; i--) { 
            if (!subQueues[i].isEmpty()) { 
                s += "Nivel " + i + ": " + subQueues[i].toString() + "\n"; 
            }
        }
        return s; 
    }
}