package ACT02;
import java.util.*;
public class Subconjuntos{
    public static void generarSubconjuntos(int[] arr, List<Integer> actual, int i){
        if (i == arr.length){
            System.out.println(actual);
            return;
        }
        actual.add(arr[i]); //Incluir el elemento
        generarSubconjuntos(arr, actual, i + 1); //Backtracking
        actual.remove(actual.size() - 1);
        generarSubconjuntos(arr, actual, i + 1); //No incluir el elemento
    }
    public static void main(String[] args){
        int[] arr = {1, 2, 3};
        generarSubconjuntos(arr, new ArrayList<>(), 0);
    }
}