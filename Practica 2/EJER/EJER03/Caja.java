package EJER03;
public class Caja<T>{
    private String color;
    private T objeto;
    
    public Caja(String color, T objeto){
        this.color = color;
        this.objeto = objeto;
    }
    public String getColor(){
        return color;
    }
    public T getObjeto(){
        return objeto;
    }
    @Override
    public String toString(){
        return "Color: " + color + " | Objeto: " + objeto;
    }
}