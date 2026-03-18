class ContainerRect{
    Rectangulo[] rectangulos;
    double[] distancias;
    double[] areas;
    int n;
    static int numRec=0;
    public ContainerRect(int n){
        this.n=n;
        rectangulos=new Rectangulo[n];
        distancias=new double[n];
        areas=new double[n];
    }
    public void addRectangulo(Rectangulo r){
        if(numRec<n){
            rectangulos[numRec]=r;
            distancias[numRec]=Coordenada.distancia(r.getEsquina1(),r.getEsquina2());
            areas[numRec]=Math.abs((r.getEsquina2().getX()-r.getEsquina1().getX())*(r.getEsquina2().getY()-r.getEsquina1().getY()));
            numRec++;
        }else{
            System.out.println("No hay espacio");
        }
    }
    public String toString(){
        String s = "Rectangulo\tCoordenadas\tDistancia\tArea\n";
        for (int i = 0; i < numRec; i++){
            s += (i + 1) + "\t" + rectangulos[i] + "\t" + distancias[i] + "\t" + areas[i] + "\n";
        }
    return s;
    }
}

class Coordenada{
    private double x;
    private double y;
    public Coordenada(){
        this.x=0;this.y=0;
    }
    public Coordenada(double x,double y){
        this.x=x;this.y=y;
    }
    public Coordenada(Coordenada c){
        this.x=c.x;this.y=c.y;
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
        return "["+x+", "+y+"]";
    }
}
class Rectangulo{
    private Coordenada esquina1;
    private Coordenada esquina2;

    public Rectangulo(Coordenada c1,Coordenada c2){
        this.esquina1=new Coordenada(c1);
        this.esquina2=new Coordenada(c2);
    }

    public Coordenada getEsquina1(){
        return esquina1;
    }
    public Coordenada getEsquina2(){
        return esquina2;
    }
    public String toString(){
        return "("+esquina1+", "+esquina2+")";
    }
}

public class JavPooEjer1{
    public static void main(String[] args){
        ContainerRect c=new ContainerRect(5);
        Rectangulo r1=new Rectangulo(new Coordenada(1.5,0.3),new Coordenada(7.6,2.2));
        Rectangulo r2=new Rectangulo(new Coordenada(4.0,4.2),new Coordenada(9.4,-2.5));
        c.addRectangulo(r1);
        c.addRectangulo(r2);
        System.out.println(c);
    }
}