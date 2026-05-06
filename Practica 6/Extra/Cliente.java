package Extra;

public class Cliente {
    private String nombre;
    private String pedido;
    public Cliente(String nombre, String pedido) {
        this.nombre = nombre;
        this.pedido = pedido;
    }
    public String getNombre() {
        return nombre;
    }
    public String getPedido() {
        return pedido;
    }
    @Override
    public String toString() {
        return nombre + " pidió: " + pedido;
    }
}