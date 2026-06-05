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