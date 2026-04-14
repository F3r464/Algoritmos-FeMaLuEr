package ACT01;
public class Hanoi{
    public static void main(String[] args) {
        torresHanoi(5, 1, 2, 3);
    }
    public static void torresHanoi(int discos, int torre1, int torre2, int torre3) {//Metodo recursivo para la solución de las torres hanoi
        if (discos == 1) { //Caso base
            System.out.println("Mover disco de torre " + torre1 + " a torre " + torre3);
        } else {
            torresHanoi(discos - 1, torre1, torre3, torre2); //Dominio (problema - 1)
            System.out.println("Mover disco de torre " + torre1 + " a torre " + torre3);
            torresHanoi(discos - 1, torre2, torre1, torre3);
        }
    }
}