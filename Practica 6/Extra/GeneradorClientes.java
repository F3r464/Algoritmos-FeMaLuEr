package Extra;

import java.util.Random;

public class GeneradorClientes {

    private static String[] nombres = {"Ana", "Luis", "Carlos", "María", "Pedro"};
    private static String[] pedidos = {"Hamburguesa", "Pizza", "Hot Dog", "Ensalada"};

    public static Cliente generarCliente() {
        Random rand = new Random();

        String nombre = nombres[rand.nextInt(nombres.length)];
        String pedido = pedidos[rand.nextInt(pedidos.length)];

        return new Cliente(nombre, pedido);
    }
}