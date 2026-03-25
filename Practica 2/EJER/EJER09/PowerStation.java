package Ejercicio09;
import java.util.ArrayList;


public class PowerStation<T extends Cargable> { //clase generica restringida (solamente se puede usar si tienen "contrato" con Cargable

 private ArrayList<T> dispositivos;

 public PowerStation() {	//constructor
     this.dispositivos = new ArrayList<>();
 }

 public void conectar(T dispositivo) {
         this.dispositivos.add(dispositivo);
 }

 public double calcularConsumoTotal() {
     double totalVatios = 0;
     for (T dispositivo : dispositivos) {
         totalVatios += dispositivo.getConsumoVatios(); //se puede llamar a getconsumovatios porque la clase esta restringida
     }
     return totalVatios;
 }


 public int buscarDispositivo(T prototipo) {
     return dispositivos.indexOf(prototipo); //utiliza iternamente el metodo equals
 }

 public void mostrarReporte() {
     System.out.println("Posición Consumo (W)");
     for (int i = 0; i < dispositivos.size(); i++) {
         T d = dispositivos.get(i);
         System.out.println(i + d.getConsumoVatios());
     }
 }
}