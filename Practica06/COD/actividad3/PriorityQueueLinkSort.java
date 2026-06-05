package actividad3; 

import actividad1.ExceptionIsEmpty; 
import actividad2.Nodo; 

public class PriorityQueueLinkSort<E, N extends Comparable<N>> implements PriorityQueue<E, N> { 
    public class EntryNode { 
        public E data; 
        public N priority; 

        EntryNode(E data, N priority) { 
            this.data = data; 
            this.priority = priority; 
        }
    }

    private Nodo<EntryNode> first; 
    private Nodo<EntryNode> last; 

    public PriorityQueueLinkSort(){
        this.first = null; 
        this.last = null; 
    }

  
    public void enqueue(E x, N pr) { 
        EntryNode newEntry = new EntryNode(x, pr); 
        Nodo<EntryNode> nuevoNodo = new Nodo<EntryNode>(newEntry);

        if (isEmpty()) { 
            this.first = nuevoNodo; 
            this.last = nuevoNodo; 
        } 
       
        else if (pr.compareTo(this.first.dato.priority) >= 0){ 
            nuevoNodo.next = this.first; 
            this.first = nuevoNodo; 
        } 

        else { 
            Nodo<EntryNode> actual = this.first;
            while (actual.next != null && actual.next.dato.priority.compareTo(pr) > 0) { 
                actual = actual.next; 
            }
            nuevoNodo.next = actual.next;
            actual.next = nuevoNodo; 

            if (nuevoNodo.next == null) { 
                this.last = nuevoNodo; 
            }
        }
    }

    @Override
    public E dequeue() throws ExceptionIsEmpty { 
        if (isEmpty()) 
            throw new ExceptionIsEmpty("Queue is empty"); 
        E aux = this.first.dato.data; 
        this.first = this.first.next; 
        if (this.first == null) 
            this.last = null; 
        return aux; 
    }

    @Override
    public E front() throws ExceptionIsEmpty { 
        if (isEmpty()) 
            throw new ExceptionIsEmpty("Queue is empty"); 
        return this.first.dato.data; 
    }

    @Override
    public E back() throws ExceptionIsEmpty { 
        if (isEmpty()) // Valida la existencia de datos
            throw new ExceptionIsEmpty("Queue is empty"); 
        return this.last.dato.data; 
    }

    @Override
    public boolean isEmpty() { 
        return this.first == null; 
    }

    @Override
    public String toString() { 
        if (isEmpty()) return ""; 
        String s = ""; 
        Nodo<EntryNode> actual = this.first; 
        while (actual != null) { 
            s += "(" + actual.dato.data + ", P:" + actual.dato.priority + ") "; 
            actual = actual.next; 
        }
        return s; 
    }
}