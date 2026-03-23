import java.util.*;
class Bolsa<T> implements Iterable<T>{
    private ArrayList<T> lista = new ArrayList<T>();
    private int tope;
    public Bolsa(int tope){
        this.tope=tope;
    }
    public void add(T objeto){
        if(lista.size() < tope){
            lista.add(objeto);
        }else{
            throw new RuntimeException("No espacio");
        }
    }
    @Override
    public Iterator<T> iterator(){
        return lista.iterator();
    }
}
