package EJER01;
import java.util.Scanner;
public class Ejercicio1a{
    static int[] arr;
    static int objetivo;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();
        objetivo = sc.nextInt();
        sc.close();

        System.out.println(resolver(0, 0));
    }
    static boolean puedeElegir(int i){
        if (arr[i] % 2 == 0 && i + 1 < arr.length && arr[i + 1] % 2 == 0) // arr[i] no puede elegirse si el siguiente tambien es par
            return false;
        return true;
    }
    static boolean resolver(int i, int sumaActual) {
        if (i == arr.length)
            return sumaActual == objetivo;
        if (arr[i] % 3 == 0) {
            return resolver(i + 1, sumaActual + arr[i]);
        } else {
            if (puedeElegir(i)) {
                if (resolver(i + 1, sumaActual + arr[i]))
                    return true;
            }
            return resolver(i + 1, sumaActual);
        }
    }
}
