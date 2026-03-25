package Ejercicio08;

public class Main {

	public static void main(String[] args) {
		String[] letras = {"A","B","C","D"};
		Utilidades.intercambiar(letras, 1, 3); //llamamos a la clase Utilidades ya que el metodo es de clase "static"
		System.out.println("Arreglo de letras: " + java.util.Arrays.toString(letras));
		
		Integer[] notas = {10,20,30,40};
		Utilidades.intercambiar(notas,0,2); //de igual manera se llama a la clase Utilidades
		System.out.println("Arreglo de notas: "+ java.util.Arrays.toString(notas));
	}

}
