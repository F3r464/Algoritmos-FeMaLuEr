package ExtraP6;

public class Cliente{
    private String nombre;
    private String pedido;
    public Cliente(String nombre, String pedido){
        this.nombre=nombre;
        this.pedido=pedido;
    }
    public String getNombre(){
        return nombre;
    }
    public String getPedido(){
        return pedido;
    }
    public String toString(){
        return nombre +" pidio: "+ pedido;
    }
}