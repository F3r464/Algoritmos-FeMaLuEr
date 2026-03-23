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
class Chocolatina{
    private String marca;
    public Chocolatina(String marca){
        this.marca=marca;
    }
    public String getMarca(){
        return marca;
    }
    public void setMarca(String marca){
        this.marca=marca;
    }
}
class Golosina{
    private String nombre;
    private double peso;
    public Golosina(String nombre, double peso){
        this.nombre=nombre;
        this.peso=peso;
    }
    public String getNombre(){
        return this.nombre;
    }
    public void setNombre(String nombre){
        this.nombre=nombre;
    }
    public double getPeso(){
        return this.peso;
    }
    public void setPeso(double peso){
        this.peso=peso;
    }
}
public class ACT14Ejec{
    public static void main(String [] args){
        Bolsa<Chocolatina> bolsaCho=new Bolsa<>(3);
        Chocolatina c1=new Chocolatina("milky");
        Chocolatina c2=new Chocolatina("Iberica");
        Chocolatina c3=new Chocolatina("Valenzuela");
        bolsaCho.add(c1);
        bolsaCho.add(c2);
        bolsaCho.add(c3);
        for(Chocolatina choco: bolsaCho){
            System.out.println(choco.getMarca());
        }
    }
}