class Generico<T>{
    private T objeto;
    public Generico(T objeto){
        this.objeto=objeto;
    }
    public void mostrar(){
        System.out.println("clase generica"+ objeto.getClass().getName());
    }
    public <L> void funcion(L fun){
        System.out.println(objeto+fun);
    }
}

public class Main2{
    public static void main(String [] args){
        Generico<String> o1=new Generico<>("hola");
        o1.mostrar();
        o1.funcion(" mundo");
    }
}
        
        