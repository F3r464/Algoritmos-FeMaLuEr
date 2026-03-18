import java.util.*;
class Coordenada{
    private double x;
    private double y;
    public Coordenada(){
        this.x=0;
        this.y=0;
    }
    public Coordenada(double x,double y){
        this.x=x;
        this.y=y;
    }
    public Coordenada(Coordenada c){
        this.x=c.x;
        this.y=c.y;
    }
    void setX(double x){
        this.x=x;
    }
    void setY(double y){
        this.y=y;
    }
    double getX(){
        return x;
    }
    double getY(){
        return y;
    }
    double distancia(Coordenada c){
        return Math.sqrt(Math.pow(this.x-c.x,2)+Math.pow(this.y-c.y,2));
    }
    static double distancia(Coordenada c1,Coordenada c2){
        return Math.sqrt(Math.pow(c1.x-c2.x,2)+Math.pow(c1.y-c2.y,2));
    }
    public String toString(){
        return "("+x+","+y+")";
    }
}

class Rectangulo{
    private Coordenada esquina1;
    private Coordenada esquina2;
    public Rectangulo(Coordenada c1,Coordenada c2){
        setEsquina1(c1);
        setEsquina2(c2);
    }
    public void setEsquina1(Coordenada coo){
        this.esquina1=new Coordenada(coo);
    }
    public void setEsquina2(Coordenada coo){
        this.esquina2=new Coordenada(coo);
    }
    public Coordenada getEsquina1(){
        return esquina1;
    }
    public Coordenada getEsquina2(){
        return esquina2;
    }
    public String toString(){
        return "["+esquina1.toString()+" , "+esquina2.toString()+"]";
    }
}
public class MainRec1{
    public static void main(String[] args){
        Coordenada c1=new Coordenada(1,2);
        Coordenada c2=new Coordenada(4,6);
        Rectangulo r=new Rectangulo(c1,c2);
        System.out.println("Rectangulo: "+r);
        System.out.println("Distancia: "+c1.distancia(c2));
    }
}