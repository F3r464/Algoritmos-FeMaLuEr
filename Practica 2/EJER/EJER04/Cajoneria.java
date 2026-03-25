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
    public String search(T obj){ 
        for(int i=0;i<lista.length;i++){ 
            Caja<T> c=lista.get(i); 
            if(c.getObjeto()!=null && c.getObjeto().equals(obj)){ 
                return "Posicion: "+i+" Color: "+c.getColor(); 
            } 
        } 
        return "No encontrado"; 
    } 
    public T delete(T obj){ 
        for(int i=0;i<lista.length;i++){ 
            Caja<T> c=lista.get(i); 
            if(c.getObjeto()!=null && c.getObjeto().equals(obj)){ 
                T eliminado=c.getObjeto(); 
                c.setObjeto(null);
                return eliminado; 
            } 
        } 
        return null; 
    } 
    public String toString(){ 
        String res="Posicion Color Objeto\n"; 
        for(int i=0;i<lista.length;i++){ 
            Caja<T> c = lista.get(i); 
            rpta += i+" "+c.getColor()+" "+c.getObjeto()+"\n"; 
        } 
        return rpta; 
    } 
}  