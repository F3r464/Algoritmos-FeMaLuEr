package Practica10.EJER.EJER02;

public class ListLinked<E>{

    private Node<E> first;
    private int size;

    public ListLinked(){
        first = null;
        size = 0;
    }
    public boolean isEmpty(){
        return first == null;
    }

    public int size(){
        return size;
    }

    public void addLast(E data){

        Node<E> nuevo = new Node<>(data);
        if (isEmpty()){
            first = nuevo;
        }else{
            Node<E> aux = first;
            while(aux.getNext() != null){
                aux = aux.getNext();
            }
            aux.setNext(nuevo);
        }

        size++;
    }

    public E get(int index)throws ExceptionGraph{
        if (index < 0 || index >= size){
            throw new ExceptionGraph("Error");
        }
        Node<E> aux = first;
        for (int i = 0; i < index; i++){
            aux = aux.getNext();
        }
        return aux.getData();
    }

    public boolean contains(E data){
        Node<E> aux = first;
        while (aux != null){

            if (aux.getData().equals(data)){
                return true;
            }
            aux = aux.getNext();
        }
        return false;
    }

    @Override
    public String toString(){
        String resultado = "";
        Node<E> aux = first;
        while (aux != null){
            resultado += aux.getData();
            if (aux.getNext() != null){
                resultado += " -> ";
            }
            aux = aux.getNext();
        }
        return resultado;
    }

}