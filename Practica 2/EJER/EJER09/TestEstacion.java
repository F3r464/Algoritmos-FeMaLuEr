package Ejercicio09;


public class TestEstacion {
    public static void main(String[] args) {
        PowerStation<Cargable> zonaDispositivos = new PowerStation<>(); //creamos la estacion de carga restringida (solo aceptara clases que implementen la interfaz cargable

        Smartphone s1 = new Smartphone("iPhone 15", 20.5);
        Smartphone s2 = new Smartphone("Galaxy S24", 25.0); //creamos los dispositivos
        Laptop l1 = new Laptop("HP Pavilion", 65.0);

       
        zonaDispositivos.conectar(s1);
        zonaDispositivos.conectar(s2); //Conectamos los dispositivos a la estacion
        zonaDispositivos.conectar(l1);

        double consumoTotal = zonaDispositivos.calcularConsumoTotal();
        System.out.println("Consumo Total: " + consumoTotal + "W"); 

        zonaDispositivos.mostrarReporte();

        Smartphone prototipo = new Smartphone("Galaxy S24", 0);         // Creamos un "prototipo" con el mismo modelo para ver si lo encuentra
        int posicion = zonaDispositivos.buscarDispositivo(prototipo);
        
        if (posicion != -1) { 			//ponemos -1 por que esta estructurado que el metodo indexof() cuando no encuentre o cuando el metodo equals no encuentre posicion mande un -1
            System.out.println("El dispositivo Galaxy S24 se encuentra en la posicion: " + posicion);
        } else {
            System.out.println("Dispositivo no encontrado.");
        }
    }
}