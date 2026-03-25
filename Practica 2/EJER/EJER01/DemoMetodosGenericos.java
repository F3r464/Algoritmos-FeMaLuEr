package EJER01;
public class DemoMetodosGenericos {
    //Método xist()
    //Retorna true si el elemento está en el arreglo
    public static <T> boolean exist(T[] arreglo, T elemento) {
        for (int i = 0; i < arreglo.length; i++) {
            if (arreglo[i].equals(elemento)) {
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        //Prueba 
        Integer nums[] = {1, 2, 3, 4};

        System.out.println(exist(nums, 2)); 
        System.out.println(exist(nums, 5)); 
    }
}