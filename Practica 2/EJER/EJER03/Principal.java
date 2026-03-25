package EJER03;
public class Principal {
    public static void main(String[] args) {
        Cajoneria<Golosina> cajon = new Cajoneria<>();
        //Cajas con golosinas
        cajon.add(new Caja<>("Rojo", new Golosina("gomita", 10.5)));
        cajon.add(new Caja<>("Verde", new Golosina("caramelo", 5.0)));
        cajon.add(new Caja<>("Azul", new Golosina("chocolate", 20.0)));
        System.out.println("Contenido de Cajonería:");

        for (Caja<Golosina> caja : cajon.getLista()) {
            System.out.println(caja);
        }

        Cajoneria<Chocolatina> cajonCho = new Cajoneria<>();
        //Cajas con chocolatinas
        cajonCho.add(new Caja<>("Amarillo", new Chocolatina("milka")));
        cajonCho.add(new Caja<>("Negro", new Chocolatina("ferrero")));
        cajonCho.add(new Caja<>("Blanco", new Chocolatina("milka")));

        System.out.println("\nContenido de Cajonería de Chocolatinas:");

        for (Caja<Chocolatina> caja : cajonCho.getLista()) {
            System.out.println(caja);
        }
    }
}