public class ListaEnlazada<T> 
    // Apunta al primer nodo de la list
    private NodoLista<T> cabeza
    // Guarda la cantidad de elementos en la list
    private int tamanio
    // Constructo
    public ListaEnlazada() 
        this.cabeza = null;   // No hay nodos al inici
        this.tamanio = 0;     // Tamaño empieza en 
    
    //Agregar un elemento al final de la list
    public void agregar(T dato) 
        // Se crea un nuevo nodo con el dat
        NodoLista<T> nuevo = new NodoLista<>(dato)
        // Si la lista está vací
        if (cabeza == null) 
            cabeza = nuevo; // El nuevo nodo será la cabez
        } else 
            // Si ya hay elementos, recorremos la list
            NodoLista<T> actual = cabeza
            // Avanza hasta el último nodo (donde siguiente es null
            while (actual.siguiente != null) 
                actual = actual.siguiente
            
            // El último nodo ahora apunta al nuevo nod
            actual.siguiente = nuevo
        
        // Aumenta el tamaño de la list
        tamanio++
    
    //  Eliminar un nodo por su dato (usa equals para comparar
    public boolean eliminar(T dato) 
        // Si la lista está vacía, no hay nada que elimina
        if (cabeza == null) return false
        // dato está en la cabez
        if (cabeza.dato.equals(dato)) 
            cabeza = cabeza.siguiente; // La cabeza avanza al siguiente nod
            tamanio--;
            return true;
        
        // Recorremos la lista desde la cabez
        NodoLista<T> actual = cabeza
        while (actual.siguiente != null) 
            // Si encontramos el dato en el siguiente nod
            if (actual.siguiente.dato.equals(dato)) 
                // Saltamos ese nodo (lo eliminamos
                actual.siguiente = actual.siguiente.siguiente;
                tamanio--;
                return true;
            } 
                        actual = actual.siguiente; // Avanza al siguiente nodo 
        } 
        // Si no se encontró el dato 
        return false; 
    } 
    // Verificar si un dato existe en la lista 
    public boolean contiene(T dato) { 
        NodoLista<T> actual = cabeza; 
        while (actual != null) { 
            // Si encuentra el dato, retorna true 
            if (actual.dato.equals(dato)) return true; 
            actual = actual.siguiente; // Avanza al siguiente nodo 
        } 
        // Si no lo encontró 
        return false; 
    } 
    //  Invertir la lista (cambia el orden completamente) 
    public void invertir() { 
        NodoLista<T> anterior = null; // Nodo previo (empieza en null) 
        NodoLista<T> actual = cabeza; // Empieza desde la cabeza 
        NodoLista<T> siguiente;       // Para guardar temporalmente el siguiente nodo 
        while (actual != null) { 
            // Guardamos el siguiente nodo antes de romper el enlace 
            siguiente = actual.siguiente; 
            // Invertimos el enlace (apunta hacia atrás) 
            actual.siguiente = anterior; 
            // Avanzamos los punteros 
            anterior = actual; 
            actual = siguiente; 
        } 
        // El último nodo procesado 
        cabeza = anterior; 
    } 

} 