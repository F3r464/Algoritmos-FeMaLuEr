public class Principal{
    public static void main(String[] args){

        Rectangulo r1=new Rectangulo(
            new Coordenada(1,1),
            new Coordenada(4,4)
        );

        Rectangulo r2=new Rectangulo(
            new Coordenada(3,3),
            new Coordenada(6,6)
        );

        System.out.println("R1: "+r1);
        System.out.println("R2: "+r2);

        System.out.println("Sobrepuestos: "+Verificador.esSobrePos(r1,r2));
        System.out.println("Juntos: "+Verificador.esJunto(r1,r2));
        System.out.println("Disjuntos: "+Verificador.esDisjunto(r1,r2));
    }
}

