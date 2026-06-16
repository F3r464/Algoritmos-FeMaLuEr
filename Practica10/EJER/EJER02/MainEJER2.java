package Practica10.EJER.EJER02;
public class MainEJER2 {
    public static void main(String[] args){
        GraphLinkCiudad red = new GraphLinkCiudad();

        red.insertarCiudad("Arequipa");
        red.insertarCiudad("Cusco");
        red.insertarCiudad("Puno");
        red.insertarCiudad("Tacna");
        red.insertarCiudad("Moquegua");
        red.insertarCarretera("Arequipa", "Cusco", 510);
        red.insertarCarretera("Arequipa", "Moquegua", 230);
        red.insertarCarretera("Moquegua", "Tacna", 160);
        red.insertarCarretera("Cusco", "Puno", 390);
        red.insertarCarretera("Puno", "Tacna", 420);
        System.out.println(red);
    }
}