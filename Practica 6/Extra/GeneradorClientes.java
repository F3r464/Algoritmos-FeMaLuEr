package Extra;
import java.util.Random;
public class GeneradorClientes{
    private static String[] nombres = {"p1", "p2", "p3", "p4", "p5"};
    private static String[] pedidos = {"Hamburguesa", "Pizza", "Hot Dog", "Ensalada"};

    public static Cliente generarCliente(){
        Random rand = new Random();

        String nombre = nombres[rand.nextInt(nombres.length)];
        String pedido = pedidos[rand.nextInt(pedidos.length)];

        return new Cliente(nombre, pedido);
    }
}