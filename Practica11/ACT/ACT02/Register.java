package ACT02;
/**
 * Clase genérica que representa un registro almacenado en la tabla hash.
 * Cada registro contiene una clave entera y un valor asociado.
 */
public class Register<E> implements Comparable<Register<E>>{
    // Clave utilizada para identificar el registro.
    private int key;

    // Valor almacenado asociado a la clave.
    private E value;

    //Crea un nuevo registro con una clave y un valor.
    public Register(int key, E value){
        this.key = key;
        this.value = value;
    }

    //Retorna la clave del registro.
    public int getKey(){
        return key;
    }

    //Retorna el valor almacenado en el registro.
    public E getValue() {
        return value;
    }

    //Devuelve una representación textual del registro.
    @Override
    public String toString(){
        return "(" + key + ", " + value + ")";
    }

    //Compara dos registros utilizando únicamente sus claves.
    @Override
    public int compareTo(Register<E> other){
        return this.key - other.key;
    }

    //Determina si dos registros son iguales comparando sus claves.
    @Override
    public boolean equals(Object o) {
        if (o instanceof Register<?> r){
            return r.key == this.key;
        }
        return false;
    }
}