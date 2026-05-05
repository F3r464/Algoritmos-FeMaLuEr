package Extra;

import java.util.*;

public class Mostrador {

    private Cola<Cliente> colaClientes;

    public Mostrador() {
        colaClientes = new ColaLink<>();
    }

    public void iniciar() {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n=== MOSTRADOR ===");
            System.out.println("1. Llega cliente");
            System.out.println("2. Atender cliente");
            System.out.println("3. Ver siguiente cliente");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    Cliente nuevo = GeneradorClientes.generarCliente();
                    colaClientes.encolar(nuevo);
                    System.out.println("Llegó: " + nuevo);
                    break;

                case 2:
                    if (!colaClientes.estaVacia()) {
                        Cliente atendido = colaClientes.desencolar();
                        System.out.println("Atendiendo a: " + atendido);
                    } else {
                        System.out.println("No hay clientes.");
                    }
                    break;

                case 3:
                    if (!colaClientes.estaVacia()) {
                        System.out.println("Siguiente: " + colaClientes.frente());
                    } else {
                        System.out.println("No hay clientes en cola.");
                    }
                    break;
            }

        } while (opcion != 0);
    }
}