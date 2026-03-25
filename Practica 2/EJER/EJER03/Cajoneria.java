package EJER03;
import java.util.ArrayList;
public class Cajoneria<T>{
    private ArrayList<Caja<T>> lista;

    public Cajoneria(){
        lista = new ArrayList<>();
    }
    public void add(Caja<T> caja){
        lista.add(caja);
    }
    public ArrayList<Caja<T>> getLista(){
        return lista;
    }
}