package Ejercicio08;

public class Utilidades {
	//El metodo generico para intercambiar la posicion de dos elementos
	public static <T> void intercambiar(T[] arreglo, int i, int j) {
		if (arreglo == null || i < 0 || i >= arreglo.length || j < 0 || j >= arreglo.length) {
			throw new IndexOutOfBoundsException("Indice fuera de rango"); //lanza excepcion por fuera de rango del indice
		}
		T temporal = arreglo[i]; //Usamos una variable temporal para no perder el dato original
		arreglo[i] = arreglo[j]; //Movemos el elemento de la posicion 'j' a la 'i'
		arreglo[j] = temporal; //Ponemos lo que guardamos en 'temporal' en la posicion 'j'
	}	
}
